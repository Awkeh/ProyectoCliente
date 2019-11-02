package com.proyecto.admin.mockup.entities;

public class Usuario {

	protected Long id;
	protected String usuario, nombre, apellido, email;

	public Long getId() {
		return id;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
