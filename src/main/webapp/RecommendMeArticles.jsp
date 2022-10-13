<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Auteur" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Article" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recommendations page</title>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
<script type="text/javascript">
</script>
 <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Kaushan+Script'><link rel="stylesheet" href="./style.css">
<link rel='stylesheet' href='css/style.css'><link rel="stylesheet" href="./style.css">
</head>
<body>


<% HttpSession sessionEmail = request.getSession();
 %>
<% if(session.getAttribute("userSession") ==null){
	request.getRequestDispatcher("Login.jsp").forward(request,response);
}
	ArrayList<Auteur> recommendation =(ArrayList<Auteur>)request.getAttribute("Recommendation");
	Auteur user = (Auteur) request.getAttribute("user");

	if(request.getAttribute("user") ==null){
		request.getRequestDispatcher("GetRecommendation").forward(request,response);
	}
	%>


<header>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
  <div class="container">
    <a class="navbar-brand js-scroll-trigger" href="#page-top">findYourPeers</a>
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
            <img src="" class="img-responsive" alt="">
          </div>
          <!-- END SIDEBAR USERPIC -->
          <!-- SIDEBAR USER TITLE -->
          <div class="profile-usertitle">
            <div class="profile-usertitle-name">
            </div>
            <div class="profile-usertitle-job">
            		<h6 style="color:black"> </h6>
            </div>
          </div>
          <!-- END SIDEBAR USER TITLE -->
          <!-- SIDEBAR BUTTONS -->
          <div class="profile-userbuttons">
          <%
          	if(user != null){%>
          		<h6><%=("mr. "+user.getPrenom()+" "+user.getNom()) %></h6>
          		
          	<%}
          %>
                        	<BR>

       </div>
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
                <a class="nav-link" href="index.jsp">
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
          <br/>
                    <div class="profile-userbuttons">
                    <h6 style="color:blue">you might know </h6>
        </div>
          <div class="profile-usermenu sidebar-sticky">
            <ul class="nav flex-column">
              <li class="active" style="text-align:center">
                <%
                	if(recommendation !=null){
                		for(Auteur author:recommendation){%>
                			<a class="nav-link "><i class="" style="color:black"><%=(author.getPrenom()+" "+author.getNom()) %></i> </a>
                		<%}
                	}
                %>
              </li>
            </ul>
          </div>
          
          <!-- END MENU -->
        </div>
      </div>
      <div class="col-md-9">
        <div class="profile-content">
        	<h2 style="text-align:center;color:#6699ff" >Article Recommended</h2>
        	<br/>
        	<%
				if(recommendation !=null)
				{
					for(Auteur auteur:recommendation){%>
					<div class="card" >
  						<div class="card-body">
    						<h5 class="card-title" style="color:#000066"><%=auteur.getArticle().getTitle() %></h5>
    						<h6 class="card-subtitle mb-2 text-muted"><%=auteur.getArticle().getAbbreviation() %> </h6>
   							<p class="card-text"> 
   							</p>
   							<h6 style="color:#000066">Published by <b><%= (auteur.getPrenom()+" "+auteur.getNom()) %></b></h6>
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


</body>
</html>