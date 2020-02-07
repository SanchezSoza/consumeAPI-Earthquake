package cl.chl.rs.project.earthquake.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.chl.rs.project.earthquake.model.EntradaModel;
import cl.chl.rs.project.earthquake.model.SalidaModel;
import cl.chl.rs.project.earthquake.service.EarthquakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase Controller, encargada de exponer los servicios REST para ser consumidos.
 * {@link #obtainDataByDate(String, String)}
 * {@link #obtainDataByMagnitud(double, double)}
 * {@link #obtainDataByTwoDates(EntradaModel)}
 * {@link #obtainDataByCountry(String)}
 * {@link #obtainDataByDateAndCountry(EntradaModel)}
 */
@Controller
@Api(tags = "Earthquake")
public class EarthquakeController {
	
	/**
	 * Variable para escribir en el Log
	 */
	private final static Logger logger = LoggerFactory.getLogger(EarthquakeController.class);
	
	/**
	 * 
	 * @param fechaIni
	 * @param fechaFin
	 * @return lista
	 * Servicio REST encargado de retornar los sismos en un rango de fechas
	 */
	@CrossOrigin
	@PostMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEFECHA)
	@ApiOperation(value = "Obtiene datos por fecha", notes = "Retorna la informacion de los sismos en un rango de fechas")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody List<SalidaModel> obtainDataByDate(@RequestBody EntradaModel entrada) {
		logger.info("Fecha Inicial: "+entrada.getStarttime());
		logger.info("Fecha Final: "+entrada.getEndtime());
		List<SalidaModel> lista = new ArrayList<SalidaModel>();
		EarthquakeService service = new EarthquakeService();
		lista = service.obtieneDatosPorFecha(entrada.getStarttime(), entrada.getEndtime());
		logger.info("tamano lista controller: "+lista.size());
		return lista;
	}
	
	/**
	 * 
	 * @param magnitudIni
	 * @param magnitudMax
	 * @return lista
	 * Servicio REST encargado de retornar los sismos en un rango de magnitudes
	 */
	@CrossOrigin
	@PostMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEMAGNITUD)
	@ApiOperation(value = "Obtiene datos por magnitud", notes = "Retorna la informacion de los sismos en un rango de magnitudes")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody List<SalidaModel> obtainDataByMagnitud(@RequestBody EntradaModel entrada) {
		logger.info("Magnitud Inicial: "+entrada.getMagnitudIni());
		logger.info("Magnitud Final: "+entrada.getMagnitudFin());
		List<SalidaModel> lista = new ArrayList<SalidaModel>();
		EarthquakeService service = new EarthquakeService();
		lista = service.obtieneDatosPorMagnitud(entrada.getMagnitudIni(), entrada.getMagnitudFin());
		logger.info("tamano lista controller: "+lista.size());
		return lista;
	}
	
	/**
	 * 
	 * @param entrada
	 * @return lista
	 * Servicio REST encargado de retornar los sismos en un rango de fechas, recibe 4 fechas,
	 * 2 fechas de entrada y 2 fechas de salida
	 */
	@CrossOrigin
	@PostMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEDOSFECHAS)
	@ApiOperation(value = "Obtiene datos por dos fechas", notes = "Retorna la informacion de los sismos en un rango de fechas")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody List<SalidaModel> obtainDataByTwoDates(@RequestBody EntradaModel entrada) {
		logger.info("Variables de fechas: "+entrada.getStarttime()+" - "+entrada.getEndtime());
		logger.info("Variables de fechas2: "+entrada.getStarttime2()+" - "+entrada.getEndtime2());
		List<SalidaModel> listaSalida = new ArrayList<SalidaModel>();
		EarthquakeService service = new EarthquakeService();
		listaSalida = service.obtieneDatosPorDosFechas(entrada.getStarttime(),entrada.getEndtime(), entrada.getStarttime2(), entrada.getEndtime2());
		return listaSalida;
	}
	
	/**
	 * 
	 * @param country
	 * @return lista
	 * Servicio REST encargado de retornar los sismos de un pais
	 */
	@CrossOrigin
	@PostMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKECOUNTRY)
	@ApiOperation(value = "Obtiene datos por pais", notes = "Retorna la informacion de los sismos por un pais")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody List<SalidaModel> obtainDataByCountry(@RequestBody EntradaModel entrada) {
		logger.info("Valor de Country: "+entrada.getCountry());
		List<SalidaModel> listaSalida = new ArrayList<SalidaModel>();
		EarthquakeService service = new EarthquakeService();
		listaSalida = service.obtieneDatosPorPais(entrada.getCountry());
		return listaSalida;
	}
	
	/**
	 * 
	 * @param entrada
	 * @return lista
	 * Servicio REST encargado de retornar los sismos en un rango de fechas y por un pais
	 */
	@CrossOrigin
	@PostMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEDATECOUNTRY)
	@ApiOperation(value = "Obtiene datos por pais y fechas", notes = "Retorna la informacion de los sismos por un pais y fechas")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody List<SalidaModel> obtainDataByDateAndCountry(@RequestBody EntradaModel entrada) {
		logger.info("Variables de fechas: "+entrada.getStarttime()+" - "+entrada.getEndtime());
		logger.info("Variables de fechas2: "+entrada.getStarttime2()+" - "+entrada.getEndtime2());
		logger.info("Variable country: "+entrada.getCountry());
		logger.info("Variable country2: "+entrada.getCountry2());
		List<SalidaModel> listaSalida = new ArrayList<SalidaModel>();
		EarthquakeService service = new EarthquakeService();
		listaSalida = service.obtieneDatosPorDosFechasYDosPaises(entrada.getStarttime(), entrada.getEndtime(), entrada.getStarttime2(), entrada.getEndtime(), entrada.getCountry(), entrada.getCountry2());
		return listaSalida;
	}
}
