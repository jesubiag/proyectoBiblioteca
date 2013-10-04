package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="libro")
public class Libro implements Serializable{
	
	//Campos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean activo;
	@Transient //AUTO sacar el transient!
	private Autor autor;
	@Transient //AUTO sacar el transient!
	private Editorial editorial;

	private String etiquetas;

	private int isbn;

	private String paisOrigen;

	private int rango;

	private String titulo;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	//Constructores
	
	Libro(){
		
	}
	
	public Libro(boolean activo, Autor autor, Editorial editorial,
			String etiquetas, int isbn,
			String paisOrigen, int rango, String titulo) {
		super();
		this.activo = activo;
		this.autor = autor;
		this.editorial = editorial;
		this.etiquetas = etiquetas;
		this.isbn = isbn;
		this.paisOrigen = paisOrigen;
		this.rango = rango;
		this.titulo = titulo;
	}
	
	//Getters y Setters
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Editorial getEditorial() {
		return editorial;
	}
	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}
	public String getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	public int getRango() {
		return rango;
	}
	public void setRango(int rango) {
		this.rango = rango;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
