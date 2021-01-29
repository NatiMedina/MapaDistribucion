package distribucion;

import java.util.ArrayList;

public class CentroDistribucion {

	private String nombre;
	private Coordenada coordenada;
	private double distanciaMedia;

	public CentroDistribucion(String nombre, Coordenada coordenada) {
		this.nombre = nombre;
		this.coordenada = coordenada;
		this.distanciaMedia = 0;
	}

	public void distanciaMedia(ArrayList<Cliente> clientes) {
		double distancia = 0;
		for (Cliente c : clientes) {
			distancia += c.obtenerDistancia(this.coordenada);
		}
		distanciaMedia = distancia / (clientes.size());
	}

	public String getNombre() {
		return nombre;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public double getDistanciaMedia() {
		return distanciaMedia;
	}

	@Override
	public String toString() {
		return nombre +" : " + distanciaMedia + "  " +coordenada;
	}
	
	public double getLatitud() { 
		return this.coordenada.getLatitud();
	}
	
	public double getLongitud() { 
		return this.coordenada.getLongitud();
	}
	
	

}
