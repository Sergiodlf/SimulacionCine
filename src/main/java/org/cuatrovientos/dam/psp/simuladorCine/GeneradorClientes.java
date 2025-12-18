package org.cuatrovientos.dam.psp.simuladorCine;

public class GeneradorClientes implements Runnable {
	
	private ColaCine cola;
	private long timestampInicio = 0;

	public GeneradorClientes() {
		super();
	}
	
	@Override
	public void run() {
		
	}
	
	public void asignarCola(ColaCine cola) {
		this.cola = cola;
	}

	private void log(String mensaje) {
		System.out.println("[" + Thread.currentThread().threadId() + "][ GeneradorClientes ] "
				+ (System.currentTimeMillis() - timestampInicio) + ": " + mensaje);
	}
}
