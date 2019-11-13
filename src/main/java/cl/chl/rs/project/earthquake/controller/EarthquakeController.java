package cl.chl.rs.project.earthquake.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.chl.rs.project.earthquake.model.SalidaModel;
import cl.chl.rs.project.earthquake.service.EarthquakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Controller
@Api(tags = "Earthquake")
public class EarthquakeController {
	
	private final static Logger logger = LoggerFactory.getLogger(EarthquakeController.class);
	
	@CrossOrigin
	@GetMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEFECHA)
	@ApiOperation(value = "Obtiene datos por fecha", notes = "Retorna la informacion de los sismos en un rango de fechas")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody List<SalidaModel> obtainDataByDate(@PathVariable("fechaIni") String fechaIni, @PathVariable("fechaFin") String fechaFin) {
		List<SalidaModel> lista = new ArrayList<SalidaModel>();
		EarthquakeService service = new EarthquakeService();
		lista = service.obtieneDatosPorFecha(fechaIni, fechaFin);
		logger.info("tamano lista controller: "+lista.size());
		return lista;
	}
	
	@CrossOrigin
	@RequestMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEMAGNITUD, method = RequestMethod.GET)
	@ApiOperation(value = "Obtiene datos por magnitud", notes = "Retorna la informacion de los sismos en un rango de magnitudes")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody SalidaModel obtainDataByMagnitud(@PathVariable("magnitudIni") double magnitudIni, @PathVariable("magnitudMax") double magnitudMax) {
		return null;
	}
	
	@CrossOrigin
	@RequestMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEDOSFECHAS, method=RequestMethod.POST)
	@ApiOperation(value = "Obtiene datos por dos fechas", notes = "Retorna la informacion de los sismos en un rango de fechas")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody SalidaModel obtainDataByTwoDates() {
		return null;
	}
	
	@CrossOrigin
	@RequestMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKECOUNTRY, method=RequestMethod.POST)
	@ApiOperation(value = "Obtiene datos por pais", notes = "Retorna la informacion de los sismos por un pais")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody List<SalidaModel> obtainDataByCountry() {
		List<SalidaModel> listaSalida = new ArrayList<SalidaModel>();
		EarthquakeService service = new EarthquakeService();
		service.obtieneDatosPorPais("AK");
		return listaSalida;
	}
	
	@CrossOrigin
	@RequestMapping(value = EarthquakeURIConstants.OBTENEREARTHQUAKEDATECOUNTRY, method=RequestMethod.POST)
	@ApiOperation(value = "Obtiene datos por pais y fechas", notes = "Retorna la informacion de los sismos por un pais y fechas")
	@ApiResponses({@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
		@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
		@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND")
	})
	public @ResponseBody SalidaModel obtainDataByDateAndCountry() {
		return null;
	}
}
