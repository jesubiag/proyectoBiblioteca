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

    <!-- CSS -->
    <link href="resources/SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
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
    <section class="jumbotron">
      <div class="container">
        <h1>Bienvenido, ${user.usuario}!</h1>
      </div>
    </section>
    
    <section id="main">
		      
    	<section id="menuItems"> <!-- Menu Items -->
			<div class="row">
			
			    <a href="Libros">
        		<div class="menuItem">
		          <img src="resources/images/book.png" alt="Imagen Libros">
		          <h3>Libros y Ejemplares</h3>
	        	</div>
	        	</a>	
	        	
	        	<a href="Socios">
        		<div class="menuItem">
		          <img src="resources/images/user.png" alt="Imagen Socios">
		          <h3>Socios</h3>
	        	</div>
	        	</a>
	        	
	        	<a href="Autores">
        		<div class="menuItem">
		          <img src="resources/images/edit.png" alt="Imagen Autores">
		          <h3>Autores</h3>
	        	</div>
	        	</a>	        	

	        	<a href="Editoriales">
        		<div class="menuItem">
		          <img src="resources/images/books.png" alt="Imagen Editoriales">
		          <h3>Editoriales</h3>
	        	</div>
	        	</a>
	        	
	        	<a href="Localidades">
        		<div class="menuItem">
		          <img src="resources/images/globe.png" alt="Imagen Localidades">
		          <h3>Localidades</h3>
	        	</div>
	        	</a>	        	

	        	<a href="DiasNoHabiles">
        		<div class="menuItem">
		          <img src="resources/images/calendar.png" alt="Imagen Días No Hábiles">
		          <h3>Días No Hábiles</h3>
	        	</div>
	        	</a>	
	        		        		     
	        </div>   

	    
	    
    	</section> <!-- End of Menu Items -->
    
    	<section>
		
			<h2>Búsqueda de Libro:</h2>
		
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
	            <button type="submit" class="btn btn-default searchButton">Buscar</button>
                <br>
                <span class="textfieldRequiredMsg"> Se necesita un valor.</span>
	            <span class="textfieldMinCharsMsg"> No se cumple el mínimo de caracteres requerido (3).</span>
	            </span>
	      	
	      	</form>
	      	<!-- fin barra de búsqueda -->
		
		</section>
    
    </section>

<!-- Incluyo el footer -->
	<jsp:include page="resources/footer.jsp" />
	
	<!-- Validaciones Javascript-->
	<script src="resources/SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
	<script type="text/javascript">
		function sacarclase(id){
			document.getElementById(id).className = "";
			}
    	var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {minChars:3, validateOn:["change"]});
    </script>
  </body>
</html>
