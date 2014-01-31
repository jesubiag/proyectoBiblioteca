<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="resources/bootstrap/assets/ico/favicon.png">

    <title>Reportes</title>

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

<!-- Incluyo el header -->
	<jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    
		<div class="container">
	      
	      	<ol class="breadcrumb">
			  <li><a href="/proyectoBiblioteca">Inicio</a></li>
			  <li class="active">Reportes</li>
			</ol>
	      
	        <h1>Reportes</h1>
	      
			<section id="main">
		    
		    	<ul>
		    		<li><a href="Reportes?report=retirosMesAMes" target="_blank">Cantidad de retiros mes a mes</a></li>
		    		<li><a href="Reportes?report=devolucionesEnMora" target="_blank">Devoluciones en Mora</a></li>
		    		<li><a href="Reportes?report=librosMasRetirados" target="_blank">Libros más retirados</a></li>
		    	</ul>
		    	
		    	<p class="buttonGroup">
		    		<a href="javascript:history.back()" class="btn btn-warning btn-lg">Volver al Menú  <span class="glyphicon glyphicon-backward"></span></a>
				</p>
				
		    </section>

		</div>

    </div> <!-- jumbotron end -->

	
	
<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />

</body>
</html>
