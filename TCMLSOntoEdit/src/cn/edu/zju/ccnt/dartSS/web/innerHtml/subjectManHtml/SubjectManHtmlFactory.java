package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;

/**专题管理的抽象工厂类
 * @author zhm
 * 
 */
public class SubjectManHtmlFactory {

	public Logger logger=Logger.getLogger(SubjectManHtmlFactory.class.getName());
	
	public ArrayList<String> zhuangTiList;

	public String setInnerHtml(){
		return null;
	}
	
	/**标题提示信息
	 * @param pageTitle
	 * @return
	 */
	public String setHintHtml(String pageTitle){
		return "<span><img src=\"./public/images/menu_create.gif\"></img>"
			+ pageTitle + "<span>";
	}
	
	
	/**画专题下拉列表
	 * @return
	 */
	public String zhuanTiOptions(){
		this.getZhuangTiList();
		String optionHtml="<select id=\"zhuantiList\" name=\"zhuantiList\"  style=\"width:200\" border=\"0\">" ;
		for(int i=0;i<zhuangTiList.size();i++){
			String ztmc=zhuangTiList.get(i);
			optionHtml+="<option value=\""+ztmc+"\">"+ztmc+"</option>";
		}
		optionHtml+="</select>" ;
		return optionHtml;
		
	}

	public ArrayList<String> getZhuangTiList() {
		if(zhuangTiList==null)
			setZhuangTiList();
		return zhuangTiList;
	}

	public void setZhuangTiList() {
		this.zhuangTiList = new Dss_subjectDAO().GetAllSubject();
	}
}
