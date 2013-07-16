package cn.edu.zju.ccnt.TFGW;

public class LoadConf {
	int expertDiseaseCount;
	
	/**
	 * The number of expert use to ge disease.
	 */
	int expertCount;

	/**
	 * The number of disease get by expert.
	 */
	int diseaseExpertCount;
	
	/**
	 * @return the expertDiseaseCount
	 */
	public int getExpertDiseaseCount() {
		return expertDiseaseCount;
	}

	/**
	 * @param expertDiseaseCount the expertDiseaseCount to set
	 */
	public void setExpertDiseaseCount(int expertDiseaseCount) {
		this.expertDiseaseCount = expertDiseaseCount;
	}

	/**
	 * @return the diseaseExpertCount
	 */
	public int getDiseaseExpertCount() {
		return diseaseExpertCount;
	}

	/**
	 * @param diseaseExpertCount the diseaseExpertCount to set
	 */
	public void setDiseaseExpertCount(int diseaseExpertCount) {
		this.diseaseExpertCount = diseaseExpertCount;
	}

	/**
	 * @return the expertCount
	 */
	public int getExpertCount() {
		return expertCount;
	}

	/**
	 * @param expertCount the expertCount to set
	 */
	public void setExpertCount(int expertCount) {
		this.expertCount = expertCount;
	}
	
}
