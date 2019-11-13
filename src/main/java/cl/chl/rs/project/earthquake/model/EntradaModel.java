package cl.chl.rs.project.earthquake.model;

/**
 * Clase Model para indicar los parametros de entrada que tendran los servicios REST
 */
public class EntradaModel {
	private String starttime;
	private String endtime;
	private String starttime2;
	private String endtime2;
	private String country;
	private String country2;
	
	public EntradaModel(String starttime, String endtime, String starttime2, String endtime2, String country, String country2) {
		this.starttime = starttime;
		this.endtime = endtime;
		this.starttime2 = starttime2;
		this.endtime2 = endtime2;
		this.country = country;
		this.country2 = country2;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStarttime2() {
		return starttime2;
	}

	public void setStarttime2(String starttime2) {
		this.starttime2 = starttime2;
	}

	public String getEndtime2() {
		return endtime2;
	}

	public void setEndtime2(String endtime2) {
		this.endtime2 = endtime2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountry2() {
		return country2;
	}

	public void setCountry2(String country2) {
		this.country2 = country2;
	}
}
