package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;

/**
 * 用于一个专题所需要的子表的创建和删除操作
 * 同一个专题的子表名称都有相同的前缀，即 专题编码+"_"
 * @author zhm
 * 
 */
public class SubjectTablesDAO {
	static Logger logger = Logger.getLogger(SubjectTablesDAO.class.getName());

	/**
	 * 专题编码
	 */
	public String tablePrefix = ""; 
	

	/**
	 * 产生子表的主题词查询条件
	 */
	public String subCondition = ""; 
	public String sqlJB = "";

	public String sqlCxlcyjdx = "";

	public String sqlLCYJ = "";

	public String sqllCZL = "";

	public String sqlDZZ = "";

	public String sqlSYJC = "";

	public String sqlJTZLGC = "";

	public String sqlZLGCDXGL = "";

	public String sqlJBZHGL = "";

	public String sqlJBZZGL = "";

	public String sqlLCZZ = "";

	public String sqlZH = "";

	public String sqlZHZZGL = "";

	public String sqlZJLF = "";

	public String sqlAMLF = "";

	public String sqlYFLF = "";

	public String sqlXYLF = "";

	public String sqlQTLF = "";

	public String sqlXW = "";

	public String sqlYWCF = "";

	public String sqlYW = "";

	/**
	 * 创建专题相关子表
	 */
	public int CreateTables() {
		SetSqls();
		return ExeSqls();
	}

	/**
	 * 删除专题的子表
	 * @return
	 */
	public int DropTablesOFSubject() {
		ArrayList<String> tableList = new ArrayList<String>();
		tableList.add(this.tablePrefix + "_JB");
		tableList.add(this.tablePrefix + "_Cxlcyjdx");
		tableList.add(this.tablePrefix + "_LCYJ");
		tableList.add(this.tablePrefix + "_lCZL");
		tableList.add(this.tablePrefix + "_DZZ");
		tableList.add(this.tablePrefix + "_SYJC");
		tableList.add(this.tablePrefix + "_JTZLGC");
		tableList.add(this.tablePrefix + "_ZLGCDXGL");
		tableList.add(this.tablePrefix + "_JBZHGL");
		tableList.add(this.tablePrefix + "_JBZZGL");
		tableList.add(this.tablePrefix + "_LCZZ");
		tableList.add(this.tablePrefix + "_ZH");
		tableList.add(this.tablePrefix + "_ZHZZGL");
		tableList.add(this.tablePrefix + "_ZJLF");
		tableList.add(this.tablePrefix + "_AMLF");
		tableList.add(this.tablePrefix + "_YFLF");
		tableList.add(this.tablePrefix + "_XYLF");
		tableList.add(this.tablePrefix + "_QTLF");
		tableList.add(this.tablePrefix + "_XW");
		tableList.add(this.tablePrefix + "_YWCF");
		tableList.add(this.tablePrefix + "_YW");

		return DropTable(tableList);
	}

	/**
	 * 设置需要创建的专题子表的SQl语句
	 */
	public void SetSqls() {
		setSqlJB();
		setSqlCxlcyjdx();
		setSqlLCYJ();
		setSqllCZL();
		setSqlDZZ();
		setSqlSYJC();
		setSqlJTZLGC();
		setSqlZLGCDXGL();

		setSqlJBZHGL();
		setSqlJBZZGL();
		setSqlLCZZ();
		setSqlZH();
		setSqlZHZZGL();
		setSqlZJLF();
		setSqlAMLF();
		setSqlYFLF();

		setSqlXYLF();
		setSqlQTLF();
		setSqlXW();
		setSqlYWCF();
		setSqlYW();

	}

