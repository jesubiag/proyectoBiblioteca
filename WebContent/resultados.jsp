<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="resources/bootstrap/assets/ico/favicon.png">

    <title>Resultados para la Búsqueda: ${search}</title>

    <!-- CSS -->
    <script src="resources/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
	<script src="resources/SpryAssets/SpryValidationPassword.js" type="text/javascript"></script>
    <script src="resources/SpryAssets/SpryValidationConfirm.js" type="text/javascript"></script>
    <link href="resources/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
    <link href="resources/SpryAssets/SpryValidationPassword.css" rel="stylesheet" type="text/css" />
    <link href="resources/SpryAssets/SpryValidationConfirm.css" rel="stylesheet" type="text/css" />
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
	      
	      	<ol class="breadcrumb">
			  <li><a href="#">Inicio</a></li>
			  <li class="active">Búsqueda</li>
			</ol>
	      
	        <h1>Resultados para la Búsqueda: ${search}</h1>
	      
			<section id="main">
		    
				<div class="panel panel-primary">
			  		<!-- Default panel contents -->
			  		<div class="panel-heading">Libros y Ejemplares Registrados</div>	
					
					<div class="table-responsive">
		
							<!-- Tabla Principal (Libros) -->
							<table class="table table-condensed">
							    <thead>
							        <tr>
							            <th>Título de Libro</th>
							            <th>Editorial</th>
							            <th>Autores</th>
							            <th>ISBN</th>
							            <th>Etiquetas</th>
							            <th>Rango</th>
							            <th>País de Origen</th>
							            <th>Fecha Alta</th>
							            <th colspan=2>Acción</th>
							        </tr>
							    </thead>
							    <tbody>
							        <!-- Fila de la tabla principal (Datos de un libro) -->
							            <c:set var="count" value="0" scope="page" /> <!--defino var para contar -->
							
							        <c:forEach var="libro" items="${resultados}"> <!-- For principal -->
							
							            <c:set var="count" value="${count + 1}" scope="page"/> <!--aumento en 1 el contador -->
							            <tr>
							                <td><c:out value="${libro.titulo}" /></td>
							                <td><c:out value="${libro.editorial.nombre}" /></td>
							                <td><c:out value="${libro.listaAutores}" /></td>
							                <td><c:out value="${libro.isbn}" /></td>
							                <td><c:out value="${libro.etiquetas}" /></td>
							                <td><c:out value="${libro.rango}" /></td>
							                <td><c:out value="${libro.paisOrigen}" /></td>
							                <td><c:out value="${libro.stringFechaAlta}" /></td>
							                
							                <c:if test="${!empty user}"><!-- Si está logueado entonces habilito opciones para prestar y devolver -->  
							                	<td><a href=${"Libros?action=edit&id="}${libro.id}><span class="glyphicon glyphicon-log-out"></span></a></td>
							                	<td><a href=${"Libros?action=edit&id="}${libro.id}><span class="glyphicon glyphicon-log-in"></span></a></td>
							                </c:if>
							                
							                <c:if test="${empty user}"><!-- Si no está logueado entonces habilito opción para reservar -->  
							                	<td><a href=${"Libros?action=edit&id="}${libro.id}><span class="glyphicon glyphicon-pushpin"></span></a></td>
							                </c:if>
							                
							            </tr>
							        
							        </c:forEach> <!-- Fin del for principal -->
							
							    </tbody>
							</table> <!-- Fin tabla principal -->
		
					</div>
					
				</div>	
			
		    </section>

		</div>

    </div> <!-- jumbotron end -->

	
	
<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
    <!-- Validaciones Javascript-->
	<script type="text/javascript">
		function sacarclase(id){
			document.getElementById(id).className = "";
			}
    	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {minChars:3, validateOn:["change"]});
    </script>
</body>
</html>
