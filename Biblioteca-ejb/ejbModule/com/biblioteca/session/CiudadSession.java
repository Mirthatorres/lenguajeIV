package com.biblioteca.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.biblioteca.entidad.Ciudad;

public class CiudadSession {
	
	@PersistenceContext
	EntityManager em;
	
	// Funcion para listar todas las ciudades.
	public List<Ciudad> listado(){
		
		String jpql = "SELECT c FROM Ciudad c ORDER BY c.codigo";
		Query q = em.createQuery(jpql);
		List<Ciudad> ciudades = q.getResultList();
		return ciudades;
	}
	
	// Funcion para buscar una ciudad en la tabla.
	public Ciudad buscar(Ciudad c) {
		
		Ciudad ciudad = em.find(Ciudad.class, c.getCodigo());
		return ciudad;
	}
	
	
	//Funcion para modificar una ciudad.
	public Ciudad editar(Ciudad c) {
		
		c = em.merge(c);
		return c;
	}
	
	// Funcion para insertar nuevas ciudades.
	public Ciudad insertar(Ciudad ciudad) {
		
		em.persist(ciudad);
		em.refresh(ciudad);
		return ciudad;
	}
	
	// Funcion para insertar o modificar
	private Ciudad actualizar(Ciudad c) {
		
		Ciudad ciudadActualizada = null;
		Ciudad ciudadBuscar = buscar(c);
		
		if( ciudadBuscar == null) {
			ciudadActualizada = insertar(c);
		}else {
			ciudadActualizada = editar(c);
		}
		return ciudadActualizada;
	}
	
	// Funcion para eliminar una ciudad.

	public void eliminar(Integer codigo) {
		Ciudad ciudadBuscar = em.find(Ciudad.class, codigo);
		em.remove(ciudadBuscar);
	}
}
