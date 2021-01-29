package distribucion;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Administradora {
	private static Gson gson = new Gson();
	private Instancia instancia;
	private Solucion solucion;

	public Administradora() {
		this.instancia = new Instancia();
	}

	public void cargarJsonCliente(String path) throws IOException {
		Reader reader = getReader(path);
		if (reader != null) {
			ArrayList<Cliente> clientes = gson.fromJson(reader, new TypeToken<ArrayList<Cliente>>() {
			}.getType());
			this.instancia.setClientes(clientes);
		}
	}

	public void cargarJsonCentroDistribucion(String path) throws IOException {
		Reader reader = getReader(path);
		if (reader != null) {
			ArrayList<CentroDistribucion> centros = gson.fromJson(reader,
					new TypeToken<ArrayList<CentroDistribucion>>() {
					}.getType());
			this.instancia.setCentros(centros);
		}
	}

	public void setK(int k) {
		this.instancia.setK(k);
	}

	public void calcularSolucion() {
		Solver solver = new Solver(this.instancia, new EstrategiaDistanciaMedia());
		this.solucion = solver.resolver();
	}
	
	public ArrayList<CentroDistribucion> getCentrosDistribucion(){ 
		return this.instancia.getCentros();
	}
	
	public ArrayList<Cliente> getClientes(){ 
		return this.instancia.getClientes();
	}
	
	public ArrayList<CentroDistribucion> getCentrosSeleccionados(){ 
		return this.solucion.getCentros();
	}
	
	public double distanciaMedia() { 
		return this.solucion.getDistanciaMedia();
	}

	private Reader getReader(String path) {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reader;
	}

	public int cantidadDeCentros() {
		return this.instancia.getCantidadDeCentros();
	}
	
	public String getExtraInfo() { 
		return this.solucion.getExtraInfo();
	}
	
	
}
