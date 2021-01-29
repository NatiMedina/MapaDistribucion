package distribucion;

import java.util.ArrayList;
import java.util.HashMap;

public class Solucion {

	private ArrayList<CentroDistribucion> centros;
	private ArrayList<Cliente> clientes;
	private HashMap<CentroDistribucion, ArrayList<Cliente>> clientesPorCentro;
	private double distanciaMedia;

	public Solucion(ArrayList<Cliente> clientes, ArrayList<CentroDistribucion> centros) {
		this.centros = centros;
		this.clientes = clientes;
		this.clientesPorCentro = new HashMap<CentroDistribucion, ArrayList<Cliente>>();
		calcularDistanciaMedia();
	}

	public ArrayList<CentroDistribucion> getCentros() {
		return centros;
	}

	public double getDistanciaMedia() {
		return this.distanciaMedia;
	}

	private void calcularDistanciaMedia() {
		if (centros != null && !centros.isEmpty()) {
			this.distanciaMedia = 0;

			for (Cliente cliente : clientes) {
				CentroDistribucion centro = cliente.centroMasCercano(centros);

				this.distanciaMedia += cliente.obtenerDistancia(centro.getCoordenada());

				if (!clientesPorCentro.containsKey(centro)) {
					clientesPorCentro.put(centro, new ArrayList<Cliente>());
				}
				clientesPorCentro.get(centro).add(cliente);
			}

			if (!clientes.isEmpty()) {
				this.distanciaMedia /= clientes.size();
			}
		}
	}

	public String getExtraInfo() {
		return this.clientesPorCentro.toString();
	}

}
