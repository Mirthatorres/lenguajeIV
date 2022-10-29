package com.biblioteca.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prestamos_libros")
public class PrestamoLibro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "pli_secuencia")
	private Integer secuencia;
	
	@Column (name = "pli_prestamo")
	private Integer prestamo;
	
	@Column (name = "pli_libro")
	private Integer libro;
	
	@Column (name = "pli_estado")
	private Integer estado;
	
	@Column (name = "pli_dias")
	private Integer dias;
	
	@Column (name = "pli_valor")
	private Float valor;
	
	@Column (name = "pli_fecha_devolucion")
	private Date fecha_devolucion;
	
	public PrestamoLibro() {
		
		super();
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public Integer getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Integer prestamo) {
		this.prestamo = prestamo;
	}

	public Integer getLibro() {
		return libro;
	}

	public void setLibro(Integer libro) {
		this.libro = libro;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(Date fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
}
