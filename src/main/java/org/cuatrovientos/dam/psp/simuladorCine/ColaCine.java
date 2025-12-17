package org.cuatrovientos.dam.psp.simuladorCine;

import java.util.ArrayList;
import java.util.List;

public class ColaCine {
	private int asientosDisponibles;

	private List<Cliente> clientesRecibidos;
	private List<Cliente> clientesEnCola;
	private List<Cliente> clientesEnAtencion;
	private List<Cliente> clientesAtendidos;
	private List<Cliente> clientesSinEntrada;

	public ColaCine(int asientosDisponibles) {
		super();
		this.asientosDisponibles = asientosDisponibles;
		this.clientesRecibidos = new ArrayList<>();
		this.clientesEnCola = new ArrayList<>();
		this.clientesEnAtencion = new ArrayList<>();
		this.clientesAtendidos = new ArrayList<>();
		this.clientesSinEntrada = new ArrayList<>();
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
