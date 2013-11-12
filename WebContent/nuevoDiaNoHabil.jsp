<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="resources/bootstrap/assets/ico/favicon.png">

    <title>Alta/Modificación de Día No Hábil - Biblioteca</title>

    <!-- CSS -->
    <link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="resources/jumbotron.css" rel="stylesheet">
    <link href="resources/styles.css" rel="stylesheet">
    <!-- JQueryUI css -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

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
		  		<li><a href="DiasNoHabiles">Administración de Días No Hábiles</a></li>
		  		<li class="active">Alta/Modificación de Día No Hábil</li>
			</ol>
        
	        <h1>Alta/Modificación de Día No Hábil</h1>
	        
			<form class="inputForm" action="DiasNoHabiles" method="post">
 
				<div class="form-group">
					Id: 
					<input type="text" readonly=readonly class="form-control" name="id" value="${diaNoHabil.id}" >
				</div>
				
				<div class="form-group">
					Fecha: 
					  <span id="sprytextfield1">
                      <input id=fecha type="text" class="form-control" name="fecha" value="${diaNoHabil.stringFecha}" onBlur="sacarclase('sprytextfield1');">
                <span class="textfieldRequiredMsg">Se necesita una fecha.</span><span class="textfieldInvalidFormatMsg">Formato no válido. Intente AAAA-MM-DD</span></span></div>
				
				<div class="form-group">
					Fecha Siguiente: 
					  <span id="sprytextfield2">
                      <input id=fechaSiguiente type="text" class="form-control" name="fechaSiguiente" value="${diaNoHabil.stringFechaSiguiente}" onBlur="sacarclase('sprytextfield2');">
                <span class="textfieldRequiredMsg">Se necesita una fecha.</span><span class="textfieldInvalidFormatMsg">Formato no válido. Intente AAAA-MM-DD</span></span></div>
				
				<div class="form-group">
					Motivo: 
					  <span id="sprytextfield3">
					  <input type="text" class="form-control" name="motivo" value="${diaNoHabil.motivo}" onBlur="sacarclase('sprytextfield3');">
			    <span class="textfieldRequiredMsg">Se necesita un motivo.</span></span></div>
				
				<!-- Falta Armar lo de Direcciones y Teléfonos -->	  		  
				
				<p class="buttonGroup">
			    <button type="submit" class="btn btn-success">Aceptar</button>
			    <a href="/proyectoBiblioteca/DiasNoHabiles" class="btn btn-danger">Cancelar</a>
				</p>
				
			</form> 

		</div>       
	
	</div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script>
	function sacarclase(id){
			document.getElementById(id).className = "";
			}
	$(function() {
			$( "#fecha,#fechaSiguiente" ).datepicker({ dateFormat: "yy-mm-dd" });
			});
	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "date", {format:"yyyy-mm-dd", validateOn:["change"]});
	var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2", "date", {format:"yyyy-mm-dd", validateOn:["change"]});
	var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3", "none", {validateOn:["change"]});
    </script>	
  </body>
</html>
