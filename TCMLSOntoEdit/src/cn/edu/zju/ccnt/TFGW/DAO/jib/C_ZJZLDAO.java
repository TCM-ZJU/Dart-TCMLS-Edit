/**
 * 
 */
package cn.edu.zju.ccnt.TFGW.DAO.jib;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.JibDBConn;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZJZL;

/**
 * @author 张小刚
 *
 */
public class C_ZJZLDAO {
	static Logger logger = Logger.getLogger(C_ZJZLDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_ZJZL> searchByJib(String jibName){
		String sql = "select * from C_ANMZL where JBMC like '%" + jibName + "%'";
	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_ZJZL> jibList = new ArrayList<C_ZJZL>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_ZJZL jib = new C_ZJZL();

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

			if (ResultArray.get(counter).get("SF") != null)
				jib.setSF(ResultArray.get(counter).get("SF").toString());
			else
				jib.setSF("&nbsp;");

			if (ResultArray.get(counter).get("FF") != null)
				jib.setFF(ResultArray.get(counter).get("FF").toString());
			else
				jib.setFF("&nbsp;");
			
			if (ResultArray.get(counter).get("QX") != null)
				jib.setQX(ResultArray.get(counter).get("QX").toString());
			else
				jib.setQX("&nbsp;");
			
			if (ResultArray.get(counter).get("CS") != null)
				jib.setCS(ResultArray.get(counter).get("CS").toString());
			else
				jib.setCS("&nbsp;");
			
			if (ResultArray.get(counter).get("JL") != null)
				jib.setJL(ResultArray.get(counter).get("JL").toString());
			else
				jib.setJL("&nbsp;");

			if (ResultArray.get(counter).get("XWMC") != null)
				jib.setXWMC(ResultArray.get(counter).get("XWMC").toString());
			else
				jib.setXWMC("&nbsp;");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				jib.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				jib.setZZ("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBZ(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBZ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
}
