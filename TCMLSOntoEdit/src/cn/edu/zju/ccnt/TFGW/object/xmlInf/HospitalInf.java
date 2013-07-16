package cn.edu.zju.ccnt.TFGW.object.xmlInf;

public class HospitalInf {
	private String name;
	private String id;
	private int correlation;
	
	public HospitalInf(String name, String id, int correlation) {
		super();
		this.name = name;
		this.id = id;
		this.correlation = correlation;
	}

	public HospitalInf() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the correlation
	 */
	public int getCorrelation() {
		return correlation;
	}

	/**
	 * @param correlation the correlation to set
	 */
	public void setCorrelation(int correlation) {
		this.correlation = correlation;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
