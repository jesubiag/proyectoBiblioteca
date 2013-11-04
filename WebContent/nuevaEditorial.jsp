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
        
	        <h1>Alta/Modificación de Editorial</h1>
	        
			<form class="inputForm" action="Editoriales" method="post">
 
				<div class="form-group">
					Id: 
					<input type="text" readonly=readonly class="form-control" name="id" value="${editorial.id}">
				</div>
				
				<div class="form-group">
					Nombre: 
					<input type="text" class="form-control" name="nombre" value="${editorial.nombre}">
				</div>
				
				<div class="form-group">
					Email: 
					<input type="text" class="form-control" name="email" value="${editorial.email}">
				</div>
				
				<div class="form-group">
			     	 Teléfonos: 
			     	 
			     	 <ul id="telefonos" class="stylelessList">
				     	 <c:forEach var="telefono" items="${editorial.telefonos}">
				     	 	<li><input type="text" class="form-control" name="telefonos[]" value="${telefono}"></li>
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
				     	 	<li><input type="text" class="form-control" name="direcciones[]" value="${direccion}"></li>
				     	 </c:forEach>
			     	 </ul>
			     	 <p class="buttonGroup"><a href='javascript:addDirField()' class="btn btn-default">Agregar Dirección</a></p>
			      	 <p>(Próximamente)</p>
			      </div>
				
				<!-- Falta Armar lo de Direcciones y Teléfonos -->	  		  
				
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

  </body>
</html>
