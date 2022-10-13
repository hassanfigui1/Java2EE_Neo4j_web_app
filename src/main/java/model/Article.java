package model;

public class Article {
	private int id;
	private String title,abbreviation;
	private Keyword keyword;
	public Keyword getKeyword() {
		return this.keyword;
	}
	

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", abbreviation=" + abbreviation + "]";
	}


	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public Article(String title, String abbreviation, Keyword keyword) {
		super();
		this.title = title;
		this.abbreviation = abbreviation;
		this.keyword = keyword;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(String title, String abbreviation) {
		super();
		this.title = title;
		this.abbreviation = abbreviation;
	}

	public Article(int id, String title, String abbreviation) {
		super();
		this.id = id;
		this.title = title;
		this.abbreviation = abbreviation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
