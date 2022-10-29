package com.biblioteca.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.biblioteca.entidad.Libro;

@Stateless
public class LibroSession {

	@PersistenceContext
	EntityManager em;
	
	// Funcion para listar todos los libros.
	public List<Libro> listado(){
		
		String jpql = "SELECT l FROM Libro l ORDER BY l.codigo";
		Query q = em.createQuery(jpql);
		List<Libro> libros = q.getResultList();
		return libros;
	}
	
	// Funcion para buscar un libro en la tabla.
	public Libro buscar(Integer l) {
		
		if(l == null) {
			return null;
		}
		Libro libro = em.find(Libro.class, l);
		return libro;
	}
	
	
	//Funcion para modificar un libro.
	public Libro editar(Libro l) {
		
		l = em.merge(l);
		return l;
	}
	
	// Funcion para insertar nuevos libros.
	public Libro insertar(Libro libro) {
		
		em.persist(libro);
		em.refresh(libro);
		return libro;
	}
	
	// Funcion para insertar o modificar
	public Libro actualizar(Libro l) {
		
		Libro libroActualizado = null;
		Libro libroBuscar = buscar(l.getCodigo());
		
		if( libroBuscar == null) {
			libroActualizado = insertar(l);
		}else {
			libroActualizado = editar(l);
		}
		return libroActualizado;
	}
	
	// Funcion para eliminar un libro.

	public void eliminar(Integer codigo) {
		Libro libroBuscar = em.find(Libro.class, codigo);
		em.remove(libroBuscar);
	}

	// Funcion para filtrar por nombre.
		public List<Libro> consultarLibrosPorNombre(String nombre){
			
			String jpql = "SELECT l FROM Libro l WHERE UPPER(l.descripcion) LIKE :n ORDER BY l.codigo";
			Query q = em.createQuery(jpql);
			q.setParameter("n", "%"+ nombre.toUpperCase() + "%");
			List<Libro> libros = q.getResultList();
			return libros;
		}
}
