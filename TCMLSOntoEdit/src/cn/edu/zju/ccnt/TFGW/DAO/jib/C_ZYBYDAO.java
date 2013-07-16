package cn.edu.zju.ccnt.TFGW.DAO.jib;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.JibDBConn;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZYBY;

public class C_ZYBYDAO {
	static Logger logger = Logger.getLogger(C_ZYBYDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_ZYBY> searchByJib(String jibName){
		String sql = "select * from C_ZhongYBY where JBMC like '%" + jibName + "%'";
	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_ZYBY> jibList = new ArrayList<C_ZYBY>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_ZYBY jib = new C_ZYBY();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJBMC(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJBMC("&nbsp;");

			if (ResultArray.get(counter).get("BYMC") != null)
				jib.setBYMC(ResultArray.get(counter).get("BYMC").toString());
			else
				jib.setBYMC("&nbsp;");

			if (ResultArray.get(counter).get("ZBTD") != null)
				jib.setZBTD(ResultArray.get(counter).get("ZBTD").toString());
			else
				jib.setZBTD("&nbsp;");

			if (ResultArray.get(counter).get("ZBTJ") != null)
				jib.setZBTJ(ResultArray.get(counter).get("ZBTJ").toString());
			else
				jib.setZBTJ("&nbsp;");

			if (ResultArray.get(counter).get("ZH") != null)
				jib.setZH(ResultArray.get(counter).get("ZH").toString());
			else
				jib.setZH("&nbsp;");

			if (ResultArray.get(counter).get("QTZBYS") != null)
				jib.setQTZBYS(ResultArray.get(counter).get("QTZBYS").toString());
			else
				jib.setQTZBYS("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBZ(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBZ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
}
