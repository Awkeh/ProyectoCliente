package com.proyecto.admin.mockup.entities;

import java.util.List;

public class Fenomeno {

	private Long id;
	private String nombre;
	private String descripcion;
	private List<String> telefonos;
	private List<Caracteristica> caracteristicas;

	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public List<String> getTelefonos() {
		return telefonos;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setTelefonos(List<String> telefonos) {
		this.telefonos = telefonos;
	}
	public List<Caracteristica> getCaracteristicas() {
		return this.caracteristicas;
	}
	public void getCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}
