package cn.edu.zju.ccnt.TFGW;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetFactory {

	static Logger logger = Logger.getLogger(GetFactory.class.getName());
	
	private static ApplicationContext factory;
	
	public static ApplicationContext getFactory(){
		if(factory == null){
			factory =  new ClassPathXmlApplicationContext(new String[]{
					"/conf/springConf/spring.xml",
					"/conf/springConf/tree.xml",
					"/conf/springConf/innerHTML.xml",
					"/conf/DBConf/DAO.xml",
					"/conf/springConf/searchOperation.xml"});
			
			logger.info("GetFactory Initial");
		}
		
		return factory;
	}
}
