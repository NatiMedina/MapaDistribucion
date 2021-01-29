package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

import distribucion.CentroDistribucion;
import distribucion.EstrategiaDistanciaMedia;

class TestEstrategiaDistanciaMedia {

	@Test
	void testEstrategia_Mayor(){
		//Arrange
		CentroDistribucion centroDistribucion = mock(CentroDistribucion.class);
		when(centroDistribucion.getDistanciaMedia()).thenReturn(1D);
		
		CentroDistribucion centroDistribucion2 = mock(CentroDistribucion.class);
		when(centroDistribucion2.getDistanciaMedia()).thenReturn(0D);
		
		EstrategiaDistanciaMedia estrategiaDistanciaMedia = new EstrategiaDistanciaMedia();
		
		//Act
		int resultado = estrategiaDistanciaMedia.compare(centroDistribucion, centroDistribucion2);
		
		
		//Assert
		assertEquals(1, resultado);
	}
	
	@Test
	void testEstrategia_igual(){
		//Arrange
		CentroDistribucion centroDistribucion = mock(CentroDistribucion.class);
		when(centroDistribucion.getDistanciaMedia()).thenReturn(0D);
		
		CentroDistribucion centroDistribucion2 = mock(CentroDistribucion.class);
		when(centroDistribucion2.getDistanciaMedia()).thenReturn(0D);
		
		EstrategiaDistanciaMedia estrategiaDistanciaMedia = new EstrategiaDistanciaMedia();
		
		//Act
		int resultado = estrategiaDistanciaMedia.compare(centroDistribucion, centroDistribucion2);
		
		
		//Assert
		assertEquals(0, resultado);
	}
	
	@Test
	void testEstrategia_Menor(){
		//Arrange
		CentroDistribucion centroDistribucion = mock(CentroDistribucion.class);
		when(centroDistribucion.getDistanciaMedia()).thenReturn(0D);
		
		CentroDistribucion centroDistribucion2 = mock(CentroDistribucion.class);
		when(centroDistribucion2.getDistanciaMedia()).thenReturn(1D);
		
		EstrategiaDistanciaMedia estrategiaDistanciaMedia = new EstrategiaDistanciaMedia();
		
		//Act
		int resultado = estrategiaDistanciaMedia.compare(centroDistribucion, centroDistribucion2);
		
		
		//Assert
		assertEquals(-1, resultado);
	}

}
