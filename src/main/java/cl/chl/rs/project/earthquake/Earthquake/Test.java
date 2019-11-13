package cl.chl.rs.project.earthquake.Earthquake;

import cl.chl.rs.project.earthquake.service.EarthquakeService;

public class Test {

	public static void main(String[] args) {
		EarthquakeService service = new EarthquakeService();
		String fechaIni = "2019-09-10";
		String fechaFin = "2019-09-11";
//		String country = "US";
//		double fechaIni = 3.2;
//		double fechaFin = 5.4;
		service.obtieneDatosPorFecha(fechaIni, fechaFin);
	}

}
