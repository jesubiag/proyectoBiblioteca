package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name="provincia")
public class Provincia implements Serializable {
	
	//Campos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nombre;

	//Constructores
	
	public Provincia() {
		super();
	}

	public Provincia(String nombre) {
		super();
		this.nombre = nombre;
	}

	//Getters y Setters
	
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
   
}
