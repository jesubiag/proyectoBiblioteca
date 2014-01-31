<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
 
<html>

<head>
	<meta charset="utf-8">
	<title>Reporte de Retiros Mes a Mes</title>
	
	<!-- CSS -->
	<link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
	<link href="resources/styles.css" rel="stylesheet">
</head>

<body>

	<section id="main">
	
		<h1>Reporte de Retiros Mes a Mes</h1>
		<p><strong>Fecha:</strong> ${fecha}</p>
		<p><strong>Hora:</strong> ${hora}</p>
		<p><strong>Usuario:</strong> ${user.nombre} ${user.apellido}</p>
		
		<c:if test="${0 != (f:length(datos))}">
		
			<ul>
				<c:forEach var="dato" items="${datos}">
				
					<li><strong>${dato.key}:</strong> ${dato.value} retiro/s.</li>
					
				</c:forEach>
				
			</ul>
			
		</c:if>
		
		<c:if test="${0 == (f:length(datos))}"> 
			<div class="alert alert-info">No hay retiros registrados.</div>
		</c:if>
	
	</section>
	
</body>

</html>

