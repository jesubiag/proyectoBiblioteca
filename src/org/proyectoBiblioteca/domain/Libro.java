package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.utils.Utilidades;

@Entity
@NamedQueries({
	@NamedQuery(name = "Libro.findAll", query = "Select l From Libro l"),
	@NamedQuery(name = "Libro.findAllActive", query = "Select l From Libro l Where l.activo = true"),
	@NamedQuery(name = "Libro.findByTitle", query = "Select l From Libro l Where l.titulo Like :titulo"),
	//Corregir la de autor
	@NamedQuery(name = "Libro.findByAuthor", query = "Select l From Libro l Where l.titulo Like :autor"), //TODO ver si se puede hacer un elem in list
	@NamedQuery(name = "Libro.findByEditorial", query = "Select l From Libro l Where l.editorial.nombre Like :editorial"),
	@NamedQuery(name = "Libro.findByIsbn", query = "Select l From Libro l Where l.isbn Like :isbn")
})
public class Libro implements Serializable{
	
	//Campos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean activo = true;
	
	@OneToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "relacionautorlibro", joinColumns = {
	        @JoinColumn(name = "idLibro", referencedColumnName = "id")
	    }, inverseJoinColumns = {
	        @JoinColumn(name = "idAutor", referencedColumnName = "id")
	    })
	private List<Autor> autores;
	
	@ManyToOne
	@JoinColumn(name = "idEditorial")
	private Editorial editorial;

	private String etiquetas;

	private String isbn;

	private String paisOrigen;

	private int rango;

	private String titulo;
	
	private String motivoBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	//Constructores
	
	public Libro(){
		
	}
	
	public Libro(List<Autor> autores, Editorial editorial, String etiquetas,
			String isbn, String paisOrigen, int rango, String titulo) {
		super();
		this.autores = autores;
		this.editorial = editorial;
		this.etiquetas = etiquetas;
		this.isbn = isbn;
		this.paisOrigen = paisOrigen;
		this.rango = rango;
		this.titulo = titulo;
		this.fechaAlta = new Date();
	}

	//Getters y Setters
	
	public String getMotivoBaja() {
		return motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
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
	
	//Métodos
	
	public String getStringFechaAlta(){
		return Utilidades.getSimpleDate(this.fechaAlta);
	}
	
	public String getStringFechaBaja(){
		return Utilidades.getSimpleDate(this.fechaBaja);
	}
	
	public String getListaAutores(){
		
		String ret = "";

		if (this.autores.size() > 1){
			
			for(Autor autor : this.autores){
				
				if("".equals(ret)){
					ret = autor.getNombre();
				}else{
					ret = autor.getNombre() + ", " + ret;
				}

			}			
			
		}else if(this.autores.size() == 1){
			ret = this.autores.get(0).getNombre();
		}
		
		return ret;
	}
	
	//TODO revisar si es correcto hacerlo de esta manera o hay una mejor
	
	public List<Ejemplar> getEjemplares(){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		List<Ejemplar> ejemplares = null;
		
		try{
			
			TypedQuery<Ejemplar> query = em.createNamedQuery("Ejemplar.findByBookId",Ejemplar.class);
			
			query.setParameter("idLibro", this.id);
			
			if(!query.getResultList().isEmpty()){
	
				ejemplares = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		return ejemplares;
	}
	
}
