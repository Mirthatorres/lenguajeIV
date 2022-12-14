package com.biblioteca.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.biblioteca.entidad.Autor;
import com.biblioteca.session.AutorSession;

@Path("/autor")
public class AutorRest {

	@EJB
	AutorSession as;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultar")
	public Map<String, Object> consultar(){
		Map<String, Object> retorno = new HashMap<String,Object>();
		try {
			List<Autor> autores = as.listado();
			retorno.put("success", true);
			retorno.put("result", autores);
			
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
			List<Autor> autores = as.consultarAutoresPorNombre(nombre);
			retorno.put("success", true);
			retorno.put("result", autores);
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/incluir")
	public Map<String, Object> incluir(Autor autor) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			retorno.put("success", true);
			retorno.put("result", as.insertar(autor));
			
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
			as.eliminar(codigo);
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
	public Map<String, Object> editar(Autor autor) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			autor = as.editar(autor);
			retorno.put("success", true);
			retorno.put("result", autor);
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/actualizar")
	public Map<String, Object> actualizar(Autor autor) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {

			retorno.put("success", true);
			retorno.put("result", as.actualizar(autor));
			
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
			Autor autor = as.buscar(codigo);
			retorno.put("success", true);
			retorno.put("result", autor);
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
}
