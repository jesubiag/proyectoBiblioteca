package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "TelefonoDeEditorial.findByEditorial", query = "Select t From TelefonoDeEditorial t Where t.editorial = :editorial")
@Table (name="telefonosdeeditorial")
public class TelefonoDeEditorial implements Serializable {
	
	//Campos
	
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@ManyToOne
	@JoinColumn (name = "idEditorial")
	private Editorial editorial;
	
	private String telefono;
	
	
	//Constructores
	
	public TelefonoDeEditorial() {
		super();
	}


	public TelefonoDeEditorial(Editorial editorial, String telefono) {
		super();
		this.editorial = editorial;
		this.telefono = telefono;
	}
	
	//Getters y setters

	public Editorial getEditorial() {
		return editorial;
	}


	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public long getId() {
		return id;
	}
   
	
}
