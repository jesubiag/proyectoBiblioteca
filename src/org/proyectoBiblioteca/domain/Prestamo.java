package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.proyectoBiblioteca.enums.EstadoPrestamo;
import org.proyectoBiblioteca.utils.Utilidades;

/**
 * Entity implementation class for Entity: Prestamo
 *
 */
@Entity
public class Prestamo implements Serializable {

	//Campos
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "idSocio")
	private Socio socio;
	
	@ManyToOne
	@JoinColumn(name = "idEjemplar")
	private Ejemplar ejemplar;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario; //Usuario que generó el préstamo
	
	@Temporal(TemporalType.DATE)
	private Date fechaAcordada;
	
	@Temporal(TemporalType.DATE)
	private Date fechaDevolucion;
	
	@Temporal(TemporalType.DATE)
	private Date fechaPrestamo;
	
	private boolean prestamoLocal; //Indica si el préstamo es local o domiciliario (falso)
	
	@Enumerated (EnumType.STRING)
	private EstadoPrestamo estado;
	
	private String nota; //Nota opcional para detalles de la devolución
	
	
	//Constructores
	
	public Prestamo() {
		super();
	}


	public Prestamo(Socio socio, Ejemplar ejemplar, Usuario usuario,
			Date fechaAcordada, boolean prestamoLocal) {
		super();
		this.socio = socio;
		this.ejemplar = ejemplar;
		this.usuario = usuario;
		this.fechaAcordada = fechaAcordada;
		this.prestamoLocal = prestamoLocal;
		this.estado = EstadoPrestamo.vigente;
		this.fechaPrestamo = new Date();
	}

	//Getters y Setters
	
	public Socio getSocio() {
		return socio;
	}


	public void setSocio(Socio socio) {
		this.socio = socio;
	}


	public Ejemplar getEjemplar() {
		return ejemplar;
	}


	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Date getFechaAcordada() {
		return fechaAcordada;
	}


	public void setFechaAcordada(Date fechaAcordada) {
		this.fechaAcordada = fechaAcordada;
	}


	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}


	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}


	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}


	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}


	public boolean isPrestamoLocal() {
		return prestamoLocal;
	}


	public void setPrestamoLocal(boolean prestamoLocal) {
		this.prestamoLocal = prestamoLocal;
	}


	public EstadoPrestamo getEstado() {
		return estado;
	}


	public void setEstado(EstadoPrestamo estado) {
		this.estado = estado;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	public long getId() {
		return id;
	}
	
	//Métodos
	
	public String getStringFechaAcordada(){
		return Utilidades.getSimpleDate(this.fechaAcordada);
	}
	
	public String getStringFechaPrestamo(){
		return Utilidades.getSimpleDate(this.fechaPrestamo);
	}
	
	public String getStringFechaDevolucion(){
		return Utilidades.getSimpleDate(this.fechaDevolucion);
	}
	
}
