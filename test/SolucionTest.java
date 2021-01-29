package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import distribucion.CentroDistribucion;
import distribucion.Cliente;
import distribucion.Coordenada;
import distribucion.Solucion;

class SolucionTest {

	@Test
	void testDistanciaMedia_CentrosNull() {
		//Arrange
		Solucion solucion = new Solucion(new ArrayList<Cliente>(), null);
		
		//Act
		double distancia = solucion.getDistanciaMedia();
		
		//Assert
		assertEquals(0D, distancia);
	}
	
	@Test
	void testDistanciaMedia_CentrosEmpty() {
		//Arrange
		Solucion solucion = new Solucion(new ArrayList<Cliente>(), new ArrayList<CentroDistribucion>());
		
		//Act
		double distancia = solucion.getDistanciaMedia();
		
		//Assert
		assertEquals(0D, distancia);
	}
	
	@Test
	void testDistanciaMedia_ConCentros() {
		//Arrange
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente("1", new Coordenada(0, 10)));
		clientes.add(new Cliente("2", new Coordenada(0, 0)));
		
		ArrayList<CentroDistribucion> centros = new ArrayList<CentroDistribucion>();
		centros.add(new CentroDistribucion("a", new Coordenada(0, 0)));
		
		Solucion solucion = new Solucion(clientes, centros);
		
		//Act
		double distancia = solucion.getDistanciaMedia();
		
		//Assert
		assertEquals(555974.6332227936D, distancia);
	}

}
