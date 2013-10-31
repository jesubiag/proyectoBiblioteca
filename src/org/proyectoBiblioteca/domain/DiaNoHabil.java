package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.proyectoBiblioteca.utils.Utilidades;
import java.util.Date;

@Entity
@Table(name="diasnohabiles")
@NamedQueries({
	@NamedQuery(name = "DiaNoHabil.findAll", query = "Select d From DiaNoHabil d"),
	@NamedQuery(name = "DiaNoHabil.findAllActive", query = "Select d From DiaNoHabil d Where d.activo = true"),
	//TODO buscar alternativa para obtener las del a�o!
	@NamedQuery(name = "DiaNoHabil.findFollowing", query = "Select d From DiaNoHabil d Where d.fecha > CURRENT_DATE")
})
public class DiaNoHabil implements Serializable {

	//Campos
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private boolean activo = true;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	private Date fechaSiguiente;

	private String motivo;

	//Constructores
	
	public DiaNoHabil() {
	}

	public DiaNoHabil(Date fecha, Date fechaSiguiente, String motivo) {
		super();
		this.fecha = fecha;
		this.fechaSiguiente = fechaSiguiente;
		this.motivo = motivo;
	}

	//Getters y Setters
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaSiguiente() {
		return this.fechaSiguiente;
	}

	public void setFechaSiguiente(Date fechaSiguiente) {
		this.fechaSiguiente = fechaSiguiente;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
		
	//M�todos
	
	public String getStringFecha() {
		return Utilidades.getSimpleDate(fecha);
	}
	
	public String getStringFechaSiguiente() {
		return Utilidades.getSimpleDate(fechaSiguiente);
	}

}