<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/proyectoBiblioteca">Proyecto Biblioteca IDS</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/proyectoBiblioteca">Inicio</a></li>
            <li><a href="#about">Sobre la Biblioteca</a></li>
            <li><a href="#contact">Contactar</a></li>
          </ul>
          
          <c:choose>
          
          	<c:when test="${empty user}">
	          <form class="navbar-form navbar-right" action="Login" method="post">
	            <div class="form-group">
	              <input type="text" placeholder="Usuario" class="form-control" name="user">
	            </div>
	            <div class="form-group">
	              <input type="password" placeholder="Contraseña" class="form-control" name="password">
	            </div>
	            <button type="submit" class="btn btn-primary">Ingresar</button>
	          </form>
          	</c:when>
          	
          	<c:otherwise>
				<ul class="nav navbar-nav navbar-right">
		        	<li class="dropdown">
		        		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ${user.usuario}<b class="caret"></b></a>
		        		<ul class="dropdown-menu">
			              	<li><a href="#profile">Perfil</a></li>
			              	<li class="divider"></li>
							<li><a href="Login?action=terminate">Cerrar Sesión</a></li>
		            	</ul>
		          	</li>
				</ul>          		
          	</c:otherwise>
          </c:choose>
          
        </div><!--/.navbar-collapse -->
      </div>
    </div>