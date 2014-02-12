package org.proyectoBiblioteca.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	@NamedQuery(name = "Libro.findByTitle", query = "Select l From Libro l Where l.activo = true And l.titulo Like :titulo"),
	@NamedQuery(name = "Libro.findByAuthor", query = "Select l From Libro l Where l.activo = true And :autor Member Of l.autores"), 
	@NamedQuery(name = "Libro.findByEditorial", query = "Select l From Libro l Where l.activo = true And l.editorial.nombre Like :editorial"),
	@NamedQuery(name = "Libro.findByIsbn", query = "Select l From Libro l Where l.activo = true And l.isbn Like :isbn"),
	@NamedQuery(name = "Libro.findByTag", query = "Select l From Libro l Where l.activo = true And :etiqueta Member Of l.etiquetas")
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
	private List<Autor> autores = new ArrayList<Autor>();
	
	@ManyToOne
	@JoinColumn(name = "idEditorial")
	private Editorial editorial;

	@ElementCollection
	  @CollectionTable(
	        name="etiquetasLibro",
	        joinColumns=@JoinColumn(name="idLibro")
	  )
	@Column(name="etiqueta")
	private List<String> etiquetas = new ArrayList<String>();

	private String isbn;

	private String paisOrigen;

	private int rango;

	private String titulo;
	
	private String motivoBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	private String linkImagen;

	private String descripcion;
	
	//Constructores
	
	public Libro(){
		
	}
	
	public Libro(List<Autor> autores, Editorial editorial, List<String> etiquetas,
			String isbn, String paisOrigen, int rango, String titulo, String descripcion, String linkImagen) {
		super();
		this.autores = autores;
		this.editorial = editorial;
		this.etiquetas = etiquetas;
		this.isbn = isbn;
		this.paisOrigen = paisOrigen;
		this.rango = rango;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.linkImagen = linkImagen;
		this.fechaAlta = new Date();
	}

	//Getters y Setters
	
	public String getLinkImagen() {
		return linkImagen;
	}

	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
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
	public List<String> getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(List<String> etiquetas) {
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
	
	public String getListaEtiquetas(){
		return Utilidades.stringListAsString(this.etiquetas);
	}
	
}
