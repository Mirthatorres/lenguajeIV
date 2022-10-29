package com.biblioteca.rest;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@ApplicationPath("rest")

public class BibliotecaRestApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private HashSet<Class<?>> classes = new HashSet<Class<?>>();


	public BibliotecaRestApplication() {

			CorsFilter corsFilter = new CorsFilter();
			corsFilter.getAllowedOrigins().add("*");
			corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");

			singletons.add(corsFilter);

			classes.add(AutorRest.class);
			classes.add(CiudadRest.class);
			classes.add(ClienteRest.class);
			classes.add(LibroRest.class);
	}
	
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public HashSet<Class<?>> getClasses() {
		return classes;		
	}

}