package distribucion;

import java.util.ArrayList;

public class Instancia {

	private ArrayList<Cliente> clientes;
	private ArrayList<CentroDistribucion> centros; 
	private int k;
	
	public Instancia() { 
		this.clientes = new ArrayList<Cliente>();
		this.centros = new ArrayList<CentroDistribucion>();
		this.k = 0;
	}
	
	public Instancia(ArrayList<Cliente> clientes, ArrayList<CentroDistribucion> centros, int k) {
		this.clientes = clientes;
		this.centros = centros;
		this.k = k;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<CentroDistribucion> getCentros() {
		return (ArrayList<CentroDistribucion>) centros.clone();
	}

	public int getK() {
		return k;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
		
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
		for (CentroDistribucion centroDistribucion : this.centros) {
			centroDistribucion.distanciaMedia(clientes);
		}
	}

	public void setCentros(ArrayList<CentroDistribucion> centros) {
		this.centros = centros;
		for (CentroDistribucion centroDistribucion : this.centros) {
			centroDistribucion.distanciaMedia(clientes);
		}
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getCantidadDeCentros() {
		if(this.centros == null || this.centros.isEmpty())
		{ 
			return 0;
		}
		
		return this.centros.size();
	}

}
