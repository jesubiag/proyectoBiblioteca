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

    <title>Alta/Modificación de Socio - Biblioteca</title>

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
   	<jsp:forward page="index.jsp" />
   </c:if>
   
<!-- Incluyo el header -->
	<jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    	<div class="container">
        
	        <h1>Alta/Modificación de Socio</h1>
	        
			<form class="inputForm" action="Socios" method="post">
 
 				  <div class="form-group">
			      Id: 
			      <input type="text" readonly=readonly class="form-control" name="id" value="${socio.id}">
			      </div>
			      
			      <div class="form-group">
			      Nombre: 
			        <span id="sprytextfield1">
			        <input type="text" class="form-control" name="nombre" value="${socio.nombre}" onBlur="sacarclase('sprytextfield1')">
		          <span class="textfieldRequiredMsg">Se necesita un nombre.</span></span></div>
			      
			      <div class="form-group">
				      Apellido: 
				        <span id="sprytextfield2">
				        <input type="text" class="form-control" name="apellido" value="${socio.apellido}" onBlur="sacarclase('sprytextfield2')">
		          <span class="textfieldRequiredMsg">Se necesita un apellido.</span></span></div>
			      
			      <div class="form-group">
				      DNI: 
				        <span id="sprytextfield3">
                        <input type="text" class="form-control" name="dni" value="${socio.dni}" onBlur="sacarclase('sprytextfield3')">
                  <span class="textfieldRequiredMsg">Se necesita un DNI.</span><span class="textfieldInvalidFormatMsg">Formato no válido.</span><span class="textfieldMinCharsMsg">No se cumple el mínimo de caracteres requerido.</span><span class="textfieldMaxCharsMsg">Se ha superado el número máximo de caracteres.</span></span></div>
			      
			      <div class="form-group">
				      Email: 
				        <span id="sprytextfield4">
                        <input type="text" class="form-control" name="email" value="${socio.email}" onBlur="sacarclase('sprytextfield4')">
                  <span class="textfieldRequiredMsg">Se necesita un e-mail.</span><span class="textfieldInvalidFormatMsg">Formato no válido.</span></span></div>
			      
			      <div class="form-group">
			     	 Teléfono: 
			     	   <span id="sprytextfield5">
			     	   <input type="text" class="form-control" name="telefono" value="${socio.telefono}" onBlur="sacarclase('sprytextfield5')">
	     	      <span class="textfieldRequiredMsg">Se necesita un tel&eacute;fono.</span></span></div>
			      
			      <div class="form-group">
				      Rango: 
				        <span id="sprytextfield6">
				        <input type="text" class="form-control" name="rango" value="${socio.rango}" onBlur="sacarclase('sprytextfield6')">
		          <span class="textfieldRequiredMsg">Se necesita un valor.</span></span></div>		
			      
			      <div class="form-group">
					  Estado: 
					    <span id="spryselect1">
					    <select name="estado" class="form-control" onBlur="sacarclase('spryselect1')">
					      <c:forEach var="estado" items="${estados}">
					        <option value="${estado}">${estado}</option>
				          </c:forEach>
				        </select>
			      <span class="selectRequiredMsg">Seleccione un elemento.</span></span></div>
				  
				  <!--  hacer esto con js para poder cambiar las opciones del select al cambiar una provincia -->
				  
				  <fieldset>
					  <legend>Datos de Dirección</legend> 
					 
					  <div class="form-group">
						  Provincia: 
						    <span id="spryselect2">
						    <select name="provincia" class="form-control" onBlur="sacarclase('spryselect2')">
						      <c:forEach var="provincia" items="${provincias}">
						        <option value="${provincia.nombre}">${provincia.nombre}</option>
					          </c:forEach>
					        </select>
				      <span class="selectRequiredMsg">Seleccione una provincia.</span></span></div>
					  
					  <div class="form-group">
				     	 Localidad: 
				     	   <span id="sprytextfield7">
				     	   <input type="text" class="form-control" name="localidad" value="${socio.direccion.localidad.nombre}" onBlur="sacarclase('spryselect2')">
		     	      <span class="textfieldRequiredMsg">Se necesita una localidad.</span></span></div>
	
					  <div class="form-group">
				     	 Calle: 
				     	   <span id="sprytextfield8">
				     	   <input type="text" class="form-control" name="calle" value="${socio.direccion.calle}" onBlur="sacarclase('sprytextfield8')">
		     	      <span class="textfieldRequiredMsg">Se necesita una calle.</span></span></div>
				      
				      <div class="form-group">
				     	 Número: 
				     	   <span id="sprytextfield9">
	                       <input type="text" class="form-control" name="numero" value="${socio.direccion.numero}" onBlur="sacarclase('sprytextfield9')">
	                  <span class="textfieldRequiredMsg">Se necesita un n&uacute;mero.</span><span class="textfieldInvalidFormatMsg">Formato no válido, debe ser un n&uacute;mero.</span></span></div>		  				  
					  
					  <div class="form-group">
				     	 Código Postal: 
				     	   <span id="sprytextfield10">
				     	   <input type="text" class="form-control" name="codigoPostal" value="${socio.direccion.codigoPostal}" onBlur="sacarclase('sprytextfield10')">
		     	      <span class="textfieldRequiredMsg">Se necesita un CP.</span></span></div>
				      
				      <div class="form-group">
				     	 Piso: 
				     	   
				     	   <input type="text" class="form-control" name="piso" value="${socio.direccion.piso}">
		     	      </div>
				      
				      <div class="form-group">
				     	 Departamento: 
				     	 <input type="text" class="form-control" name="departamento" value="${socio.direccion.departamento}" >
				      </div>
			     			  		  
				  </fieldset>
				  
				  <p class="buttonGroup">
			    	<button type="submit" class="btn btn-success">Aceptar</button>
			    	<a href="/proyectoBiblioteca/Socios" class="btn btn-danger">Cancelar</a>
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
	var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3", "integer", {validateOn:["change"], minChars:6, maxChars:8});
	var sprytextfield4 = new Spry.Widget.ValidationTextField("sprytextfield4", "email");
	var sprytextfield5 = new Spry.Widget.ValidationTextField("sprytextfield5");
	var sprytextfield6 = new Spry.Widget.ValidationTextField("sprytextfield6");
	var spryselect1 = new Spry.Widget.ValidationSelect("spryselect1");
	var spryselect2 = new Spry.Widget.ValidationSelect("spryselect2");
	var sprytextfield7 = new Spry.Widget.ValidationTextField("sprytextfield7");
	var sprytextfield8 = new Spry.Widget.ValidationTextField("sprytextfield8");
	var sprytextfield9 = new Spry.Widget.ValidationTextField("sprytextfield9", "integer", {validateOn:["change"]});
	var sprytextfield10 = new Spry.Widget.ValidationTextField("sprytextfield10");
  </script>
  </body>
</html>
