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

import com.biblioteca.entidad.Cliente;
import com.biblioteca.session.ClienteSession;

@Path("/cliente")
public class ClienteRest {
	@EJB
	ClienteSession cls;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultar")
	public Map<String, Object> consultar(){
		Map<String, Object> retorno = new HashMap<String,Object>();
		try {
			List<Cliente> clientes = cls.listado();
			retorno.put("success", true);
			retorno.put("result", clientes);
			
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
			List<Cliente> clientes = cls.consultarClientesPorNombre(nombre);
			retorno.put("success", true);
			retorno.put("result", clientes);
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/incluir")
	public Map<String, Object> incluir(Cliente cliente) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			retorno.put("success", true);
			retorno.put("result", cls.insertar(cliente));
			
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
			cls.eliminar(codigo);
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
	public Map<String, Object> editar(Cliente cliente) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {
			cliente = cls.editar(cliente);
			retorno.put("success", true);
			retorno.put("result", cliente);
			
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/actualizar")
	public Map<String, Object> actualizar(Cliente cliente) {

		Map<String, Object> retorno = new HashMap<String,Object>();

		try {

			retorno.put("success", true);
			retorno.put("result", cls.actualizar(cliente));
			
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
			Cliente cliente = cls.buscar(codigo);
			retorno.put("success", true);
			retorno.put("result", cliente);
		} catch (Exception e) {

			retorno.put("success", false);
			retorno.put("error", e.getMessage());
		}
		return retorno;
	}
}
