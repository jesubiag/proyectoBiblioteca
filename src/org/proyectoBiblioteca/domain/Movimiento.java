package org.proyectoBiblioteca.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.proyectoBiblioteca.enums.TipoMovimiento;

@Entity
@NamedQueries({
	@NamedQuery(name = "Movimiento.findAll", query = "Select m From Movimiento m"),
	@NamedQuery(name = "Movimiento.findByType", query = "Select m From Movimiento m Where m.tipoMovimiento like :tipo"),
	@NamedQuery(name = "Movimiento.findByLoanId", query = "Select m From Movimiento m Where m.prestamo.id = :idPrestamo")
})
public class Movimiento implements Serializable{

	//Campos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "idPrestamo")
	private Prestamo prestamo;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@Enumerated (EnumType.STRING)
	private TipoMovimiento tipoMovimiento;
	
	//Constructores
	
	public Movimiento(){
		
	}
	
	public Movimiento(Prestamo prestamo, Usuario usuario,
			TipoMovimiento tipoMovimiento) {
		super();
		this.prestamo = prestamo;
		this.usuario = usuario;
		this.tipoMovimiento = tipoMovimiento;
	}

	//Getters y setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
}
