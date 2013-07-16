package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Subject;

/**
 * 专题的相关操作
 * 用于操作专题管理主表Dss_subject的DAO
 * @author zhm
 */
public class Dss_subjectDAO {

	static Logger logger = Logger.getLogger(Dss_subjectDAO.class.getName());

	/**
	 * 用于产生专题相关的20张子表的sql条件
	 */
	private String sqlAddSubject = "";// 添加子表的sql

	/**
	 * 专题编码，如呼吸专题,其编码可以为：hx
	 */
	public String tablePrefix = ""; // 表头

	/**
	 * 产生专题主题词的条件，为一个标准的SQl语句。
	 */
	public String subCondition = ""; 

	/**
	 * 从专题编码得到专题名称
	 * @param tag 专题编码
	 * @return 专题名称
	 */
	public String GetNameByTag(String tag) {
		String sqlString = "select S_NAME from dss_subject where s_tag='" + tag
				+ "' ";
		logger.debug("GetNameByTag:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			if (resultArray.get(0).get("S_NAME") != null)
				return resultArray.get(0).get("S_NAME").toString();
			else
				return "";
		else {
			logger.info("Get no S_NAME  by s_tag=" + tag);
			return "";
		}
	}

	/**
	 * 取得一个专题的所有主题词
	 * 
	 * @param Sqlcondition 查询的SQl语句
	 * @return 包含疾病名称的ArrayList<String>
	 */
	public ArrayList<String> getZhuticilist(String Sqlcondition) {
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(Sqlcondition);

		ArrayList<String> TitlesArray = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			String item = ResultArray.get(counter).get("MHCHI").toString();
			TitlesArray.add(item.trim().replace("\n", "").replace("\r", "").replace(" ", ""));
		}

		return TitlesArray;
	}
	
	/**
	 * 取得一个专题的所有主题词
	 * 
	 * @param Sqlcondition 查询的SQl语句
	 * @return 包含疾病名称的ArrayList<String>
	 */
	public ArrayList<String> getZhuticiFromSubtable(String Sqlcondition) {
		try
		{
			DataBaseActivity dba = new DataBaseActivity();
			ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
			ResultArray = dba.DBSelect(Sqlcondition);
			logger.info("getZhuticiFromSubtable");
			ArrayList<String> TitlesArray = new ArrayList<String>();
			for (int counter = 0; counter < ResultArray.size(); counter++) {
				String item = ResultArray.get(counter).get("JBMC").toString();
				TitlesArray.add(item.trim().replace("\n", "").replace("\r", "").replace(" ", ""));
				//if (TitlesArray.size()>50)
				//	break;
			}
	
			return TitlesArray;
		}
		catch(Exception ex)
		{
			logger.error("getZhuticiFromSubtable:"+ex.getStackTrace());
			return null;
		}
	}

	/**
	 * 根据专题名称，得倒该专题下所有Jbmc,包括主题词，入口词，以及库外主题词 形式为:('jbmc1','jbmc2','jbmc3','jbmc4')
	 * 1. 查询得倒所有该专题的主题词。
	 * 2. 得倒所有的库外主题词。
	 * 3. 根据每个主题词和库外主题词，找得其对应的入口词。
	 * 4. 排除重复的jbmc
	 * @param subjectName 专题名称
	 * @return 形式为类似于('jbmc1','jbmc2','jbmc3','jbmc4')
	 */
	public String getAllJbmc(String subjectName) {
		String jbmcAll = "";
		String condition = GetConditionBySubjectName(subjectName);
		// 得倒所有的主题词
		ArrayList<String> zhuticiList = getZhuticilist(condition);
		// 得倒所有的库外主题词
		String addedTitlesStr = getAddedTitlesBySubjectName(subjectName);
		String atitles[] = addedTitlesStr.split("\\|");
		for (int i = 0; i < atitles.length; i++)
			if (atitles[i].toString().trim().equals(""))
				zhuticiList.add(atitles[i].toString());
		
		if(zhuticiList.size()>1000)			
			logger.warn("主题词的数量大于1000");
		
		// 产生包含主题词，库外主题词的jbmcAll
		jbmcAll = "(";
		for (int i = 0; i < zhuticiList.size(); i++){
			if(i>=1000) break;
			jbmcAll += "'" + zhuticiList.get(i).replace("'","") + "',";
		}
			
		jbmcAll = jbmcAll.substring(0, jbmcAll.length() - 1);
		jbmcAll += ")";
		// 得倒入口词
		ArrayList<String> rkcList = getRukouciList(jbmcAll);
		// 产生包含主题词，库外主题词，入口词的jbmcAll
		jbmcAll = jbmcAll.substring(0, jbmcAll.length() - 1) + ",";
		
				
		for (int i = 0; i <rkcList.size(); i++)
		{
			String itemString=rkcList.get(i).replace("'","");
			if(!zhuticiList.contains(itemString)){
				zhuticiList.add(itemString);
				if(zhuticiList.size()>=1000)
				{
					logger.warn("主题词总数据量大于1000!!!");
					break;
				}
				jbmcAll += "'" +itemString + "',";
			}
		}
		jbmcAll = jbmcAll.substring(0, jbmcAll.length() - 1);
		jbmcAll += ")";
 	
		return jbmcAll;
	}
	

