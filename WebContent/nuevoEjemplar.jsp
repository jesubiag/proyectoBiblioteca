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

    <title>Alta/Modificación de Ejemplar - Biblioteca</title>

    <!-- CSS -->
    <link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="resources/jumbotron.css" rel="stylesheet">
    <link href="resources/styles.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="resources/bootstrap/assets/js/html5shiv.js"></script>
      <script src="resources/bootstrap/assets/js/respond.min.js"></script>
    <![endif]-->
  <script src="resources/SpryAssets/SpryValidationSelect.js" type="text/javascript"></script>
  <script src="resources/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
  <link href="resources/SpryAssets/SpryValidationSelect.css" rel="stylesheet" type="text/css">
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
		  		<li><a href="Libros">Administración de Libros y Ejemplares</a></li>
		  		<li class="active">Alta/Modificación Ejemplar</li>
			</ol>
        
	        <h1>Alta/Modificación de Ejemplar</h1>
	        
			<form class="inputForm" action="Ejemplares" method="post">
 
 				  <div class="form-group">
			      	Id: 
			      	<input type="text" readonly=readonly class="form-control" name="id" value="${ejemplar.id}">
			      </div>
			      
			      <div class="form-group">
					  Libro: 
					    <span id="spryselect1">
					    <select name="libro" class="form-control" onBlur="sacarclase('spryselect1');">
					      <c:forEach var="libro" items="${libros}">
					        <option value="${libro.id}">${libro.titulo}</option>
				          </c:forEach>
				        </select>
			      <span class="selectRequiredMsg">Seleccione un libro.</span></span></div>
			      
			      <div class="form-group">
				  	Año: 
				  	  <span id="sprytextfield1">
                      <input type="text" class="form-control" name="anio" value="${ejemplar.anio}" onBlur="sacarclase('sprytextfield1');">
                  <span class="textfieldRequiredMsg">Se necesita un a&ntilde;o.</span><span class="textfieldInvalidFormatMsg">A&ntilde;o no v&aacute;lido.</span><span class="textfieldMaxCharsMsg">Incluya un a&ntilde;o v&aacute;lido. (M&aacute;ximo 2013.)</span><span class="textfieldMinCharsMsg">Incluya un a&ntilde;o v&aacute;lido. (M&iacute;nimo 1000)</span><span class="textfieldMinValueMsg">El valor introducido es inferior al mínimo permitido. (1000).</span><span class="textfieldMaxValueMsg">El valor introducido es superior al máximo permitido. (2013).</span></span></div>
			      
			      <div class="form-group">
				  	Pasillo: 
				  	  <span id="sprytextfield2">
				  	  <input type="text" class="form-control" name="pasillo" value="${ejemplar.pasillo}" onBlur="sacarclase('sprytextfield2');">
			  	  <span class="textfieldRequiredMsg">Se necesita un pasillo.</span></span></div>
			      
			      <div class="form-group">
				  	Mueble: 
				  	  <span id="sprytextfield3">
				  	  <input type="text" class="form-control" name="mueble" value="${ejemplar.mueble}" onBlur="sacarclase('sprytextfield3');">
			  	  <span class="textfieldRequiredMsg">Se necesita un mueble.</span></span></div>
			      
			      <div class="form-group">
			      	Estante: 
			      	  <span id="sprytextfield4">
			      	  <input type="text" class="form-control" name="estante" value="${ejemplar.estante}" onBlur="sacarclase('sprytextfield4');">
		      	  <span class="textfieldRequiredMsg">Se necesita un estante.</span></span></div>

			      <div class="form-group">
					  Estado: 
					    <span id="spryselect2">
					    <select name="estado" class="form-control" onBlur="sacarclase('spryselect2');">
					      <c:forEach var="estado" items="${estados}">
					        <option value="${estado}">${estado}</option>
				          </c:forEach>
				        </select>
			      <span class="selectRequiredMsg">Seleccione un estado.</span></span></div>

				  <div class="form-group">
			      	Número de Ejemplar: 
			      	  <span id="sprytextfield5">
                      <input type="text" class="form-control" name="numeroEjemplar" value="${ejemplar.numeroEjemplar}" onBlur="sacarclase('sprytextfield5');">
                  <span class="textfieldRequiredMsg">Se necesita un valor num&eacute;rico.</span><span class="textfieldInvalidFormatMsg">Formato no válido.</span><span class="textfieldMinCharsMsg">No se cumple el mínimo de caracteres requerido.</span><span class="textfieldMinValueMsg">El valor introducido es inferior al mínimo permitido.</span><span class="textfieldMaxCharsMsg">Se ha superado el número máximo de caracteres.</span><span class="textfieldMaxValueMsg">El valor introducido es superior al máximo permitido.</span></span></div>

				  <div class="form-group">
			      	Precio en Dólares: 
			      	  <span id="sprytextfield6">
                      <input type="text" class="form-control" name="precioDolares" value="${ejemplar.precioDolares}" onBlur="sacarclase('sprytextfield6');">
                  <span class="textfieldRequiredMsg">Se necesita un valor en d&oacute;lares.</span><span class="textfieldInvalidFormatMsg">Formato no válido. Debe ser 0000.00</span></span></div>  		  
				  
				  <p class="buttonGroup">
			    	<button type="submit" class="btn btn-success">Aceptar</button>
			    	<a href="/proyectoBiblioteca/Ejemplares" class="btn btn-danger">Cancelar</a>
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
	var spryselect1 = new Spry.Widget.ValidationSelect("spryselect1");
	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "integer", {validateOn:["change"], maxChars:4, minChars:4, minValue:1000, maxValue:2013});
	var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2");
	var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3");
	var sprytextfield4 = new Spry.Widget.ValidationTextField("sprytextfield4");
	var spryselect2 = new Spry.Widget.ValidationSelect("spryselect2");
	var sprytextfield5 = new Spry.Widget.ValidationTextField("sprytextfield5", "integer", {validateOn:["change"], minChars:1, minValue:1, maxChars:3, maxValue:500});
	var sprytextfield6 = new Spry.Widget.ValidationTextField("sprytextfield6", "currency", {validateOn:["change"]});
  </script>
  </body>
</html>
