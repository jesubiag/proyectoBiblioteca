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
				  
				  <!--  hacer esto con js para poder cambiar las opciones del select al cambiar una provincia -->
				  
				  <div class="form-group">
					  Provincia: 
					  <select name="provincia" class="form-control">
					  	<c:forEach var="provincia" items="${provincias}">
					  		<option value="${provincia.nombre}">${provincia.nombre}</option>
					  	</c:forEach>
					  </select>
				  </div>
				  
				  <div class="form-group">
			     	 Localidad: 
			     	 <input type="text" class="form-control" name="localidad" value="${socio.direccion.localidad.nombre}">
			      </div>

				  <div class="form-group">
			     	 Calle: 
			     	 <input type="text" class="form-control" name="calle" value="${socio.direccion.calle}">
			      </div>
			      
			      <div class="form-group">
			     	 Número: 
			     	 <input type="text" class="form-control" name="numero" value="${socio.direccion.numero}">
			      </div>		  				  
				  
				  <div class="form-group">
			     	 Código Postal: 
			     	 <input type="text" class="form-control" name="codigoPostal" value="${socio.direccion.codigoPostal}">
			      </div>
			      
			      <div class="form-group">
			     	 Piso: 
			     	 <input type="text" class="form-control" name="piso" value="${socio.direccion.piso}">
			      </div>
			      
			      <div class="form-group">
			     	 Departamento: 
			     	 <input type="text" class="form-control" name="departamento" value="${socio.direccion.departamento}">
			      </div>  				  		  
				  
				  <p class="buttonGroup">
			    	<button type="submit" class="btn btn-success">Aceptar</button>
			    	<a href="/proyectoBiblioteca/Socios" class="btn btn-danger">Cancelar</a>
			      </p>
			</form> 

		</div>       
	
	</div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />

  </body>
</html>