	/**执行创建专题子表的操作。
	 * 如果成功，则在tableList中当前已经创建的子表的名称；
	 * 如果失败，则调用DropTable(tableList)，回滚操作。
	 * @return
	 */
	public int ExeSqls() {
		ArrayList<String> tableList = new ArrayList<String>();
		try {
			int errorFlag = -1;
			DataBaseActivity dba = new DataBaseActivity();
			errorFlag = dba.DBExecuteUpdate(this.getSqlJB());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_JB");

			errorFlag = dba.DBExecuteUpdate(this.getSqlCxlcyjdx());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_Cxlcyjdx");

			errorFlag = dba.DBExecuteUpdate(this.getSqlLCYJ());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_LCYJ");

			errorFlag = dba.DBExecuteUpdate(this.getSqllCZL());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_lCZL");

			errorFlag = dba.DBExecuteUpdate(this.getSqlDZZ());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_DZZ");

			errorFlag = dba.DBExecuteUpdate(this.getSqlSYJC());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_SYJC");

			errorFlag = dba.DBExecuteUpdate(this.getSqlJTZLGC());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_JTZLGC");

			errorFlag = dba.DBExecuteUpdate(this.getSqlZLGCDXGL());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_ZLGCDXGL");

			errorFlag = dba.DBExecuteUpdate(this.getSqlJBZHGL());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_JBZHGL");

			errorFlag = dba.DBExecuteUpdate(this.getSqlJBZZGL());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_JBZZGL");

			errorFlag = dba.DBExecuteUpdate(this.getSqlLCZZ());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_LCZZ");

			errorFlag = dba.DBExecuteUpdate(this.getSqlZH());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_ZH");

			errorFlag = dba.DBExecuteUpdate(this.getSqlZHZZGL());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_ZHZZGL");

			errorFlag = dba.DBExecuteUpdate(this.getSqlZJLF());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_ZJLF");

			errorFlag = dba.DBExecuteUpdate(this.getSqlAMLF());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_AMLF");

			errorFlag = dba.DBExecuteUpdate(this.getSqlYFLF());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_YFLF");

			errorFlag = dba.DBExecuteUpdate(this.getSqlXYLF());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_XYLF");

			errorFlag = dba.DBExecuteUpdate(this.getSqlQTLF());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_QTLF");

			errorFlag = dba.DBExecuteUpdate(this.getSqlXW());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_XW");
			errorFlag = dba.DBExecuteUpdate(this.getSqlYWCF());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_YWCF");
			errorFlag = dba.DBExecuteUpdate(this.getSqlYW());
			if (errorFlag < 0)
				return DropTable(tableList);
			else
				tableList.add(this.tablePrefix + "_YW");

			return errorFlag;
		} catch (Exception ex) {
			logger.info(ex.getMessage()+"\r\n"+ex.toString());
			return -1;
		}
	}

	/**
	 * 回滚，当创建专题子表失败时，删除删除已经创建的部分专题数据表。
	 * 
	 * @param tableList ArrayList<String> 需要删除的数据表
	 * @return
	 */
	public int DropTable(ArrayList<String> tableList) {
		for (int i = 0; i < tableList.size(); i++) {
			try {
				String sqlDropTable = "drop table " + tableList.get(i);
				DataBaseActivity dba = new DataBaseActivity();
				dba.DBExecuteUpdate(sqlDropTable);
			} catch (Exception ex) {
				logger.info(ex.getMessage()+"\r\n"+ex.toString());
				return 0;
			}
		}
		return 1;
	}

	public String getSubCondition() {
		return subCondition;
	}

