package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

@Entity
@NamedQueries({
	@NamedQuery(name = "Localidad.findAll", query = "Select l From Localidad l")
})
public class Localidad implements Serializable {
	
	//Campos
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;

	@OneToOne (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "idProvincia")
	private Provincia provincia;

	private String nombre;

	//Constructores
	
	public Localidad() {
	}
	
	public Localidad(Provincia provincia, String nombre) {
		super();
		this.provincia = provincia;
		this.nombre = nombre;
		this.fechaAlta = new Date();
	}

	//Getters y setters
	
	public long getId() {
		return this.id;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}