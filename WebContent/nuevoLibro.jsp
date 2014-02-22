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

    <title>Alta/Modificación de Libro - Biblioteca</title>

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
  <script src="resources/SpryAssets/SpryValidationSelect.js" type="text/javascript"></script>
  <link href="resources/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
  <link href="resources/SpryAssets/SpryValidationSelect.css" rel="stylesheet" type="text/css">
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
		  		<li class="active">Alta/Modificación Libro</li>
			</ol>
        
	        <h1>Alta/Modificación de Libro</h1>
	        
			<form class="inputForm" action="Libros" method="post">
 
 				  <div class="form-group">
			      	Id: 
			      	<input type="text" readonly=readonly class="form-control" name="id" value="${libro.id}">
			      </div>
			      
			      <div class="form-group">
			      	Título: 
			      	  <span id="sprytextfield1">
			      	  <input type="text" class="form-control" name="titulo" value="${libro.titulo}" onBlur="sacarclase('sprytextfield1');">
		      	  <span class="textfieldRequiredMsg">Se necesita un t&iacute;tulo.</span></span></div>
			      
			      <div class="form-group">
					  Editorial: 
					    <span id="spryselect1">
					    <select name="editorial" class="form-control" onBlur="sacarclase('spryselect1');">
					      <c:forEach var="editorial" items="${editoriales}">
					        <option value="${editorial.id}">${editorial.nombre}</option>
				          </c:forEach>
				        </select>
			      <span class="selectRequiredMsg">Seleccione una editorial.</span></span></div>
			      
			      <div class="form-group">
					  Autor: 
					    <span id="spryselect2">
					    <select name="autores" class="form-control" onBlur="sacarclase('spryselect2');">
					      <c:forEach var="autor" items="${autores}">
					        <option value="${autor.id}">${autor.nombre}</option>
				          </c:forEach>
				        </select>
			      <span class="selectRequiredMsg">Seleccione un autor.</span></span></div>
			      
			      <div class="form-group">
				  	ISBN: 
				  	  <span id="sprytextfield2">
                      <input type="text" class="form-control" name="isbn" value="${libro.isbn}" onBlur="sacarclase('sprytextfield2');">
                  <span class="textfieldRequiredMsg">Se necesita un ISBN.</span><span class="textfieldInvalidFormatMsg">Formato no válido. (El ISBN es numérico).</span><span class="textfieldMinCharsMsg">No se cumple el mínimo de caracteres requerido (10).</span><span class="textfieldMaxCharsMsg">Se ha superado el número máximo de caracteres (13).</span></span></div>
			      
			      <div class="form-group">
			      	Etiquetas: 
			      	  <span id="sprytextfield3">
			      	  <input type="text" class="form-control" name="etiquetas" value="${libro.listaEtiquetas}" onBlur="sacarclase('sprytextfield3');">
		      	  <span class="textfieldRequiredMsg">Se necesita al menos una etiqueta.</span></span></div>

				  <div class="form-group">
			      	Rango: 
			      	  <span id="sprytextfield4">
                      <input type="text" class="form-control" name="rango" value="${libro.rango}" onBlur="sacarclase('sprytextfield4');">
                  <span class="textfieldRequiredMsg">Se necesita darle un rango al libro.</span><span class="textfieldInvalidFormatMsg">Formato no válido (debe ser numérico).</span><span class="textfieldMinValueMsg">El valor introducido es inferior al mínimo permitido (1).</span><span class="textfieldMaxValueMsg">El valor introducido es superior al máximo permitido (10).</span><span class="textfieldMinCharsMsg">No se cumple el mínimo de caracteres requerido.</span><span class="textfieldMaxCharsMsg">Se ha superado el número máximo de caracteres.</span></span></div>

				  <div class="form-group">
			      	País de Origen: 
			      	  <span id="sprytextfield5">
			      	  <input type="text" class="form-control" name="paisOrigen" value="${libro.paisOrigen}" onBlur="sacarclase('sprytextfield5');">
		      	  <span class="textfieldRequiredMsg">Se necesita un país.</span></span></div>  	  
				  
				  <div class="form-group">
				  	Descripción:
				  	  <span id="sprytextfield7">
				  	  <input type="text" class="form-control" name="descripcion" value="${libro.descripcion}" onBlur="sacarclase('sprytextfield7');">
			  	  <span class="textfieldRequiredMsg">Se necesita una descripci&oacute;n.</span></span></div>
				  
				  <div class="form-group">
				  	Link a Imagen:
				  	  <span id="sprytextfield6">
                      <input type="text" class="form-control" name="linkImagen" value="${libro.linkImagen}" onBlur="sacarclase('sprytextfield6');">
                  <span class="textfieldRequiredMsg">Se necesita un link a imagen.</span><span class="textfieldInvalidFormatMsg">Formato no válido, debe ser una URL (dirección web).</span></span></div>
				  
				  <p class="buttonGroup">
			    	<button type="submit" class="btn btn-success">Aceptar</button>
			    	<a href="/proyectoBiblioteca/Libros" class="btn btn-danger">Cancelar</a>
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
		var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {validateOn:["change"]});
		var spryselect1 = new Spry.Widget.ValidationSelect("spryselect1", {validateOn:["change"]});
		var spryselect2 = new Spry.Widget.ValidationSelect("spryselect2", {validateOn:["change"]});
		var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2", "integer", {validateOn:["change"], minChars:10, maxChars:13});
		var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3", "none", {validateOn:["change"]});
		var sprytextfield4 = new Spry.Widget.ValidationTextField("sprytextfield4", "integer", {validateOn:["change"], minValue:1, maxValue:10, minChars:1, maxChars:2});
		var sprytextfield5 = new Spry.Widget.ValidationTextField("sprytextfield5", "none", {validateOn:["change"]});
		var sprytextfield6 = new Spry.Widget.ValidationTextField("sprytextfield6", "url", {validateOn:["change"]});
		var sprytextfield7 = new Spry.Widget.ValidationTextField("sprytextfield7", "none", {validateOn:["change"]});
  </script>
  </body>
</html>
