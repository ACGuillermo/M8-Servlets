<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
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
                <a class="nav-item nav-link text-primary" href="login.jsp">Login</a>
                <a class="btn btn-outline-info ml-1 mr-1" href="registrar.jsp">REGISTRAR</a>
            </div>
        </div>
        <!-- DERECHA -->
    </nav>
    <!-- NAVBAR -->
    
    <div class="container">
    	<!-- POST CURSOS -->
        <form action="../CursosServlet" method="post" autocomplete="off">
            <div class="row justify-content-center">
                <h2>Cursos</h2>
            </div>
            <div class="row my-4 justify-content-between">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="cursos" id="curso1" value="curso1" required>
                    <label class="form-check-label" for="curso3">1</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="cursos" id="curso2" value="curso2">
                    <label class="form-check-label" for="curso2">2</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="cursos" id="curso3" value="curso3">
                    <label class="form-check-label" for="curso3">3</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="cursos" id="curso4" value="curso4">
                    <label class="form-check-label" for="curso3">4</label>
                </div>
            </div>
            <div class="row justify-content-center my-4">
                <h2>Pago</h2>
            </div>
            <div class="row my-4 justify-content-between">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pagos" id="pago1" value="Efectivo" required>
                    <label class="form-check-label" for="curso3">Efectivo</label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pagos" id="pago2" value="Tarjeta">
                    <label class="form-check-label" for="curso2">Tarjeta de credito</label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pagos" id="pago3" value="Paypal">
                    <label class="form-check-label" for="curso3">Paypal</label>
                  </div>
            </div>
            <div class="row my-4">
            	<div class="form-group">
				  <label for="sel1">Grado:</label>
				  <select class="form-control" id="grado" name="grado">
				    <option value="1">1</option>
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				  </select>
				</div> 
            </div>
            <div class="row my-4">
                <input type="submit" class="btn btn-primary mb-2"></input>
            </div>
        </form>
    </div>

    <hr>

    <div class="container">
    	<!-- GET COMENTARIOS -->
        <form action="../ComentarioServlet" method="get" autocomplete="off">
            <div class="my-4 justify-content-center">
                <div class="form-group">
                    <label for="comment">Comment:</label>
                    <textarea class="form-control" rows="5" id="comentario" name="comentario" maxlength="200" required></textarea>
                </div>
            </div>
            <input type="submit" class="btn btn-primary mb-2"></input>
        </form>
    </div>
	
	
	 <!--FOOTER-->
   <div class="bg-dark mt-4">
    <div class="bg-dark container">
        <div class="row ml-4">
            <div class="col-6 my-4 col-lg-3">
                <h5 class="text-info">Sobre Domum</h5>
                <a class="text-white">Mapa web</a><br>
                <a class="text-white">Sala de prensa</a><br>
                <a class="text-white">Índice de precios</a><br>
                <a class="text-white">Gestionar Publicidad</a><br>
                <a class="text-white">Trabaja con nosotros</a><br>
            </div>
            <div class="col-6 my-4 col-lg-3">
                <h5 class="text-info">Para particulares</h5>
                <a class="text-white">Nuestras Apps</a><br>
                <a class="text-white">Blog</a><br>
                <a class="text-white">Catálogo</a><br>
                <a class="text-white">Ayuda</a><br>
            </div>
            <div class="col-3 my-4 d-none d-lg-block">
                <h5 class="text-info">Para profesionales</h5>
                <a class="text-white">Anúnciate</a><br>
                <a class="text-white">Tu cuenta</a><br>
            </div>
            <div class="col-3 my-4 d-none d-lg-block">
                <h5 class="text-info">Descárgate nuestra App</h5>
                <img alt="Android Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_android_app.png"><br>
                <img class="mt-2"alt="iOS Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_ios_app.png">
            </div>
        </div>
        <div class="row ml-4 d-lg-none">
            <div class="col-6 my-4">
                <h5 class="text-info">Para profesionales</h5>
                <a class="text-white">Anúnciate</a><br>
                <a class="text-white">Tu cuenta</a><br>
            </div>
            <div class="col-6 my-4">
                <h5 class="text-info">Descárgate nuestra App</h5>
                <img alt="Android Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_android_app.png"><br>
                <img class="mt-2"alt="iOS Fotocasa App" src="https://frtassets.fotocasa.es/statics/footer_download_ios_app.png">
            </div>
        </div>
    </div>
    </div>
    <!--FOOTER-->
</body>
</html>