package org.cuatrovientos.dam.psp.simuladorCine;

public class Cliente {
	private final String nombre;

	public Cliente(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + "]";
	}
}
