package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.DiseaseDAO;
import cn.edu.zju.ccnt.dartSS.object.Disease;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.NavigateQueryHtmlGetDisease;

/**从症状到疾病的导航查询
 * @author zhm
 * 
 */
public class NavigateQueryHtmlGetDiseaseFromZZ extends NavigateQueryHtmlGetDisease{

	@Override
	public ArrayList<Disease> generateDiseaseList() {
		DiseaseDAO diseaseDAO=new DiseaseDAO();
		diseaseDAO.setTablePrefix(this.getTablePrefix());
		return diseaseDAO.GetJbmcByZZMC(this.KeyWord);
	}

	@Override
	public void SetTitleHints() {
		this.setTitleString("与症状<b>"+this.KeyWord+"</b>相关的<b>疾病</b>列表");
		this.setNavigeteTypeName("症状名称");
	}
	
	

}
