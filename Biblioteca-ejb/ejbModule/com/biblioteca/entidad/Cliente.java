package com.biblioteca.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "clientes")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "cli_codigo")
	private Integer codigo;

	@Column (name = "cli_nombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "cli_ciudad")
	Ciudad ciudad;
	
	@Column (name = "cli_direccion")
	private String direccion;

	@Column (name = "cli_obs")
	private String observacion;
	
	public Cliente() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
}
