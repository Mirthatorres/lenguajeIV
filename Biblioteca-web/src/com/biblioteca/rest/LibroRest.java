package com.biblioteca.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.biblioteca.entidad.Libro;
import com.biblioteca.session.LibroSession;


@Path("/libro")
public class LibroRest {
	@EJB
	LibroSession ls;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultar")
	public Map<String, Object> consultar(){
		Map<String, Object> retorno = new HashMap<String,Object>();
		try {
			List<Libro> libros = ls.listado();
			retorno.put("success", true);
			retorno.put("result", libros);
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultar-por-nombre")
	public Map<String, Object> consultarPorNombre(@QueryParam("q") String nombre) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			List<Libro> libros = ls.consultarLibrosPorNombre(nombre);
			retorno.put("success", true);
			retorno.put("result", libros);
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/incluir")
	public Map<String, Object> incluir(Libro libros) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			retorno.put("success", true);
			retorno.put("result", ls.insertar(libros));
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
	
	@DELETE
	@Path("/eliminar/{id}")
	public Map<String, Object> eliminar(@PathParam("id") Integer codigo) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			ls.eliminar(codigo);
			retorno.put("success", true);
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editar")
	public Map<String, Object> editar(Libro libro) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			libro = ls.editar(libro);
			retorno.put("success", true);
			retorno.put("result", libro);
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/actualizar")
	public Map<String, Object> actualizar(Libro l) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {

			retorno.put("success", true);
			retorno.put("result", ls.actualizar(l));
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/{id}")
	public Map<String, Object> buscarPorCodigo(@PathParam("id") Integer codigo) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			Libro libro = ls.buscar(codigo);
			retorno.put("success", true);
			retorno.put("result", libro);
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
}
