<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="resources/bootstrap/assets/ico/favicon.png">

    <title>Alta/Modificación de Autor - Biblioteca</title>

    <!-- CSS -->
    <link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="resources/jumbotron.css" rel="stylesheet">
    <link href="resources/styles.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="resources/bootstrap/assets/js/html5shiv.js"></script>
      <script src="resources/bootstrap/assets/js/respond.min.js"></script>
    <![endif]-->
  <script src="resources/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
  <link href="resources/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
  </head>

  <body>
  
  <!-- Veo si no está logueado, lo mando al login en caso de que no -->   
   <c:if test="${empty user}">  
   	<jsp:forward page="/" />
   </c:if>
   
<!-- Incluyo el header -->
	<jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    	<div class="container">
        
        	<ol class="breadcrumb">
		  		<li><a href="/proyectoBiblioteca">Inicio</a></li>
		  		<li><a href="Autores">Administración de Autores</a></li>
		  		<li class="active">Alta/Modificación de Autor</li>
			</ol>
        
	        <h1>Alta/Modificación de Autor</h1>
	        
			<form class="inputForm" action="Autores" method="post">
 
				<div class="form-group">
					Id: 
					<input type="text" readonly=readonly class="form-control" name="id" value="${autor.id}">
				</div>
				
				<div class="form-group">
					Nombre: 
					  <span id="sprytextfield1">
					  <input type="text" class="form-control" name="nombre" value="${autor.nombre}" onBlur="sacarclase('sprytextfield1');">
			    <span class="textfieldRequiredMsg">Se necesita un nombre.</span></span></div>
				
				<div class="form-group">
					País de Origen: 
					  <span id="sprytextfield2">
					  <input type="text" class="form-control" name="paisOrigen" value="${autor.paisOrigen}" onBlur="sacarclase('sprytextfield2');">
			    <span class="textfieldRequiredMsg">Se necesita un pa&iacute;s.</span></span></div>	  		  
				<p class="buttonGroup">
			    <button type="submit" class="btn btn-success">Aceptar</button>
			    <a href="/proyectoBiblioteca/Autores" class="btn btn-danger">Cancelar</a>
				</p>
			</form> 

		</div>       
	
	</div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
  <script type="text/javascript">
	  function sacarclase(id){
			document.getElementById(id).className = "";
			}
	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1");
	var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2");
  </script>
  </body>
</html>
