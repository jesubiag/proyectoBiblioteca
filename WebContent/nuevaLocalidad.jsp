<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="resources/bootstrap/assets/ico/favicon.png">

    <title>Alta/Modificación de Localidad - Biblioteca</title>

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
		  		<li><a href="Localidades">Administración de Localidades</a></li>
		  		<li class="active">Alta/Modificación Localidad</li>
			</ol>
        
	        <h1>Alta/Modificación de Localidad</h1>
	        
			<form class="inputForm" action="Localidades" method="post">
 
				<div class="form-group">
					Id: 
					<input type="text" readonly=readonly class="form-control" name="id" value="${localidad.id}">
				</div>
				
				<div class="form-group">
					Nombre: 
					  <span id="sprytextfield1">
					  <input type="text" class="form-control" name="nombre" value="${localidad.nombre}" onBlur="sacarclase('sprytextfield1')">
			    <span class="textfieldRequiredMsg">Se necesita un valor.</span></span></div>
				
				<div class="form-group">
					  Provincia: 
					    <span id="spryselect1">
					    <select name="provincia" class="form-control" onBlur="sacarclase('spryselect1')">
					      <c:forEach var="provincia" items="${provincias}">
					        <option value="${provincia.nombre}"
					        	<c:if test="${provincia.nombre == localidad.provincia.nombre}">${"selected='selected'"}</c:if> 
					        >${provincia.nombre}</option>
				        
				          </c:forEach>
				        </select>
				        
			    <span class="selectRequiredMsg">Seleccione un elemento.</span></span></div>

				<p class="buttonGroup">
			    <button type="submit" class="btn btn-success">Aceptar</button>
			    <a href="/proyectoBiblioteca/Localidades" class="btn btn-danger">Cancelar</a>
				</p>
				
			</form> 

		</div>       
	
	</div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
  <script type="text/javascript">
	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1");
	var spryselect1 = new Spry.Widget.ValidationSelect("spryselect1");
	function sacarclase(id){
			document.getElementById(id).className = "";
			}
  </script>
  </body>
</html>
