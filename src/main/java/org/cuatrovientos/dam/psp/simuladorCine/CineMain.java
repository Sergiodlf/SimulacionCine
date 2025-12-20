package org.cuatrovientos.dam.psp.simuladorCine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CineMain {

	// DATOS CONFIGURABLES
	private static final int NUMERO_TAQUILLAS = 2;
	private static final int NUMERO_COLAS = 4;
	private static final int ASIENTOS_DISPONIBLES = 200;
	private static final int CAPACIDAD_MAXIMA_COLA = 10;
	private static final int TIEMPO_MIN_VENTA = 20_000;
	private static final int TIEMPO_MAX_VENTA = 30_000;
	private static final Duration INTERVALO_MONITORIZACION = Duration.ofSeconds(10);

	public static void main(String[] args) {

		// Crear colas, taquillas y generador de clientes
		List<ColaCine> colas = new ArrayList<>();
		for (int i = 1; i <= NUMERO_COLAS; i++) {
			colas.add(new ColaCine("Cola_" + i, ASIENTOS_DISPONIBLES, CAPACIDAD_MAXIMA_COLA));
		}

		// Crear taquillas, asignarles las colas e iniciar hilos
		for (int i = 1; i <= NUMERO_TAQUILLAS; i++) {
			Taquilla t = new Taquilla("Taquilla_" + i, TIEMPO_MIN_VENTA, TIEMPO_MAX_VENTA);
			t.asignarColas(colas);
			new Thread(t).start();
		}

		// Crear generador, asignarle las colas e iniciar hilo
		GeneradorClientes generador = new GeneradorClientes();
		generador.asignarColas(colas);
		new Thread(generador).start();

		// Abrir el cine
		try {
			boolean cineAbierto = true;
			while (cineAbierto) {
				Thread.sleep(INTERVALO_MONITORIZACION);
				for (ColaCine cola : colas) {
					System.out.println("[" + Thread.currentThread().threadId() + "][MAIN]: " + cola.toString());
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
