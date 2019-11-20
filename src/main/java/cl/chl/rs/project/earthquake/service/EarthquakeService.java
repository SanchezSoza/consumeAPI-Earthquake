package cl.chl.rs.project.earthquake.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.chl.rs.earthquake.util.IniProperties;
import cl.chl.rs.project.earthquake.model.SalidaModel;


/**
 * Clase service, encargada de consumir los servicios REST externos y aplicar la logica de negocio
 * {@link #obtieneDatosPorFecha(String, String)}
 * {@link #obtieneDatosPorMagnitud(double, double)}
 * {@link #obtieneDatosPorDosFechas(String, String, String, String)}
 * {@link #obtieneDatosPorPais(String)}
 * {@link #obtieneDatosPorDosFechasYDosPaises(String, String, String, String, String, String)}
 */
public class EarthquakeService {

	/**
	 * Variable para escribir en el Log
	 */
	private final static Logger logger = LoggerFactory.getLogger(EarthquakeService.class);

	/**
	 * 
	 * @param fechaIni
	 * @param fechaFin
	 * @return lista
	 * Metodo encargado de consumir la API https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson
	 * que retorna la informacion en un periodo de tiempo
	 */
	public List<SalidaModel> obtieneDatosPorFecha(String fechaIni, String fechaFin) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
		if(IniProperties.inicializador()) {
			urlAux = IniProperties.getPropiedad("prop.url");
		}
		String url = urlAux + "&starttime="+fechaIni+"&endtime="+fechaFin	;
    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<SalidaModel> lista = new ArrayList<SalidaModel>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuffer resultado = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			logger.info("Resultado: "+result);
			JSONObject jo = new JSONObject(result); 
			JSONArray msg = jo.getJSONArray("features");
			for(int i = 0; i < msg.length(); i++) {
				JSONObject jObj = msg.getJSONObject(i);
				JSONObject jObj2 = jObj.getJSONObject("properties");
				SalidaModel properties = new SalidaModel();
				
				properties.setDmin(isNullDouble(String.valueOf(jObj2.get("dmin"))));
				properties.setCode(isNull(String.valueOf(jObj2.get("code"))));
				properties.setSources(isNull(String.valueOf(jObj2.get("sources"))));
				properties.setTz(isNullInt(String.valueOf(jObj2.get("tz"))));
				properties.setNst(isNull(String.valueOf(jObj2.get("nst"))));
				properties.setType(isNull(String.valueOf(jObj2.get("type"))));
				properties.setTitle(isNull(String.valueOf(jObj2.get("title"))));
				properties.setMagType(isNull(String.valueOf("magType")));
				properties.setSig(isNullInt(String.valueOf(jObj2.get("sig"))));
				properties.setTsunami(isNullInt(String.valueOf(jObj2.get("tsunami"))));
				properties.setMag(isNullDouble(String.valueOf(jObj2.get("mag"))));
				properties.setGap(isNullDouble(String.valueOf(jObj2.get("gap"))));
				properties.setRms(isNullDouble(String.valueOf(jObj2.get("rms"))));
				properties.setPlace(isNull(String.valueOf(jObj2.get("place"))));
				properties.setNet(isNull(String.valueOf(jObj2.get("net"))));
				properties.setTypes(isNull(String.valueOf(jObj2.get("types"))));
				properties.setUrl2(isNull(String.valueOf(jObj2.get("url"))));
				properties.setIds(isNull(String.valueOf(jObj2.get("ids"))));
				properties.setTime(isNull(String.valueOf(jObj2.get("time"))));
				properties.setDetail(isNull(String.valueOf(jObj2.get("detail"))));
				properties.setUpdate(isNull(String.valueOf(jObj2.get("updated"))));
				properties.setStatusApi(isNull(String.valueOf(jObj2.get("status"))));
	            if(properties.getStatusApi() != null) {
	            	lista.add(properties);
	            }
	        } 	
		} catch (IOException e) {
			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getMessage());
			logger.error("Error <obtieneDatosPorFecha> IOException: "+e.getCause());
		}
		logger.info("Tamano de la lista: "+lista.size());
		return lista;
	}
	
	/**
	 * 
	 * @param magnitudIni
	 * @param magnitudFin
	 * @return lista
	 * Metodo encargado de consumir la API https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson
	 * en un rango de magnitudes 
	 */
	public List<SalidaModel> obtieneDatosPorMagnitud(double magnitudIni, double magnitudFin) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
		if(IniProperties.inicializador()) {
			urlAux = IniProperties.getPropiedad("prop.url");
		}
		String url = urlAux + "&minmagnitude="+magnitudIni+"&maxmagnitude="+magnitudFin;
    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<SalidaModel> lista = new ArrayList<SalidaModel>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuffer resultado = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			logger.info("Resultado: "+result);
			JSONObject jo = new JSONObject(result); 
			JSONArray msg = jo.getJSONArray("features");
			for(int i = 0; i < msg.length(); i++) {
				JSONObject jObj = msg.getJSONObject(i);
				JSONObject jObj2 = jObj.getJSONObject("properties");
				SalidaModel properties = new SalidaModel();
				
				properties.setDmin(isNullDouble(String.valueOf(jObj2.get("dmin"))));
				properties.setCode(isNull(String.valueOf(jObj2.get("code"))));
				properties.setSources(isNull(String.valueOf(jObj2.get("sources"))));
				properties.setTz(isNullInt(String.valueOf(jObj2.get("tz"))));
				properties.setNst(isNull(String.valueOf(jObj2.get("nst"))));
				properties.setType(isNull(String.valueOf(jObj2.get("type"))));
				properties.setTitle(isNull(String.valueOf(jObj2.get("title"))));
				properties.setMagType(isNull(String.valueOf("magType")));
				properties.setSig(isNullInt(String.valueOf(jObj2.get("sig"))));
				properties.setTsunami(isNullInt(String.valueOf(jObj2.get("tsunami"))));
				properties.setMag(isNullDouble(String.valueOf(jObj2.get("mag"))));
				properties.setGap(isNullDouble(String.valueOf(jObj2.get("gap"))));
				properties.setRms(isNullDouble(String.valueOf(jObj2.get("rms"))));
				properties.setPlace(isNull(String.valueOf(jObj2.get("place"))));
				properties.setNet(isNull(String.valueOf(jObj2.get("net"))));
				properties.setTypes(isNull(String.valueOf(jObj2.get("types"))));
				properties.setUrl2(isNull(String.valueOf(jObj2.get("url"))));
				properties.setIds(isNull(String.valueOf(jObj2.get("ids"))));
				properties.setTime(isNull(String.valueOf(jObj2.get("time"))));
				properties.setDetail(isNull(String.valueOf(jObj2.get("detail"))));
				properties.setUpdate(isNull(String.valueOf(jObj2.get("updated"))));
				properties.setStatusApi(isNull(String.valueOf(jObj2.get("status"))));
	            if(properties.getStatusApi() != null) {
	            	lista.add(properties);
	            }
	        } 	
		} catch (IOException e) {
			logger.error("Error <obtieneDatosPorMagnitud> IOException: "+e.getMessage());
			logger.error("Error <obtieneDatosPorMagnitud> IOException: "+e.getCause());
		}
		return lista;
	}
	
	/**
	 * 
	 * @param fechaIni
	 * @param fechaFin
	 * @param fechaIni2
	 * @param fechaFin2
	 * @return lista
	 * Metodo encargado de consumir la API https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson
	 * en un rango de fechas
	 */
	public List<SalidaModel> obtieneDatosPorDosFechas(String fechaIni, String fechaFin, String fechaIni2, String fechaFin2) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
		if(IniProperties.inicializador()) {
			urlAux = IniProperties.getPropiedad("prop.url");
		}
		String url = urlAux + "&starttime="+fechaIni+"&endtime="+fechaFin;
    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<SalidaModel> lista = new ArrayList<SalidaModel>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuilder resultado = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			logger.info("Resultado: "+result);
			JSONObject jo = new JSONObject(result); 
			JSONArray msg = jo.getJSONArray("features");
			for(int i = 0; i < msg.length(); i++) {
				JSONObject jObj = msg.getJSONObject(i);
				JSONObject jObj2 = jObj.getJSONObject("properties");
				SalidaModel properties = new SalidaModel();
				
				properties.setDmin(isNullDouble(String.valueOf(jObj2.get("dmin"))));
				properties.setCode(isNull(String.valueOf(jObj2.get("code"))));
				properties.setSources(isNull(String.valueOf(jObj2.get("sources"))));
				properties.setTz(isNullInt(String.valueOf(jObj2.get("tz"))));
				properties.setNst(isNull(String.valueOf(jObj2.get("nst"))));
				properties.setType(isNull(String.valueOf(jObj2.get("type"))));
				properties.setTitle(isNull(String.valueOf(jObj2.get("title"))));
				properties.setMagType(isNull(String.valueOf("magType")));
				properties.setSig(isNullInt(String.valueOf(jObj2.get("sig"))));
				properties.setTsunami(isNullInt(String.valueOf(jObj2.get("tsunami"))));
				properties.setMag(isNullDouble(String.valueOf(jObj2.get("mag"))));
				properties.setGap(isNullDouble(String.valueOf(jObj2.get("gap"))));
				properties.setRms(isNullDouble(String.valueOf(jObj2.get("rms"))));
				properties.setPlace(isNull(String.valueOf(jObj2.get("place"))));
				properties.setNet(isNull(String.valueOf(jObj2.get("net"))));
				properties.setTypes(isNull(String.valueOf(jObj2.get("types"))));
				properties.setUrl2(isNull(String.valueOf(jObj2.get("url"))));
				properties.setIds(isNull(String.valueOf(jObj2.get("ids"))));
				properties.setTime(isNull(String.valueOf(jObj2.get("time"))));
				properties.setDetail(isNull(String.valueOf(jObj2.get("detail"))));
				properties.setUpdate(isNull(String.valueOf(jObj2.get("updated"))));
				properties.setStatusApi(isNull(String.valueOf(jObj2.get("status"))));
	            if(properties.getStatusApi() != null) {
	            	lista.add(properties);
	            }
	        } 	
			
			url = urlAux + "&starttime="+fechaIni2+"&endtime="+fechaFin2;
			logger.info("URL2: "+url);
			httpget = new HttpGet(url);
			response = httpclient.execute(httpget);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	resultado = new StringBuilder();
			line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			
			result = resultado.toString();
			logger.info("Resultado: "+result); 
			JSONObject jo2 = new JSONObject(result); 
			JSONArray msg2 = jo2.getJSONArray("features");
			for(int i = 0; i < msg2.length(); i++) {
				JSONObject jObj = msg2.getJSONObject(i);
				JSONObject jObj2 = jObj.getJSONObject("properties");
				SalidaModel properties = new SalidaModel();
				
				properties.setDmin(isNullDouble(String.valueOf(jObj2.get("dmin"))));
				properties.setCode(isNull(String.valueOf(jObj2.get("code"))));
				properties.setSources(isNull(String.valueOf(jObj2.get("sources"))));
				properties.setTz(isNullInt(String.valueOf(jObj2.get("tz"))));
				properties.setNst(isNull(String.valueOf(jObj2.get("nst"))));
				properties.setType(isNull(String.valueOf(jObj2.get("type"))));
				properties.setTitle(isNull(String.valueOf(jObj2.get("title"))));
				properties.setMagType(isNull(String.valueOf("magType")));
				properties.setSig(isNullInt(String.valueOf(jObj2.get("sig"))));
				properties.setTsunami(isNullInt(String.valueOf(jObj2.get("tsunami"))));
				properties.setMag(isNullDouble(String.valueOf(jObj2.get("mag"))));
				properties.setGap(isNullDouble(String.valueOf(jObj2.get("gap"))));
				properties.setRms(isNullDouble(String.valueOf(jObj2.get("rms"))));
				properties.setPlace(isNull(String.valueOf(jObj2.get("place"))));
				properties.setNet(isNull(String.valueOf(jObj2.get("net"))));
				properties.setTypes(isNull(String.valueOf(jObj2.get("types"))));
				properties.setUrl2(isNull(String.valueOf(jObj2.get("url"))));
				properties.setIds(isNull(String.valueOf(jObj2.get("ids"))));
				properties.setTime(isNull(String.valueOf(jObj2.get("time"))));
				properties.setDetail(isNull(String.valueOf(jObj2.get("detail"))));
				properties.setUpdate(isNull(String.valueOf(jObj2.get("updated"))));
				properties.setStatusApi(isNull(String.valueOf(jObj2.get("status"))));
	            if(properties.getStatusApi() != null) {
	            	lista.add(properties);
	            }
	        }
		} catch (IOException e) {
			logger.error("Error <obtieneDatosPorDosFechas> IOException: "+e.getMessage());
			logger.error("Error <obtieneDatosPorDosFechas> IOException: "+e.getCause());
		}
		return lista;
	}
	
	/**
	 * 
	 * @param country
	 * @return lista
	 * Metodo encargado de consumir la API https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson
	 * filtrando el contenido por el pais ingresado como parametro
	 */
	public List<SalidaModel> obtieneDatosPorPais(String country) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
		if(IniProperties.inicializador()) {
			urlAux = IniProperties.getPropiedad("prop.url");
		}
		String url = urlAux;
    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<SalidaModel> lista = new ArrayList<SalidaModel>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuffer resultado = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			logger.info("Resultado: "+result);
			JSONObject jo = new JSONObject(result); 
			JSONArray msg = jo.getJSONArray("features");
			for(int i = 0; i < msg.length(); i++) {
				JSONObject jObj = msg.getJSONObject(i);
				JSONObject jObj2 = jObj.getJSONObject("properties");
				SalidaModel properties = new SalidaModel();
				
				properties.setDmin(isNullDouble(String.valueOf(jObj2.get("dmin"))));
				properties.setCode(isNull(String.valueOf(jObj2.get("code"))));
				properties.setSources(isNull(String.valueOf(jObj2.get("sources"))));
				properties.setTz(isNullInt(String.valueOf(jObj2.get("tz"))));
				properties.setNst(isNull(String.valueOf(jObj2.get("nst"))));
				properties.setType(isNull(String.valueOf(jObj2.get("type"))));
				properties.setTitle(isNull(String.valueOf(jObj2.get("title"))));
				properties.setMagType(isNull(String.valueOf("magType")));
				properties.setSig(isNullInt(String.valueOf(jObj2.get("sig"))));
				properties.setTsunami(isNullInt(String.valueOf(jObj2.get("tsunami"))));
				properties.setMag(isNullDouble(String.valueOf(jObj2.get("mag"))));
				properties.setGap(isNullDouble(String.valueOf(jObj2.get("gap"))));
				properties.setRms(isNullDouble(String.valueOf(jObj2.get("rms"))));
				properties.setPlace(isNull(String.valueOf(jObj2.get("place"))));
				properties.setNet(isNull(String.valueOf(jObj2.get("net"))));
				properties.setTypes(isNull(String.valueOf(jObj2.get("types"))));
				properties.setUrl2(isNull(String.valueOf(jObj2.get("url"))));
				properties.setIds(isNull(String.valueOf(jObj2.get("ids"))));
				properties.setTime(isNull(String.valueOf(jObj2.get("time"))));
				properties.setDetail(isNull(String.valueOf(jObj2.get("detail"))));
				properties.setUpdate(isNull(String.valueOf(jObj2.get("updated"))));
				properties.setStatusApi(isNull(String.valueOf(jObj2.get("status"))));
	            if((properties.getTitle() != null) && (properties.getStatusApi() != null)) {
	            	String[] direccion = properties.getTitle().split("-");
	            	if(direccion[1].contains(",")) {
	            		direccion = properties.getTitle().split(",");
	            		if(direccion[1].trim().equalsIgnoreCase(country.trim())) {
		            		lista.add(properties);
		            	}
	            	}else {
	            		if((country.equalsIgnoreCase(direccion[1]))) {
		            		lista.add(properties);
		            	}
	            	}
	            }
	        } 	
		} catch (IOException e) {
			logger.error("Error <obtieneDatosPorPais> IOException: "+e.getMessage());
			logger.error("Error <obtieneDatosPorPais> IOException: "+e.getCause());
		}
		logger.info("Tamaño de la lista: "+lista.size());
		return lista;
	}
	
	/**
	 * 
	 * @param fechaIni
	 * @param fechaFin
	 * @param fechaIni2
	 * @param fechaFin2
	 * @param country
	 * @param country2
	 * @return lista
	 * Metodo encargado de consumir la API https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson
	 * obteniendo la informacion en un rango de fechas y filtrando la informacion por los paises ingresados
	 * como parametros de entrada
	 */
	public List<SalidaModel> obtieneDatosPorDosFechasYDosPaises(String fechaIni, String fechaFin, String fechaIni2, String fechaFin2, String country, String country2) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String urlAux = "";
		if(IniProperties.inicializador()) {
			urlAux = IniProperties.getPropiedad("prop.url");
		}
		String url = urlAux + "&starttime="+fechaIni+"&endtime="+fechaFin;
    	logger.info("URL: "+url);
    	HttpGet httpget = new HttpGet(url);
    	List<SalidaModel> lista = new ArrayList<SalidaModel>();
    	HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	StringBuilder resultado = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			logger.info("Resultado: "+result);
			JSONObject jo = new JSONObject(result); 
			JSONArray msg = jo.getJSONArray("features");
			for(int i = 0; i < msg.length(); i++) {
				JSONObject jObj = msg.getJSONObject(i);
				JSONObject jObj2 = jObj.getJSONObject("properties");
				SalidaModel properties = new SalidaModel();
				
				properties.setDmin(isNullDouble(String.valueOf(jObj2.get("dmin"))));
				properties.setCode(isNull(String.valueOf(jObj2.get("code"))));
				properties.setSources(isNull(String.valueOf(jObj2.get("sources"))));
				properties.setTz(isNullInt(String.valueOf(jObj2.get("tz"))));
				properties.setNst(isNull(String.valueOf(jObj2.get("nst"))));
				properties.setType(isNull(String.valueOf(jObj2.get("type"))));
				properties.setTitle(isNull(String.valueOf(jObj2.get("title"))));
				properties.setMagType(isNull(String.valueOf("magType")));
				properties.setSig(isNullInt(String.valueOf(jObj2.get("sig"))));
				properties.setTsunami(isNullInt(String.valueOf(jObj2.get("tsunami"))));
				properties.setMag(isNullDouble(String.valueOf(jObj2.get("mag"))));
				properties.setGap(isNullDouble(String.valueOf(jObj2.get("gap"))));
				properties.setRms(isNullDouble(String.valueOf(jObj2.get("rms"))));
				properties.setPlace(isNull(String.valueOf(jObj2.get("place"))));
				properties.setNet(isNull(String.valueOf(jObj2.get("net"))));
				properties.setTypes(isNull(String.valueOf(jObj2.get("types"))));
				properties.setUrl2(isNull(String.valueOf(jObj2.get("url"))));
				properties.setIds(isNull(String.valueOf(jObj2.get("ids"))));
				properties.setTime(isNull(String.valueOf(jObj2.get("time"))));
				properties.setDetail(isNull(String.valueOf(jObj2.get("detail"))));
				properties.setUpdate(isNull(String.valueOf(jObj2.get("updated"))));
				properties.setStatusApi(isNull(String.valueOf(jObj2.get("status"))));
	            if((properties.getTitle() != null) && (properties.getStatusApi() != null)) {
	            	String[] direccion = properties.getTitle().split("-");
	            	if(direccion[1].contains(",")) {
	            		direccion = properties.getTitle().split(",");
	            		if(direccion[1].trim().equalsIgnoreCase(country.trim()) || direccion[1].trim().equalsIgnoreCase(country2.trim())) {
		            		lista.add(properties);
		            	}
	            	}else {
	            		if((country.equalsIgnoreCase(direccion[1])) || country2.equalsIgnoreCase(direccion[1])) {
		            		lista.add(properties);
		            	}
	            	}
	            }
	        } 
			
			url = urlAux + "&starttime="+fechaIni2+"&endtime="+fechaFin2;
			httpget = new HttpGet(url);
			response = httpclient.execute(httpget);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	resultado = new StringBuilder();
			line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			logger.info("Resultado: "+result);
			JSONObject jo2 = new JSONObject(result); 
			JSONArray msg2 = jo2.getJSONArray("features");
			for(int i = 0; i < msg2.length(); i++) {
				JSONObject jObj = msg2.getJSONObject(i);
				JSONObject jObj2 = jObj.getJSONObject("properties");
				SalidaModel properties = new SalidaModel();
				
				properties.setDmin(isNullDouble(String.valueOf(jObj2.get("dmin"))));
				properties.setCode(isNull(String.valueOf(jObj2.get("code"))));
				properties.setSources(isNull(String.valueOf(jObj2.get("sources"))));
				properties.setTz(isNullInt(String.valueOf(jObj2.get("tz"))));
				properties.setNst(isNull(String.valueOf(jObj2.get("nst"))));
				properties.setType(isNull(String.valueOf(jObj2.get("type"))));
				properties.setTitle(isNull(String.valueOf(jObj2.get("title"))));
				properties.setMagType(isNull(String.valueOf("magType")));
				properties.setSig(isNullInt(String.valueOf(jObj2.get("sig"))));
				properties.setTsunami(isNullInt(String.valueOf(jObj2.get("tsunami"))));
				properties.setMag(isNullDouble(String.valueOf(jObj2.get("mag"))));
				properties.setGap(isNullDouble(String.valueOf(jObj2.get("gap"))));
				properties.setRms(isNullDouble(String.valueOf(jObj2.get("rms"))));
				properties.setPlace(isNull(String.valueOf(jObj2.get("place"))));
				properties.setNet(isNull(String.valueOf(jObj2.get("net"))));
				properties.setTypes(isNull(String.valueOf(jObj2.get("types"))));
				properties.setUrl2(isNull(String.valueOf(jObj2.get("url"))));
				properties.setIds(isNull(String.valueOf(jObj2.get("ids"))));
				properties.setTime(isNull(String.valueOf(jObj2.get("time"))));
				properties.setDetail(isNull(String.valueOf(jObj2.get("detail"))));
				properties.setUpdate(isNull(String.valueOf(jObj2.get("updated"))));
				properties.setStatusApi(isNull(String.valueOf(jObj2.get("status"))));
	            if((properties.getTitle() != null) && (properties.getStatusApi() != null)) {
	            	String[] direccion = properties.getTitle().split("-");
	            	if(direccion[1].contains(",")) {
	            		direccion = properties.getTitle().split(",");
	            		if(direccion[1].trim().equalsIgnoreCase(country.trim()) || direccion[1].trim().equalsIgnoreCase(country2.trim())) {
		            		lista.add(properties);
		            	}
	            	}else {
	            		if((country.equalsIgnoreCase(direccion[1])) || country2.equalsIgnoreCase(direccion[1])) {
		            		lista.add(properties);
		            	}
	            	}
	            }
	        } 			
		} catch (IOException e) {
			logger.error("Error <obtieneDatosPorDosFechasYDosPaises> IOException: "+e.getMessage());
			logger.error("Error <obtieneDatosPorDosFechasYDosPaises> IOException: "+e.getCause());
		}
		return lista;
	}
	
	public String isNull(String valor) {
		if ((valor == "null") || (valor == null)) {
			valor = "";
		}
		return valor;
	}
	
	public Double isNullDouble(String valor) {
		if ((valor == "null") || (valor == null)) {
			valor = "0.0";
		}
		return Double.parseDouble(valor);
	}
	
	public int isNullInt(String valor) {
		if ((valor == "null") || (valor == null)) {
			valor = "0";
		}
		return Integer.parseInt(valor);
	}
}
