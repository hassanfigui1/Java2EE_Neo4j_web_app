<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Info page</title>
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
<body class="my-login-page">
<header>
		<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
  <div class="container">
    <a class="navbar-brand js-scroll-trigger" href="#page-top">findYourPeers</a>
    <div>
      <ul class="navbar-nav text-uppercase ml-auto">
        <li class="nav-item">
              <form action="LogOut" method="post">
        	   <input type="hidden" value="">
        	  <button type="submit" name="logout" class="btn btn-primary my-2 my-sm-0" "><i class="fa fa-sign-out"></i> Log Out</a></button>
        	</form>
        </li>
      </ul>
    </div>
  </div>
</nav>
</header>
<% HttpSession sessionEmail = request.getSession();
	Auteur user =(Auteur)request.getAttribute("user") ;
%>
<%
	if(user ==null){
		response.sendRedirect("ModifyInfoAuthor");
	}
%>

<div class="container content">
    <div class="row profile">
      <div class="col-md-3">
        <div class="profile-sidebar position-fixed">
          <!-- SIDEBAR USERPIC -->
          <div class="profile-userpic">
            <img src="" class="" alt="">
          </div>
          <!-- END SIDEBAR USERPIC -->
          <!-- SIDEBAR USER TITLE -->
          <div class="profile-usertitle">
            <div class="profile-usertitle-name">
            	<%
            		if(user!=null){%>
            			<h6 style="color:black"><%=("mr. "+user.getPrenom()+" "+user.getNom()) %></h6>
            		<% }
            	%>
            </div> 
          </div>
          <!-- END SIDEBAR USER TITLE -->
          <!-- SIDEBAR BUTTONS -->
          <div class="profile-userbuttons">
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
                <a class="nav-link" href="#" target="_blank">
							<i class="fa fa-check"></i>
							Tasks </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="GetRecommendation">
					<i class="fa fa-flag"></i>Recommendations </a>
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
              </li>
            </ul>
          </div>
          
          <!-- END MENU -->
        </div>
      </div>
      <div class="col-md-9">
        <div class="profile-content">
        	<h2 style="text-align:center;color:#6699ff" >Update your personal data</h2>
        	<br/>
					<div class="card" >
  						<div class="card-body">
    							<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
				
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title"></h4>
							<form action="ModifyInfoAuthor" method="post" class="my-login-validation" novalidate="">
								<div class="form-group">
									<label for=""> First Name </label>
									<input id="prenom" required type="text" class="form-control" name="prenom" value="<%=(user!=null ? user.getPrenom():"") %>" required autofocus>
								</div>
								<div class="form-group">
									<label for="">Last Name</label>
									<input id="nom" required type="text" class="form-control" name="nom" value="<%=(user!=null ? user.getNom():"") %>" required autofocus>
								</div>
								<div class="form-group">
									<label for=""> Departement </label>
									<input id="departement" required type="text" class="form-control" name="departement" value="<%=(user!=null ? user.getDepartement():"") %>" required autofocus>
								</div>
								
								<div class="form-group">
									<label for="">E-Mail Address</label>
									<input id="email" required type="email" class="form-control" name="email" value="<%=(user!=null ? user.getEmail():"") %>" required autofocus>
									<div class="" style="color:red">
									<b>
									</b>
									</div>
								</div>
								
								<div class="form-group">
									<label for="">Password</label>
									<input id="mdp"  value="<%=(user!=null ? user.getMdp():"") %>" required type="text" class="form-control" name="mdp" required data-eye>
								    <input id="id"  value="<%=(user!=null ? user.getId():"") %>" required type="text" class="form-control" name="id" required data-eye>
								    
								    <div class="invalid-feedback">
							    	</div>
								</div>
								<div class="form-group m-0">
									<button type="submit" name="act_upd" class="btn btn-primary btn-block">
										Update
									</button>
								</div>
							</form>
						</div>
					</div>
					<div class="footer">
						You can Modify any field you want - Email should be unique
					</div>
				</div>
			</div>
		</div>

 						</div>	
					</div>
					
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

	</section>



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="js/my-login.js"></script>
</body>

</html>