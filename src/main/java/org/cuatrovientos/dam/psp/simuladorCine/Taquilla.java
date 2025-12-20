package org.cuatrovientos.dam.psp.simuladorCine;

import java.time.Duration;
import java.util.Random;

public class Taquilla implements Runnable {

	private String nombre;
	private ColaCine cola;
	private long timestampInicio = 0;
    private Random random = new Random();

    // DATOS CONFIGURABLES
    private static final Duration ESPERA_TAQUILLA = Duration.ofMillis(1000);
    private static final int TIEMPO_MIN_VENTA = 20_000;
    private static final int TIEMPO_MAX_VENTA = 30_000;

	public Taquilla(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public void run() {
		timestampInicio = System.currentTimeMillis();

		try {
			boolean taquillaAbierta = true;
			while (taquillaAbierta) {
				Cliente cliente = cola.recuperarSiguienteCliente();

				if (cliente != null) {
					log("Vendiendo entrada a " + cliente.getNombre());
					Thread.sleep(TIEMPO_MIN_VENTA + random.nextInt(TIEMPO_MAX_VENTA - TIEMPO_MIN_VENTA));
					log("Entrada vendida a " + cliente.getNombre());
					cola.finalizarCliente(cliente);
				} else {
					Thread.sleep(ESPERA_TAQUILLA);
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void asignarCola(ColaCine cola) {
		this.cola = cola;
	}

	private void log(String mensaje) {
		System.out.println("\t[" + Thread.currentThread().threadId() + "][" + nombre + "] "
				+ (System.currentTimeMillis() - timestampInicio) + ": " + mensaje);
	}
}
