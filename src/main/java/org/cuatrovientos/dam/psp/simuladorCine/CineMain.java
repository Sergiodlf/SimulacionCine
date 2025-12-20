package org.cuatrovientos.dam.psp.simuladorCine;

import java.time.Duration;

public class CineMain {

	// DATOS CONFIGURABLES
	private static final int ASIENTOS_DISPONIBLES = 200;
	private static final int CAPACIDAD_MAXIMA_COLA = 10;
	private static final Duration INTERVALO_MONITORIZACION = Duration.ofSeconds(10);

	public static void main(String[] args) {
		// Crear cola, taquillas y generador de clientes
		ColaCine cola = new ColaCine(ASIENTOS_DISPONIBLES, CAPACIDAD_MAXIMA_COLA);
		Taquilla taquilla1 = new Taquilla("Taquilla_1");
		Taquilla taquilla2 = new Taquilla("Taquilla_2");
		GeneradorClientes generador = new GeneradorClientes();

		// Asignar la cola a las taquillas y el generador de clientes
		taquilla1.asignarCola(cola);
		taquilla2.asignarCola(cola);
		generador.asignarCola(cola);

		// Iniciar los hilos
		new Thread(taquilla1).start();
		new Thread(taquilla2).start();
		new Thread(generador).start();

		// Abrir el cine
		try {
			boolean cineAbierto = true;
			while (cineAbierto) {
				Thread.sleep(INTERVALO_MONITORIZACION);
				System.out.println("[" + Thread.currentThread().threadId() + "][MAIN]: " + cola.toString());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
