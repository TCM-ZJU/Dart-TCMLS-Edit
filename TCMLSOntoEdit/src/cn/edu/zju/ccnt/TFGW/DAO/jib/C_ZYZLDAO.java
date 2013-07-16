package cn.edu.zju.ccnt.TFGW.DAO.jib;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.JibDBConn;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZYZL;

public class C_ZYZLDAO {
	static Logger logger = Logger.getLogger(C_ZYZLDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_ZYZL> searchByJib(String jibName){
		String sql = "select * from C_ZHONGYZL where JBMC like '%" + jibName + "%'";
	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_ZYZL> jibList = new ArrayList<C_ZYZL>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_ZYZL jib = new C_ZYZL();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJBMC(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJBMC("&nbsp;");

			if (ResultArray.get(counter).get("BZMC") != null)
				jib.setBZMC(ResultArray.get(counter).get("BZMC").toString());
			else
				jib.setBZMC("&nbsp;");

			if (ResultArray.get(counter).get("FM") != null)
				jib.setFM(ResultArray.get(counter).get("FM").toString());
			else
				jib.setFM("&nbsp;");

			if (ResultArray.get(counter).get("YFJX") != null)
				jib.setYFJX(ResultArray.get(counter).get("YFJX").toString());
			else
				jib.setYFJX("&nbsp;");
			
			if (ResultArray.get(counter).get("JL") != null)
				jib.setJL(ResultArray.get(counter).get("JL").toString());
			else
				jib.setJL("&nbsp;");

			if (ResultArray.get(counter).get("JLDW") != null)
				jib.setJLDW(ResultArray.get(counter).get("JLDW").toString());
			else
				jib.setJLDW("&nbsp;");

			if (ResultArray.get(counter).get("GYFS") != null)
				jib.setGYFS(ResultArray.get(counter).get("GYFS").toString());
			else
				jib.setGYFS("&nbsp;");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				jib.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				jib.setZZ("&nbsp;");
			
			if (ResultArray.get(counter).get("YWXX") != null)
				jib.setYWXX(ResultArray.get(counter).get("YWXX").toString());
			else
				jib.setYWXX("&nbsp;");

			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBZ(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBZ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
}
