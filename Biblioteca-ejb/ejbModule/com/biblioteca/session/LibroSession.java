package com.biblioteca.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.biblioteca.entidad.Libro;

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
	public Libro buscar(Libro l) {
		
		Libro libro = em.find(Libro.class, l.getCodigo());
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
	private Libro actualizar(Libro l) {
		
		Libro libroActualizado = null;
		Libro libroBuscar = buscar(l);
		
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
}
