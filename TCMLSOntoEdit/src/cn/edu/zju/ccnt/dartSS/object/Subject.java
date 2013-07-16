package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的专题类
 * @author zhm
 *
 */
public class Subject {
	public int S_ID;
	public String S_NAME;
	public String S_TAG;
	public String condition;
	public String addedtitles;	
	public String picName;
	public String notes;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getS_ID() {
		return S_ID;
	}
	public void setS_ID(int s_id) {
		S_ID = s_id;
	}

	public String getAddedtitles() {
		return addedtitles;
	}
	public void setAddedtitles(String addedtitles) {
		this.addedtitles = addedtitles;
	}
	
	public String getS_TAG() {
		return S_TAG;
	}
	public void setS_TAG(String s_tag) {
		S_TAG = s_tag;
	}
	public String getS_NAME() {
		return S_NAME;
	}
	public void setS_NAME(String s_name) {
		S_NAME = s_name;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	
}
