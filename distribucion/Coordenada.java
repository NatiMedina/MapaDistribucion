package distribucion;

public class Coordenada {
	private static final double RADIO = 6371e3;
	private static final double RADIAN = Math.PI / 180;

	private double latitud;
	private double longitud;

	public Coordenada(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public double calcularDistancia(Coordenada otra) {
		
		/*
		 * const R = 6371e3; // metres const φ1 = lat1 * Math.PI/180; // φ, λ in radians
		 * const φ2 = lat2 * Math.PI/180; const Δφ = (lat2-lat1) * Math.PI/180; const Δλ
		 * = (lon2-lon1) * Math.PI/180;
		 * 
		 * const a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + Math.cos(φ1) * Math.cos(φ2) *
		 * Math.sin(Δλ/2) * Math.sin(Δλ/2); const c = 2 * Math.atan2(Math.sqrt(a),
		 * Math.sqrt(1-a));
		 * 
		 * const d = R * c; // in metres
		 */
		
		double lat = this.latitud * RADIAN;
		double lat2 = otra.latitud * RADIAN;

		double distanciaLat = (lat2 - lat) * RADIAN;
		double distanciaLon = (otra.longitud - longitud) * RADIAN;

		double a = Math.sin(distanciaLat / 2) * Math.sin(distanciaLat / 2)
				+ Math.cos(lat) * Math.cos(lat2) * Math.sin(distanciaLon / 2) * Math.sin(distanciaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return RADIO * c;
	}
	@Override
	public String toString() {
		
		return "["+ latitud + "," + longitud +"]";
	}
}
