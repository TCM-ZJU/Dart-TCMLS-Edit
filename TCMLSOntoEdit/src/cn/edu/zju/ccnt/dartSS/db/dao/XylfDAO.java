package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Xylf;

/**
 * 西药疗法DAO
 * @author zhm
 *
 */
public class XylfDAO {
	static Logger logger = Logger.getLogger(XylfDAO.class.getName());

	public String JBMC = "";

	public String tablePrefix = "";

	private String SqlGetYFSLByJBMC = "";

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getJBMC() {
		return JBMC;
	}

	public void setJBMC(String jbmc) {
		JBMC = jbmc;
	}

	/**
	 * 根据疾病名称得倒西药疗法的列表，考虑入口词和主题词
	 * @return
	 */
	public ArrayList<Xylf> GetYflfByJbmc() {
		setSqlGetYFSLByJBMC();
		logger.debug("Get 西药药方疗法 By Jb.JBMC with sql:" + SqlGetYFSLByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetYFSLByJBMC);

		ArrayList<Xylf> yflfArray = new ArrayList<Xylf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Xylf xylf = new Xylf();
			if (ResultArray.get(counter).get("YWMC") != null)
				xylf.setYWMC(ResultArray.get(counter).get("YWMC").toString());
			else
				xylf.setYWMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				xylf.setREFNUM(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				xylf.setREFNUM("");

			yflfArray.add(xylf);
		}

		return yflfArray;
	}

	public String getSqlGetYFSLByJBMC() {
		return SqlGetYFSLByJBMC;
	}

	public void setSqlGetYFSLByJBMC() {
		SqlGetYFSLByJBMC = "select Ywmc,count(Ywmc) REFNUM from " + tablePrefix
				+ "_Xylf where Zlgc_Id in (select Jtzlgc_Id from "
				+ tablePrefix
				+ "_Jtzlgc where Jtzlgc_Id in (select JTZLGC_ID from  "
				+ tablePrefix
				+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
				+ tablePrefix
				+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
				+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
				+  getJbmcs() + ")))) group by Ywmc order by count(ywmc) desc";
	}
	public String getJbmcs()
	{
		Dss_subjectDAO subjectDAO=new Dss_subjectDAO();
		
		return  subjectDAO.getRelatedJbmc(this.JBMC);
	}
	

}
