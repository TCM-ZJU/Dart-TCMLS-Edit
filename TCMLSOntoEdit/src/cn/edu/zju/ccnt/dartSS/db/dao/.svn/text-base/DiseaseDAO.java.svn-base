package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Disease;
import cn.edu.zju.ccnt.dartSS.object.Subject;

import org.apache.log4j.Logger;

/**
 * 疾病类的DAO
 * 
 * @author zhm
 * 
 */
public class DiseaseDAO {

	static Logger logger = Logger.getLogger(DiseaseDAO.class.getName());

	private String sqlDiseaseInfo;

	/**
	 * _JB表的前缀，用专题的编码表示。
	 */
	public String tablePrefix = "";

	public String diseaseName = "";

	/**
	 * 搜索与jbmc匹配的所有的疾病名称
	 * 
	 * @param jbmc
	 *            疾病名称
	 * @return 返回包含所有符合条件的疾病的ArrayList<Disease>
	 */
	public ArrayList<Disease> DiseaseSearch(String jbmc) {
		Dss_subjectDAO subjectDAO = new Dss_subjectDAO();
		ArrayList<Disease> diseaselist = new ArrayList<Disease>();
		// 得倒所有的专题
		ArrayList<Subject> subjectList = subjectDAO.GetAllSubjectDetail();
		for (int i = 0; i < subjectList.size(); i++) {
			String subjectName = subjectList.get(i).getS_NAME();
			String subjectTag = subjectList.get(i).getS_TAG();
			// 依次查询各个专题的疾病表
			ArrayList<String> jbmcList = getJbmcInJBTable(subjectTag, jbmc);
			if (null == jbmcList)
				continue;
			for (int j = 0; j < jbmcList.size(); j++) {
				Disease newDisease = new Disease();
				newDisease.setSubject(subjectName);
				newDisease.setJBMC(jbmcList.get(j).toString());
				diseaselist.add(newDisease);
			}
		}
		return diseaselist;
	}

	/**
	 * 搜索与jbmc匹配的所有的疾病名称 在指定的专题库中搜索
	 * 
	 * @param jbmc
	 *            疾病名称
	 * @return 返回包含所有符合条件的疾病的ArrayList<Disease>
	 */
	public ArrayList<Disease> DiseaseSearch(String jbmc, String subjectName,
			String subjectTag) {
		Dss_subjectDAO subjectDAO = new Dss_subjectDAO();
		ArrayList<Disease> diseaselist = new ArrayList<Disease>();

		// 依次查询各个专题的疾病表
		ArrayList<String> jbmcList = getJbmcInJBTable(subjectTag, jbmc);
		if (jbmcList == null)
			return null;
		for (int j = 0; j < jbmcList.size(); j++) {
			Disease newDisease = new Disease();
			newDisease.setSubject(subjectName);
			newDisease.setJBMC(jbmcList.get(j).toString());
			diseaselist.add(newDisease);
		}
		return diseaselist;
	}

	/**
	 * 从一个指定的专题中，根据疾病名称关键字，在疾病表（_JB）中查找符合条件的疾病
	 * 
	 * @param subjectTag
	 *            专题编码
	 * @param jbmc
	 *            疾病名称关键字
	 * @return 返回包含所有符合条件的疾病的ArrayList<String>
	 */
	public ArrayList<String> getJbmcInJBTable(String subjectTag, String jbmc) {
		ArrayList<String> DiseaseNameList = new ArrayList<String>();
		try {
			String SqlStr = "select distinct(JBMC) from " + subjectTag
					+ "_JB where JBMC like '%" + jbmc + "%' ";

			DataBaseActivity dba = new DataBaseActivity();

			ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
			ResultArray = dba.DBSelect(SqlStr);

			if (DiseaseNameList == null)
				return null;
			for (int counter = 0; counter < ResultArray.size(); counter++) {
				String item = ResultArray.get(counter).get("JBMC").toString();
				DiseaseNameList.add(item);
			}
		} catch (Exception ec) {
			logger.warn("查询疾病名称时发生错误：" + ec.getMessage() + " " + ec.toString());
		}
		return DiseaseNameList;
	}

