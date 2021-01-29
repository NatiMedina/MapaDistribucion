package distribucion;

import java.util.ArrayList;

public class Cliente {

	private String nombre;
	private Coordenada coordenada;

	public Cliente(String nombre, Coordenada coordenadas) {

		this.nombre = nombre;
		this.coordenada = coordenadas;
	}

	public String getNombre() {
		return nombre;
	}

	public Coordenada getCoordenadas() {
		return coordenada;
	}

	public double obtenerDistancia(Coordenada coordenada) {
		if(coordenada == null)
			return 0;
		
		return this.coordenada.calcularDistancia(coordenada);
	}

	public double getLatitud() {
		return this.coordenada.getLatitud();
	}

	public double getLongitud() {
		return this.coordenada.getLongitud();
	}

	@Override
	public String toString() {
		return nombre + " : " + coordenada;
	}

	public CentroDistribucion centroMasCercano(ArrayList<CentroDistribucion> centros) {
		if (centros == null || centros.isEmpty()) {
			return null;
		}

		CentroDistribucion centro = centros.get(0);
		double distancia = obtenerDistancia(centro.getCoordenada());
		
		for (CentroDistribucion centroDistribucion : centros) {
			if (distancia > obtenerDistancia(centroDistribucion.getCoordenada())) {
				centro = centroDistribucion;
				distancia = obtenerDistancia(centro.getCoordenada());
			}
		}

		return centro;

	}

}
