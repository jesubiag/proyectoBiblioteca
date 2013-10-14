package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

@Entity
@NamedQueries({
	@NamedQuery(name = "Localidad.findAll", query = "Select l From Localidad l"),
	@NamedQuery(name = "Localidad.findAllActive", query = "Select l From Localidad l Where l.activo = true"),
	@NamedQuery(name = "Localidad.findByFields", query = "Select l From Localidad l Where l.provincia.nombre = :provincia And l.nombre = :nombre")
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
	
	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn (name = "idProvincia")
	private Provincia provincia;

	private String nombre;
	
	private boolean activo = true;

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
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}

