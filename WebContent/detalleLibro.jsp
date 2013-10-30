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

    <title>Detalle de "${libro.titulo}" por ${libro.listaAutores}</title>

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
  

   
<!-- Incluyo el header -->
	<jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Página de detalle de "${libro.titulo}" </h1>
    
    	<ul>
    		<li>ISBN: ${libro.isbn}</li>
    		<li>Autores: ${libro.listaAutores}</li> <!-- Hacer un foreach para linkear a los libros de cada autor individualmente -->
    		<li>Editorial: <a href=${"/Busqueda?type=editorial&search="}${libro.editorial.nombre}>${libro.editorial.nombre}</a></li>
    		<li>País de Orgine: ${libro.paisOrigen}</li>
    		<li>Etiquetas/Temas: ${libro.etiquetas}</li>
    		<li>Rango: ${libro.rango}</li>
    	</ul>
    	        
        
        <!--  Tabla de ejemplares -->
        
		<div class="panel panel-primary">
	  		<!-- Default panel contents -->
	  		<div class="panel-heading">Ejemplares Registrados</div>	
			
			<div class="table-responsive">       			
	            <table class="table">
	                <thead>
	                	<tr>
	                     <th>Ejemplar Nro.</th>
	                     <th>Año de Pub.</th>
	                     <th>Estado</th>
	                     <th>Estante</th>
	                     <th>Mueble</th>
	                     <th>Pasillo</th>
	                	</tr>
	                </thead>
	                <tbody>
	
	                    <c:forEach var="ejemplar" items="${libro.ejemplares}"> <!-- For secundario -->
	
	                    <tr>
	                        <td><c:out value="${ejemplar.numeroEjemplar}" /></td>
	                        <td><c:out value="${ejemplar.anio}" /></td>
	                        <td><c:out value="${ejemplar.estado}" /></td>
	                        <td><c:out value="${ejemplar.estante}" /></td>
	                        <td><c:out value="${ejemplar.mueble}" /></td>
	                        <td><c:out value="${ejemplar.pasillo}" /></td>
	                        <!--  hacer un if para poner botones de prestar o devolver en caso de ser bibliotecario y de resevar en caso de no estar logueado -->
	                    </tr>
	
	                    </c:forEach>
	
	                </tbody>
	            </table>

			</div>
			
		</div>
       
      </div>
    </div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />

  </body>
</html>
