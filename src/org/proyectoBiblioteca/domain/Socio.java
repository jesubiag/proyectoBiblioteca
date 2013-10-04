package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.proyectoBiblioteca.enums.EstadoSocio;

@Entity
@Table (name="socio")
public class Socio implements Serializable {
	
	//Campos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private int dni;
	
	private String nombre;
	
	private String apellido;
	
	private String email;
	
	private String telefono;
	
	private int rango;
	
	@OneToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idDireccion")
	private Direccion direccion;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	@Enumerated (EnumType.STRING)
	private EstadoSocio estado;

	//Constructores
	
	public Socio() {
		
	}
	   
	public Socio(int dni, String nombre, String apellido, String email,
			String telefono,Direccion direccion) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.fechaAlta = new Date();
		this.estado = EstadoSocio.habilitado;
		this.rango = 5; //TODO plantear esto para ver si es correcto
		this.direccion = direccion;
	}
	
	//Setters y Getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public EstadoSocio getEstado() {
		return estado;
	}

	public void setEstado(EstadoSocio estado) {
		this.estado = estado;
	}
	
	public int getRango() {
		return rango;
	}

	public void setRango(int rango) {
		this.rango = rango;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	
}
