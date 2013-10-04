package org.proyectoBiblioteca.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.proyectoBiblioteca.enums.EstadoEjemplar;

@Entity
public class Ejemplar implements Serializable{

	//Campos

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	
	@Transient private Libro libro; //AUTO sacar transient
	
	private int anio;
	
	private String estante;
	
	private String mueble;
	
	private String pasillo;
	
	private EstadoEjemplar estado;
	
	private long numeroEjemplar;
	
	private int precioDolares;
	
	//Constructores
	
	Ejemplar(){
		
	}
	
	public Ejemplar(Libro libro, int anio, String estante, String mueble,
			String pasillo, EstadoEjemplar estado, long numeroEjemplar,
			int precioDolares) {
		super();
		this.libro = libro;
		this.anio = anio;
		this.estante = estante;
		this.mueble = mueble;
		this.pasillo = pasillo;
		this.estado = estado;
		this.numeroEjemplar = numeroEjemplar;
		this.precioDolares = precioDolares;
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

	public int getPrecioDolares() {
		return precioDolares;
	}

	public void setPrecioDolares(int precioDolares) {
		this.precioDolares = precioDolares;
	}

	public long getId() {
		return id;
	}
	
	
	
	
	
}
