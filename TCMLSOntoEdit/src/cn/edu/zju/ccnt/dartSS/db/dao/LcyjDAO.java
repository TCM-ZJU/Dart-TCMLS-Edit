package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.By;
import cn.edu.zju.ccnt.dartSS.object.Dzz;
import cn.edu.zju.ccnt.dartSS.object.Lcyj;
import cn.edu.zju.ccnt.dartSS.object.Lczl;
import cn.edu.zju.ccnt.dartSS.object.Syjc;

import org.apache.log4j.Logger;

/**
 * 临床研究对象的DAO
 * 
 * @author zhm
 * 
 */

public class LcyjDAO {
	static Logger logger = Logger.getLogger(LcyjDAO.class.getName());

	public String JBMC = "";

	private String SqlGetlcyjmcByJBMC = "";

	private String SqlGetLcyjslByJBMC = ""; // 根据疾病名称取临床研究数量

	private String sqlGetBYByJBMC = ""; // 病因

	public String tablePrefix = "";

	/**
	 * 根据临床研究名称得倒相关原文信息
	 * 
	 * @param LcyjName
	 * @return
	 */
	public ArrayList<String> getYuanWenByLcyjmc(String LcyjName) {
		String SqlStr = "select ID from Pic_Lcyj where LCYJ_ID in (select LCYJ_ID from "
				+ this.tablePrefix
				+ "_LCYJ where Lcyjmc='"
				+ LcyjName
				+ "') order by PICNO asc";
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		ArrayList<String> PicNoList = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			String item = ResultArray.get(counter).get("ID").toString();
			PicNoList.add(item);
		}

