<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="resources/bootstrap/assets/ico/favicon.png">

    <title>Préstamos Pendientes</title>

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
			  <li><a href="/proyectoBiblioteca">Inicio</a></li>
			  <li class="active">Préstamos Pendientes</li>
			</ol>
	      
	        <h1>Préstamos</h1>
	      
			<section id="main">
		    
		    	<c:if test="${0 != (f:length(prestamos))}"> 
		    
					<div class="panel panel-primary">
				  		<!-- Default panel contents -->
				  		<div class="panel-heading">Listado de Préstamos Pendientes</div>	
						
						<div class="table-responsive">
			
								<!-- Tabla Principal (Libros) -->
								<table class="table table-condensed">
								    <thead>
								        <tr>
								            <th>Fecha Acordada</th>
								            <th>Fecha de Préstamo</th>
								            <th>Estado</th>
								            <th>Nro. de Socio</th>      
								            <th>Nro. de Ejemplar</th>
								            <th>Tipo</th>
								            <th>Acción</th>      
								        </tr>
								    </thead>
								    <tbody>
								        <!-- Fila de la tabla principal (Datos de un libro) -->

								        <c:forEach var="prestamo" items="${prestamos}"> <!-- For principal -->

								            <tr class="${prestamo.estado}">
								                
								                <td>${prestamo.stringFechaAcordada}</td> 
								                <td>${prestamo.stringFechaPrestamo}</td> 
								                <td>${prestamo.estado}</td>  
								                <td>${prestamo.socio.id} (${prestamo.socio.resumenSocio})</td> 
								                <td>${prestamo.ejemplar.id} (Del libro <a href=${"Busqueda?type=detalleLibro&id="}${prestamo.ejemplar.libro.id}>${prestamo.ejemplar.libro.titulo})</a></td>
								                <td>
								                	<c:if test="${prestamo.prestamoLocal}"> 
														Local
													</c:if>
													<c:if test="${!prestamo.prestamoLocal}"> 
														Domiciliario
													</c:if>
								                </td>
								                <td><a href=${"Prestamos?action=return&id="}${prestamo.ejemplar.id}><span title="Devolver" class="glyphicon glyphicon-log-in"></span></a></td>							                

								            </tr>
								        
								        </c:forEach> <!-- Fin del for principal -->
								
								    </tbody>
								</table> <!-- Fin tabla principal -->
			
						</div>
						
					</div>	
				
				</c:if>
				
				<c:if test="${0 == (f:length(prestamos))}"> 
					<div class="alert alert-info">No hay préstamos pendientes.</div>
				</c:if>
				
				<p class="buttonGroup">
		    		<a href="javascript:history.back()" class="btn btn-warning btn-lg">Volver <span class="glyphicon glyphicon-backward"></span></a>
				</p>
				
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
