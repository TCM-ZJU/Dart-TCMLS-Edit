package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Amlf;
import cn.edu.zju.ccnt.dartSS.object.Qtlf;
import cn.edu.zju.ccnt.dartSS.object.Xw;
import cn.edu.zju.ccnt.dartSS.object.Xylf;
import cn.edu.zju.ccnt.dartSS.object.Yflf;
import cn.edu.zju.ccnt.dartSS.object.Yw;
import cn.edu.zju.ccnt.dartSS.object.Zjlf;
import cn.edu.zju.ccnt.dartSS.object.Zlfa;

/** 治疗方案DAO
 * @author zhm
 *
 */
public class ZlfaDAO {
	static Logger logger = Logger.getLogger(LcyjDAO.class.getName());

	public String JBMC = "";

	public String tablePrefix = "";

	private String SqlGetZlfaByJbmc = "";
	
	/**根据药物ID得到药物信息
	 * @param ywid
	 * @return
	 */
	public ArrayList<Yw> getYwByYwID(String ywid){
		logger.debug("根据药物ID得到药物信息:" + this.JBMC);
		String sql="select * from " + this.tablePrefix +"_YW where Zlgc_Id ="+ywid;

		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sql);

		ArrayList<Yw> zlfaArray = new ArrayList<Yw>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Yw zlfa = new Yw();
			if (ResultArray.get(counter).get("ZLGC_ID") != null)
				zlfa.setZLGC_ID(ResultArray.get(counter).get("ZLGC_ID")
						.toString());
			else
				zlfa.setZLGC_ID("");
			
			if (ResultArray.get(counter).get("YWMC") != null)
				zlfa.setYWMC(ResultArray.get(counter).get("YWMC")
						.toString());
			else
				zlfa.setYWMC("");

			if (ResultArray.get(counter).get("YMJL") != null)
				zlfa.setYMJL(ResultArray.get(counter).get("YMJL")
						.toString());
			else
				zlfa.setYMJL("");
			
			if (ResultArray.get(counter).get("JLDW") != null)
				zlfa.setJLDW(ResultArray.get(counter).get("JLDW")
						.toString());
			else
				zlfa.setJLDW("");
			
