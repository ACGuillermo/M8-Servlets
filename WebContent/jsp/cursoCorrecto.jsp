<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Curso correcto</title>
</head>
<body>
	
	 <!-- NAVBAR -->
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <!-- IZQUIERDA -->
        <a class="navbar-brand text-primary" href="jsp/index.jsp">Noticias Guillermo</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
        <!-- IZQUIERDA -->

        <!-- DERECHA -->
            <div class="navbar-nav">
            	<a class="nav-item nav-link text-primary" href="jsp/cursos.jsp">Cursos</a>
                <a class="nav-item nav-link text-primary" href="jsp/perfil.jsp">Perfil</a>
                <a class="btn btn-outline-info ml-1 mr-1" href="jsp/registrar.jsp">REGISTRAR</a>
            </div>
        </div>
        <!-- DERECHA -->
    </nav>
    <!-- NAVBAR -->
    	<div class="container">
	    	<h1>Cursos adquiridos!</h1>
	    	<p><strong>Nick</strong>: ${requestScope.nick} </p>
	    	<p><strong>Cursos</strong>: ${requestScope.producto} </p>
	    	<p><strong>Grado</strong>: ${requestScope.grado} </p>
	    	<p><strong>Metodo de pago</strong>: ${requestScope.pago} </p>
    	</div>
    	
    	
    
	
</body>
</html>