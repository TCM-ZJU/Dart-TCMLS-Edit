package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.object.Lczl;

/**临床治疗DAO
 * @author zhm
 * 
 */
public class LczlDAO {
	static Logger logger = Logger.getLogger(LczlDAO.class.getName());

	/**
	 * 疾病名称
	 */
	public String JBMC = "";

	/**
	 * 表的前缀，用专题的编码表示
	 */
	public String tablePrefix = "";

	private String SqlGetLczlByJBMC = "";
	private String sqlGetLxbzByJBMC="";

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
	 * 通过疾病名称得倒临床治疗信息
	 * @return
	 */
	public ArrayList<Lczl> GetLczlInfoByJbmc() {
		setSqlGetLczlByJBMC();
		logger.debug("Get 临床治疗 By JBMC with sql:" + SqlGetLczlByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetLczlByJBMC);

		ArrayList<Lczl> lczlArray = new ArrayList<Lczl>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Lczl lczl = new Lczl();

			if (ResultArray.get(counter).get("LCZL_ID") != null)
				lczl.setLCZL_ID(ResultArray.get(counter).get("LCZL_ID")
						.toString());
			else
				lczl.setLCZL_ID("");

			if (ResultArray.get(counter).get("LCYJ_ID") != null)
				lczl.setLCYJ_ID(ResultArray.get(counter).get("LCYJ_ID")
						.toString());
			else
				lczl.setLCYJ_ID("");

			if (ResultArray.get(counter).get("BZZMC") != null)
				lczl.setBZZMC(ResultArray.get(counter).get("BZZMC").toString());
			else
				lczl.setBZZMC("");

			if (ResultArray.get(counter).get("ZDZBFL") != null)
				lczl.setZDZBFL(ResultArray.get(counter).get("ZDZBFL")
						.toString());
			else
				lczl.setZDZBFL("");

			if (ResultArray.get(counter).get("ZBQZ") != null)
				lczl.setZBQZ(ResultArray.get(counter).get("ZBQZ").toString());
			else
				lczl.setZBQZ("");

			if (ResultArray.get(counter).get("ZBXM") != null)
				lczl.setZBXM(ResultArray.get(counter).get("ZBXM").toString());
			else
				lczl.setZBXM("");

			if (ResultArray.get(counter).get("LXBZLY") != null)
				lczl.setLXBZLY(ResultArray.get(counter).get("LXBZLY")
						.toString());
			else
				lczl.setLXBZLY("");

			if (ResultArray.get(counter).get("LXBZ") != null)
				lczl.setLXBZ(ResultArray.get(counter).get("LXBZ").toString());
			else
				lczl.setLXBZ("");

			if (ResultArray.get(counter).get("ZDBZLY") != null)
				lczl.setZDBZLY(ResultArray.get(counter).get("ZDBZLY")
						.toString());
			else
				lczl.setZDBZLY("");

			if (ResultArray.get(counter).get("ZDYQ") != null)
				lczl.setZDYQ(ResultArray.get(counter).get("ZDYQ").toString());
			else
				lczl.setZDYQ("");
			if (ResultArray.get(counter).get("ZYZDFF") != null)
				lczl.setZYZDFF(ResultArray.get(counter).get("ZYZDFF")
						.toString());
			else
				lczl.setZYZDFF("");
			if (ResultArray.get(counter).get("ZYXL") != null)
				lczl.setZYXL(ResultArray.get(counter).get("ZYXL").toString());
			else
				lczl.setZYXL("");

			lczlArray.add(lczl);
		}

