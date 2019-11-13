package cl.chl.rs.project.earthquake.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.chl.rs.project.earthquake.model.SalidaModel;

public class EarthquakeService {

	private final static Logger logger = LoggerFactory.getLogger(EarthquakeService.class);

	
	public List<SalidaModel> obtieneDatosPorFecha(String fechaIni, String fechaFin) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="+fechaIni+"&endtime="+fechaFin	;
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
			Object obj = new JSONParser().parse(result); 
			JSONObject jo = (JSONObject) obj; 
			JSONArray msg = (JSONArray) jo.get("features");
			Iterator<JSONObject> itrParticipants = msg.iterator();
			while (itrParticipants.hasNext()) {
				JSONObject jObj = (itrParticipants).next();
				Map propertiesMap = ((Map)jObj.get("properties")); 
				SalidaModel properties = new SalidaModel();
		        // iterating address Map 
		        Iterator<Map.Entry> itr2 = propertiesMap.entrySet().iterator(); 
		        while (itr2.hasNext()) {
		            Map.Entry pair2 = itr2.next(); 
		            if(pair2.getKey().equals("dmin")) {
		            	properties.setDmin((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("code")) {
		            	properties.setCode((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sources")) {
		            	properties.setSources((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("tz")) {
		            	properties.setTz((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("nst")) {
		            	properties.setNst((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("type")) {
		            	properties.setTypeEarthquake((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("title")) {
		            	properties.setTitle((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("magType")) {
		            	properties.setMagType((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sig")) {
		            	properties.setSig((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("tsunami")) {
		            	properties.setTsunami((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("mag")) {
		            	properties.setMag((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("gap")) {
		            	properties.setGap((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("rms")) {
		            	properties.setRms((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("place")) {
		            	properties.setPlace((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("net")) {
		            	properties.setNet((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("types")) {
		            	properties.setTypes((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("url")) {
		            	properties.setUrl2((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("ids")) {
		            	properties.setIds((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("time")) {
		            	properties.setTime((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("detail")) {
		            	properties.setDetail((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("updated")) {
		            	properties.setUpdate((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("status")) {
		            	properties.setStatusApi((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }
		            if(properties.getStatusApi() != null) {
		            	lista.add(properties);
		            }
		        } 
			}	
		} catch (IOException e) {
			logger.error("Error IOException: "+e.getMessage());
			logger.error("Error IOException: "+e.getCause());
		} catch (ParseException e) {
			logger.error("Error ParseException: "+e.getMessage());
			logger.error("Error ParseException: "+e.getCause());
		}
		logger.info("Tamano de la lista: "+lista.size());
		return lista;
	}
	
	public List<SalidaModel> obtieneDatosPorMagnitud(double magnitudIni, double magnitudFin) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude="+magnitudIni+"&maxmagnitude="+magnitudFin;
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
			Object obj = new JSONParser().parse(result); 
			JSONObject jo = (JSONObject) obj; 
			JSONArray msg = (JSONArray) jo.get("features");
			Iterator<JSONObject> itrParticipants = msg.iterator();
			while (itrParticipants.hasNext()) {
				JSONObject jObj = (itrParticipants).next();
				Map propertiesMap = ((Map)jObj.get("properties")); 
				SalidaModel properties = new SalidaModel();
		        // iterating address Map 
		        Iterator<Map.Entry> itr2 = propertiesMap.entrySet().iterator(); 
		        while (itr2.hasNext()) {
		            Map.Entry pair2 = itr2.next(); 
		            if(pair2.getKey().equals("dmin")) {
		            	properties.setDmin((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("code")) {
		            	properties.setCode((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sources")) {
		            	properties.setSources((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("tz")) {
		            	properties.setTz((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("nst")) {
		            	properties.setNst((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("type")) {
		            	properties.setTypeEarthquake((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("title")) {
		            	properties.setTitle((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("magType")) {
		            	properties.setMagType((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sig")) {
		            	properties.setSig((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("tsunami")) {
		            	properties.setTsunami((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("mag")) {
		            	properties.setMag((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("gap")) {
		            	properties.setGap((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("rms")) {
		            	properties.setRms((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("place")) {
		            	properties.setPlace((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("net")) {
		            	properties.setNet((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("types")) {
		            	properties.setTypes((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("url")) {
		            	properties.setUrl2((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("ids")) {
		            	properties.setIds((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("time")) {
		            	properties.setTime((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("detail")) {
		            	properties.setDetail((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("updated")) {
		            	properties.setUpdate((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("status")) {
		            	properties.setStatusApi((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }
		            if(properties.getStatusApi() != null) {
		            	lista.add(properties);
		            }
		        } 
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<SalidaModel> obtieneDatosPorDosFechas(String fechaIni, String fechaFin, String fechaIni2, String fechaFin2) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="+fechaIni+"&endtime="+fechaFin;
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
			Object obj = new JSONParser().parse(result); 
			JSONObject jo = (JSONObject) obj; 
			
			Map metadataMap = ((Map)jo.get("metadata")); 
	          
	        // iterating address Map 
	        Iterator<Map.Entry> itr1 = metadataMap.entrySet().iterator(); 
			
			JSONArray msg = (JSONArray) jo.get("features");
			Iterator<JSONObject> itrParticipants = msg.iterator();
			while (itrParticipants.hasNext()) {
				JSONObject jObj = (itrParticipants).next();
				Map propertiesMap = ((Map)jObj.get("properties")); 
				SalidaModel properties = new SalidaModel();
		        // iterating address Map 
		        Iterator<Map.Entry> itr2 = propertiesMap.entrySet().iterator(); 
		        while (itr2.hasNext()) {
		            Map.Entry pair2 = itr2.next(); 
		            
		            if(pair2.getKey().equals("dmin")) {
		            	properties.setDmin((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("code")) {
		            	properties.setCode((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sources")) {
		            	properties.setSources((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("tz")) {
		            	properties.setTz((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("nst")) {
		            	properties.setNst((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("type")) {
		            	properties.setTypeEarthquake((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("title")) {
		            	properties.setTitle((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("magType")) {
		            	properties.setMagType((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sig")) {
		            	properties.setSig((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("tsunami")) {
		            	properties.setTsunami((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("mag")) {
		            	properties.setMag((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("gap")) {
		            	properties.setGap((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("rms")) {
		            	properties.setRms((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("place")) {
		            	properties.setPlace((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("net")) {
		            	properties.setNet((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("types")) {
		            	properties.setTypes((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("url")) {
		            	properties.setUrl2((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("ids")) {
		            	properties.setIds((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("time")) {
		            	properties.setTime((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("detail")) {
		            	properties.setDetail((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("updated")) {
		            	properties.setUpdate((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("status")) {
		            	properties.setStatusApi((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }
		            if(properties.getStatusApi() != null) {
		            	lista.add(properties);
		            }
		        } 
			}
			
			url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="+fechaIni2+"&endtime="+fechaFin2;
			httpget = new HttpGet(url);
			response = httpclient.execute(httpget);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	resultado = new StringBuilder();
			line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			Object obj2 = new JSONParser().parse(result); 
			JSONObject jo2 = (JSONObject) obj2; 
			
			JSONArray msg2 = (JSONArray) jo2.get("features");
			Iterator<JSONObject> itr3 = msg2.iterator();
			while (itr3.hasNext()) {
				JSONObject jObj = (itr3).next();
				Map propertiesMap = ((Map)jObj.get("properties")); 
				SalidaModel properties = new SalidaModel();
		        // iterating address Map 
		        Iterator<Map.Entry> itr4 = propertiesMap.entrySet().iterator(); 
		        while (itr4.hasNext()) {
		            Map.Entry pair2 = itr4.next(); 
		            if(pair2.getKey().equals("dmin")) {
		            	properties.setDmin((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("code")) {
		            	properties.setCode((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sources")) {
		            	properties.setSources((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("tz")) {
		            	properties.setTz((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("nst")) {
		            	properties.setNst((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("type")) {
		            	properties.setTypeEarthquake((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("title")) {
		            	properties.setTitle((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("magType")) {
		            	properties.setMagType((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sig")) {
		            	properties.setSig((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("tsunami")) {
		            	properties.setTsunami((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("mag")) {
		            	properties.setMag((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("gap")) {
		            	properties.setGap((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("rms")) {
		            	properties.setRms((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("place")) {
		            	properties.setPlace((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("net")) {
		            	properties.setNet((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("types")) {
		            	properties.setTypes((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("url")) {
		            	properties.setUrl2((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("ids")) {
		            	properties.setIds((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("time")) {
		            	properties.setTime((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("detail")) {
		            	properties.setDetail((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("updated")) {
		            	properties.setUpdate((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("status")) {
		            	properties.setStatusApi((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }
		            if(properties.getStatusApi() != null) {
		            	lista.add(properties);
		            }
		        } 
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<SalidaModel> obtieneDatosPorPais(String country) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson";
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
			Object obj = new JSONParser().parse(result); 
			JSONObject jo = (JSONObject) obj; 
			JSONArray msg = (JSONArray) jo.get("features");
			Iterator<JSONObject> itrParticipants = msg.iterator();
			while (itrParticipants.hasNext()) {
				JSONObject jObj = (itrParticipants).next();
				Map propertiesMap = ((Map)jObj.get("properties")); 
				SalidaModel properties = new SalidaModel();
		        // iterating address Map 
		        Iterator<Map.Entry> itr2 = propertiesMap.entrySet().iterator(); 
		        while (itr2.hasNext()) {
		            Map.Entry pair2 = itr2.next(); 
		            if(pair2.getKey().equals("dmin")) {
		            	properties.setDmin((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("code")) {
		            	properties.setCode((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sources")) {
		            	properties.setSources((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("tz")) {
		            	properties.setTz((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("nst")) {
		            	properties.setNst((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("type")) {
		            	properties.setTypeEarthquake((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("title")) {
		            	properties.setTitle((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("magType")) {
		            	properties.setMagType((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sig")) {
		            	properties.setSig((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("tsunami")) {
		            	properties.setTsunami((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("mag")) {
		            	properties.setMag((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("gap")) {
		            	properties.setGap((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("rms")) {
		            	properties.setRms((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("place")) {
		            	properties.setPlace((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("net")) {
		            	properties.setNet((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("types")) {
		            	properties.setTypes((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("url")) {
		            	properties.setUrl2((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("ids")) {
		            	properties.setIds((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("time")) {
		            	properties.setTime((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("detail")) {
		            	properties.setDetail((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("updated")) {
		            	properties.setUpdate((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("status")) {
		            	properties.setStatusApi((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }
		            if((properties.getTitle() != null) && (properties.getStatusApi() != null)) {
		            	logger.info("properties.getTitle(): "+properties.getTitle());
		            	String[] direccion = properties.getTitle().split("-");
		            	logger.info("Direcciones:"+direccion[1]);
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
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Tamaño de la lista: "+lista.size());
		return lista;
	}
	
	public List<SalidaModel> obtieneDatosPorDosFechasYDosPaises(String fechaIni, String fechaFin, String fechaIni2, String fechaFin2, String country, String country2) {

		String result = "";
		HttpClient httpclient = HttpClientBuilder.create().build();
		String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="+fechaIni+"&endtime="+fechaFin;
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
			Object obj = new JSONParser().parse(result); 
			JSONObject jo = (JSONObject) obj; 
			
			Map metadataMap = ((Map)jo.get("metadata")); 
	          
	        // iterating address Map 
	        Iterator<Map.Entry> itr1 = metadataMap.entrySet().iterator(); 
	        
			JSONArray msg = (JSONArray) jo.get("features");
			Iterator<JSONObject> itrParticipants = msg.iterator();
			while (itrParticipants.hasNext()) {
				JSONObject jObj = (itrParticipants).next();
				Map propertiesMap = ((Map)jObj.get("properties")); 
		          
		        // iterating address Map 
		        Iterator<Map.Entry> itr2 = propertiesMap.entrySet().iterator(); 
		        while (itr2.hasNext()) {
		            Map.Entry pair2 = itr2.next(); 
		            SalidaModel properties = new SalidaModel();
		            if(pair2.getKey().equals("dmin")) {
		            	properties.setDmin((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("code")) {
		            	properties.setCode((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sources")) {
		            	properties.setSources((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("tz")) {
		            	properties.setTz((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("nst")) {
		            	properties.setNst((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("type")) {
		            	properties.setTypeEarthquake((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("title")) {
		            	properties.setTitle((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("magType")) {
		            	properties.setMagType((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sig")) {
		            	properties.setSig((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("tsunami")) {
		            	properties.setTsunami((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("mag")) {
		            	properties.setMag((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("gap")) {
		            	properties.setGap((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("rms")) {
		            	properties.setRms((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("place")) {
		            	properties.setPlace((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("net")) {
		            	properties.setNet((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("types")) {
		            	properties.setTypes((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("url")) {
		            	properties.setUrl2((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("ids")) {
		            	properties.setIds((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("time")) {
		            	properties.setTime((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("detail")) {
		            	properties.setDetail((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("updated")) {
		            	properties.setUpdate((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("status")) {
		            	properties.setStatusApi((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }
		            if(properties.getStatusApi() != null) {
		            	lista.add(properties);
		            }
		        } 
			}
			
			url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="+fechaIni2+"&endtime="+fechaFin2;
			httpget = new HttpGet(url);
			response = httpclient.execute(httpget);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	    	resultado = new StringBuilder();
			line = "";
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			result = resultado.toString();
			Object obj2 = new JSONParser().parse(result); 
			JSONObject jo2 = (JSONObject) obj2; 
			
			JSONArray msg2 = (JSONArray) jo2.get("features");
			Iterator<JSONObject> itr3 = msg2.iterator();
			while (itr3.hasNext()) {
				JSONObject jObj = (itr3).next();
				Map propertiesMap = ((Map)jObj.get("properties")); 
				SalidaModel properties = new SalidaModel();
		        // iterating address Map 
		        Iterator<Map.Entry> itr4 = propertiesMap.entrySet().iterator(); 
		        while (itr4.hasNext()) {
		            Map.Entry pair2 = itr4.next(); 
		            if(pair2.getKey().equals("dmin")) {
		            	properties.setDmin((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("code")) {
		            	properties.setCode((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sources")) {
		            	properties.setSources((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("tz")) {
		            	properties.setTz((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("nst")) {
		            	properties.setNst((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("type")) {
		            	properties.setTypeEarthquake((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("title")) {
		            	properties.setTitle((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("magType")) {
		            	properties.setMagType((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("sig")) {
		            	properties.setSig((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("tsunami")) {
		            	properties.setTsunami((pair2.getValue() != null)?Integer.parseInt(pair2.getValue().toString()):0);
		            }else if(pair2.getKey().equals("mag")) {
		            	properties.setMag((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("gap")) {
		            	properties.setGap((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("rms")) {
		            	properties.setRms((pair2.getValue() != null)?Double.parseDouble(pair2.getValue().toString()):0.0);
		            }else if(pair2.getKey().equals("place")) {
		            	properties.setPlace((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("net")) {
		            	properties.setNet((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("types")) {
		            	properties.setTypes((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("url")) {
		            	properties.setUrl2((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("ids")) {
		            	properties.setIds((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("time")) {
		            	properties.setTime((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("detail")) {
		            	properties.setDetail((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("updated")) {
		            	properties.setUpdate((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }else if(pair2.getKey().equals("status")) {
		            	properties.setStatusApi((pair2.getValue() != null)?pair2.getValue().toString():"");
		            }
		            if((properties.getTitle() != null) && (properties.getStatusApi() != null)) {
		            	logger.info("properties.getTitle(): "+properties.getTitle());
		            	String[] direccion = properties.getTitle().split("-");
		            	logger.info("Direcciones:"+direccion[1]);
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
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
