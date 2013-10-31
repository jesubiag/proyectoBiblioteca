package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.proyectoBiblioteca.enums.TipoUsuario;
import org.proyectoBiblioteca.utils.Utilidades;

@Entity
@NamedQueries({
	@NamedQuery(name = "Usuario.findAll", query = "Select u From Usuario u"),
	@NamedQuery(name = "Usuario.findAllByType", query = "Select u From Usuario u Where u.tipoUsuario = :tipoUsuario"),
	@NamedQuery(name = "Usuario.findByUser", query = "Select u From Usuario u Where u.usuario like :usuario")
})
public class Usuario implements Serializable{
	
	//Campos
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String usuario;
	
	private String clave;
	
	private static final long serialVersionUID = 1L;
	
	private boolean activo = true;
	
	private String nombre;
	
	private String apellido;
	
	@Enumerated (EnumType.STRING)
	private TipoUsuario tipoUsuario;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	//Constructores
	
	Usuario(){
		
	}
	
	public Usuario(String nombre, String apellido, String clave,
			String usuario, TipoUsuario tipoUsuario) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
		this.fechaAlta = new Date();
	}

	//Getters y setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getUsuario() {
		return usuario;
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
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	
	//Métodos
	
	public String getStringFechaAlta(){
		return Utilidades.getSimpleDate(this.fechaAlta);
	}
	
	public String getStringFechaBaja(){
		return Utilidades.getSimpleDate(this.fechaBaja);
	}
	
}
