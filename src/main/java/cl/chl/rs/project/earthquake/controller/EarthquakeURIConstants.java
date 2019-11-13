package cl.chl.rs.project.earthquake.controller;

/**
 * Clase que tiene los parametros de los servicios REST
 *
 */
public class EarthquakeURIConstants {
	public static final String OBTENEREARTHQUAKEFECHA = "/services/obtainbyfecha/{fechaIni}/{fechaFin}";
	public static final String OBTENEREARTHQUAKEMAGNITUD = "/services/obtainbymagnitud/{magnitudIni}/{magnitudMax}";
	public static final String OBTENEREARTHQUAKEDOSFECHAS = "/services/obtainbytwodates";
	public static final String OBTENEREARTHQUAKECOUNTRY = "/services/obtainbycountry/{country}";
	public static final String OBTENEREARTHQUAKEDATECOUNTRY = "/services/obtainbydateandcountry";
}
