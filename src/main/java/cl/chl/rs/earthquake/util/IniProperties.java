package cl.chl.rs.earthquake.util;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Nombre de clase = LeerPropertiesExternas
 * Descripción = Clase encarga de cargar las properties externas a nuestra aplicación
 * @version 1.0
 */

public class IniProperties {

	/**
	 * Atributos de la clase
	 * */
	//private static final String FICHERO_PROPERTIES = "configuracion.properties";
	//private static final String RUTA_FICHEROS_ENTORNO = "PROPERTIES_APP";
	//public static final String NOMBRE_APLICACION = "LeerPropertiesExternas";
	public static String directorio_properties;
	public static PropertiesConfiguration properties = null;

	private static final Logger logger = LoggerFactory.getLogger(IniProperties.class);
	/***
	 * Método inicializador que carga los ficheros .properties indicados
	 * 
	 * */
	public static boolean inicializador(){
		
		boolean inicializado=false;
		//Buscamos la variable/propiedad del entorno de la siguiente manera 
		Constantes constantes = new Constantes();
		logger.info("rutaProperties : "+constantes.pathProperties);
		
		//System.out.println("RUTA_FICHEROS_ENTORNO = "+RUTA_FICHEROS_ENTORNO);
		directorio_properties = constantes.pathProperties;
		//System.getProperty(RUTA_FICHEROS_ENTORNO);
		if(directorio_properties==null)
		{
			directorio_properties = System.getenv(constantes.pathProperties);
		}
		//System.out.println(directorio_properties);
		logger.info(directorio_properties);
		if(directorio_properties!=null && existeDirectorio(directorio_properties)){
			//Como el directorio existe, cargo el nombre del fichero
			String ficheroProperties = directorio_properties+File.separator+constantes.fileProperties;
			if(existeFichero(ficheroProperties))
			{

				try {
					properties = new PropertiesConfiguration();
					properties.setFileName(ficheroProperties);
					properties.setReloadingStrategy(new FileChangedReloadingStrategy());
					properties.setDelimiterParsingDisabled(true);
					properties.load();	
					inicializado=true;
				}catch (ConfigurationException e) {
					System.err.println("Error Cargando las properties");
				}				

			}else{
				System.err.println("El fichero de properties no existe");
			}
		}else{
			System.err.println("El directorio no existe");
		}
		return inicializado;
	}


	/**
	 * Método que verifica si un fichero existe
	 *  @param String ruta
	 *  @return boolean, que indica si el fichero existe
	 * */
	private static boolean existeFichero(String ruta){
		File f = new File(ruta);
		return f.exists() && f.canRead();
	}

	/**
	 * Método que verifica si un directorio existe
	 * @param String ruta
	 * @return boolean, que indica si el directorio existe
	 * */
	private static boolean existeDirectorio(String ruta){
		File f = new File(ruta);
		return f.exists() && f.isDirectory();
	}
	
	/**
	 * Método que lee una propiedad y la retorna
	 * @param String nombre de la propiedad a leer
	 * @return String, valor de la propiedad en el fichero
	 * */
	
	public static String getPropiedad(String propiedad){
		return properties.getString(propiedad);
	}
}
