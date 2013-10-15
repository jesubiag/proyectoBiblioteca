package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.utils.Utilidades;

@Entity
@NamedQueries({
	@NamedQuery(name = "Editorial.findAll", query = "Select e From Editorial e"),
	@NamedQuery(name = "Editorial.findAllActive", query = "Select e From Editorial e Where e.activo = true"),
	@NamedQuery(name = "Editorial.findByName", query = "Select e From Editorial e Where e.nombre = :nombre")
})
public class Editorial implements Serializable{
	
	//Atributos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean activo = true;

	private String nombre;

	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	@ManyToMany //TODO revisar funcionamiento de esta relaci�n
	@JoinTable(name = "relacioneditorialdireccion", 
	joinColumns = { @JoinColumn(name = "idEditorial") }, 
	inverseJoinColumns = { @JoinColumn(name = "idDireccion") })
	private List<Direccion> direcciones;
	
	//Constructores
	
	public Editorial(){
		
	}
	
	public Editorial(String nombre, String email, List<Direccion> direcciones) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.direcciones = direcciones;
		this.fechaAlta = new Date();
	}

	
	//Getters y Setters
	
	public long getId() {
		return id;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public void setDirecciones(List<Direccion> direcciones){
		this.direcciones = direcciones;
	}
	public List<Direccion> getDirecciones(){
		return direcciones;
	}
	
	//M�todos
	
	public String getStringFechaAlta(){
		return Utilidades.getSimpleDate(this.fechaAlta);
	}
	
	public String getStringFechaBaja(){
		return Utilidades.getSimpleDate(this.fechaBaja);
	}
	
	public String getListaDirecciones(){
		
		String ret = null;
		
		if(this.direcciones.size() > 1){
			
			for(Direccion direccion : this.direcciones){
				ret = direccion + "; " + ret;
			}	
		}else{
			ret = this.direcciones.get(0).toString();
		}
		
		return ret;
	}
	
	public String getListaTelefonos(){
		
		String ret = null;
		List<TelefonoDeEditorial> telefonos = null;
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<TelefonoDeEditorial> query = em.createNamedQuery("TelefonoDeEditorial.findByEditorial",TelefonoDeEditorial.class);
		
		query.setParameter("idEditorial",this.id);
		
		try{
			
			if(!query.getResultList().isEmpty()){

				telefonos = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		if (telefonos.size() > 1){
			
			for(TelefonoDeEditorial telefono : telefonos ){
				ret = telefono.getTelefono() + "; " + ret;
			}
			
		}else{
			ret = telefonos.get(1).getTelefono();
		}
		
		return ret;
	}
}
