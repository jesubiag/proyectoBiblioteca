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

    <title>Página Principal - Biblioteca</title>

    <!-- CSS -->
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
  
  <!-- Veo si está logueado, si lo está entonces lo redirijo al menú principal -->   
   <c:if test="${!empty user}">  
   	<jsp:forward page="/menu.jsp" />
   </c:if>

<!-- Incluyo el header -->
	<jsp:include page="resources/header.jsp" />

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
    
    	<c:if test="${!empty loginError}">

			<!--  alerta de error en login -->
			<div class="alert alert-danger">${loginError}</div>
	
		</c:if>
    
      <div class="container">
        <h1>Bienvenido a la biblioteca!</h1>
      </div>
      
      <div class="container">
      	<!-- barra de búsqueda -->
      	<form action="Busqueda" method="get">
				<select name=type class="form-control searchSelect">
					<option value="libro">Libro</option>
					<!--  <option value="autor">Autor</option>
					<option value="temas">Temas</option> -->
					<option value="editorial">Editorial</option>
					<option value="isbn">ISBN</option>
				</select>
      		<span id="sprytextfield1">
            <input type="text" class="form-control searchField" name="search" placeholder="Buscar"  onblur="sacarclase('sprytextfield1')">
            <span class="textfieldRequiredMsg">Se necesita un valor.</span><span class="textfieldMinCharsMsg"> No se cumple el mínimo de caracteres requerido (3).</span></span>
			<button type="submit" class="btn btn-default searchButton">Buscar</button>
      	</form>
      	<!-- fin barra de búsqueda -->
      </div>
      
    </div> <!-- jumbotron end -->

	<section id="main">
    
    <!-- Esto hay que automatizarlo para traer x cantidad de libros recientes-->
	
    </section>
	
	
<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
    <!-- Validaciones Javascript-->
    <script src="resources/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
	<script src="resources/SpryAssets/SpryValidationPassword.js" type="text/javascript"></script>
    <script src="resources/SpryAssets/SpryValidationConfirm.js" type="text/javascript"></script>
	<script type="text/javascript">
		function sacarclase(id){
			document.getElementById(id).className = "";
			}
    	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {minChars:3, validateOn:["change"]});
    </script>
</body>
</html>
