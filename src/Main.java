import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.dao.UsuarioDAO;
import org.proyectoBiblioteca.domain.Direccion;
import org.proyectoBiblioteca.domain.Localidad;
import org.proyectoBiblioteca.domain.Provincia;
import org.proyectoBiblioteca.domain.Socio;
import org.proyectoBiblioteca.domain.Usuario;
import org.proyectoBiblioteca.enums.TipoUsuario;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Localidad localidad = new Localidad(new Provincia("Buenos Aires"),"Campana");
		
		Direccion direccion = new Direccion("Necochea","2804",690,localidad);
		
		Socio socio = new Socio(36493716, "Lucas", "Couchot", "lucas.couchot@gmail.com","3489652714",direccion);
		
		SocioDAO.create(socio);
		
		socio.setNombre("Lucas J.");
		
		SocioDAO.update(socio);
		
		Usuario usuario = new Usuario("Juan","Perez","clave","administrador",TipoUsuario.administrador);
		
		UsuarioDAO.create(usuario);
		
	}

}
