package com.biblioteca.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "prestamos")
public class Prestamo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "pre_numero")
	private Integer numero;
	
	@Column (name = "pre_cliente")
	private Integer cliente;
	
	@Column (name = "pre_usuario")
	private Integer usuario;
	
	@Column (name = "pre_fecha")
	private Date fecha;
	
	@Column (name = "pre_obs")
	private String observacion;
	
	@Column (name = "pre_situacion")
	private Integer situacion;
	
	@Column (name = "pre_total")
	private Float total;
	
	public Prestamo() {
		super();
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Integer getSituacion() {
		return situacion;
	}
	public void setSituacion(Integer situacion) {
		this.situacion = situacion;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
}
