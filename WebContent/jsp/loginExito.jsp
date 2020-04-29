<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Datos correctos</title>
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
                <a class="btn btn-outline-info ml-1 mr-1" href="registrar.jsp">REGISTRAR</a>
            </div>
        </div>
        <!-- DERECHA -->
    </nav>
    <!-- NAVBAR -->
    	<h1>Logeado como: ${sessionScope.nick} </h1>
    
	
</body>
</html>