		return PicNoList;
	}

	/**
	 * 根据临床研究名称得倒相关临床诊疗信息
	 * 
	 * @param LcyjName
	 *            临床研究名称
	 * @return
	 */
	public ArrayList<Lczl> getLczlByLcyjmc(String LcyjName) {
		String SqlStr = "select ZDBZLY,ZYZDFF,ZDZBFL,ZBXM,LXBZLY,LXBZ,ZYXL from "
				+ this.tablePrefix
				+ "_LCZL where LCYJ_ID in (select LCYJ_ID from "
				+ this.tablePrefix
				+ "_LCYJ where Lcyjmc='"
				+ LcyjName
				+ "')"
				+ "group by (ZDBZLY,ZYZDFF,ZDZBFL,ZBXM,LXBZLY,LXBZ,ZYXL)";
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		ArrayList<Lczl> lczlList = new ArrayList<Lczl>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Lczl myLczl = new Lczl();

			if (ResultArray.get(counter).get("ZDBZLY") != null)
				myLczl.setZDBZLY(ResultArray.get(counter).get("ZDBZLY")
						.toString());
			else
				myLczl.setZDBZLY("");

			if (ResultArray.get(counter).get("ZYZDFF") != null)
				myLczl.setZYZDFF(ResultArray.get(counter).get("ZYZDFF")
						.toString());
			else
				myLczl.setZYZDFF("");

			if (ResultArray.get(counter).get("ZDZBFL") != null)
				myLczl.setZDZBFL(ResultArray.get(counter).get("ZDZBFL")
						.toString());
			else
				myLczl.setZDZBFL("");

			if (ResultArray.get(counter).get("ZBXM") != null)
				myLczl.setZBXM(ResultArray.get(counter).get("ZBXM").toString());
			else
				myLczl.setZBXM("");

			if (ResultArray.get(counter).get("LXBZLY") != null)
				myLczl.setLXBZLY(ResultArray.get(counter).get("LXBZLY")
						.toString());
			else
				myLczl.setLXBZLY("");

			if (ResultArray.get(counter).get("LXBZ") != null)
				myLczl.setLXBZ(ResultArray.get(counter).get("LXBZ").toString());
			else
				myLczl.setLXBZ("");

			if (ResultArray.get(counter).get("ZYXL") != null)
				myLczl.setZYXL(ResultArray.get(counter).get("ZYXL").toString());
			else
				myLczl.setZYXL("");

			lczlList.add(myLczl);
		}

		return lczlList;
	}

	/**
	 * 根据临床研究名称得倒相关对照组
	 * 
	 * @return
	 */
	public ArrayList<Dzz> GetDzzByLcyjmc(String LcyjName) {
		String SqlStr = "select DZZMC,NL,LS,ZLYW,JC from " + this.tablePrefix
				+ "_dzz where LCYJ_ID in (select LCYJ_ID from "
				+ this.tablePrefix + "_LCYJ where Lcyjmc='" + LcyjName
				+ "') group by(DZZMC,NL,LS,ZLYW,JC)";
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		ArrayList<Dzz> dzzList = new ArrayList<Dzz>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Dzz myDzz = new Dzz();

			if (ResultArray.get(counter).get("DZZMC") != null)
				myDzz
						.setDZZMC(ResultArray.get(counter).get("DZZMC")
								.toString());
			else
				myDzz.setDZZMC("");

			if (ResultArray.get(counter).get("NL") != null)
				myDzz.setNL(ResultArray.get(counter).get("NL").toString());
			else
				myDzz.setNL("");

			if (ResultArray.get(counter).get("LS") != null)
				myDzz.setLS(ResultArray.get(counter).get("LS").toString());
			else
				myDzz.setLS("");

			if (ResultArray.get(counter).get("ZLYW") != null)
				myDzz.setZLYW(ResultArray.get(counter).get("ZLYW").toString());
			else
				myDzz.setZLYW("");
			if (ResultArray.get(counter).get("JC") != null)
				myDzz.setJC(ResultArray.get(counter).get("JC").toString());
			else
				myDzz.setJC("");
			dzzList.add(myDzz);
		}

		return dzzList;
	}

	/**
	 * 根据临床研究名称得倒实验检查信息
	 * 
	 * @param LcyjName
	 *            临床研究名称
	 * @return 返回实验检查对象的ArrayList<Syjc>
	 */
	public ArrayList<Syjc> GetSyjcByLcyjmc(String LcyjName) {
		String SqlStr = "select JCXM,JCSJ,BHZB,GCFZ from " + this.tablePrefix
				+ "_syjc where LCYJ_ID in (select LCYJ_ID from "
				+ this.tablePrefix + "_LCYJ where Lcyjmc='" + LcyjName
				+ "') group by (JCXM,JCSJ,BHZB,GCFZ)";
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		ArrayList<Syjc> syjcList = new ArrayList<Syjc>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Syjc mySyjc = new Syjc();

			if (ResultArray.get(counter).get("JCXM") != null)
				mySyjc.setJCXM(ResultArray.get(counter).get("JCXM").toString());
			else
				mySyjc.setJCXM("");

			if (ResultArray.get(counter).get("JCSJ") != null)
				mySyjc.setJCSJ(ResultArray.get(counter).get("JCSJ").toString());
			else
				mySyjc.setJCSJ("");

			if (ResultArray.get(counter).get("BHZB") != null)
				mySyjc.setBHZB(ResultArray.get(counter).get("BHZB").toString());
			else
				mySyjc.setBHZB("");

			if (ResultArray.get(counter).get("GCFZ") != null)
				mySyjc.setGCFZ(ResultArray.get(counter).get("GCFZ").toString());
			else
				mySyjc.setGCFZ("");

			syjcList.add(mySyjc);
		}

		return syjcList;
	}

	/**
	 * 根据临床研究名称LcyjName得倒一个临床研究的详细信息
	 * 
	 * @param LcyjName
	 *            临床研究名称
	 * @return 返回一个临床研究对象
	 */
	public Lcyj getLcyjByLcyjmc(String LcyjName) {
		String SqlStr = "select * from " + this.tablePrefix
				+ "_LCYJ where Lcyjmc='" + LcyjName + "'";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		Lcyj myLcyj = new Lcyj();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			if (ResultArray.get(counter).get("LCYJMC") != null)
				myLcyj.setLCYJMC(ResultArray.get(counter).get("LCYJMC")
						.toString());
			else
				myLcyj.setLCYJMC("");

			if (ResultArray.get(counter).get("YJFL") != null)
				myLcyj.setYJFL(ResultArray.get(counter).get("YJFL").toString());
			else
				myLcyj.setYJFL("");

			if (ResultArray.get(counter).get("BLCJSJ") != null)
				myLcyj.setBLCJSJ(ResultArray.get(counter).get("BLCJSJ")
						.toString());
			else
				myLcyj.setBLCJSJ("");

			if (ResultArray.get(counter).get("GCZZLS") != null)
				myLcyj.setGCZZLS(ResultArray.get(counter).get("GCZZLS")
						.toString());
			else
				myLcyj.setGCZZLS("");

			if (ResultArray.get(counter).get("GCZZLS") != null)
				myLcyj.setGCZZLS(ResultArray.get(counter).get("GCZZLS")
						.toString());
			else
				myLcyj.setGCZZLS("");

			if (ResultArray.get(counter).get("FGCLS") != null)
				myLcyj.setFGCLS(ResultArray.get(counter).get("FGCLS")
						.toString());
			else
				myLcyj.setFGCLS("");

			if (ResultArray.get(counter).get("MGCLS") != null)
				myLcyj.setMGCLS(ResultArray.get(counter).get("MGCLS")
						.toString());
			else
				myLcyj.setMGCLS("");

			if (ResultArray.get(counter).get("NLZ") != null)
				myLcyj.setNLZ(ResultArray.get(counter).get("NLZ").toString());
			else
				myLcyj.setNLZ("");

			if (ResultArray.get(counter).get("YJFF") != null)
				myLcyj.setYJFF(ResultArray.get(counter).get("YJFF").toString());
			else
				myLcyj.setYJFF("");

			if (ResultArray.get(counter).get("YJFZ") != null)
				myLcyj.setYJFZ(ResultArray.get(counter).get("YJFZ").toString());
			else
				myLcyj.setYJFZ("");

			if (ResultArray.get(counter).get("JG") != null)
				myLcyj.setJG(ResultArray.get(counter).get("JG").toString());
			else
				myLcyj.setJG("");

			if (ResultArray.get(counter).get("JG") != null)
				myLcyj.setLCYJDW(ResultArray.get(counter).get("LCYJDW")
						.toString());
			else
				myLcyj.setLCYJDW("");

			break;
		}
		return myLcyj;
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

	public String getSqlGetlcyjmcByJBMC() {
		return SqlGetlcyjmcByJBMC;
	}

	private void setSqlGetlcyjmcByJBMC() {

		SqlGetlcyjmcByJBMC = "select LCYJMC from " + this.tablePrefix
				+ "_lcyj where LCYJ_ID in (select lCYJ_ID from " + tablePrefix
				+ "_Cxlcyjdx where CXDX_ID in " + "(select JB_ID from "
				+ tablePrefix + "_JB where JBMC in " + getJbmcs() + "))";
	}

	public String getJbmcs() {
		Dss_subjectDAO subjectDAO = new Dss_subjectDAO();

		return subjectDAO.getRelatedJbmc(this.JBMC);
	}

	/**
	 * 根据疾病名称得到所有的临床研究的名称
	 * 
	 * @return
	 */
	public ArrayList<String> GetLcyjmcByJbmc() {
		setSqlGetlcyjmcByJBMC();
		logger.debug("Get lcyj.Lcyjmc By Jb.JBMC with sql:"
				+ SqlGetlcyjmcByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetlcyjmcByJBMC);

		ArrayList<String> LcyjmcArray = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			String item = ResultArray.get(counter).get("LCYJMC").toString();
			LcyjmcArray.add(item);
		}

		return LcyjmcArray;
	}

	/**
	 * 根据疾病名称得到所有的临床研究单位的信息
	 * 
	 * @return
	 */
	public ArrayList<Lcyj> GetDWmcByJbmc() {
		String queryStr = "select distinct Lcyjdw,count(Lcyjdw) Num   from "
				+ this.tablePrefix + "_lcyj where "
				+ " Lcyj_Id in ( select Lcyj_Id from " + this.tablePrefix
				+ "_Cxlcyjdx where Cxdx_Id in " + " (select Jb_Id from "
				+ this.tablePrefix + "_Jb  where Jbmc='" + this.JBMC + "'))"
				+ " group by Lcyjdw  order by Num  desc";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(queryStr);

		ArrayList<Lcyj> LcyjList = new ArrayList<Lcyj>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Lcyj lcyj = new Lcyj();
			if (ResultArray.get(counter).get("LCYJDW") != null)
				lcyj.setDW(ResultArray.get(counter).get("LCYJDW").toString());
			else
				lcyj.setDW("");
			lcyj.setNUM(ResultArray.get(counter).get("NUM").toString());
			
			LcyjList.add(lcyj);
		}

		return LcyjList;
	}

	/**
	 * 根据疾病名称和临床研究单位得到文献信息
	 * 
	 * @return
	 */
	public ArrayList<String> GetLCYJMCByJbmcAndDW(String dw) {
		String queryStr = " select LCYJMC from JMZ_lcyj where Lcyjdw like '%"
				+ dw + "%' " + " and Lcyj_Id in ( select Lcyj_Id from "
				+ this.tablePrefix + "_Cxlcyjdx where Cxdx_Id in "
				+ " (select Jb_Id from " + this.tablePrefix
				+ "_Jb  where Jbmc='" + this.JBMC + "'))";

		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(queryStr);

		ArrayList<String> LcyjList = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("LCYJMC") != null)
				LcyjList.add(ResultArray.get(counter).get("LCYJMC").toString());
			else
				LcyjList.add("");
		}

		return LcyjList;
	}

	/**
	 * 根据疾病名称得到所有的临床研究期刊名称
	 * 
	 * @return
	 */
	public ArrayList<Lcyj> GetQKMCByJbmc() {
		String queryStr = " select distinct QKMC,count(QKMC) Num   from  c_lcyj " +
				"where c_lcyj.lcyjmc in (select LCYJMC from Jmz_Lcyj where Jmz_Lcyj.Lcyj_Id   " +
				"in ( select Lcyj_Id from "+this.tablePrefix+"_Cxlcyjdx where Cxdx_Id in   " +
				"(select Jb_Id from "+this.tablePrefix+"_Jb  where Jbmc='"+this.JBMC+"')))  " +
				"group by QKMC  order by Num  desc";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(queryStr);

		ArrayList<Lcyj> LcyjList = new ArrayList<Lcyj>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Lcyj lcyj = new Lcyj();
			if (ResultArray.get(counter).get("QKMC") != null)
				lcyj.setQKMC(ResultArray.get(counter).get("QKMC").toString());
			else
				lcyj.setQKMC("");
			lcyj.setNUM(ResultArray.get(counter).get("NUM").toString());
			
			LcyjList.add(lcyj);
		}

		return LcyjList;
	}
	
	
	/**
	 * 根据疾病名称和期刊名称得到所有的临床文献
	 * 
	 * @return
	 */
	public ArrayList<String> GetLCYJByJbmcAndQKMC(String QKMC) {
		String queryStr = "select LCYJMC from c_lcyj "+
		"where QKMC='"+QKMC+"' and c_lcyj.lcyjmc in (select LCYJMC from Jmz_Lcyj where Jmz_Lcyj.Lcyj_Id   " +
		"in ( select Lcyj_Id from "+this.tablePrefix+"_Cxlcyjdx where Cxdx_Id in   " +
		"(select Jb_Id from "+this.tablePrefix+"_Jb  where Jbmc='"+this.JBMC+"')))  " ;
			
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(queryStr);

		ArrayList<String> LcyjList = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {			
			if (ResultArray.get(counter).get("LCYJMC") != null)
				LcyjList.add(ResultArray.get(counter).get("LCYJMC").toString());
			else
				LcyjList.add("");
		}

		return LcyjList;
	}
	
	/**
	 * 根据疾病名称得到所有的期刊的期数信息
	 * 
	 * @return
	 */
	public ArrayList<Lcyj> GetQSByJbmc() {
		String queryStr = "select QS,QKMC,LCYJMC from "
				+ " c_lcyj where c_lcyj.lcyjmc in (select LCYJMC from Jmz_Lcyj where Jmz_Lcyj.Lcyj_Id   " +
				"in ( select Lcyj_Id from "+this.tablePrefix+"_Cxlcyjdx where Cxdx_Id in   " +
				"(select Jb_Id from "+this.tablePrefix+"_Jb  where Jbmc='"+this.JBMC+"')))  "
				+ " order by QS  desc";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(queryStr);

		ArrayList<Lcyj> LcyjList = new ArrayList<Lcyj>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Lcyj lcyj = new Lcyj();
			if (ResultArray.get(counter).get("QS") != null)
				lcyj.setQS(ResultArray.get(counter).get("QS").toString());
			else
				lcyj.setQS("");
			if (ResultArray.get(counter).get("QKMC") != null)
				lcyj.setQKMC(ResultArray.get(counter).get("QKMC").toString());
			else
				lcyj.setQKMC("");
			if (ResultArray.get(counter).get("LCYJMC") != null)
				lcyj.setLCYJMC(ResultArray.get(counter).get("LCYJMC").toString());
			else
				lcyj.setLCYJMC("");
			
			LcyjList.add(lcyj);
		}

		return LcyjList;
	}
	
	/**
	 * 根据疾病名称得倒临床研究数量
	 * 
	 * @return
	 */
	public String GetLcyjslByJBMC() {
		setSqlGetLcyjslByJBMC();
		logger.debug("Get 临床研究数量 By Jb.JBMC with sql:" + SqlGetLcyjslByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetLcyjslByJBMC);

		return ResultArray.get(0).get("SL").toString();
	}

	/**
	 * 根据疾病名称得倒病因
	 * 
	 * @return
	 */
	public ArrayList<By> GetByByJbmc() {
		setSqlGetBYByJBMC();
		logger.debug("Get lcyj.J_BY By Jb.JBMC with sql:" + sqlGetBYByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sqlGetBYByJBMC);

		ArrayList<By> byArray = new ArrayList<By>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			By newBy = new By();

			if (ResultArray.get(counter).get("J_BY") != null)
				newBy.setJ_BY(ResultArray.get(counter).get("J_BY").toString());
			else
				newBy.setJ_BY("");

			if (ResultArray.get(counter).get("NLZ") != null)
				newBy.setNLZ(ResultArray.get(counter).get("NLZ").toString());
			else
				newBy.setNLZ("");

			if (ResultArray.get(counter).get("JG") != null)
				newBy.setJG(ResultArray.get(counter).get("JG").toString());
			else
				newBy.setJG("");

			if (ResultArray.get(counter).get("LCYJMC") != null)
				newBy.setLCYJMC(ResultArray.get(counter).get("LCYJMC")
						.toString());
			else
				newBy.setLCYJMC("");

			byArray.add(newBy);
		}

		return byArray;
	}

	public String getSqlGetBYByJBMC() {
		return sqlGetBYByJBMC;
	}

	public void setSqlGetBYByJBMC() {
		this.sqlGetBYByJBMC = "select J_BY,NLZ,JG,LCYJMC from "
				+ this.tablePrefix
				+ "_Lcyj where LCYJ_ID in (select LCYJ_ID from " + tablePrefix
				+ "_Jtzlgc where JTZLGC_ID in (select JTZLGC_ID from "
				+ tablePrefix
				+ "_Zlgcdxgl  where CXDX_ID in (select JB_ID from "
				+ tablePrefix + "_jb where jbmc in " + getJbmcs()
				+ " ))) and J_BY<>' ' order by J_BY";
	}

	public String getSqlGetLcyjslByJBMC() {
		return SqlGetLcyjslByJBMC;
	}

	public void setSqlGetLcyjslByJBMC() {
		SqlGetLcyjslByJBMC = "select count(LCYJMC) SL from " + this.tablePrefix
				+ "_lcyj where LCYJ_ID in (select lCYJ_ID from " + tablePrefix
				+ "_Cxlcyjdx where CXDX_ID in " + "(select JB_ID from "
				+ tablePrefix + "_JB where JBMC in" + getJbmcs() + "))";
	}

	/**
	 * 根据临床症状名称得倒相关的临床研究列表，用于证候到临床研究的导航查询
	 * 
	 * @param zzmc
	 *            症状名称
	 * @return
	 */
	public ArrayList<String> getLcyjmcByZZMC(String zzmc) {
		String Sql = "select LCYJMC from " + this.tablePrefix
				+ "_Lcyj where Lcyj_Id in(select lcyj_ID  from "
				+ this.tablePrefix
				+ "_Cxlcyjdx where Cxdx_Id in(select ZZ_ID from "
				+ this.tablePrefix + "_Lczz where Zzmc='" + zzmc + "'))";
		logger.debug("Get related lcyj.Lcyjmc  By LCZZ.zzmc with sql:" + Sql);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(Sql);

		ArrayList<String> LcyjmcArray = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			String item = ResultArray.get(counter).get("LCYJMC").toString();
			LcyjmcArray.add(item);
		}

		return LcyjmcArray;

	}

	/**
	 * 根据临床证候名称得倒相关的临床研究列表，用于证候到临床研究的导航查询
	 * 
	 * @param zhmc
	 *            证候名称
	 * @return
	 */
	public ArrayList<String> getLcyjmcByZhMC(String zhmc) {
		String Sql = "select LCYJMC from " + this.tablePrefix
				+ "_Lcyj where Lcyj_Id in(select lcyj_ID  from "
				+ this.tablePrefix
				+ "_Cxlcyjdx where Cxdx_Id in(select ZH_ID from "
				+ this.tablePrefix + "_Zh where zhmc='" + zhmc + "'))";
		logger.debug("Get related lcyj.Lcyjmc  By Zh.zhmc with sql:" + Sql);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(Sql);

		ArrayList<String> LcyjmcArray = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			String item = ResultArray.get(counter).get("LCYJMC").toString();
			LcyjmcArray.add(item);
		}

		return LcyjmcArray;

	}

}
