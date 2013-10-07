package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "Editorial.findAll", query = "Select e From Editorial e"),
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
	
	//TODO agregar direcciones y teléfonos OneToMany
	
	//Constructores
	
	Editorial(){
		
	}
	
	public Editorial(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
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
	
}
