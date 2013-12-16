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

    <title>Creación de Préstamo - Biblioteca</title>

    <!-- CSS -->
    <link href="resources/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="resources/jumbotron.css" rel="stylesheet">
    <link href="resources/styles.css" rel="stylesheet">
    <link href="resources/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
    <link href="resources/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
  <link href="resources/SpryAssets/SpryValidationSelect.css" rel="stylesheet" type="text/css">
    <!-- JQueryUI css -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

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
        
        	<ol class="breadcrumb">
		  		<li><a href="/proyectoBiblioteca">Inicio</a></li>
		  		<li class="active">Creación de Préstamo</li>
			</ol>
        
	        <h1>Nuevo Préstamo</h1>
	        
			<form class="inputForm" action="Prestamos" method="post">
 
				<div class="form-group">
					<input type="hidden" readonly=readonly class="form-control" name="id" value="${ejemplar.id}">
				</div>
				
				<div class="form-group">
					<strong>Ejemplar:</strong> ${ejemplar.libro.titulo}(código: ${ejemplar.numeroEjemplar})
			    </div>
				
				<!-- Meter más datos del ejemplar para identificarlo más fácil -->
				
				<!-- Falta combo socios y combo tipo de préstamo -->
	  		  
	  		  	<div class="form-group">
					<strong>Préstamo Local:</strong> 
					<span id="spryselect1">
					    <select name="prestamoLocal" class="form-control" onBlur="sacarclase('spryselect1');">					   
					        <option value="true">Si</option>
							<option value="false" selected="selected">No</option>
				        </select>
			    		<span class="selectRequiredMsg">Seleccione una opción.</span>
			    	</span>
			    </div>
			    
			    <div class="form-group">
					<strong>Número de Socio</strong> (receptor del préstamo):
					<input type="text" class="form-control" name="idSocio">
			    </div>
	  		  
	  		  	<div class="form-group">
					<strong>Fecha de Devolución</strong> (Estimada):
					<span id="sprytextfield1">
                    	<input id=fecha type="text" class="form-control" name="fecha" onBlur="sacarclase('sprytextfield1');">
                		<span class="textfieldRequiredMsg">Se necesita una fecha.</span>
                		<span class="textfieldInvalidFormatMsg">Formato no válido. Intente AAAA-MM-DD</span>
                	</span>
                
                </div>
	  		  
				<p class="buttonGroup">
			    <button type="submit" class="btn btn-success">Aceptar</button>
			    <a href="javascript:history.back()" class="btn btn-danger">Cancelar</a>
				</p>
			</form> 

		</div>       
	
	</div>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script src="resources/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
	<script src="resources/SpryAssets/SpryValidationSelect.js" type="text/javascript"></script>
	<script>
	function sacarclase(id){
			document.getElementById(id).className = "";
			}
	$(function() {
			$( "#fecha" ).datepicker({ dateFormat: "yy-mm-dd" });
			});
	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "date", {format:"yyyy-mm-dd", validateOn:["change"]});
	var spryselect1 = new Spry.Widget.ValidationSelect("spryselect1");
	</script>	
    
  </body>
</html>
