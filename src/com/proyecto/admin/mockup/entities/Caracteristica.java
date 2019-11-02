package com.proyecto.admin.mockup.entities;

public class Caracteristica {

	private Long id;
	private String nombre;
	private String etiqueta;
	private TipoDato tipoDato;

	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public TipoDato getTipoDato() {
		return tipoDato;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

}
