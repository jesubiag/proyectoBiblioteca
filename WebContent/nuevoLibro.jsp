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
        
	        <h1>Alta/Modificación de Libro</h1>
	        
			<form class="inputForm" action="Libros" method="post">
 
 				  <div class="form-group">
			      	Id: 
			      	<input type="text" readonly=readonly class="form-control" name="id" value="${libro.id}">
			      </div>
			      
			      <div class="form-group">
			      	Título: 
			      	<input type="text" class="form-control" name="titulo" value="${libro.titulo}">
			      </div>
			      
			      <div class="form-group">
					  Editorial: 
					  <select name="editorial" class="form-control">
					  	<c:forEach var="editorial" items="${editoriales}">
					  		<option value="${editorial.id}">${editorial.nombre}</option>
					  	</c:forEach>
					  </select>
				  </div>
			      
			      <div class="form-group">
					  Autor: 
					  <select name="autores" class="form-control">
					  	<c:forEach var="autor" items="${autores}">
					  		<option value="${autor.id}">${autor.nombre}</option>
					  	</c:forEach>
					  </select>
				  </div>
			      
			      <div class="form-group">
				  	ISBN: 
				  	<input type="text" class="form-control" name="isbn" value="${libro.isbn}">
			      </div>
			      
			      <div class="form-group">
			      	Etiquetas: 
			      	<input type="text" class="form-control" name="etiquetas" value="${libro.etiquetas}">
			      </div>

				  <div class="form-group">
			      	Rango: 
			      	<input type="text" class="form-control" name="rango" value="${libro.rango}">
			      </div>

				  <div class="form-group">
			      	País de Origen: 
			      	<input type="text" class="form-control" name="paisOrigen" value="${libro.paisOrigen}">
			      </div>  	  
				  
				  <p class="buttonGroup">
			    	<button type="submit" class="btn btn-success">Aceptar</button>
			    	<a href="/proyectoBiblioteca/Libros" class="btn btn-danger">Cancelar</a>
			      </p>
			</form> 

		</div>       
	
	</div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />

  </body>
</html>
