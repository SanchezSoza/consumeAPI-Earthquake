package cl.chl.rs.project.earthquake.model;

public class EntradaModel {
	private String starttime;
	private String endtime;
	private String minmagnitude;
	private String maxmagnitude;
	
	public EntradaModel(String starttime, String endtime, String minmagnitude, String maxmagnitude) {
		this.starttime = starttime;
		this.endtime = endtime;
		this.minmagnitude = minmagnitude;
		this.maxmagnitude = maxmagnitude;
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
	public String getMinmagnitude() {
		return minmagnitude;
	}
	public void setMinmagnitude(String minmagnitude) {
		this.minmagnitude = minmagnitude;
	}
	public String getMaxmagnitude() {
		return maxmagnitude;
	}
	public void setMaxmagnitude(String maxmagnitude) {
		this.maxmagnitude = maxmagnitude;
	}
}
