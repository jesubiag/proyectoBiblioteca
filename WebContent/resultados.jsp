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

    <title>Administración de Libros y Ejemplares - Biblioteca</title>

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
        <h1>Administración de Libros y Ejemplares</h1>
        
		<p class="buttonGroup">
			<a href="Libros?action=new" class="btn btn-success btn-lg">Registrar Libro  <span class="glyphicon glyphicon-plus"></span></a>
			<a href="Ejemplares?action=new" class="btn btn-success btn-lg">Registrar Ejemplar  <span class="glyphicon glyphicon-plus"></span></a>
			<a href="menu.jsp" class="btn btn-warning btn-lg">Volver al Menú  <span class="glyphicon glyphicon-backward"></span></a>
		</p>	        
        
        <!--  Tabla de socios -->
        
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
					                <td><a href=${"Libros?action=edit&id="}${libro.id}><span class="glyphicon glyphicon-pencil"></span></a></td>
				    				<td><a href=${"Libros?action=delete&id="}${libro.id}><span class="glyphicon glyphicon-remove"></span></a></td>
					            </tr>
					            <!-- Fila que colapsa de la tabla principal (Datos de los ejemplares del libro anterior) -->
					
					            <tr>
					                <td colspan="10" class="hiddenRow">
					              
					                        <!-- Tabla de ejemplares -->
					
					                        <table class="subTable">
					                            <thead>
					                            	<tr>
						                                <th>Ejemplar Nro.</th>
						                                <th>Año de Pub.</th>
						                                <th>Estado</th>
						                                <th>Estante</th>
						                                <th>Mueble</th>
						                                <th>Pasillo</th>
						                                <th>Precio (U$S)</th>
						                                <th>Fecha Alta</th>
						                                <th colspan=2>Acción</th>
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
					                                    <td><c:out value="${ejemplar.precioDolares}" /></td>
					                                    <td><c:out value="${ejemplar.stringFechaAlta}" /></td>
					                                	<td><a href=${"Ejemplares?action=edit&id="}${ejemplar.id}><span class="glyphicon glyphicon-pencil"></span></a></td>
				    									<td><a href=${"Ejemplares?action=delete&id="}${ejemplar.id}><span class="glyphicon glyphicon-remove"></span></a></td>
					                                </tr>
					
					                                </c:forEach> <!-- Fin del for secundario -->
					
					                            </tbody>
					                        </table>
					                </td>
					            </tr>
					        
					        </c:forEach> <!-- Fin del for principal -->
					
					    </tbody>
					</table> <!-- Fin tabla principal -->

			</div>
			
		</div>
       
      </div>
    </div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />

  </body>
</html>