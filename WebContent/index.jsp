<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="resources/bootstrap/assets/ico/favicon.png">

    <title>Página Principal - Biblioteca</title>

    <!-- CSS -->
    <link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="resources/jumbotron.css" rel="stylesheet">
    <link href="resources/styles.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="resources/bootstrap/assets/js/html5shiv.js"></script>
      <script src="resources/bootstrap/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
  
  <!-- Veo si está logueado, si lo está entonces lo redirijo al menú principal -->   
   <c:if test="${!empty user}">  
   	<jsp:forward page="/menu.jsp" />
   </c:if>

<!-- Incluyo el header -->
	<jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    
    	<c:if test="${!empty loginError}">

			<!--  alerta de error en login -->
			<div class="alert alert-danger">${loginError}</div>
	
		</c:if>
    
      <div class="container">
        <h1>Bienvenido a la biblioteca!</h1>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus</p>
        <!-- <p><a class="btn btn-primary btn-lg">Learn more &raquo;</a></p> -->
      </div>
    </div> <!-- jumbotron end -->

	<section id="main">
    
    <!-- Esto hay que automatizarlo para traer x cantidad de libros recientes-->
    
      <h2>Libros agregados recientemente:</h2>
      <article>
    		<img style="float: left;" src="http://2.bp.blogspot.com/_McgfrVkIzjY/TNB8qAbrjdI/AAAAAAAAAQ4/Qrt-EhA830I/s320/CaperucitaRoja.jpg" /> 
			<h3><a href="#">Caperucita Roja</a></h3>
			<p>
			Una niña recibe de su madre el encargo de llevar una cesta a su abuela enferma que vive en el bosque, advirtiéndole que no hable con desconocidos. Pero por el camino se encuentra un lobo y se para a hablar con él, dándole detalles de lo que va a hacer.
			El lobo aprovecha para engañar a caperucita y llegar antes a casa de la abuelita, a quien se come, y luego ocupa su lugar para engañar a caperucita y comérsela también. Afortunadamente, un leñador que andaba por allí descubre al lobo durmiendo tras su comida, y rescata a caperucita y su abuelita de la tripa del lobo, sustituyéndolas por piedras que hacen que el lobo se ahoge al ir a beber al río.
        	</p>
      </article>
      
      <article>
			<img style="float: left;" src="http://cinemania.es/app/webroot/images/2011/concursos/blancanieves_libro.jpg" /> 
			<h3><a href="#">Blancanieves y la Leyenda del Cazador</a></h3>
			<p>
			Blancanieves es la única persona en el mundo aún más bella que la malvada reina Ravenna, deseosa de acabar con ella. Pero lo que la reina no imagina es que la joven que amenaza su reinado ha sido entrenada en las artes de la guerra por el cazador que tenía la misión de asesinarla.
			Y es aquí donde la historia que conocíamos de Blancanieves tiene otro final… Una nueva, sobrecogedora y sorprendente versión del cuento clásico.
       		</p>
      </article>

    </section>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
	
</body>
</html>
