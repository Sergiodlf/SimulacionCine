package org.cuatrovientos.dam.psp.simuladorCine;

import java.util.ArrayList;
import java.util.List;

public class ColaCine {
	private int asientosDisponibles;
	private int capacidadMaximaCola;

	// Listas para los estados de los clientes
	private List<Cliente> clientesRecibidos;
	private List<Cliente> clientesEnCola;
	private List<Cliente> clientesEnAtencion;
	private List<Cliente> clientesAtendidos;
	private List<Cliente> clientesSinEntrada;

	public ColaCine(int asientosDisponibles, int capacidadMaximaCola) {
		super();
		this.asientosDisponibles = asientosDisponibles;
		this.capacidadMaximaCola = capacidadMaximaCola;
		this.clientesRecibidos = new ArrayList<>();
		this.clientesEnCola = new ArrayList<>();
		this.clientesEnAtencion = new ArrayList<>();
		this.clientesAtendidos = new ArrayList<>();
		this.clientesSinEntrada = new ArrayList<>();
	}

	// Método para añadir un cliente nuevo a la cola
	public synchronized boolean agregarCliente(Cliente cliente) {
		log("Llega a la cola cliente: " + cliente.getNombre());
		clientesRecibidos.add(cliente);

		if (clientesEnCola.size() >= capacidadMaximaCola) { // Añadir
			clientesSinEntrada.add(cliente);
			log("Cola llena, cliente " + cliente.getNombre() + " se va");
			return false;
		}

		clientesEnCola.add(cliente);
		return true;
	}

	// Función para conseguir el siguiente cliente de la cola
	public synchronized Cliente recuperarSiguienteCliente() {
		if (clientesEnCola.isEmpty()) {
			return null;
		}

		Cliente c = clientesEnCola.remove(0);

		if (asientosDisponibles <= 0) {
			clientesSinEntrada.add(c);
			log("No hay asientos, cliente se queda sin entrada: " + c.getNombre());
			return null;
		}

		clientesEnAtencion.add(c);
		asientosDisponibles--;

		log("Cliente pasa a taquilla: " + c.getNombre());
		return c;
	}

	// Método para finalizar la venta de un cliente
	public synchronized void finalizarCliente(Cliente cliente) {
		clientesEnAtencion.remove(cliente);
		clientesAtendidos.add(cliente);
		log("Cliente atendido: " + cliente.getNombre());
	}

	private void log(String mensaje) {
		System.out.println("[" + Thread.currentThread().threadId() + "][ COLA ] " + mensaje);
	}

	@Override
	public synchronized String toString() {
		return "ColaCine [ ColaUnica ] " + "Recibidos=" + clientesRecibidos.size() + ", EnCola=" + clientesEnCola.size()
				+ ", Atendidos=" + clientesAtendidos.size() + ", SinEntrada=" + clientesSinEntrada.size()
				+ ", AsientosRestantes=" + asientosDisponibles;
	}
}
