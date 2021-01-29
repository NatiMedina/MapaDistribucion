package distribucion;

import java.util.Comparator;

public class EstrategiaDistanciaMedia implements Comparator<CentroDistribucion> {
	
	@Override
	public int compare(CentroDistribucion arg0, CentroDistribucion arg1) {
		if (arg0.getDistanciaMedia() > arg1.getDistanciaMedia())
			return 1;
		else if (arg0.getDistanciaMedia() == arg1.getDistanciaMedia())
			return 0;
		else
			return -1;
	}
	
}
