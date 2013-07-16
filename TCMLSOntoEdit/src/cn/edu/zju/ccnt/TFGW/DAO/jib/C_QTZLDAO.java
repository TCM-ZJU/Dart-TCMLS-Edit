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
import cn.edu.zju.ccnt.TFGW.object.jib.C_QTZL;

/**
 * @author 张小刚
 *
 */
public class C_QTZLDAO {
	static Logger logger = Logger.getLogger(C_QTZLDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_QTZL> searchByJib(String jibName){
		String sql = "select * from C_QITZL where JBMC like '%" + jibName + "%'";
	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_QTZL> jibList = new ArrayList<C_QTZL>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_QTZL jib = new C_QTZL();

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

			if (ResultArray.get(counter).get("LFMS") != null)
				jib.setLFMS(ResultArray.get(counter).get("LFMS").toString());
			else
				jib.setLFMS("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBZ(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBZ("&nbsp;");
			
			if (ResultArray.get(counter).get("YWXX") != null)
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
}