	public void setSubCondition(String subCondition) {
		this.subCondition = subCondition;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getSqlAMLF() {
		return sqlAMLF;
	}

	public void setSqlAMLF() {
		this.sqlAMLF = "create table " + this.tablePrefix + "_AMLF as  "
				+ "select * from Jmz_Amlf where Jmz_Amlf.Zlgc_Id  in "
				+ "(select Zlgc_Id from " + this.tablePrefix + "_Jtzlgc)";
	}

	public String getSqlCxlcyjdx() {
		return sqlCxlcyjdx;
	}

	public void setSqlCxlcyjdx() {
		this.sqlCxlcyjdx = "create table " + this.tablePrefix
				+ "_Cxlcyjdx  as  " + "select * from Jmz_Cxlcyjdx "
				+ "where Jmz_Cxlcyjdx.Cxdx_Id  in" + "(select JB_ID from "
				+ this.tablePrefix + "_JB)";
	}

	public String getSqlDZZ() {
		return sqlDZZ;
	}

	public void setSqlDZZ() {
		this.sqlDZZ = " create table " + this.tablePrefix + "_DZZ  as  "
				+ "select * from Jmz_DZZ " + "where Jmz_DZZ.lcyj_Id  in "
				+ "(select Lcyj_Id from " + this.tablePrefix + "_LCYJ)";
	}

	public String getSqlJB() {
		return sqlJB;
	}

	
	public void setSqlJB() {
		
		Dss_subjectDAO subjectDAO=new Dss_subjectDAO();
		String subjectName=subjectDAO.GetNameByTag(this.tablePrefix);
		String jbmcStr= subjectDAO.getAllJbmc(subjectName);
		this.sqlJB = "create table " + this.tablePrefix + "_JB  as "
		+ " select  * from JMZ_JB where JMZ_JB.Jbmc in "+jbmcStr;

	}

	public String getSqlJBZHGL() {
		return sqlJBZHGL;
	}

	public void setSqlJBZHGL() {
		this.sqlJBZHGL = "create table " + this.tablePrefix + "_JBZHGL as  "
				+ "select * from Jmz_JBZHGL where Jmz_JBZHGL.JB_Id  in "
				+ "(select JB_Id from " + this.tablePrefix + "_JB)";
	}

	public String getSqlJBZZGL() {
		return sqlJBZZGL;
	}

	public void setSqlJBZZGL() {
		this.sqlJBZZGL = "create table " + this.tablePrefix + "_JBZZGL as  "
				+ "select * from Jmz_JBZZGL where Jmz_JBZZGL.JB_Id  in "
				+ "(select JB_Id from " + this.tablePrefix + "_JB)";
	}

	public String getSqlJTZLGC() {
		return sqlJTZLGC;
	}

	public void setSqlJTZLGC() {
		this.sqlJTZLGC = " create table " + this.tablePrefix + "_JTZLGC as  "
				+ "select * from Jmz_JTZLGC where Jmz_JTZLGC.lcyj_Id  in "
				+ "(select Lcyj_Id from " + this.tablePrefix + "_LCYJ)";
	}

	public String getSqlLCYJ() {
		return sqlLCYJ;
	}

	public void setSqlLCYJ() {
		this.sqlLCYJ = "create table " + this.tablePrefix + "_LCYJ  as  "
				+ "select * from Jmz_Lcyj " + "where Jmz_Lcyj.Lcyj_Id  in "
				+ "(select Lcyj_Id from " + this.tablePrefix + "_cxlcyjdx)";
	}

	public String getSqlZH() {
		return sqlZH;
	}

	public void setSqlZH() {
		this.sqlZH = "create table " + this.tablePrefix + "_ZH as  "
				+ "select * from Jmz_ZH where Jmz_Zh.ZH_ID  in "
				+ "(select ZH_ID from " + this.tablePrefix + "_JBZHGL)";
	}

	public String getSqllCZL() {
		return sqllCZL;
	}

	public void setSqllCZL() {
		this.sqllCZL = "create table " + this.tablePrefix + "_lCZL  as  "
				+ "select * from Jmz_Lczl " + "where Jmz_Lczl.lcyj_Id  in 	"
				+ "(select Lcyj_Id from " + this.tablePrefix + "_LCYJ)";
	}

	public String getSqlLCZZ() {
		return sqlLCZZ;
	}

	public void setSqlLCZZ() {
		this.sqlLCZZ = "create table " + this.tablePrefix + "_LCZZ as "
				+ " select * from Jmz_LCZZ where Jmz_Lczz.Zz_Id  in "
				+ "(select ZZ_ID from " + this.tablePrefix + "_JBZZGL)";
	}

	public String getSqlQTLF() {
		return sqlQTLF;
	}

	public void setSqlQTLF() {
		this.sqlQTLF = "create table " + this.tablePrefix + "_QTLF as  "
				+ "select * from Jmz_QTLF where Jmz_QTLF.Zlgc_Id  in "
				+ "(select Zlgc_Id from " + this.tablePrefix + "_Jtzlgc)";
	}

	public String getSqlSYJC() {
		return sqlSYJC;
	}

	public void setSqlSYJC() {
		this.sqlSYJC = "create table " + this.tablePrefix + "_SYJC  as  "
				+ "select * from Jmz_SYJC " + "where Jmz_SYJC.lcyj_Id  in "
				+ "(select Lcyj_Id from " + this.tablePrefix + "_LCYJ)";
	}

	public String getSqlXW() {
		return sqlXW;
	}

	public void setSqlXW() {
		this.sqlXW = "create table " + this.tablePrefix + "_XW as  "
				+ "select * from Jmz_XW ";
	}

	public String getSqlXYLF() {
		return sqlXYLF;
	}

	public void setSqlXYLF() {
		this.sqlXYLF = "create table " + this.tablePrefix + "_XYLF as  "
				+ "select * from Jmz_XYLF where Jmz_XYLF.Zlgc_Id  in "
				+ "(select Zlgc_Id from " + this.tablePrefix + "_Jtzlgc)";
	}

	public String getSqlYFLF() {
		return sqlYFLF;
	}

	public void setSqlYFLF() {
		this.sqlYFLF = "create table " + this.tablePrefix + "_YFLF as  "
				+ "select * from Jmz_YFLF where Jmz_YFLF.Zlgc_Id  in "
				+ "(select Zlgc_Id from " + this.tablePrefix + "_Jtzlgc)";
	}

	public String getSqlYW() {
		return sqlYW;
	}

	public void setSqlYW() {
		this.sqlYW = "create table " + this.tablePrefix + "_YW as  "
				+ "select * from Jmz_YW where Jmz_YW.Zlgc_Id  in "
				+ "(select Zlgc_Id from " + this.tablePrefix + "_YFLF)";
	}

	public String getSqlYWCF() {
		return sqlYWCF;
	}

	public void setSqlYWCF() {
		this.sqlYWCF = "create table " + this.tablePrefix + "_YWCF as  "
				+ "select * from Jmz_YWCF where Jmz_YWCF.Zlgc_Id  in "
				+ "(select Zlgc_Id from " + this.tablePrefix + "_Jtzlgc)";
	}

	public String getSqlZHZZGL() {
		return sqlZHZZGL;
	}

	public void setSqlZHZZGL() {
		this.sqlZHZZGL = "create table " + this.tablePrefix + "_ZHZZGL as  "
				+ "select * from Jmz_ZHZZGL where Jmz_ZHZZGL.ZH_Id  in"
				+ "(select ZH_ID from " + this.tablePrefix + "_ZH)";
	}

	public String getSqlZJLF() {
		return sqlZJLF;
	}

	public void setSqlZJLF() {
		this.sqlZJLF = "create table " + this.tablePrefix + "_ZJLF as  "
				+ "select * from Jmz_Zjlf where Jmz_Zjlf.Zlgc_Id  in "
				+ "(select Zlgc_Id from " + this.tablePrefix + "_Jtzlgc)";
	}

	public String getSqlZLGCDXGL() {
		return sqlZLGCDXGL;
	}

	public void setSqlZLGCDXGL() {
		this.sqlZLGCDXGL = "create table " + this.tablePrefix
				+ "_ZLGCDXGL as  "
				+ "select * from Jmz_ZLGCDXGL where Jmz_ZLGCDXGL.Cxdx_Id  in "
				+ "(select Cxdx_Id from " + this.tablePrefix + "_Cxlcyjdx)";
	}
}
