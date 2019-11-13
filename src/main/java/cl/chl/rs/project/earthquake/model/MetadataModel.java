package cl.chl.rs.project.earthquake.model;

public class MetadataModel {
	private String generated;
	private String url;
	private String title;
	private int status;
	private double api;
	private int count;
	
	public MetadataModel(String generated, String url, String title, int status, double api, int count) {
		super();
		this.generated = generated;
		this.url = url;
		this.title = title;
		this.status = status;
		this.api = api;
		this.count = count;
	}
	
	public MetadataModel() {
		
	}

	public String getGenerated() {
		return generated;
	}

	public void setGenerated(String generated) {
		this.generated = generated;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getApi() {
		return api;
	}

	public void setApi(double api) {
		this.api = api;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
