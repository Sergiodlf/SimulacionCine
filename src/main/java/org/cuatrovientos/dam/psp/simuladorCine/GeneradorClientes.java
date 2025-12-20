package org.cuatrovientos.dam.psp.simuladorCine;

import java.time.Duration;
import java.util.List;

public class GeneradorClientes implements Runnable {

	private static final Duration ESPERA_NUEVO_CLIENTE = Duration.ofMillis(4000);

	private List<ColaCine> colas;
	private long timestampInicio = 0;

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

				boolean agregado = false;
				for (ColaCine cola : colas) {
					if (cola.agregarCliente(cliente)) {
						log("Se manda a la cola nuevo cliente: " + cliente.getNombre());
						agregado = true;
						break;
					}
				}

				if (!agregado) {
					System.out.println("[GENERADOR] Cliente se va por colas llenas: " + cliente.getNombre());
				}

				Thread.sleep(ESPERA_NUEVO_CLIENTE);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public void asignarColas(List<ColaCine> colas) {
		this.colas = colas;
	}

	private void log(String mensaje) {
		System.out.println("[" + Thread.currentThread().threadId() + "][ GENERADOR ] "
				+ (System.currentTimeMillis() - timestampInicio) + ": " + mensaje);
	}
}
