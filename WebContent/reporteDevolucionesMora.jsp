<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<html>

<head>
	<meta charset="utf-8">
	<title>Reporte de Devoluciones en Mora</title>
	
	<!-- CSS -->
	<link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
	<link href="resources/styles.css" rel="stylesheet">
	
</head>

<body>
	<h1>Reporte de Devoluciones en Mora</h1>
	<p><strong>Fecha:</strong> ${fecha}</p>
	<p><strong>Usuario:</strong> ${user.nombre} ${user.apellido}</p>
		
	<c:if test="${0 != (f:length(prestamos))}"> 
		    
		<div class="panel">

					<table class="table table-condensed">
					    <thead>
					        <tr>
					            <th>Fecha Acordada</th>
					            <th>Fecha de Préstamo</th>        
					            <th>Nro. de Socio</th>      
					            <th>Nro. de Ejemplar</th>     
					        </tr>
					    </thead>
					    <tbody>

					        <c:forEach var="prestamo" items="${prestamos}"> <!-- For principal -->

					            <tr>
					                
					                <td>${prestamo.stringFechaAcordada}</td> 
					                <td>${prestamo.stringFechaPrestamo}</td> 
					                <td>${prestamo.socio.id} (${prestamo.socio.resumenSocio})</td> 
					                <td>${prestamo.ejemplar.id} (Del libro ${prestamo.ejemplar.libro.titulo})</td>							                

					            </tr>
					        
					        </c:forEach>
					
					    </tbody>
					</table> 

			
		</div>	
				
	</c:if>
	
	<c:if test="${0 == (f:length(prestamos))}"> 
		<div class="alert alert-info">No hay devoluciones en mora.</div>
	</c:if>
	
</body>

</html>

