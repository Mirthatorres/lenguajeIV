package com.biblioteca.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.biblioteca.entidad.Autor;

@Stateless
public class AutorSession {
	
	@PersistenceContext
	EntityManager em;
	
	// Funcion para listar todos los autores.
	public List<Autor> listado(){
		
		String jpql = "SELECT a FROM Autor a ORDER BY a.codigo";
		Query q = em.createQuery(jpql);
		List<Autor> autores = q.getResultList();
		return autores;
	}
	
	// Funcion para filtrar por nombre.
	public List<Autor> consultarAutoresPorNombreV1(String nombre){
		
		String jpql = "SELECT a FROM Autor a WHERE UPPER(a.nombre) LIKE :n ORDER BY a.codigo";
		Query q = em.createQuery(jpql);
		q.setParameter("n", "%"+ nombre.toUpperCase() + "%");
		List<Autor> autores = q.getResultList();
		return autores;
	}

	// Funcion para filtrar por nombre.
	public Map<String, Object> consultarAutoresPorNombre(String nombre){
		
		Map<String, Object> retorno = new HashMap<String,Object>();
		
		try {
			String jpql = "SELECT a FROM Autor a WHERE UPPER(a.nombre) LIKE :n ORDER BY a.codigo";
			Query q = em.createQuery(jpql);
			q.setParameter("n", "%"+ nombre.toUpperCase() + "%");
			List<Autor> autores = q.getResultList();
			retorno.put("success", true);
			retorno.put("result", autores);
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		
		return retorno;
	}
	
	// Funcion para buscar un autor en la tabla.
	public Autor buscar(Autor a) {
		
		Autor autor = em.find(Autor.class, a.getCodigo());
		return autor;
	}
	
	
	//Funcion para modificar un autor.
	public Autor editar(Autor a) {
		
		a = em.merge(a);
		return a;
	}
	
	// Funcion para insertar nuevos autores.
	public Autor insertar(Autor autor) {
		
		em.persist(autor);
		em.refresh(autor);
		return autor;
	}
	
	// Funcion para insertar o modificar
	private Autor actualizar(Autor a) {
		
		Autor autorActualizado = null;
		Autor autorBuscar = buscar(a);
		
		if( autorBuscar == null) {
			autorActualizado = insertar(a);
		}else {
			autorActualizado = editar(a);
		}
		return autorActualizado;
	}
	
	// Funcion para eliminar un autor.

	public void eliminar(Integer codigo) {
		Autor autorBuscar = em.find(Autor.class, codigo);
		em.remove(autorBuscar);
	}
}
