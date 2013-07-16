package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Yflf;

/**药方疗法DAO
 * @author zhm
 *
 */
public class YflfDAO {
	static Logger logger = Logger.getLogger(YflfDAO.class.getName());

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
	 * 根据疾病名称得倒中药药方疗法的列表，考虑入口词和主题词
	 * @return
	 */
	public ArrayList<Yflf> GetYflfByJbmc() {
		setSqlGetYFSLByJBMC();
		logger.debug("Get 药方疗法 By Jb.JBMC with sql:" + SqlGetYFSLByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetYFSLByJBMC);

		ArrayList<Yflf> yflfArray = new ArrayList<Yflf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Yflf yflf = new Yflf();
			if (ResultArray.get(counter).get("YFMC") != null)
				yflf.setYFMC(ResultArray.get(counter).get("YFMC").toString());
			else
				yflf.setYFMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				yflf.setREFNUM(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				yflf.setREFNUM("");

			yflfArray.add(yflf);
		}

		return yflfArray;
	}

	public String getSqlGetYFSLByJBMC() {
		return SqlGetYFSLByJBMC;
	}

	public void setSqlGetYFSLByJBMC() {
		SqlGetYFSLByJBMC = "select YFMC,count(YFMC) REFNUM from " + tablePrefix
				+ "_Yflf where Zlgc_Id in (select Jtzlgc_Id from "
				+ tablePrefix
				+ "_Jtzlgc where Jtzlgc_Id in (select JTZLGC_ID from  "
				+ tablePrefix
				+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
				+ tablePrefix
				+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
				+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
				+ getJbmcs() + ")))) group by YFMC order by (count(YFMC)) desc";
	}
	public String getJbmcs()
	{
		Dss_subjectDAO subjectDAO=new Dss_subjectDAO();
		
		return  subjectDAO.getRelatedJbmc(this.JBMC);
	}


}
