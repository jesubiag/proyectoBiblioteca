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
	@NamedQuery(name = "Autor.findAll", query = "Select a From Autor a"),
	@NamedQuery(name = "Autor.findByName", query = "Select a From Autor a Where a.nombre = :nombre") //TODO quizás usar like
})
public class Autor implements Serializable{

	//Campos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private boolean activo = true;

	private String nombre;

	private String paisOrigen;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	//Constructores
	
	Autor(){
		
	}
	
	public Autor(String name, String paisOrigen) {
		super();
		this.nombre = name;
		this.paisOrigen = paisOrigen;
		this.fechaAlta = new Date();
	}
	
	//Setters y getters
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public long getId() {
		return id;
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
	
}
