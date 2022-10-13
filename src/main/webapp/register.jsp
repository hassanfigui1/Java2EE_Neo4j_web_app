<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.AddAuthor" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/my-login.css">

</head>
<% HttpSession sessionEmail = request.getSession();
 %>
<% if(session.getAttribute("userSession") !=null){
	request.getRequestDispatcher("index.jsp").forward(request,response);
}
%>
<body class="my-login-page">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="img/login.png" alt="logo">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Login</h4>
							<form action="AddAuthor" method="post" class="my-login-validation" novalidate="">
								<div class="form-group">
									<label for=""> First Name </label>
									<input id="prenom" required type="text" class="form-control" name="prenom" value="" required autofocus>
								</div>
								<div class="form-group">
									<label for="">Last Name</label>
									<input id="nom" required type="text" class="form-control" name="nom" value="" required autofocus>
								</div>
								<div class="form-group">
									<label for=""> Departement </label>
									<input id="departement" required type="text" class="form-control" name="departement" value="" required autofocus>
								</div>
								
								<div class="form-group">
									<label for="">E-Mail Address</label>
									<input id="email" required type="email" class="form-control" name="email" value="" required autofocus>
									<div class="" style="color:red">
									<b>
										<% String msgError =(String)request.getAttribute("msgError"); %>
										<%= (msgError!=null ? msgError : "") %>
									</b>
									</div>
								</div>
								
								<div class="form-group">
									<label for="">Password</label>
									<input id="mdp"  required type="text" class="form-control" name="mdp" required data-eye>
								    <div class="invalid-feedback">
								    	Password is required
							    	</div>
								</div>

								<div class="form-group">
									<div class="custom-checkbox custom-control">
										<input type="checkbox" name="remember" id="remember" class="custom-control-input">
										<label for="remember" class="custom-control-label">Remember Me</label>
									</div>
								</div>

								<div class="form-group m-0">
									<button type="submit" name="frm_sub" class="btn btn-primary btn-block">
										Login
									</button>
								</div>
								<div class="mt-4 text-center">
									Do you have an account? <a href="Login.jsp">Sign in</a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer">
						Copyright &copy; 2022 &mdash; Facult� des chouaib doukkali 
					</div>
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