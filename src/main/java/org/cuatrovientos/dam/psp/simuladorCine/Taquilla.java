package org.cuatrovientos.dam.psp.simuladorCine;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Taquilla implements Runnable {

	private static final Duration ESPERA_TAQUILLA = Duration.ofMillis(1000);

	private String nombre;
	private List<ColaCine> colas;
	private long timestampInicio = 0;
	private Random random = new Random();
	private int tiempoMinVenta;
	private int tiempoMaxVenta;

	public Taquilla(String nombre, int tiempoMinVenta, int tiempoMaxVenta) {
		super();
		this.nombre = nombre;
		this.tiempoMinVenta = tiempoMinVenta;
		this.tiempoMaxVenta = tiempoMaxVenta;
	}

	@Override
	public void run() {
		timestampInicio = System.currentTimeMillis();

		try {
			boolean taquillaAbierta = true;
			while (taquillaAbierta) {

				boolean atendio = false;
				for (ColaCine cola : colas) {
					Cliente cliente = cola.recuperarSiguienteCliente();
					if (cliente != null) {
						log("Vendiendo entrada a " + cliente.getNombre());
						Thread.sleep(tiempoMinVenta + random.nextInt(tiempoMaxVenta - tiempoMinVenta));
						cola.finalizarCliente(cliente);
						log("Entrada vendida a " + cliente.getNombre());
						atendio = true;
						break;
					}
				}

				if (!atendio) {
					Thread.sleep(ESPERA_TAQUILLA);
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void asignarColas(List<ColaCine> colas) {
		this.colas = colas;
	}

	private void log(String mensaje) {
		System.out.println("\t[" + Thread.currentThread().threadId() + "][" + nombre + "] "
				+ (System.currentTimeMillis() - timestampInicio) + ": " + mensaje);
	}
}