			zlfaArray.add(zlfa);
		}
		
		return zlfaArray;
	}
	
	
	/**根据药物ID得到药物信息
	 * @param ywid
	 * @return
	 */
	public ArrayList<Xw> getXwByID(String XwID){
		logger.debug("根据穴位ID得到穴位信息:" + this.JBMC);
		String sql="select * from "+this.tablePrefix+"_xw where zlgc_id="+XwID;

		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sql);

		ArrayList<Xw> zlfaArray = new ArrayList<Xw>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Xw zlfa = new Xw();
			if (ResultArray.get(counter).get("ZLGC_ID") != null)
				zlfa.setZLGC_ID(ResultArray.get(counter).get("ZLGC_ID")
						.toString());
			else
				zlfa.setZLGC_ID("");
			
			if (ResultArray.get(counter).get("XWMC") != null)
				zlfa.setXWMC(ResultArray.get(counter).get("XWMC")
						.toString());
			else
				zlfa.setXWMC("");

			if (ResultArray.get(counter).get("XWLX") != null)
				zlfa.setXWLX(ResultArray.get(counter).get("XWLX")
						.toString());
			else
				zlfa.setXWLX("");		
			
			
			zlfaArray.add(zlfa);
		}
		
		return zlfaArray;
	}
	
	/**根据疾病名称得到药方疗法的方案
	 * @return
	 */
	public ArrayList<Yflf> getYflfByJbmc(){
		logger.debug("根据疾病名称得到中药药方的方案:" + this.JBMC);
		String sql="select * from " + this.tablePrefix +"_Yflf " +
				"where Zlgc_Id in (select "
		+ this.tablePrefix +"_Jtzlgc.Jtzlgc_Id from "
		+ this.tablePrefix +"_Jtzlgc  where Jtzlgc_Id in (select JTZLGC_ID from  "
		+ tablePrefix
		+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
		+ tablePrefix
		+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
		+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
		+ getJbmcs()+ "))))"
		+ " order by YFMC asc";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sql);

		ArrayList<Yflf> zlfaArray = new ArrayList<Yflf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Yflf zlfa = new Yflf();
			if (ResultArray.get(counter).get("ZLGC_ID") != null)
				zlfa.setZLGC_ID(ResultArray.get(counter).get("ZLGC_ID")
						.toString());
			else
				zlfa.setZLGC_ID("");
			
			if (ResultArray.get(counter).get("GYFS") != null)
				zlfa.setGYFS(ResultArray.get(counter).get("GYFS")
						.toString());
			else
				zlfa.setGYFS("");

			if (ResultArray.get(counter).get("YFMC") != null)
				zlfa.setYFMC(ResultArray.get(counter).get("YFMC")
						.toString());
			else
				zlfa.setYFMC("");
			
			if (ResultArray.get(counter).get("YFJX") != null)
				zlfa.setYFJX(ResultArray.get(counter).get("YFJX")
						.toString());
			else
				zlfa.setYFJX("");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				zlfa.setZZ(ResultArray.get(counter).get("ZZ")
						.toString());
			else
				zlfa.setZZ("");
			
			zlfaArray.add(zlfa);
		}
		
		return zlfaArray;
	}
		
	/**根据疾病名称得到针灸疗法的方案
	 * @return
	 */
	public ArrayList<Zjlf> getZjlfByJbmc(){
		logger.debug("根据疾病名称得到针灸疗法的方案:" + this.JBMC);
		String sql="select * from " + this.tablePrefix +"_zjlf " +
				"where Zlgc_Id in (select "
		+ this.tablePrefix +"_Jtzlgc.Jtzlgc_Id from "
		+ this.tablePrefix +"_Jtzlgc  where Jtzlgc_Id in (select JTZLGC_ID from  "
		+ tablePrefix
		+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
		+ tablePrefix
		+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
		+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
		+ getJbmcs()+ "))))"
		+ " order by ZJFF asc";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sql);

		ArrayList<Zjlf> zlfaArray = new ArrayList<Zjlf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Zjlf zlfa = new Zjlf();
			if (ResultArray.get(counter).get("ZLGC_ID") != null)
				zlfa.setZLGC_ID(ResultArray.get(counter).get("ZLGC_ID")
						.toString());
			else
				zlfa.setZLGC_ID("");
			
			if (ResultArray.get(counter).get("ZJFF") != null)
				zlfa.setZJFF(ResultArray.get(counter).get("ZJFF")
						.toString());
			else
				zlfa.setZJFF("");

			if (ResultArray.get(counter).get("ZJQX") != null)
				zlfa.setZJQX(ResultArray.get(counter).get("ZJQX")
						.toString());
			else
				zlfa.setZJQX("");
			
			if (ResultArray.get(counter).get("ZCSF") != null)
				zlfa.setZCSF(ResultArray.get(counter).get("ZCSF")
						.toString());
			else
				zlfa.setZCSF("");
			
			if (ResultArray.get(counter).get("ZCCS") != null)
				zlfa.setZCCS(ResultArray.get(counter).get("ZCCS")
						.toString());
			else
				zlfa.setZCCS("");
			if (ResultArray.get(counter).get("JL") != null)
				zlfa.setJL(ResultArray.get(counter).get("JL")
						.toString());
			else
				zlfa.setJL("");
			if (ResultArray.get(counter).get("ZZ") != null)
				zlfa.setZZ(ResultArray.get(counter).get("ZZ")
						.toString());
			else
				zlfa.setZZ("");
			
			zlfaArray.add(zlfa);
		}
		
		return zlfaArray;
	}
	
	/**根据疾病名称得到其他疗法的方案
	 * @return
	 */
	public ArrayList<Qtlf> getQtalfByJbmc(){
		logger.debug("根据疾病名称得到其他疗法的方案:" + this.JBMC);
		String sql="select * from " + this.tablePrefix +"_qtlf " +
				"where Zlgc_Id in (select "
		+ this.tablePrefix +"_Jtzlgc.Jtzlgc_Id from "
		+ this.tablePrefix +"_Jtzlgc  where Jtzlgc_Id in (select JTZLGC_ID from  "
		+ tablePrefix
		+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
		+ tablePrefix
		+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
		+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
		+ getJbmcs()+ "))))"
		+ " order by FFMC asc";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sql);

		ArrayList<Qtlf> zlfaArray = new ArrayList<Qtlf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Qtlf zlfa = new Qtlf();
			if (ResultArray.get(counter).get("ZLGC_ID") != null)
				zlfa.setZLGC_ID(ResultArray.get(counter).get("ZLGC_ID")
						.toString());
			else
				zlfa.setZLGC_ID("");
			
			if (ResultArray.get(counter).get("FFMC") != null)
				zlfa.setFFMC(ResultArray.get(counter).get("FFMC")
						.toString());
			else
				zlfa.setFFMC("");

			if (ResultArray.get(counter).get("FFMS") != null)
				zlfa.setFFMS(ResultArray.get(counter).get("FFMS")
						.toString());
			else
				zlfa.setFFMS("");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				zlfa.setZZ(ResultArray.get(counter).get("ZZ")
						.toString());
			else
				zlfa.setZZ("");			
			
			zlfaArray.add(zlfa);
		}
		
		return zlfaArray;
	}
		
	
	/**根据疾病名称得到按摩疗法的方案
	 * @return
	 */
	public ArrayList<Amlf> getAmlfByJbmc(){
		logger.debug("根据疾病名称得到按摩疗法的方案:" + this.JBMC);
		String sql="select * from " + this.tablePrefix +"_amlf " +
				"where Zlgc_Id in (select "
		+ this.tablePrefix +"_Jtzlgc.Jtzlgc_Id from "
		+ this.tablePrefix +"_Jtzlgc  where Jtzlgc_Id in (select JTZLGC_ID from  "
		+ tablePrefix
		+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
		+ tablePrefix
		+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
		+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
		+ getJbmcs()+ "))))"
		+ " order by AMFF asc";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sql);

		ArrayList<Amlf> zlfaArray = new ArrayList<Amlf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Amlf zlfa = new Amlf();
			if (ResultArray.get(counter).get("ZLGC_ID") != null)
				zlfa.setZLGC_ID(ResultArray.get(counter).get("ZLGC_ID")
						.toString());
			else
				zlfa.setZLGC_ID("");
			
			if (ResultArray.get(counter).get("AMFF") != null)
				zlfa.setAMFF(ResultArray.get(counter).get("AMFF")
						.toString());
			else
				zlfa.setAMFF("");

			if (ResultArray.get(counter).get("AMSJ") != null)
				zlfa.setAMSJ(ResultArray.get(counter).get("AMSJ")
						.toString());
			else
				zlfa.setAMSJ("");
			
			if (ResultArray.get(counter).get("AMSF") != null)
				zlfa.setAMSF(ResultArray.get(counter).get("AMSF")
						.toString());
			else
				zlfa.setAMSF("");
			
			if (ResultArray.get(counter).get("AMCS") != null)
				zlfa.setAMCS(ResultArray.get(counter).get("AMCS")
						.toString());
			else
				zlfa.setAMCS("");
			if (ResultArray.get(counter).get("JL") != null)
				zlfa.setJL(ResultArray.get(counter).get("JL")
						.toString());
			else
				zlfa.setJL("");
			if (ResultArray.get(counter).get("ZZ") != null)
				zlfa.setZZ(ResultArray.get(counter).get("ZZ")
						.toString());
			else
				zlfa.setZZ("");
			
			zlfaArray.add(zlfa);
		}
		
		return zlfaArray;
	}
		
	
		/**根据疾病名称得到西药疗法的方案
		 * @return
		 */
	public ArrayList<Xylf> GetXylfByJbmc(){
		
		logger.debug("根据疾病名称得到西药疗法的方案:" + this.JBMC);
		String sql="select * from " + this.tablePrefix +"_Xylf " +
				"where Zlgc_Id in (select "
		+ this.tablePrefix +"_Jtzlgc.Jtzlgc_Id from "
		+ this.tablePrefix +"_Jtzlgc  where Jtzlgc_Id in (select JTZLGC_ID from  "
		+ tablePrefix
		+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
		+ tablePrefix
		+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
		+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
		+ getJbmcs()+ "))))"
		+ " order by YWMC asc";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sql);

		ArrayList<Xylf> zlfaArray = new ArrayList<Xylf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Xylf zlfa = new Xylf();
			if (ResultArray.get(counter).get("GYFS") != null)
				zlfa.setGYFS(ResultArray.get(counter).get("GYFS")
						.toString());
			else
				zlfa.setGYFS("");

			if (ResultArray.get(counter).get("YWMC") != null)
				zlfa.setYWMC(ResultArray.get(counter).get("YWMC")
						.toString());
			else
				zlfa.setYWMC("");
			
			if (ResultArray.get(counter).get("YWJX") != null)
				zlfa.setYWJX(ResultArray.get(counter).get("YWJX")
						.toString());
			else
				zlfa.setYWJX("");
			
			if (ResultArray.get(counter).get("GYSJ") != null)
				zlfa.setGYSJ(ResultArray.get(counter).get("GYSJ")
						.toString());
			else
				zlfa.setGYSJ("");
			
			if (ResultArray.get(counter).get("ZLYZ") != null)
				zlfa.setZLYZ(ResultArray.get(counter).get("ZLYZ")
						.toString());
			else
				zlfa.setZLYZ("");

			zlfaArray.add(zlfa);
		}

		return zlfaArray;
	}

	/**
	 * 根据疾病名称得倒治疗方案的列表，考虑入口词和主题词
	 * @return
	 */
	public ArrayList<Zlfa> GetZlfaByJbmc() {
		setSqlGetZlfaByJbmc();
		logger.debug("Get 治疗方案列表 By Jb.JBMC with sql:" + SqlGetZlfaByJbmc);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetZlfaByJbmc);

		ArrayList<Zlfa> zlfaArray = new ArrayList<Zlfa>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Zlfa zlfa = new Zlfa();
			if (ResultArray.get(counter).get("ZLFALY") != null)
				zlfa.setZlfaly(ResultArray.get(counter).get("ZLFALY")
						.toString());
			else
				zlfa.setZlfaly("");

			if (ResultArray.get(counter).get("ZLFAGS") != null)
				zlfa.setZlfags(ResultArray.get(counter).get("ZLFAGS")
						.toString());
			else
				zlfa.setZlfags("");

			zlfaArray.add(zlfa);
		}

		return zlfaArray;

	}

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

	public String getSqlGetZlfaByJbmc() {
		return SqlGetZlfaByJbmc;
	}

	public void setSqlGetZlfaByJbmc() {
		SqlGetZlfaByJbmc = "select " + this.tablePrefix
				+ "_Lcyj.Lcyjmc Zlfaly, count(" + this.tablePrefix
				+ "_Jtzlgc.Lcyj_Id) Zlfags from " + this.tablePrefix
				+ "_Jtzlgc inner join " + this.tablePrefix + "_Lcyj on "
				+ tablePrefix + "_Lcyj.Lcyj_Id=" + tablePrefix
				+ "_Jtzlgc.Lcyj_Id where Jtzlgc_Id in (select JTZLGC_ID from  "
				+ tablePrefix
				+ "_ZLGCDXGL where CXDX_ID in (select Cxdx_Id from "
				+ tablePrefix
				+ "_Cxlcyjdx where CXDX_ID in (select JB_ID  from "
				+ tablePrefix + "_Jb where  " + tablePrefix + "_Jb.Jbmc in"
				+ getJbmcs()+ ")))"
				+ "group by Lcyjmc order by count(Lcyjmc) asc";
	}
	
	public String getJbmcs()
	{
		Dss_subjectDAO subjectDAO=new Dss_subjectDAO();
		
		return  subjectDAO.getRelatedJbmc(this.JBMC);
	}
	

}
