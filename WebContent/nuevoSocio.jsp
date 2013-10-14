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

    <title>Administración de Socios - Biblioteca</title>

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
   	<jsp:forward page="/" />
   </c:if>
   
<!-- Incluyo el header -->
	<jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    	<div class="container">
        
	        <h1>Alta/Modificación de Socio</h1>
	        
			<form action="Socios" method="post">
				 
			      <input type="hidden" class="form-control" name="id" value="${socio.id}">
      
			      <div class="form-group">
			      Nombre: 
			      <input type="text" class="form-control" name="nombre" value="${socio.nombre}">
			      </div>
			      
			      <div class="form-group">
				      Apellido: 
				      <input type="text" class="form-control" name="apellido" value="${socio.apellido}">
			      </div>
			      
			      <div class="form-group">
				      DNI: 
				      <input type="text" class="form-control" name="dni" value="${socio.dni}">
			      </div>
			      
			      <div class="form-group">
				      Email: 
				      <input type="text" class="form-control" name="email" value="${socio.email}">
			      </div>
			      
			      <div class="form-group">
			     	 Teléfono: 
			     	 <input type="text" class="form-control" name="telefono" value="${socio.telefono}">
			      </div>
			      
			      <div class="form-group">
				      Rango: 
				      <input type="text" class="form-control" name="rango" value="${socio.rango}">
				  </div>		
			      
			      <div class="form-group">
					  Estado: 
					  <select name="estado" class="form-control">
					  	<c:forEach var="estado" items="${estados}">
					  		<option value="${estado}">${estado}</option>
					  	</c:forEach>
					  </select>
				  </div>
			    <button type="submit" class="btn btn-success">Aceptar</button>
			    <a href="/proyectoBiblioteca/Socios" class="btn btn-danger">Cancelar</a>
			</form> 

		</div>       
	
	</div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />

  </body>
</html>
