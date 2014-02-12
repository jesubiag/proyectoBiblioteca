package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.proyectoBiblioteca.utils.Utilidades;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="suspensiones")
@NamedQueries({
	@NamedQuery(name = "Suspension.findAll", query = "Select s From Suspension s"),
	//recordar formato q.setParameter("today",todaysDateObject,TemporalType.DATE); para setear el parámetro
	@NamedQuery(name = "Suspension.findAllActive", query = "Select s From Suspension s Where s.fechaVencimiento > :fecha"),
	@NamedQuery(name = "Suspension.findByMemberId", query = "Select s From Suspension s Where s.socio.id = :idSocio")
})
public class Suspension implements Serializable {
	
	//Campos
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	@OneToOne (cascade = CascadeType.MERGE)
	@JoinColumn(name = "idPrestamo")
	private Prestamo prestamo;

	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn(name = "idSocio")
	private Socio socio;

	private String motivo;

	//Constructores
	
	public Suspension() {
		
	}

	public Suspension(Prestamo prestamo, Socio socio, String motivo, int diasSuspendido) {
		super();
		this.prestamo = prestamo;
		this.socio = socio;
		this.motivo = motivo;
		this.fechaAlta = new Date();
		Calendar calAux = Calendar.getInstance();
		calAux.setTime(this.fechaAlta);
		calAux.add(Calendar.DAY_OF_YEAR, diasSuspendido);
		this.fechaVencimiento = calAux.getTime();
	}
	
	//Getters y setters

	public long getId() {
		return this.id;
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