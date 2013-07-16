package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.DiseaseDAO;
import cn.edu.zju.ccnt.dartSS.object.Disease;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.NavigateQueryHtmlGetDisease;

/**
 *  从证候到疾病的导航查询，
 * @author zhm
 */
public class NavigateQueryHtmlGetDiseaseFromZh extends NavigateQueryHtmlGetDisease{

	@Override
	public ArrayList<Disease> generateDiseaseList() {
		DiseaseDAO diseaseDAO=new DiseaseDAO();
		diseaseDAO.setTablePrefix(this.getTablePrefix());
		return diseaseDAO.GetJbmcByZhmc(this.KeyWord);
	}


	@Override
	public void SetTitleHints() {
		this.setTitleString("与证候<b>"+this.KeyWord+"</b>相关的<b>疾病</b>列表");
		this.setNavigeteTypeName("证候名称");
	}
	
	

}
