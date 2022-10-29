package com.biblioteca.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.biblioteca.entidad.Ciudad;

@Stateless
public class CiudadSession {
	
	@PersistenceContext(name = "BibliotecaPU")
	EntityManager em;
	
	// Funcion para listar todas las ciudades.
	public List<Ciudad> listado(){
		
		String jpql = "SELECT c FROM Ciudad c ORDER BY c.codigo";
		Query q = em.createQuery(jpql);
		List<Ciudad> ciudades = q.getResultList();
		return ciudades;
	}
	
	// Funcion para buscar una ciudad en la tabla.
	public Ciudad buscar(Integer c) {
		if(c == null) {
			return null;
		}
		Ciudad ciudad = em.find(Ciudad.class, c);
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
	public Ciudad actualizar(Ciudad c) {
		
		Ciudad ciudadActualizada = null;
		Ciudad ciudadBuscar = buscar(c.getCodigo());
		
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
	
	// Funcion para filtrar por nombre.
		public List<Ciudad> consultarCiudadesPorNombre(String nombre){
			
			String jpql = "SELECT c FROM Ciudad c WHERE UPPER(c.descripcion) LIKE :n ORDER BY c.codigo";
			Query q = em.createQuery(jpql);
			q.setParameter("n", "%"+ nombre.toUpperCase() + "%");
			List<Ciudad> ciudades = q.getResultList();
			return ciudades;
		}
}
