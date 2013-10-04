package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="editoriales")
public class Editorial implements Serializable{
	
	//Atributos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean activo;

	private String nombre;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	//Constructores
	
	Editorial(){
		
	}
	
	public Editorial(long id, boolean activo,
			String nombre, String email,
			Date fechaAlta, Date fechaBaja) {
		super();
		this.id = id;
		this.activo = activo;
		this.nombre = nombre;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}

	
	//Getters y Setters
	
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
	
}
