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

    <title>Administración de Localidades - Biblioteca</title>

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
        <c:if test="${!empty mensajeAccion}">
                <div class="alert alert-info">${mensajeAccion}</div>
                <c:remove var="mensajeAccion" scope="session" />
        </c:if>

        <h1>Administración de Localidades</h1>
        
		<p class="buttonGroup">
			<a href="Localidades?action=new" class="btn btn-success btn-lg">Registrar Localidad  <span class="glyphicon glyphicon-plus"></span></a>
			<a href="menu.jsp" class="btn btn-warning btn-lg">Volver al Menú  <span class="glyphicon glyphicon-backward"></span></a>
		</p>	        
        
        <!--  Tabla -->
        
		<div class="panel panel-primary">
	  		<!-- Default panel contents -->
	  		<div class="panel-heading">Localidades Registradas</div>	
			
			<div class="table-responsive">
			    <table class="table">
			    
			    	<tr>
			    		<th>Nombre</th>
			    		<th>Provincia</th>
			    		<th>Fecha Alta</th>
			    		<th colspan=2 >Acción</th>
			    	</tr>
			    	
			    	<c:forEach var="localidad" items="${localidades}">
				    	<tr>
				    		<td><c:out value="${localidad.nombre}" /></td>
				    		<td><c:out value="${localidad.provincia.nombre}" /></td>
				    		<td><c:out value="${localidad.stringFechaAlta}" /></td>
				    		<td><a href=${"Localidades?action=edit&id="}${localidad.id}><span class="glyphicon glyphicon-pencil"></span></a></td>
				    		<td><a href=${"Localidades?action=delete&id="}${localidad.id} onclick="return confirm('¿Está seguro de que desea dar de baja la localidad?')"><span class="glyphicon glyphicon-remove"></span></a></td>
				    	</tr>
			    	</c:forEach>
			    
			    </table>
			</div>
			
		</div>
       
      </div>
    </div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />

  </body>
</html>
