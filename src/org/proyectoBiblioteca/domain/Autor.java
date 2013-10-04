package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Autor implements Serializable{

	//Campos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;

	private String paisOrigen;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	//Constructores
	
	Autor(){
		
	}
	
	public Autor(long id, String name, String paisOrigen, Date fechaBaja,
			Date fechaAlta) {
		super();
		this.id = id;
		this.nombre = name;
		this.paisOrigen = paisOrigen;
		this.fechaBaja = fechaBaja;
		this.fechaAlta = fechaAlta;
	}
	
	//Setters y getters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
}
