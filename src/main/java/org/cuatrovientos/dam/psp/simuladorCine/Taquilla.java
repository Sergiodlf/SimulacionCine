package org.cuatrovientos.dam.psp.simuladorCine;

public class Taquilla implements Runnable {

	private String nombre;
	private ColaCine cola;
	private long timestampInicio = 0;

	public Taquilla(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public void run() {

	}

	public void asignarCola(ColaCine cola) {
		this.cola = cola;
	}

	private void log(String mensaje) {
		System.out.println("\t[" + Thread.currentThread().threadId() + "][" + nombre + "] "
				+ (System.currentTimeMillis() - timestampInicio) + ": " + mensaje);
	}
}
