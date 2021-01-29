package distribucion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solver {

	private Instancia instancia;
	private Comparator<CentroDistribucion> comparator;

	public Solver(Instancia instancia, Comparator<CentroDistribucion> comparator) {
		this.instancia = instancia;
		this.comparator = comparator;
	}

	public Solucion resolver() {

		ArrayList<CentroDistribucion> ordenados = centrosOrdenados();

		if (ordenados.size() > instancia.getK()) {
			ArrayList<CentroDistribucion> centros = new ArrayList<CentroDistribucion>(
					ordenados.subList(0, instancia.getK()));

			return new Solucion(instancia.getClientes(), centros);
		}

		return new Solucion(instancia.getClientes(), ordenados);
	}

	private ArrayList<CentroDistribucion> centrosOrdenados() {
		ArrayList<CentroDistribucion> ret = instancia.getCentros();
		Collections.sort(ret, comparator);
		return ret;
	}
}
