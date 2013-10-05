package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.proyectoBiblioteca.enums.EstadoEjemplar;

@Entity
@NamedQueries({
	@NamedQuery(name = "Ejemplar.findAll", query = "Select e From Ejemplar e"),
	//@NamedQuery(name = "Ejemplar.findByTitle", query = "Select e From Ejemplar e Where e. ...") TODO armar la query - Join entre libro y ejemplar por idLibro
})
public class Ejemplar implements Serializable{

	//Campos

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne (cascade = CascadeType.MERGE)
	@JoinColumn(name = "idLibro")
	private Libro libro; //AUTO sacar transient
	
	private int anio;
	
	private String estante;
	
	private String mueble;
	
	private String pasillo;
	
	@Enumerated (EnumType.STRING)
	private EstadoEjemplar estado;
	
	private long numeroEjemplar;
	
	private double precioDolares;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	//Constructores
	
	Ejemplar(){
		
	}
	
	public Ejemplar(Libro libro, int anio, String estante, String mueble,
			String pasillo, long numeroEjemplar,
			double precioDolares) {
		super();
		this.libro = libro;
		this.anio = anio;
		this.estante = estante;
		this.mueble = mueble;
		this.pasillo = pasillo;
		this.estado = EstadoEjemplar.disponible;
		this.numeroEjemplar = numeroEjemplar;
		this.precioDolares = precioDolares;
		this.fechaAlta = new Date();
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getEstante() {
		return estante;
	}

	public void setEstante(String estante) {
		this.estante = estante;
	}

	public String getMueble() {
		return mueble;
	}

	public void setMueble(String mueble) {
		this.mueble = mueble;
	}

	public String getPasillo() {
		return pasillo;
	}

	public void setPasillo(String pasillo) {
		this.pasillo = pasillo;
	}

	public EstadoEjemplar getEstado() {
		return estado;
	}

	public void setEstado(EstadoEjemplar estado) {
		this.estado = estado;
	}

	public long getNumeroEjemplar() {
		return numeroEjemplar;
	}

	public void setNumeroEjemplar(long numeroEjemplar) {
		this.numeroEjemplar = numeroEjemplar;
	}

	public double getPrecioDolares() {
		return precioDolares;
	}

	public void setPrecioDolares(double precioDolares) {
		this.precioDolares = precioDolares;
	}

	public long getId() {
		return id;
	}
	
	
	
	
	
}
