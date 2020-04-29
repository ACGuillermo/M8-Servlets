<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Registro</title>
</head>
<body>
	<!-- NAVBAR -->
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <!-- IZQUIERDA -->
        <a class="navbar-brand text-primary" href="index.jsp">Noticias Guillermo</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
        <!-- IZQUIERDA -->

        <!-- DERECHA -->
            <div class="navbar-nav">
            	<a class="nav-item nav-link text-primary" href="cursos.jsp">Cursos</a>
                <a class="nav-item nav-link text-primary" href="login.jsp">Login</a>
            </div>
        </div>
        <!-- DERECHA -->
    </nav>
    <!-- NAVBAR -->
    
    
    <form action="../registrarServlet" method="post" autocomplete="off">
    	<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="basic-addon1">@</span>
		  </div>
		  <input type="text" class="form-control" placeholder="Jhon Doe!" aria-label="Username" aria-describedby="basic-addon1" name="nombre">
		</div>
		
		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="basic-addon2">Email</span>
		  </div>
		  <input type="email" class="form-control" placeholder="ejemplo@hotmail.com" aria-label="Username" aria-describedby="basic-addon1" name="email">
		</div>
		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="basic-addon3">Password</span>
		  </div>
		  <input type="password" class="form-control" placeholder="********" aria-label="Username" aria-describedby="basic-addon1" name="password">
		</div>
		<input type="submit" value="Registrar">
    </form>
    
</body>
</html>