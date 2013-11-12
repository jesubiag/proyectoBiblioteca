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

    <title>Alta/Modificación de Editorial - Biblioteca</title>

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
			  		<li><a href="Editoriales">Administración de Editoriales</a></li>
			  		<li class="active">Alta/Modificación Editorial</li>
				</ol>
        
                <h1>Alta/Modificación de Editorial</h1>
                
                        <form class="inputForm" action="Editoriales" method="post">
 
                                <div class="form-group">
                                        Id: 
                                        <input type="text" readonly=readonly class="form-control" name="id" value="${editorial.id}">
                                </div>
                                
                                <div class="form-group">
                                        Nombre: 
                                          <span id="sprytextfield1">
                                          <input type="text" class="form-control" name="nombre" value="${editorial.nombre}" onBlur="sacarclase('sprytextfield1');">
                                <span class="textfieldRequiredMsg">Se necesita un nombre.</span></span></div>
                                
                                <div class="form-group">
                                        Email: 
                                          <span id="sprytextfield2">
                                          <input type="text" class="form-control" name="email" value="${editorial.email}" onBlur="sacarclase('sprytextfield2');">
                                <span class="textfieldRequiredMsg">Se necesita un email.</span><span class="textfieldInvalidFormatMsg">Formato no válido.</span></span></div>
                                
                                
                                <!--  Falta arreglar esto para que funcione bien
                                
                                <div class="form-group">
                                      Teléfonos: 
                                      
                                      <ul id="telefonos" class="stylelessList">
                                              <c:forEach var="telefono" items="${editorial.telefonos}">
                                                      <li><span id="sprytextfield3">
                                                        <input type="text" class="form-control" name="telefonos[]" value="${telefono}" onBlur="sacarclase('sprytextfield3');">
                                                      <span class="textfieldRequiredMsg">Se necesita un valor.</span></span></li>
                                              </c:forEach>
                                      </ul>
                                      <p class="buttonGroup">
                                              <a href='javascript:addTelField()' class="btn btn-default">Agregar Teléfono</a>
                                  </p>
                                       <p>(Preguntar cómo hacer para eliminar campos cuando están en blanco)</p>
                          		</div>
                              
                              <div class="form-group">
                                      Direcciones: 
                                      
                                      <ul id="direcciones" class="stylelessList">
                                              <c:forEach var="direccion" items="${editorial.direcciones}">
                                                      <li><span id="sprytextfield4">
                                                        <input type="text" class="form-control" name="direcciones[]" value="${direccion}" onBlur="sacarclase('sprytextfield4');">
                                                      <span class="textfieldRequiredMsg">Se necesita una direcci&oacute;n.</span></span></li>
                                              </c:forEach>
                                      </ul>
                                      <p class="buttonGroup"><a href='javascript:addDirField()' class="btn btn-default">Agregar Dirección</a></p>
                                       <p>(Próximamente)</p>
                              </div>
                                
                                -->
                                
                                <!-- Falta Armar lo de Direcciones y Teléfonos -->                            
                                
                                
                       <!--  Borrar una vez arreglado lo de arriba -->         
                                
                                <div class="form-group">
                                	Teléfono:
                                	<input type="text" class="form-control" name="telefonos[]" value="${editorial.telefonos[0]}">
                                </div>
       
                        <!--  Fin borrar -->            
                                <p class="buttonGroup">
                            <button type="submit" class="btn btn-success">Aceptar</button>
                            <a href="/proyectoBiblioteca/Editoriales" class="btn btn-danger">Cancelar</a>
                                </p>
                                
                        </form> 

      </div>       
        
  </div>

<!-- Incluyo el footer -->
  <jsp:include page="resources/footer.jsp" />
        <script src="resources/editorialUtils.js"></script>
        <script type="text/javascript">
			function sacarclase(id){
								document.getElementById(id).className = "";
								}
			var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1");
			var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2", "email", {validateOn:["change"]});
			var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3");
			var sprytextfield4 = new Spry.Widget.ValidationTextField("sprytextfield4");
        </script>

</body>
</html>