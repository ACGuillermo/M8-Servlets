<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Contraseña cambiada</title>
</head>
<body>
	 <!-- NAVBAR -->
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <!-- IZQUIERDA -->
        <a class="navbar-brand text-primary" href="jsp/index.jsp">Cursos Guillermo</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
        <!-- IZQUIERDA -->

        <!-- DERECHA -->
            <div class="navbar-nav">
            	<a class="nav-item nav-link text-primary" href="jsp/cursos.jsp">Cursos</a>
            	<%
					if(session.getAttribute("nick") != null){
				
				%>
                <a class="nav-item nav-link text-primary" href="jsp/perfil.jsp">Perfil</a>
                <%
                	}else{ 
                %>
                <a class="nav-item nav-link text-primary" href="jsp/login.jsp">Login</a>
                <%
                	} 
                %>
                <a class="btn btn-outline-info ml-1 mr-1" href="jsp/registrar.jsp">REGISTRAR</a>
            </div>
        </div>
        <!-- DERECHA -->
    </nav>
    <!-- NAVBAR -->
    
    <div class="container d-flex juntify-content-center">
    
    	<div class="card" style="width: 18rem;">
		  <div class="card-body">
		    <h5 class="card-title">Contraseña cambiada correctamente</h5>
		    <p class="card-text">Procura recordar tu contraseña y no dársela a nadie por seguridad</p>
		    <a href="jsp/perfil.jsp" class="btn btn-primary">Volver</a>
		  </div>
		</div>
    
    </div>
	
	 <!--FOOTER-->
   <div class="bg-dark mt-4">
    <div class="bg-dark container">
        <div class="row ml-4">
            <div class="col-6 my-4 col-lg-3">
                <h5 class="text-info">Sobre cursos</h5>
                <a class="text-white">Mapa web</a><br>
                <a class="text-white">Sala de prensa</a><br>
                <a class="text-white">Indice de precios</a><br>
                <a class="text-white">Gestionar Publicidad</a><br>
                <a class="text-white">Trabaja con nosotros</a><br>
            </div>
            <div class="col-6 my-4 col-lg-3">
                <h5 class="text-info">Para particulares</h5>
                <a class="text-white">Nuestras Apps</a><br>
                <a class="text-white">Blog</a><br>
                <a class="text-white">Catalogo</a><br>
                <a class="text-white">Ayuda</a><br>
            </div>
            <div class="col-3 my-4 d-none d-lg-block">
                <h5 class="text-info">Para profesionales</h5>
                <a class="text-white">Anunciate</a><br>
                <a class="text-white">Tu cuenta</a><br>
            </div>
            <div class="col-3 my-4 d-none d-lg-block">
                <h5 class="text-info">Descargate nuestra App</h5>
                <img alt="Android Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_android_app.png"><br>
                <img class="mt-2"alt="iOS Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_ios_app.png">
            </div>
        </div>
        <div class="row ml-4 d-lg-none">
            <div class="col-6 my-4">
                <h5 class="text-info">Para profesionales</h5>
                <a class="text-white">Anunciate</a><br>
                <a class="text-white">Tu cuenta</a><br>
            </div>
            <div class="col-6 my-4">
                <h5 class="text-info">Descargate nuestra App</h5>
                <img alt="Android Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_android_app.png"><br>
                <img class="mt-2"alt="iOS Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_ios_app.png">
            </div>
        </div>
    </div>
    <!--FOOTER-->
</body>
</html>