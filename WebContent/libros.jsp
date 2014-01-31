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
  
  <!-- Veo si no está logueado, lo mando al login en caso de que no -->   
   <c:if test="${empty user}">  
           <jsp:forward page="/" />
   </c:if>
   
<!-- Incluyo el header -->
        <jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        
        <!-- Si hay un mensaje lo muestro, luego lo borro -->
                <c:if test="${!empty mensajeLibro}">
                        <div class="alert alert-info">${mensajeLibro}</div>
                        <c:remove var="mensajeLibro" scope="session" />
                </c:if>
        
        <h1>Administración de Libros y Ejemplares</h1>
        
                <p class="buttonGroup">
                        <a href="Libros?action=new" class="btn btn-success btn-lg">Registrar Libro  <span class="glyphicon glyphicon-plus"></span></a>
                        <a href="Ejemplares?action=new" class="btn btn-success btn-lg">Registrar Ejemplar  <span class="glyphicon glyphicon-plus"></span></a>
                        <a href="menu.jsp" class="btn btn-warning btn-lg">Volver al Menú  <span class="glyphicon glyphicon-backward"></span></a>
                </p>                

                <div class="panel panel-primary">
                          <!-- Default panel contents -->
                          <div class="panel-heading">Libros y Ejemplares Registrados - Click en nombre de Libro para ocultar Ejemplares</div>        
                        
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
                                                <c:forEach var="libro" items="${libros}"> <!-- For principal -->

                                                    <tr  class="clickable">
                                                        <td  colspan="2"><a href=${"Busqueda?type=detalleLibro&id="}${libro.id}>${libro.titulo}</a></td>
                                                        <td><c:out value="${libro.editorial.nombre}" /></td>
                                                        <td><c:out value="${libro.listaAutores}" /></td>
                                                        <td><c:out value="${libro.isbn}" /></td>
                                                        <td><c:out value="${libro.listaEtiquetas}" /></td>
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
                                                
                                                                                <c:if test="${0 != (f:length(libro.ejemplares))}">  
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
                                                                                <th>Datos Préstamo</th>
                                                                                <th colspan=3>Acción</th>
                                                                            </tr>
                                                                    </thead>
                                                                    <tbody>
                                        
                                                                        <c:forEach var="ejemplar" items="${libro.ejemplares}"> <!-- For secundario -->
                                        
                                                                        <tr>
                                                                            <td>${ejemplar.numeroEjemplar}</td>
                                                                            <td>${ejemplar.anio}</td>
                                                                            <td>${ejemplar.estado}</td>
                                                                            <td>${ejemplar.estante}</td>
                                                                            <td>${ejemplar.mueble}</td>
                                                                            <td>${ejemplar.pasillo}</td>
                                                                            <td>${ejemplar.precioDolares}</td>
                                                                            <td>${ejemplar.stringFechaAlta}</td>
                                                                            <td>${ejemplar.datosPrestamo}</td>
                                                                            <td><a href=${"Ejemplares?action=edit&id="}${ejemplar.id}><span class="glyphicon glyphicon-pencil"></span></a></td>
                                                                            <td><a href=${"Ejemplares?action=delete&id="}${ejemplar.id}><span class="glyphicon glyphicon-remove"></span></a></td>   
                                                                            
                                                                            <c:if test="${ejemplar.estado == 'disponible'}">
                                                                            	<td><a href=${"Prestamos?action=new&id="}${ejemplar.id}><span class="glyphicon glyphicon-log-out"></span></a></td>
                                                                            </c:if>
                                                                                                
                                                                            <c:if test="${ejemplar.estado == 'prestado'}">
                                                                            	<td><a href=${"Prestamos?action=return&id="}${ejemplar.id}><span class="glyphicon glyphicon-log-in"></span></a></td>
                                                                            </c:if>
                                                                        </tr>
                                        
                                                                        </c:forEach> <!-- Fin del for secundario -->
                                        
                                                                    </tbody>
                                                                </table>

                                                                </c:if> 
                                                               
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
		<script src="resources/hide.js" type="text/javascript"></script>
  </body>
</html>