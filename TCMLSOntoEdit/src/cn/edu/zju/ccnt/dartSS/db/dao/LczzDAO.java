package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Lczz;

/**
 * 临床症状的DAO
 * @author zhm 
 */
public class LczzDAO {
	static Logger logger = Logger.getLogger(LczlDAO.class.getName());

	/**
	 * 疾病名称
	 */
	public String JBMC = "";

	public String tablePrefix = "";

	private String SqlGetLczzByJBMC = "";

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

	/**通过疾病名称得到临床症状
	 * @return
	 */
	public ArrayList<Lczz> GetLczzByJbmc() {
		setSqlGetLczzByJBMC();
		logger.debug("Get 临床症状 By Jb.JBMC with sql:" + SqlGetLczzByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetLczzByJBMC);

		ArrayList<Lczz> lczzArray = new ArrayList<Lczz>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Lczz lczz = new Lczz();
			if (ResultArray.get(counter).get("ZZMC") != null)
				lczz.setZZMC(ResultArray.get(counter).get("ZZMC").toString());
			else
				lczz.setZZMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				lczz.setRefNum(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				lczz.setRefNum("");

			lczzArray.add(lczz);
		}

		return lczzArray;
	}

	public String getSqlGetLczzByJBMC() {
		return SqlGetLczzByJBMC;
	}

	public void setSqlGetLczzByJBMC() {
		SqlGetLczzByJBMC = "select distinct(ZZMC),count(ZZMC) REFNUM from "
				+ this.tablePrefix + "_LCZZ where " + tablePrefix
				+ "_LCZZ.Zz_Id in(select ZZ_ID from " + tablePrefix
				+ "_JBZZGL where JB_ID in (select JB_ID from " + tablePrefix
				+ "_JB  where JBMC in " +getJbmcs()
				+ ")) group by ZZMC order by(count(ZZMC)) desc";
	}
	public String getJbmcs()
	{
		Dss_subjectDAO subjectDAO=new Dss_subjectDAO();
		
		return  subjectDAO.getRelatedJbmc(this.JBMC);
	}
	

	/**
	 * 从临床证候名称得倒相关的临床症状名称
	 * @param zhmc 证候名称
	 * @return
	 */
	public ArrayList<Lczz> getZzmcByZhMC(String zhmc) {
		String Sql = "select Zzmc ,count(ZZMC) REFNUM from " + this.tablePrefix
				+ "_Lczz where Zz_Id in	(select Zz_Id  from " + tablePrefix
				+ "_Zhzzgl where Zh_Id in (select ZH_ID from " + tablePrefix
				+ "_ZH where ZHmc='" + zhmc + "')) group by ZZMC order by(count(ZZMC)) desc";
		logger.debug("从临床证候名称得倒相关的临床症状名称 with sql:" + Sql);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(Sql);

		ArrayList<Lczz> zzmcArray = new ArrayList<Lczz>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Lczz lczz = new Lczz();
			if (ResultArray.get(counter).get("ZZMC") != null)
				lczz.setZZMC(ResultArray.get(counter).get("ZZMC").toString());
			else
				lczz.setZZMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				lczz.setRefNum(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				lczz.setRefNum("");
			zzmcArray.add(lczz);
		}

		return zzmcArray;

	}
}
