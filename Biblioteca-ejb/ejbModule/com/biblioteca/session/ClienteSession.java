package com.biblioteca.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.biblioteca.entidad.Cliente;

@Stateless
public class ClienteSession {

	@PersistenceContext(name = "BibliotecaPU")
	EntityManager em;
	
	// Funcion para listar todos los clientes.
	public List<Cliente> listado(){
		
		String jpql = "SELECT c FROM Cliente c ORDER BY c.codigo";
		Query q = em.createQuery(jpql);
		List<Cliente> clientes = q.getResultList();
		return clientes;
	}
	
	// Funcion para buscar un cliente en la tabla.
	public Cliente buscar(Integer c) {
		
		if(c == null) {
			return null;
		}
		Cliente cliente = em.find(Cliente.class, c);
		return cliente;
	}
	
	
	//Funcion para modificar un cliente.
	public Cliente editar(Cliente c) {
		
		c = em.merge(c);
		return c;
	}
	
	// Funcion para insertar nuevos clientes.
	public Cliente insertar(Cliente cliente) {
		
		em.persist(cliente);
		em.refresh(cliente);
		return cliente;
	}
	
	// Funcion para insertar o modificar
	public Cliente actualizar(Cliente c) {
		
		Cliente clienteActualizado = null;
		Cliente clienteBuscar = buscar(c.getCodigo());
		
		if( clienteBuscar == null || c.getCodigo() == 0) {
			clienteActualizado = insertar(c);
		}else {
			clienteActualizado = editar(c);
		}
		return clienteActualizado;
	}
	
	// Funcion para eliminar un cliente.

	public void eliminar(Integer codigo) {
		Cliente clienteBuscar = em.find(Cliente.class, codigo);
		em.remove(clienteBuscar);
	}

	// Funcion para filtrar por nombre.
		public List<Cliente> consultarClientesPorNombre(String nombre){
			
			String jpql = "SELECT c FROM Cliente c WHERE UPPER(c.nombre) LIKE :n ORDER BY c.codigo";
			Query q = em.createQuery(jpql);
			q.setParameter("n", "%"+ nombre.toUpperCase() + "%");
			List<Cliente> clientes = q.getResultList();
			return clientes;
		}
}
