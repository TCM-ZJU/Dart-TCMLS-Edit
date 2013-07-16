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
import cn.edu.zju.ccnt.TFGW.object.jib.C_XYZL;

/**
 * @author 张小刚
 *
 */
public class C_XYZLDAO {
	static Logger logger = Logger.getLogger(C_ZYZLDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_XYZL> searchByJib(String jibName){
		String sql = "select * from C_XIYZL where JBMC like '%" + jibName + "%'";
	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_XYZL> jibList = new ArrayList<C_XYZL>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_XYZL jib = new C_XYZL();

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

			if (ResultArray.get(counter).get("YWMC") != null)
				jib.setYWMC(ResultArray.get(counter).get("YWMC").toString());
			else
				jib.setYWMC("&nbsp;");

			if (ResultArray.get(counter).get("YWJX") != null)
				jib.setYWJX(ResultArray.get(counter).get("YWJX").toString());
			else
				jib.setYWJX("&nbsp;");
			
			if (ResultArray.get(counter).get("GYFS") != null)
				jib.setGYFS(ResultArray.get(counter).get("GYFS").toString());
			else
				jib.setGYFS("&nbsp;");

			if (ResultArray.get(counter).get("GYSJ") != null)
				jib.setGYSJ(ResultArray.get(counter).get("GYSJ").toString());
			else
				jib.setGYSJ("&nbsp;");

			if (ResultArray.get(counter).get("YWCFXX") != null)
				jib.setYWCFXX(ResultArray.get(counter).get("YWCFXX").toString());
			else
				jib.setYWCFXX("&nbsp;");
			
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