		return lczlArray;
	}
	
	public ArrayList<Lczl> GetLczlByJbmc() {
		setSqlGetLczlByJBMC();
		logger.debug("Get 临床治疗 By Jb.JBMC with sql:" + SqlGetLczlByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(SqlGetLczlByJBMC);

		ArrayList<Lczl> lczlArray = new ArrayList<Lczl>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Lczl lczl = new Lczl();
			if (ResultArray.get(counter).get("BZZMC") != null)
				lczl.setBZZMC(ResultArray.get(counter).get("BZZMC").toString());
			else
				lczl.setBZZMC("");
			if (ResultArray.get(counter).get("ZYZDFF") != null)
				lczl.setZYZDFF(ResultArray.get(counter).get("ZYZDFF")
						.toString());
			else
				lczl.setZYZDFF("");
			if (ResultArray.get(counter).get("ZDYQ") != null)
				lczl.setZDYQ(ResultArray.get(counter).get("ZDYQ").toString());
			else
				lczl.setZDYQ("");
			if (ResultArray.get(counter).get("ZYXL") != null)
				lczl.setZYXL(ResultArray.get(counter).get("ZYXL").toString());
			else
				lczl.setZYXL("");
			if (ResultArray.get(counter).get("LCYJMC") != null)
				lczl.setLCYJMC(ResultArray.get(counter).get("LCYJMC")
						.toString());
			else
				lczl.setLCYJMC("");
			
			lczlArray.add(lczl);
		}

		return lczlArray;
	}
	
	
	/**
	 * 根据JBMC名称取疗效标准信息
	 * @return 
	 */
	public ArrayList<Lczl> GetLxbzByJbmc() {
		setSqlGetLxbzByJBMC();
		logger.debug("Get 疗效标准 By JBMC with sql:" + sqlGetLxbzByJBMC);
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sqlGetLxbzByJBMC);

		ArrayList<Lczl> lczlArray = new ArrayList<Lczl>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {

			Lczl lczl = new Lczl();
			if (ResultArray.get(counter).get("BZZMC") != null)
				lczl.setBZZMC(ResultArray.get(counter).get("BZZMC").toString());
			else
				lczl.setBZZMC("");			
			if (ResultArray.get(counter).get("ZBXM") != null)
				lczl.setZBXM(ResultArray.get(counter).get("ZBXM").toString());
			else
				lczl.setZBXM("");			
		
			if (ResultArray.get(counter).get("ZYXL") != null)
				lczl.setZYXL(ResultArray.get(counter).get("ZYXL").toString());
			else
				lczl.setZYXL("");
			if (ResultArray.get(counter).get("LXBZ") != null)
				lczl.setLXBZ(ResultArray.get(counter).get("LXBZ")
						.toString());
			else
				lczl.setLXBZ("");
			if (ResultArray.get(counter).get("LXBZLY") != null)
				lczl.setLXBZLY(ResultArray.get(counter).get("LXBZLY")
						.toString());
			else
				lczl.setLXBZLY("");

			if (ResultArray.get(counter).get("ZDZBFL") != null)
				lczl.setZDZBFL(ResultArray.get(counter).get("ZDZBFL")
						.toString());
			else
				lczl.setZDZBFL("");
			if (ResultArray.get(counter).get("ZDBZLY") != null)
				lczl.setZDBZLY(ResultArray.get(counter).get("ZDBZLY")
						.toString());
			else
				lczl.setZDBZLY("");		

			lczlArray.add(lczl);
		}

		return lczlArray;
	}

	public String getSqlGetLczlByJBMC() {
		return SqlGetLczlByJBMC;
	}

	public void setSqlGetLczlByJBMC() {
		SqlGetLczlByJBMC = "select BZZMC,ZYZDFF,ZDYQ,ZYXL,LCYJMC  " +
				"from "+this.tablePrefix+"_LCZL,"+this.tablePrefix+"_LCYJ where BZZMC='"+this.JBMC+"' " +
				"and "+tablePrefix+"_Lcyj.Lcyj_Id="+tablePrefix+"_LCZL.LCYJ_ID and ZYZDFF<>' ' and ZDYQ<>' '";
		 }

	public String getSqlGetLxbzByJBMC() {
		return sqlGetLxbzByJBMC;
	}

	public void setSqlGetLxbzByJBMC() {
		this.sqlGetLxbzByJBMC ="select distinct(LXBZLY),LXBZ,ZYXL,BZZMC,ZBXM,ZDZBFL,ZDBZLY " +
				"from "+this.tablePrefix
				+"_LCZL where BZZMC='"+this.JBMC+"' " +
				"and ZBXM<>' ' and ZDZBFL<>' ' and LXBZLY<>' ' and LXBZ<>' ' and ZDBZLY<>' ' order by ZYXL ";
	}

}
