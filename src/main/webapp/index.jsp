<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Auteur" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Article" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
<script type="text/javascript">
$(document).ready(function() {             
	$('#AddArticleModal').modal('show');
	  $(function () {
	    $('[data-toggle="tooltip"]').tooltip()
	  })
	});

</script>
 <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Kaushan+Script'><link rel="stylesheet" href="./style.css">
<link rel='stylesheet' href='css/style.css'><link rel="stylesheet" href="./style.css">


</head>
<body>
<% ArrayList<Auteur> user =(ArrayList<Auteur>)request.getAttribute("userInfo");

ArrayList<Article> userArticles =(ArrayList<Article>)request.getAttribute("userArticles");

%>

<% HttpSession sessionEmail = request.getSession();
 %>
<% if(session.getAttribute("userSession") ==null){
	request.getRequestDispatcher("Login.jsp").forward(request,response);
}

	if(request.getAttribute("userArticles") ==null){
		response.sendRedirect("Login");
	}
	%>


<header>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
  <div class="container">
    <a class="navbar-brand js-scroll-trigger" href="index.jsp">findYourPeers</a>
    <div>
      <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item">
        	<form action="LogOut" method="post">
        	   <input type="hidden" value="'"+(sessEmail !=null ? sessEmail : "") + "'">
        	  <button type="submit" name="logout" class="btn btn-primary my-2 my-sm-0" "><i class="fa fa-sign-out"></i> Log Out</a></button>
        	</form>
        </li>
      </ul>
    </div>
  </div>
</nav>
<!--our content goes here-->
<div class="container content">
    <div class="row profile">
      <div class="col-md-3">
        <div class="profile-sidebar position-fixed">
          <!-- SIDEBAR USERPIC -->
          <div class="profile-userpic">
            <img src="https://media.rockstargames.com/chinatownwars/global/downloads/avatars/zhou_256x256.jpg" class="img-responsive" alt="">
          </div>
          <!-- END SIDEBAR USERPIC -->
          <!-- SIDEBAR USER TITLE -->
          <div class="profile-usertitle">
            <div class="profile-usertitle-name">
            <% if(user!=null){ 
            		for(Auteur userConnected: user) {%>
            		<h6 style="color:black"><%= "mr "+(userConnected.getPrenom()+" "+ userConnected.getNom())%> </h6>
            		<%}
            		
            		%>
            <%}%>
            </div>
          </div>
          <!-- END SIDEBAR USER TITLE -->
          <!-- SIDEBAR BUTTONS -->
          <div class="profile-userbuttons">
            <button type="button" class="btn btn-info btn-round" data-toggle="modal" data-target="#AddArticleModal" ><b>Add new Article</b></button>
<!--             <button type="button" class="btn btn-danger btn-sm">Message</button>
 -->          </div>
          <!-- END SIDEBAR BUTTONS -->
          <!-- SIDEBAR MENU -->
          <div class="profile-usermenu sidebar-sticky">
            <ul class="nav flex-column">
              <li class="active nav-item">
                <a href="index.jsp" class="nav-link active">
							<i class="fa fa-home"></i>
							Overview </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="ModifyInfoAuthor">
							<i class="fa fa-user"></i>
							Account Settings </a>
              </li>

              <li class="nav-item">
                <a class="nav-link" href="GetRecommendation">
							<i class="fa fa-flag"></i>
							Recommendations </a>
              </li>
            </ul>
          </div>
          <!-- END MENU -->
        </div>
      </div>
      <div class="col-md-9">
        <div class="profile-content">
			<%if(userArticles !=null) {
				for(Article article:userArticles){%>
					<div class="card" >
  						<div class="card-body">
    						<h5 class="card-title"><%=article.getTitle() %></h5>
    						<h6 class="card-subtitle mb-2 text-muted"><%=(article.getAbbreviation()) %></h6>
   							<p class="card-text"> 
   								
   							</p>
    						<form action ="Login" method="post">
    							<input type="hidden" value='<%= article.getId()%>' name="deleteId">
<!--     							<button type="button" class="btn btn-success">Modify Article</button>
 -->								<button type='submit' name='sub_delete' class="btn btn-danger"> Remove Article</button>
    							   
    						</form>
 						</div>	
					</div>
					
				<%}
			}
			%>
        </div>
      </div>
    </div>
  </div>
<div class="container">
  <div class="row">
    <div class="col-md-3">
    </div>
    <div class="col-md-9 ft">
  
  </div>
</footer>
</div>
  </div>
</div>
<!-- partial -->
  <script src='https://code.jquery.com/jquery-3.3.1.slim.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js'></script>
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js'></script>
         
	
</header>













<div class="container">
</div>

<div class="modal fade" id="AddArticleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">X</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-title ">
          <h4>Add Article</h4>
        </div>
        <div class="d-flex flex-column text-center">
          <form action="Login" method="post">
            <div class="form-group">
              <input type="text" class="form-control" name="title" placeholder="Title">
            </div>
            <div class="form-group">
              <textarea name="abbreviation" placeholder="Abbreviation"></textarea>
            </div>
            <div class="form-group">
				<input type="text" class="form-control" name="keywords" placeholder="keywords seperated by ,">
            </div>
            <div class="form-group">
				<button type="submit" name="art_sub" class="btn btn-info btn-block btn-round">Add Article</button>
            </div>
          </form>
          
          <div class="text-center text-muted delimiter"></div>
          <div class="d-flex justify-content-center social-buttons">
<!--           </di> -->
        </div>
      </div>
    </div>
      <div class="modal-footer d-flex justify-content-center">
        <div class="">Don't forget to seperate beetween keywords using virgule <b style="color:red">','</b> </div>
      </div>
  </div>
</div>
</div>

<script src='https://code.jquery.com/jquery-3.3.1.slim.min.js'></script>
<!-- Popper JS -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js'></script>
<!-- Bootstrap JS -->
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js'></script>

</body>
</html>