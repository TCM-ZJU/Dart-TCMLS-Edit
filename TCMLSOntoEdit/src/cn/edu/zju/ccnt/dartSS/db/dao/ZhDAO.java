package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Zh;

/**证候DAO
 * @author zhm
 * 
 */
public class ZhDAO {
	static Logger logger = Logger.getLogger(LczlDAO.class.getName());

	public String JBMC = "";

	public String tablePrefix = "";

	private String SqlGetZhByJBMC = "";

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

	/**根据疾病名称得倒证候列表，考虑入口词和主题词
	 * @return
	 */
	public ArrayList<Zh> GetZhByJbmc() {
		setSqlGetZhByJBMC();
		logger.debug("Get 临床证候 By Jb.JBMC with sql:" + SqlGetZhByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetZhByJBMC);

		ArrayList<Zh> zhArray = new ArrayList<Zh>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Zh zh = new Zh();
			if (ResultArray.get(counter).get("ZHMC") != null)
				zh.setZHMC(ResultArray.get(counter).get("ZHMC").toString());
			else
				zh.setZHMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				zh.setRefNum(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				zh.setRefNum("");

			zhArray.add(zh);
		}

		return zhArray;
	}

	public String getSqlGetZhByJBMC() {
		return SqlGetZhByJBMC;
	}

	public void setSqlGetZhByJBMC() {
		SqlGetZhByJBMC = "select distinct(ZHMC),count(ZHMC) RefNum from  "
				+ this.tablePrefix + "_ZH where " + tablePrefix
				+ "_ZH.ZH_ID in(select ZH_ID from " + tablePrefix
				+ "_JBZHGL where JB_ID in (select JB_ID from " + tablePrefix
				+ "_JB  where JBMC in " + getJbmcs()
				+ ")) group by ZHMC order by(count(ZHMC)) desc";
	}
	public String getJbmcs()
	{
		Dss_subjectDAO subjectDAO=new Dss_subjectDAO();
		
		return  subjectDAO.getRelatedJbmc(this.JBMC);
	}

	/**
	 * 从症状名称-->临床证候名称，用于从症状-->临床证候的导航查询
	 * @param zzmc     症状名称
	 * @return
	 */
	public ArrayList<Zh> getZzmcGetZhMC(String zzmc) {
		String Sql = "select zhmc,count(ZHMC) RefNum from " + this.tablePrefix
				+ "_ZH where ZH_ID in (select ZH_ID  from " + tablePrefix
				+ "_Zhzzgl where ZZ_Id in (select ZZ_Id from " + tablePrefix
				+ "_Lczz where Zzmc='" + zzmc
				+ "')) group by ZHMC order by(count(ZHMC)) desc";
		logger.debug("从症状名称-->临床证候名称 with sql:" + Sql);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(Sql);

		ArrayList<Zh> zhArray = new ArrayList<Zh>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Zh zh = new Zh();
			if (ResultArray.get(counter).get("ZHMC") != null)
				zh.setZHMC(ResultArray.get(counter).get("ZHMC").toString());
			else
				zh.setZHMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				zh.setRefNum(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				zh.setRefNum("");

			zhArray.add(zh);
		}

		return zhArray;

	}

}
