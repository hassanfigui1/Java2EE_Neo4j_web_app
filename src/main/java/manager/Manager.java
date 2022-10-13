package manager;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.exceptions.Neo4jException;
import org.neo4j.driver.exceptions.NoSuchRecordException;

import model.Article;
import model.Auteur;
import model.Keyword;


public class Manager {
	private Driver driver;
    private String user;
    private String password;

    public Manager( String uri, String user, String password )
    {
        this.driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ));
    }
    
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		driver.close();
	}
	//check if the email exist or not 
	 public boolean CheckEmail(String email)
	 {
		 Auteur a = new Auteur();
	     try ( Session session = this.driver.session() )
	     {
            Result result = session.run( "MATCH (a:author) where a.email=$email  RETURN a.email", parameters("email",email) );
            Record record = result.next();
            if(record !=null) {
           	 return true;
            }
	     }catch(NoSuchRecordException e) {
	    	 e.printStackTrace();
	     }
		return false;
	 }

	
    public int removeArticle(int id) {
        try (Session session = driver.session()) {
        	//delete target Article 
            session.run("MATCH(a:article)-[:has_keyword]->(keyword) WHERE id(a)=$id detach delete a", parameters("id",id));
            //delete keyword nodes that has not a relationship
            session.run("MATCH (n:keyword)\r\n"
            		+ "WHERE size((n)--())=0\r\n"
            		+ "DELETE (n)");

            return 1;
        }catch(Neo4jException ex) {
        	ex.printStackTrace();
        }
        return 0;
    }

    public int updateAuthor(Auteur a) {
        try (Session session = driver.session()) {
            session.run("MATCH(a:author) WHERE id(a)=toInteger($id) set a.nom = $nom, a.prenom=$prenom,a.email=$email,a.mdp=$mdp, a.departement=$dep",
            		parameters("nom",a.getNom(),"id",a.getId(),"prenom",a.getPrenom(),"email",a.getEmail(),"mdp",a.getMdp(),"dep",a.getDepartement()));
            return 1;
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
		return 0;
    }
    
	 public ArrayList<Auteur> getAuthors()
	 {
		ArrayList<Auteur> auteur = new ArrayList<Auteur>();
	     try ( Session session = this.driver.session() )
	     {
	         return session.readTransaction( tx -> {

	             Result result = tx.run( "MATCH (a:author) RETURN a.nom as nom,a.prenom,a.email,id(a)" );
	             List<Record> list = result.list();

	             for (int i = 0; i < list.size(); i++) {
	                 Auteur e = new Auteur();
	                 e.setId(list.get(i).get("id(a)").asInt());
	                 e.setPrenom(list.get(i).get("a.prenom").asString());
	                 e.setNom(list.get(i).get("nom").asString());
	                 e.setEmail(list.get(i).get("a.email").asString());
	                 auteur.add(e);
	             }

//	             while ( result.hasNext() )
//	             {
//	            	 auteur.add(new Auteur(result.next().get("nom").asString(),result.next().get("a.prenom").asString()
//	            			 ,result.next().get("a.email").asString(),result.next().get("a.username").asString(),
//	            			 result.next().get("a.mdp").asString()));
//	             }
	             return auteur;
	         } );
	     }
	 }
	 
	 public Auteur getAuteur(String email)
	 {
		 Auteur auteur = new Auteur();
		 try(Session session = this.driver.session()){
			 Result result = session.run("MATCH (a:author) where a.email=$email RETURN id(a) as id, a.nom as nom,a.prenom,a.email,a.departement,a.mdp", parameters("email",email));
			 Record record = result.next() ;
			 if(record !=null) {
		         auteur.setPrenom(record.get("a.prenom").asString());
		         auteur.setEmail(record.get("a.email").asString());
		         auteur.setDepartement(record.get("a.departement").asString());
		         auteur.setNom(record.get("nom").asString());
		         auteur.setMdp(record.get("a.mdp").asString());
		         auteur.setId(record.get("id").asInt());
		         return auteur;
	         }
		 }catch(NoSuchRecordException ex) {
			 ex.printStackTrace();
		 }
		return null;
	 }
	 public Auteur getAuteur(String email,String mdp) {
		 Auteur auteur = new Auteur();
		 try(Session session = this.driver.session()){
			 Result result = session.run("MATCH (a:author) where a.email=$email and a.mdp=$mdp RETURN a.nom as nom,a.prenom,a.email,a.mdp,a.departement,id(a)", parameters("email",email,"mdp",mdp));
			 Record record = result.next() ;
			 if(record !=null) {
		         auteur.setPrenom(record.get("a.prenom").asString());
		         auteur.setEmail(record.get("a.email").asString());
		         auteur.setMdp(record.get("a.mdp").asString());
		         auteur.setNom(record.get("nom").asString());
		         auteur.setDepartement(record.get("a.departement").asString());
		         auteur.setId(record.get("id(a)").asInt());

		         return auteur;
	         }
		 }catch(NoSuchRecordException ex) {
			 ex.printStackTrace();
		 }
		return null;
	 }
	    public int addArticle(Auteur author){
	        try (Session session = driver.session()) {

	            Result rest = session.run("CREATE (a:article{title:'" + author.getArticle().getTitle() + "', abbreviation:'"+ author.getArticle().getAbbreviation()+"',authorId:'"+author.getId()+"'}) return id(a) as id");
	            Record record = rest.next();
	            author.getArticle().setId(record.get("id").asInt());
	            System.out.println(author.getArticle().getId());
	            for(String motCle :author.getArticle().getKeyword().getName()) {
	                ArrayList<Integer> ids = new ArrayList<Integer>();
	                System.out.println("Article id = "+author.getArticle().getId());
	            	Result rs = session.run("match(a:article) where id(a)=toInteger('"+author.getArticle().getId()+ "') merge(m:keyword{name:'"+motCle+"'}) merge(a)-[:has_keyword]->(m) return id(m) as id");
		            List<Record> list = rs.list();
	            	for(int i=0; i<list.size();i++) {
	            		ids.add(list.get(i).get("id").asInt());
	            	}

//	            	for(Integer in:ids) {
//	            		System.out.println(in);
//	            		session.run("match(a:article) where id(a)='"+article.getId()+"' match(b:keyword) where id(b)=49 create(a)-[:has_keyword]->(b) ");
//	            	}
//	                session.run("match(a:article) where id(a)='"+a.getId()+"' create(m:keyword{name:'"+ motCle+"'}),"
//	                		+ "(a)-[:has_keyword]->(m)");
	            }
//	    		session.run("match(a:article),(b:keyword) where id(a)=toInteger(b.articleId) merge(a)-[:has_keyword]->(b) ");
	    		session.run("match(au:author),(bi:article) where id(au)=toInteger(bi.authorId) merge(au)-[:has_published]->(bi) ");

	            return 1;
	        } catch (NoSuchRecordException ex) {
	            ex.printStackTrace();
	        }
			return 0;
	    }
		    


	 

	 
	 public List<String> getPeople(int id)
	 {
	     try ( Session session = driver.session() )
	     {
             List<String> names = new ArrayList<>();
	         return session.readTransaction( tx -> {
	             Result result = tx.run( "MATCH (a:Person) where id(a) =$id RETURN a.name ORDER BY a.name", parameters("id" ,id) );
	             while ( result.hasNext() )
	             {
	                 names.add( result.next().get(0).asString() );
	             }
	             return names;
	         } );
	     }
	 }
	    public int addAuthor(Auteur a){
	        try (Session session = driver.session()) {
	            session.run("CREATE (a:author{prenom:'" + a.getPrenom() + "', nom:'" + a.getNom()+ "',departement:'"+a.getDepartement()+ "',email:'" + a.getEmail() + "',mdp:'" +a.getMdp()+"'})");
	            return 1;
	        } catch (Neo4jException ex) {
	            ex.printStackTrace();
	        }
			return 0;
	    }
//	    public int addAuthor(Article a){
//	        try (Session session = driver.session()) {
//	            session.run("CREATE (a:author{prenom:'" + a.getPrenom() + "', nom:'" + a.getNom()+ "',departement:'"+a.getDepartement()+ "',email:'" + a.getEmail() + "',mdp:'" +a.getMdp()+"'})");
//	            return 1;
//	        } catch (NoSuchRecordException ex) {
//	            ex.printStackTrace();
//	        }
//			return 0;
//	    }
	    
	    

		 public ArrayList<Article> GetUserArticles(Auteur author)
		 {
			ArrayList<Article> articles = new ArrayList<Article>();
		     try ( Session session = this.driver.session() )
		     {
		         return session.readTransaction( tx -> {
		             Result result = tx.run( "MATCH(au:author)-[:has_published]->(a:article) where au.email=$email RETURN a.title ,a.abbreviation,id(a)",parameters("email",author.getEmail()) );
		             List<Record> list = result.list();

		             for (int i = 0; i < list.size(); i++) {
		                 Article a = new Article();
		                 a.setId(list.get(i).get("id(a)").asInt());
		                 a.setTitle(list.get(i).get("a.title").asString());
		                 a.setAbbreviation(list.get(i).get("a.abbreviation").asString());
		                 articles.add(a);
		             }
		             return articles;
		         } );
		     }
		 }
		    public ArrayList<Auteur> RecommendMe(Auteur author){
				ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
			     try ( Session session = this.driver.session() )
			     {
			         return (ArrayList<Auteur>) session.readTransaction( tx -> {

			             Result result = tx.run( "MATCH (auteur1:author)-[:has_published]->(a1:article)-[:has_keyword]->(keyword) where auteur1.email =$email \r\n"
			             		+ "WITH a1,auteur1, collect(id(keyword)) AS keywords\r\n"
			             		+ "MATCH (auteur2:author)-[:has_published]->(a2:article)-[:has_keyword]->(cuisine2) WHERE a1<>a2 and auteur1<>auteur2 \r\n"
			             		+ "WITH a1,auteur2,auteur1, keywords,a2, collect(id(cuisine2)) AS keywords2\r\n"
			             		+ "where gds.similarity.jaccard(keywords, keywords2)>0.5 \r\n"
			             		+ "RETURN  auteur2.nom,auteur2.prenom,a2.title,a2.abbreviation;",parameters("email",author.getEmail()) );
			             List<Record> list = result.list();

			             for (int i = 0; i < list.size(); i++) {
			            	 Auteur auteur = new Auteur();
			            	 auteur.setNom(list.get(i).get("auteur2.nom").asString());
			            	 auteur.setPrenom(list.get(i).get("auteur2.prenom").asString());
			            	 Article article = new Article();
			            	 article.setTitle(list.get(i).get("a2.title").asString());
			            	 article.setAbbreviation(list.get(i).get("a2.abbreviation").asString());
			            	 auteur.setArticle(article);
			            	 auteurs.add(auteur);
			             }
			             return auteurs;

			         } );
			     }
						    	
		    }

		 

	    
	    // ********************** Get user Articles with keywords *********
//		 public ArrayList<Article> GetUserArticles(Auteur author)
//		 {
//			ArrayList<Article> articles = new ArrayList<Article>();
//		     try ( Session session = this.driver.session() )
//		     {
//		         return session.readTransaction( tx -> {
//
//		             Result result = tx.run( "MATCH(au:author)-[:has_published]->(a:article)-[:has_keyword]-(k:keyword) where au.email=$email RETURN a.title ,a.abbreviation,id(a),k.name",parameters("email",author.getEmail()) );
//		             List<Record> list = result.list();
//		             for (int i = 0; i < list.size(); i++) {
//		                 Article a = new Article();
//		                 Keyword keyword = new Keyword();
//		                 ArrayList<String> keywords = new ArrayList<String>();
//		                 keywords.add(list.get(i).get("k.name").asString());
//		                 keyword.setName(keywords);
//		                 a.setId(list.get(i).get("id(a)").asInt());
//		                 a.setTitle(list.get(i).get("a.title").asString());
//		                 a.setAbbreviation(list.get(i).get("a.abbreviation").asString());
//		                 a.setKeyword(keyword);
//		                 articles.add(a);
//		             }
//		             return articles;
//		         } );
//		     }
//		 }

	   

	    
}
