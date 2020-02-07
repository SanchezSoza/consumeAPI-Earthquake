package cl.chl.rs.project.earthquake.model;

/**
 * Clase Model para indicar la estructura de salida que tengan los servicios REST
 */
public class SalidaModel {
	private String type;
	private double mag;
	private String place;
	private String time;
	private String update;
	private int tz;
	private String url2;
	private String detail;
	private String statusApi;
	private int tsunami;
	private int sig;
	private String net;
	private String code;
	private String ids;
	private String sources;
	private String types;
	private String nst;
	private double dmin;
	private double rms;
	private double gap;
	private String magType;
	private String typeEarthquake;
	private String ubication;
	private String typeGeometry;
	private double coordenada0;
	private double coordenada1;
	private double coordenada2;
	private String id;
	private String title;
	
	public SalidaModel(String type, double mag, String place, String time, String update, int tz,
			String url2, String detail, String statusApi, int tsunami, int sig, String net, String code, String ids,
			String sources, String types, String nst, double dmin, double rms, double gap, String magType,
			String typeEarthquake, String ubication, String typeGeometry, double coordenada0, double coordenada1,
			double coordenada2, String id, String title) {
		this.type = type;
		this.mag = mag;
		this.place = place;
		this.time = time;
		this.update = update;
		this.tz = tz;
		this.url2 = url2;
		this.detail = detail;
		this.statusApi = statusApi;
		this.tsunami = tsunami;
		this.sig = sig;
		this.net = net;
		this.code = code;
		this.ids = ids;
		this.sources = sources;
		this.types = types;
		this.nst = nst;
		this.dmin = dmin;
		this.rms = rms;
		this.gap = gap;
		this.magType = magType;
		this.typeEarthquake = typeEarthquake;
		this.ubication = ubication;
		this.typeGeometry = typeGeometry;
		this.coordenada0 = coordenada0;
		this.coordenada1 = coordenada1;
		this.coordenada2 = coordenada2;
		this.id = id;
		this.title = title;
	}
	
	public SalidaModel() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMag() {
		return mag;
	}

	public void setMag(double mag) {
		this.mag = mag;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public int getTz() {
		return tz;
	}

	public void setTz(int tz) {
		this.tz = tz;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatusApi() {
		return statusApi;
	}

	public void setStatusApi(String statusApi) {
		this.statusApi = statusApi;
	}

	public int getTsunami() {
		return tsunami;
	}

	public void setTsunami(int tsunami) {
		this.tsunami = tsunami;
	}

	public int getSig() {
		return sig;
	}

	public void setSig(int sig) {
		this.sig = sig;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getNst() {
		return nst;
	}

	public void setNst(String nst) {
		this.nst = nst;
	}

	public double getDmin() {
		return dmin;
	}

	public void setDmin(double dmin) {
		this.dmin = dmin;
	}

	public double getRms() {
		return rms;
	}

	public void setRms(double rms) {
		this.rms = rms;
	}

	public double getGap() {
		return gap;
	}

	public void setGap(double gap) {
		this.gap = gap;
	}

	public String getMagType() {
		return magType;
	}

	public void setMagType(String magType) {
		this.magType = magType;
	}

	public String getTypeEarthquake() {
		return typeEarthquake;
	}

	public void setTypeEarthquake(String typeEarthquake) {
		this.typeEarthquake = typeEarthquake;
	}

	public String getUbication() {
		return ubication;
	}

	public void setUbication(String ubication) {
		this.ubication = ubication;
	}

	public String getTypeGeometry() {
		return typeGeometry;
	}

	public void setTypeGeometry(String typeGeometry) {
		this.typeGeometry = typeGeometry;
	}

	public double getCoordenada0() {
		return coordenada0;
	}

	public void setCoordenada0(double coordenada0) {
		this.coordenada0 = coordenada0;
	}

	public double getCoordenada1() {
		return coordenada1;
	}

	public void setCoordenada1(double coordenada1) {
		this.coordenada1 = coordenada1;
	}

	public double getCoordenada2() {
		return coordenada2;
	}

	public void setCoordenada2(double coordenada2) {
		this.coordenada2 = coordenada2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
