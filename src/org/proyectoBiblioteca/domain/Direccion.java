package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Direccion implements Serializable {
	
	//Campos
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String calle;

	private String codigoPostal;

	private String departamento;

	private int numero;

	private int piso;

	@ManyToOne //(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idLocalidad")
	private Localidad localidad;

	//Constructores
	
	public Direccion() {
	}

	public Direccion(String calle, String codigoPostal, String departamento,
			int numero, int piso, Localidad localidad) {
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.departamento = departamento;
		this.numero = numero;
		this.piso = piso;
		this.localidad = localidad;
	}

	//Setters y Getters
	
	public Direccion(String calle, String codigoPostal, int numero, Localidad localidad) {
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.numero = numero;
		this.localidad = localidad;
	}



	public long getId() {
		return this.id;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPiso() {
		return this.piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	//métodos
	
	public String toString(){
		//TODO modificar para el caso de que sea un depto
		return this.calle + " " + this.numero + ", " + this.localidad.getNombre() + ", " + this.localidad.getProvincia().getNombre();
	}

}