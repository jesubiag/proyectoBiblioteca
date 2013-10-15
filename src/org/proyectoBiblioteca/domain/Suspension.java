package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.proyectoBiblioteca.utils.Utilidades;

import java.util.Date;

@Entity
@Table(name="suspensiones")
public class Suspension implements Serializable {
	
	//Campos
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	@OneToOne (cascade = CascadeType.MERGE) //TODO revisar relación
	@JoinColumn(name = "idPrestamo")
	private Prestamo prestamo;

	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn(name = "idSocio")
	private Socio socio;

	private String motivo;

	//Constructores
	
	public Suspension() {
		
	}

	public Suspension(Prestamo prestamo, Socio socio, String motivo) {
		super();
		this.prestamo = prestamo;
		this.socio = socio;
		this.motivo = motivo;
		this.fechaAlta = new Date();
	}
	
	//Getters y setters

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	//Métodos
	
	public String getStringFechaAlta(){
		return Utilidades.getSimpleDate(this.fechaAlta);
	}
	
	public String getStringFechaVencimiento(){
		return Utilidades.getSimpleDate(this.fechaVencimiento);
	}
	
}