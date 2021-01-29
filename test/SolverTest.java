package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import distribucion.CentroDistribucion;
import distribucion.Cliente;
import distribucion.Coordenada;
import distribucion.EstrategiaDistanciaMedia;
import distribucion.Instancia;
import distribucion.Solucion;
import distribucion.Solver;

class SolverTest {

	@Test
	void testSolver_getSubConjunto() {
		//Arrange
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente("1", new Coordenada(0, 10)));
		clientes.add(new Cliente("2", new Coordenada(0, 0)));
		
		ArrayList<CentroDistribucion> centros = new ArrayList<CentroDistribucion>();
		centros.add(new CentroDistribucion("a", new Coordenada(0, 0)));
		centros.add(new CentroDistribucion("b", new Coordenada(0, 5)));
		centros.add(new CentroDistribucion("c", new Coordenada(0, 10)));
		
		Instancia instancia = new Instancia();
		instancia.setCentros(centros);
		instancia.setClientes(clientes);
		instancia.setK(1);
				
		Solver solver = new Solver(instancia, new EstrategiaDistanciaMedia());
		
		//Act
		Solucion s = solver.resolver();
		
		//Assert
		assertEquals(1, s.getCentros().size());
	}
	
	@Test
	void testSolver_getTodos() {
		//Arrange
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente("1", new Coordenada(0, 10)));
		clientes.add(new Cliente("2", new Coordenada(0, 0)));
		
		ArrayList<CentroDistribucion> centros = new ArrayList<CentroDistribucion>();
		centros.add(new CentroDistribucion("a", new Coordenada(0, 0)));
		centros.add(new CentroDistribucion("b", new Coordenada(0, 5)));
		centros.add(new CentroDistribucion("c", new Coordenada(0, 10)));
		
		Instancia instancia = new Instancia();
		instancia.setCentros(centros);
		instancia.setClientes(clientes);
		instancia.setK(10);
				
		Solver solver = new Solver(instancia, new EstrategiaDistanciaMedia());
		
		//Act
		Solucion s = solver.resolver();
		
		//Assert
		assertEquals(3, s.getCentros().size());
	}

}
