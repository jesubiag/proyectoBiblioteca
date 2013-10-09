package org.proyectoBiblioteca.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Socio;

public class SocioService {

	public static void retrieveAll(HttpServletRequest request) {
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		List<Socio> socios = null;
		
		try{
			
			TypedQuery<Socio> query = em.createNamedQuery("Socio.findAll",Socio.class);
			
			if(!query.getResultList().isEmpty()){
	
				socios = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("members", socios);
		
	}

}
