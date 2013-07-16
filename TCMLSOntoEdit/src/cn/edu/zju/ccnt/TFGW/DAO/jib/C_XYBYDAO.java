package cn.edu.zju.ccnt.TFGW.DAO.jib;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.JibDBConn;
import cn.edu.zju.ccnt.TFGW.object.jib.C_XYBY;

public class C_XYBYDAO {
	static Logger logger = Logger.getLogger(C_XYBYDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_XYBY> searchByJib(String jibName){
		String sql = "select * from C_XiYBY where JBMC like '%" + jibName + "%'";
	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_XYBY> jibList = new ArrayList<C_XYBY>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_XYBY jib = new C_XYBY();

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

			if (ResultArray.get(counter).get("CRY") != null)
				jib.setCRY(ResultArray.get(counter).get("CRY").toString());
			else
				jib.setCRY("&nbsp;");
			
			if (ResultArray.get(counter).get("CRTJ") != null)
				jib.setCRTJ(ResultArray.get(counter).get("CRTJ").toString());
			else
				jib.setCRTJ("&nbsp;");

			if (ResultArray.get(counter).get("FBDQ") != null)
				jib.setFBDQ(ResultArray.get(counter).get("FBDQ").toString());
			else
				jib.setFBDQ("&nbsp;");

			if (ResultArray.get(counter).get("FBRQ") != null)
				jib.setFBRQ(ResultArray.get(counter).get("FBRQ").toString());
			else
				jib.setFBRQ("&nbsp;");
			
			if (ResultArray.get(counter).get("FBJJ") != null)
				jib.setFBJJ(ResultArray.get(counter).get("FBJJ").toString());
			else
				jib.setFBJJ("&nbsp;");
			
			if (ResultArray.get(counter).get("FBL") != null)
				jib.setFBL(ResultArray.get(counter).get("FBL").toString());
			else
				jib.setFBL("&nbsp;");

			if (ResultArray.get(counter).get("FBXGYS") != null)
				jib.setFBXGYS(ResultArray.get(counter).get("FBXGYS").toString());
			else
				jib.setFBXGYS("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBZ(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBZ("&nbsp;");
			jibList.add(jib);
		}
		
		return jibList;
	}	
}
