package org.cuatrovientos.dam.psp.simuladorCine;

import java.time.Duration;

public class GeneradorClientes implements Runnable {

	private ColaCine cola;
	private long timestampInicio = 0;

	private static final Duration ESPERA_NUEVO_CLIENTE = Duration.ofMillis(4000);

	public GeneradorClientes() {
		super();
	}

	@Override
	public void run() {
		timestampInicio = System.currentTimeMillis();
		try {
			int contadorClientes = 1;
			boolean generadorEncendido = true;
			while (generadorEncendido) {
				Cliente cliente = new Cliente("Cliente_" + contadorClientes++);

				log("Se manda a la cola nuevo cliente: " + cliente.getNombre());
				cola.agregarCliente(cliente);

				Thread.sleep(ESPERA_NUEVO_CLIENTE);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void asignarCola(ColaCine cola) {
		this.cola = cola;
	}

	private void log(String mensaje) {
		System.out.println("[" + Thread.currentThread().threadId() + "][ GENERADOR ] "
				+ (System.currentTimeMillis() - timestampInicio) + ": " + mensaje);
	}
}