	/**
	 * 根据临床研究名称得倒相关的疾病名称，用于从临床研究到疾病名称的导航查询。所属的专题由tablePrefix给出。
	 * 
	 * @param LcyjName
	 *            临床研究的名称
	 * @return 返回包含所有符合条件的疾病的ArrayList<String>
	 */
	public ArrayList<String> getJBMCByLcyjmc(String LcyjName) {
		String SqlStr = "select distinct JBMC from " + this.tablePrefix
				+ "_JB where JB_ID in (select CXDX_ID from " + this.tablePrefix
				+ "_CXLCYJDX where LCYJ_ID in (select LCYJ_ID from "
				+ this.tablePrefix + "_LCYJ where Lcyjmc='" + LcyjName + "'))";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		ArrayList<String> DiseaseNameList = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			String item = ResultArray.get(counter).get("JBMC").toString();
			DiseaseNameList.add(item);
		}

		return DiseaseNameList;
	}

	/**
	 * 根据证候名称得到相关的疾病列表，用于从证候名称到疾病名称的导航查询。所属的专题由tablePrefix给出。
	 * 
	 * @param zhmc
	 *            疾病证候名称。
	 * @return 返回包含所有符合条件的疾病的ArrayList<String>
	 */
	public ArrayList<Disease> GetJbmcByZhmc(String zhmc) {
		String SqlStr = "select JBMC,count(JBMC) RefNum from "
				+ this.tablePrefix + "_Jb where Jb_Id in (select  JB_ID from "
				+ tablePrefix + "_Jbzhgl where ZH_ID in (select Zh_Id from "
				+ tablePrefix + "_ZH where ZHMC='" + zhmc
				+ "')) group by(JBMC) order by (count(JBMC)) desc";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		ArrayList<Disease> diseaseList = new ArrayList<Disease>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Disease newDisease = new Disease();

			if (ResultArray.get(counter).get("JBMC") != null)
				newDisease.setJBMC(ResultArray.get(counter).get("JBMC")
						.toString());
			else
				newDisease.setJBMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				newDisease.setRefNum(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				newDisease.setRefNum("");

			diseaseList.add(newDisease);
		}

		return diseaseList;
	}

	/**
	 * 根据症状名称得到相关的疾病列表，用于从症状名称到疾病名称的导航查询。所属的专题由tablePrefix给出。
	 * 
	 * @param zzmc
	 *            疾病证候名称。
	 * @return 返回包含所有符合条件的疾病的ArrayList<String>
	 */
	public ArrayList<Disease> GetJbmcByZZMC(String zzmc) {
		String SqlStr = "select JBMC,count(JBMC) RefNum from "
				+ this.tablePrefix + "_Jb where Jb_Id in (select  JB_ID from "
				+ tablePrefix + "_Jbzzgl where ZZ_ID in (select ZZ_Id from "
				+ tablePrefix + "_LCZZ where ZZMC='" + zzmc
				+ "')) group by(JBMC) order by (count(JBMC)) desc";

		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlStr);

		ArrayList<Disease> diseaseList = new ArrayList<Disease>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Disease newDisease = new Disease();

			if (ResultArray.get(counter).get("JBMC") != null)
				newDisease.setJBMC(ResultArray.get(counter).get("JBMC")
						.toString());
			else
				newDisease.setJBMC("");

			if (ResultArray.get(counter).get("REFNUM") != null)
				newDisease.setRefNum(ResultArray.get(counter).get("REFNUM")
						.toString());
			else
				newDisease.setRefNum("");

			diseaseList.add(newDisease);
		}

		return diseaseList;
	}

	/**
	 * 根据tablePrefix给出的专题，得倒该专题下的所有疾病名称。用于列举专题树。 此方法不用查询入口词，应为入口词不需要列举在专题树中。
	 * 
	 * @return ArrayList<Disease>
	 */
	public ArrayList<Disease> GetDiseaseBasicInfo() {

		DataBaseActivity dba = new DataBaseActivity();
		setSqlDiseaseInfo();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sqlDiseaseInfo);
		logger.debug("get disease infomation by sql:" + sqlDiseaseInfo);
		ArrayList<Disease> DiseasesArray = new ArrayList<Disease>();

		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Disease newDisease = new Disease();

			if (ResultArray.get(counter).get("JB_ID") != null)
				newDisease.setJB_ID(ResultArray.get(counter).get("JB_ID")
						.toString());
			else
				newDisease.setJB_ID("");

			if (ResultArray.get(counter).get("JBMC") != null)
				newDisease.setJBMC(ResultArray.get(counter).get("JBMC")
						.toString());
			else
				newDisease.setJBMC("");

			if (ResultArray.get(counter).get("YFB") != null)
				newDisease.setYFB(ResultArray.get(counter).get("YFB")
						.toString());
			else
				newDisease.setYFB("");

			if (ResultArray.get(counter).get("BFZ") != null)
				newDisease.setBFZ(ResultArray.get(counter).get("BFZ")
						.toString());
			else
				newDisease.setBFZ("");

			if (ResultArray.get(counter).get("ZH") != null)
				newDisease.setZH(ResultArray.get(counter).get("ZH").toString());
			else
				newDisease.setZH("");

			if (ResultArray.get(counter).get("ZZ") != null)
				newDisease.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				newDisease.setZZ("");

			DiseasesArray.add(newDisease);
		}

		return DiseasesArray;
	}

	public String getJbmcs() {
		Dss_subjectDAO subjectDAO = new Dss_subjectDAO();

		return subjectDAO.getRelatedJbmc(this.diseaseName);
	}

	/**
	 * 根据tablePrefix给出的专题，得倒该专题下的所有疾病名称。用于列举专题树。 需要查询相关的入口词。
	 * 
	 * @return ArrayList<Disease>
	 */
	public ArrayList<Disease> GetDiseaseBasicInfoWithRkc() {

		String sqlString = "select JBMC,YFB,BFZ,ZH,ZZ from " + this.tablePrefix
				+ "_JB where JBMC in" + getJbmcs()
				+ " group by JBMC,YFB,BFZ,ZH,ZZ order by YFB";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sqlString);
		logger.debug("get disease infomation by sql:" + sqlString);
		ArrayList<Disease> DiseasesArray = new ArrayList<Disease>();

		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Disease newDisease = new Disease();

			if (ResultArray.get(counter).get("JB_ID") != null)
				newDisease.setJB_ID(ResultArray.get(counter).get("JB_ID")
						.toString());
			else
				newDisease.setJB_ID("");

			if (ResultArray.get(counter).get("JBMC") != null)
				newDisease.setJBMC(ResultArray.get(counter).get("JBMC")
						.toString());
			else
				newDisease.setJBMC("");

			if (ResultArray.get(counter).get("YFB") != null)
				newDisease.setYFB(ResultArray.get(counter).get("YFB")
						.toString());
			else
				newDisease.setYFB("");

			if (ResultArray.get(counter).get("BFZ") != null)
				newDisease.setBFZ(ResultArray.get(counter).get("BFZ")
						.toString());
			else
				newDisease.setBFZ("");

			if (ResultArray.get(counter).get("ZH") != null)
				newDisease.setZH(ResultArray.get(counter).get("ZH").toString());
			else
				newDisease.setZH("");

			if (ResultArray.get(counter).get("ZZ") != null)
				newDisease.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				newDisease.setZZ("");

			DiseasesArray.add(newDisease);
		}

		return DiseasesArray;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getSqlDiseaseInfo() {
		return sqlDiseaseInfo;
	}

	public void setSqlDiseaseInfo() {
		this.sqlDiseaseInfo = "select JBMC,YFB,BFZ,ZH,ZZ from "
				+ this.tablePrefix + "_JB where JBMC ='" + this.diseaseName
				+ "' group by JBMC,YFB,BFZ,ZH,ZZ order by YFB";
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
}
