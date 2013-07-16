package cn.edu.zju.ccnt.TFGW.DAO.jib;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DAO.ExpertDAO;
import cn.edu.zju.ccnt.TFGW.DBConnect.JibDBConn;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZHENH;

public class C_ZHENHDAO {
	static Logger logger = Logger.getLogger(ExpertDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_ZHENH> searchZHByJib(String jibName){
		String sql = "select * from C_ZHENH where JBMC like '%" + jibName + "%'";
	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_ZHENH> jibList = new ArrayList<C_ZHENH>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_ZHENH jib = new C_ZHENH();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJBMC(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJBMC("&nbsp;");

			if (ResultArray.get(counter).get("ZHMC") != null)
				jib.setZHMC(ResultArray.get(counter).get("ZHMC").toString());
			else
				jib.setZHMC("&nbsp;");

			if (ResultArray.get(counter).get("BJMC") != null)
				jib.setBJMC(ResultArray.get(counter).get("BJMC").toString());
			else
				jib.setBJMC("&nbsp;");

			if (ResultArray.get(counter).get("ZZMC") != null)
				jib.setZZMC(ResultArray.get(counter).get("ZZMC").toString());
			else
				jib.setZZMC("&nbsp;");

			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBZ(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBZ("&nbsp;");

			if (ResultArray.get(counter).get("BJZT") != null)
				jib.setBJZT(ResultArray.get(counter).get("BJZT").toString());
			else
				jib.setBJZT("&nbsp;");
			
			if (ResultArray.get(counter).get("CWXX") != null)
				jib.setCWXX(ResultArray.get(counter).get("CWXX").toString());
			else
				jib.setCWXX("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
		
}
