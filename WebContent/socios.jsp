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

    <title>Menú Principal - Biblioteca</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/jumbotron.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="resources/bootstrap/assets/js/html5shiv.js"></script>
      <script src="resources/bootstrap/assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
  
  <!-- Veo si está logueado, si lo está entonces lo redirijo al menú principal -->   
   <c:if test="${!empty use}">  
   	<jsp:forward page="/menu.jsp" />
   </c:if>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Proyecto Biblioteca IDS</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          
          <ul class="nav navbar-nav navbar-right">
          	<li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.usuario}<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Perfil</a></li>
                <li><a href="#">Otra Cosa</a></li>
                <li class="divider"></li>
				<li><a href="Login?action=terminate">Cerrar Sesión</a></li>
              </ul>
            </li>
          </ul>
          
        </div><!--/.navbar-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Administración de Socios</h1>
        
        <!--  Tabla de socios -->
        
		<div class="panel panel-primary">
	  		<!-- Default panel contents -->
	  		<div class="panel-heading">Socios Registrados</div>
	
		    <table class="table">
		    
		    	<tr>
		    		<th>Nombre</th>
		    		<th>Apellido</th>
		    		<th>Email</th>
		    		<th>Teléfono</th>
		    		<th>Rango</th>
		    		<th>Dirección</th>
		    		<th colspan=2>Acción</th>
		    	</tr>
		    	
		    	<c:forEach var="socio" items="${socios}">
			    	<tr>
			    		<td><c:out value="${socio.nombre}" /></td>
			    		<td><c:out value="${socio.apellido}" /></td>
			    		<td><c:out value="${socio.email}" /></td>
			    		<td><c:out value="${socio.telefono}" /></td>
			    		<td><c:out value="${socio.rango}" /></td>
			    		<td><c:out value="${socio.direccion}" /></td>
			    		<td><a href="Socios?action=edit&id=<c:out value="${socio.id}"/>">Modificar</a></td>
		                <td><a href="Socios?action=delete&id=<c:out value="${socio.id}"/>">Eliminar</a></td>
			    	</tr>
		    	</c:forEach>
		    
		    </table>
		</div>
		
		<p><a href="Socios?action=new" class="btn btn-primary btn-lg">Registrar Nuevo Socio &raquo;</a></p>
        
      </div>
    </div>
	
	
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="resources/bootstrap/assets/js/jquery.js"></script>
    <script src="resources/bootstrap/dist/js/bootstrap.min.js"></script>
  </body>
</html>