	/**
	 * 根据主题词查找入口词,返回一个String,包括主题词和所有相关的入口词 形式为:('jbmc1','jbmc2','jbmc3','jbmc4')
	 * @param ztc 主题词
	 * @return 形式为:('jbmc1','jbmc2','jbmc3','jbmc4')
	 */
	public String getRelatedJbmc(String ztc) {
		String jbmcAll = "";	
		jbmcAll = "('"+ztc.replace("'","")+"')";
		// 得倒入口词
		ArrayList<String> rkcList = getRukouciList(jbmcAll);
		// 产生包含主题词，库外主题词，入口词的jbmcAll
		jbmcAll = jbmcAll.substring(0, jbmcAll.length() - 1) + ",";
		for (int i = 0; i < rkcList.size(); i++)
			jbmcAll += "'" + rkcList.get(i).replace("'","") + "',";
		jbmcAll = jbmcAll.substring(0, jbmcAll.length() - 1);
		jbmcAll += ")";

		return jbmcAll;
	}

	/**
	 * 根据专题名称得倒用户添加的主题词
	 * 
	 * @param subjectName 专题名称
	 * @return 用户添加的主题词，所有的主题词之间用“|”分割。
	 */
	public String getAddedTitlesBySubjectName(String subjectName) {
		String sqlString = "select ADDEDTITLES from dss_subject where S_NAME='"
				+ subjectName + "' ";
		logger.debug("get AddedTitles By SubjectName:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			if (resultArray.get(0).get("ADDEDTITLES") != null)
				return resultArray.get(0).get("ADDEDTITLES").toString();
			else
				return "";
		else {
			return "";
		}
	}

	/**
	 * 根据主题词查询入口词
	 * 
	 * @param zhutici
	 * @return
	 */
	public ArrayList<String> getRukouciList(String zhutici) {
		String sqlString = "select  distinct(RKC) from ZTC_RKC  where ZTC_RKC.ZTC in "
				+ zhutici;
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sqlString);

		ArrayList<String> rkcArray = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			String item = ResultArray.get(counter).get("RKC").toString();
			rkcArray.add(item);
		}
		return rkcArray;

	}

	/**
	 * 取得所有的专题名称
	 * 
	 * @return 专题名称的ArrayList<String>
	 */
	public ArrayList<String> GetAllSubject() {
		String sqlString = "select S_NAME from dss_subject order by S_NAME";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);
		ArrayList<String> subjectList = new ArrayList<String>();
		for (int i = 0; i < resultArray.size(); i++) {
			subjectList.add(resultArray.get(i).get("S_NAME").toString());
		}
		return subjectList;
	}

	/**
	 * 取得所有专题的详细信息
	 * 
	 * @return 包含的专题对象的ArrayList<Subject>，按周S_NAME排序
	 */
	public ArrayList<Subject> GetAllSubjectDetail() {
		String sqlString = "select * from dss_subject order by S_NAME";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		for (int i = 0; i < resultArray.size(); i++) {
			Subject newsubject = new Subject();
			if (resultArray.get(i).get("S_NAME") != null)
				newsubject.setS_NAME(resultArray.get(i).get("S_NAME")
						.toString());
			else
				newsubject.setS_NAME("");

			if (resultArray.get(i).get("S_TAG") != null)
				newsubject.setS_TAG(resultArray.get(i).get("S_TAG").toString());
			else
				newsubject.setS_TAG("");

			if (resultArray.get(i).get("PICNAME") != null)
				newsubject.setPicName(resultArray.get(i).get("PICNAME")
						.toString());
			else
				newsubject.setPicName("");

			if (resultArray.get(i).get("CONDITION") != null)
				newsubject.setCondition(resultArray.get(i).get("CONDITION")
						.toString());
			else
				newsubject.setCondition("");

			if (resultArray.get(i).get("ADDEDTITLES") != null)
				newsubject.setAddedtitles(resultArray.get(i).get("ADDEDTITLES")
						.toString());
			else
				newsubject.setAddedtitles("");

			subjectList.add(newsubject);
		}
		return subjectList;
	}
	
	/**
	 * 根据专题编码取得一个专题的详细信息
	 * 
	 * @return 包含的专题对象的ArrayList<Subject>，按周S_NAME排序
	 */
	public Subject GetSubjectByTag(String subTag) {
		String sqlString = "select * from dss_subject where S_TAG='"+subTag.toUpperCase()+"'";
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);
		Subject newsubject = new Subject();
		for (int i = 0; i < resultArray.size(); i++) {
			
			if (resultArray.get(i).get("S_NAME") != null)
				newsubject.setS_NAME(resultArray.get(i).get("S_NAME")
						.toString());
			else
				newsubject.setS_NAME("");

			if (resultArray.get(i).get("S_TAG") != null)
				newsubject.setS_TAG(resultArray.get(i).get("S_TAG").toString());
			else
				newsubject.setS_TAG("");

			if (resultArray.get(i).get("PICNAME") != null)
				newsubject.setPicName(resultArray.get(i).get("PICNAME")
						.toString());
			else
				newsubject.setPicName("");

			if (resultArray.get(i).get("CONDITION") != null)
				newsubject.setCondition(resultArray.get(i).get("CONDITION")
						.toString());
			else
				newsubject.setCondition("");

			if (resultArray.get(i).get("ADDEDTITLES") != null)
				newsubject.setAddedtitles(resultArray.get(i).get("ADDEDTITLES")
						.toString());
			else
				newsubject.setAddedtitles("");
			
			if (resultArray.get(i).get("NOTES") != null)
				newsubject.setNotes(resultArray.get(i).get("NOTES")
						.toString());
			else
				newsubject.setNotes("");
			
		}
		return newsubject;
	}

	/**
	 * 根据主题名称得到专题编码
	 * 
	 * @param subjectName 专题名称
	 * @return 专题编码
	 */
	public String GetTagByName(String subjectName) {
		String sqlString = "select s_tag from dss_subject where S_NAME='"
				+ subjectName + "' ";
		logger.debug("GetTagByName:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			if (resultArray.get(0).get("S_TAG") != null)
				return resultArray.get(0).get("S_TAG").toString();
			else
				return null;
		else {
			logger.info("Get no s_tag  by S_NAME=" + subjectName);
			return "";
		}
	}

	/**
	 * 根据主题名称得到专题的主题词产生条件SQl语句
	 * 
	 * @param subjectName 专题名称
	 * @return String：主题词产生条件SQl语句
	 */
	public String GetConditionBySubjectName(String subjectName) {
		String sqlString = "select condition from dss_subject where S_NAME='"
				+ subjectName + "' ";
		logger.debug("Get Condition By SubjectName:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			if (resultArray.get(0).get("CONDITION") != null)
				return resultArray.get(0).get("CONDITION").toString();
			else
				return "";
		else {
			logger.info("Get no CONDITION  by S_NAME=" + subjectName);
			return "";
		}
	}

	/**
	 * 根据主题名称得到专题的介绍图片名称picname
	 * 
	 * @param subjectName 专题名称
	 * @return String 介绍图片名称picname
	 */
	public String GetPicnameBySubjectName(String subjectName) {
		String sqlString = "select PICNAME from dss_subject where S_NAME='"
				+ subjectName + "' ";
		logger.debug("Get PICNAME By SubjectName:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			if (resultArray.get(0).get("PICNAME") != null)
				return resultArray.get(0).get("PICNAME").toString();
			else
				return "";
		else {
			logger.info("Get No PICNAME  by S_NAME=" + subjectName);
			return "";
		}
	}
	
	/**
	 * 根据主题名称得到专题的介绍的头文件图片名称HEADERPIC
	 * 
	 * @param subjectName 专题名称
	 * @return String 介绍图片名称HEADERPIC
	 */
	public String GetHeaderPicBySubjectName(String subjectName) {
		String sqlString = "select HEADERPIC from dss_subject where S_NAME='"
				+ subjectName + "' ";
		logger.debug("Get HEADERPIC By SubjectName:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			if (resultArray.get(0).get("HEADERPIC") != null)
				return resultArray.get(0).get("HEADERPIC").toString();
			else
				return "";
		else {
			logger.info("Get No HEADERPIC  by S_NAME=" + subjectName);
			return "";
		}
	}

	public boolean IsSubjectNameExist(String SubjectName) {
		String sqlString = "select S_NAME from dss_subject where S_NAME='"
				+ SubjectName + "' ";
		logger.debug("whether SubjectName exist:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			return true;
		else
			return false;
	}

	public boolean IsSubjectTagExist(String SubjectTag) {
		String sqlString = "select s_tag from dss_subject where s_tag='"
				+ SubjectTag + "' ";
		logger.debug("whether SubjectName exist:" + sqlString);
		DataBaseActivity dba = new DataBaseActivity();
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		resultArray = dba.DBSelect(sqlString);

		if (resultArray.size() > 0)
			return true;
		else
			return false;
	}

	
	/**插入一个新的专题
	 * @param subjectName 专题名称
	 * @param subjectTag 专题编码
	 * @param picName  专题介绍图片
	 * @param subjectCondition 主题词产生条件
	 * @param subjectNotes 专题备注。
	 * @param subjectAddedZTC  主题词
	 * @return
	 */
	public int InsertNewSubject(String subjectName, String subjectTag,
			String picName, String subjectCondition, String subjectNotes,String subjectAddedZTC) {
		try {
			int errorFlag = -1;
			// 替换字符
			subjectCondition = subjectCondition.replace("'", "''");
			setSqlAddSubject(subjectName, subjectTag, picName,
					subjectCondition, subjectNotes,subjectAddedZTC);
			logger.debug("add subject Sql string:" + sqlAddSubject);

			// 替换字符
			subjectCondition = subjectCondition.replace("''", "'");

			setTablePrefix(subjectTag);
			setSubCondition(subjectCondition);

			DataBaseActivity dba = new DataBaseActivity();
			// 保存专题的相关条件
			errorFlag = dba.DBExecuteUpdate(sqlAddSubject);
			if (errorFlag < 0)
				return errorFlag;
			// 产生所需要的相关子表
			SubjectTablesDAO subjectTablesDAO = new SubjectTablesDAO();
			subjectTablesDAO.setSubCondition(this.subCondition);
			subjectTablesDAO.setTablePrefix(this.tablePrefix);		
			errorFlag = subjectTablesDAO.CreateTables();
			return errorFlag;
		} catch (Exception ex) {
			logger.info(ex.getMessage()+"\r\n"+ex.toString());
			return -1;
		}

	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getSqlAddSubject() {
		return sqlAddSubject;
	}

	public void setSqlAddSubject(String subjectName, String subjectTag,
			String picName, String subjectCondition, String subjectNotes,String subjectAddedZTC) {
		this.sqlAddSubject = "insert into DSS_SUBJECT(S_ID,S_NAME,S_TAG,CONDITION,ADDEDTITLES,NOTES,PICNAME,HEADERPIC) values(DSS_SUBJECT_SEQ.NEXTVAL,'"
				+ subjectName
				+ "','"
				+ subjectTag
				+ "','"
				+ subjectCondition
				+ "','" 
				+ subjectAddedZTC
				+ "','" 
				+ subjectNotes 
				+ "','" 
				+ picName 
				+ "','')";
	}

	public String getSubCondition() {
		return subCondition;
	}

	public void setSubCondition(String subCondition) {
		this.subCondition = subCondition;
	}

	public void setSqlAddSubject(String sqlAddSubject) {
		this.sqlAddSubject = sqlAddSubject;
	}

	/**
	 * 根据装题删除专题
	 * 
	 * @param tag 专题编码
	 */
	public void deleteSubjectByTag(String tag) {
		String sqlDeleteRec = "delete  from dss_subject where S_TAG= '" + tag
				+ "'";
		DataBaseActivity dba = new DataBaseActivity();
		dba.DBExecuteUpdate(sqlDeleteRec);
	}

	/**
	 * 删除一个专题
	 * 
	 * @param subjectName 专题名称
	 */
	public void deleteSubjectByName(String subjectName) {
		String sqlDeleteRec = "delete  from dss_subject where S_NAME= '"
				+ subjectName + "'";
		DataBaseActivity dba = new DataBaseActivity();
		dba.DBExecuteUpdate(sqlDeleteRec);
	}

	/**
	 * 添加主题词
	 * 
	 * @param subjectName 专题名称 
	 * @param zhutici 专题词，可以是一个，如果是多个主题词，主题词之间需要用“|”分割。
	 */
	public int addZhuTiCi(String subjectName, String zhutici) {
		String sqlDeleteRec = "update  dss_subject set addedtitles=concat(addedtitles,concat('|','"
				+ zhutici + "')) where S_name='" + subjectName + "'";
		DataBaseActivity dba = new DataBaseActivity();
		return dba.DBExecuteUpdate(sqlDeleteRec);

	}
	
	/**
	 * 将用户上传的图片保存为专题介绍图片
	 * @param SubjectName 专题名称
	 * @param IntroPicName 专题介绍图片
	 * @return
	 */
	public int updateSubjectIntro(String SubjectName,String headerPic,String introPic){
		String sqlDeleteRec = "update  dss_subject set picname='"+introPic
			+ "',HEADERPIC='"+headerPic+"' where S_name='" + SubjectName + "'";
	DataBaseActivity dba = new DataBaseActivity();
	return dba.DBExecuteUpdate(sqlDeleteRec);
	}
	
	

}
