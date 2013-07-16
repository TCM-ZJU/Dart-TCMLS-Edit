package cn.edu.zju.ccnt.dartSS.db.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;

/**
 * Dss_user表的DAO
 * @author zhm
 *
 */
public class UserDAO {

	/**判断该用户是否存在
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return
	 */
	public boolean isUserExist(String userName,String passWord){
		String sqlString="select count(*) USERNUM from Dss_user where user_name='"+userName+"' and User_password='"+passWord+"'";
		DataBaseActivity dba = new DataBaseActivity();

		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = dba.DBSelect(sqlString);
		if(ResultArray.get(0).get("USERNUM").toString().equals("0"))
			return false;
		else 
			return true;
	}
}
