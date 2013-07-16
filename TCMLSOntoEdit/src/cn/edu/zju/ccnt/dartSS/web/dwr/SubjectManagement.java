package cn.edu.zju.ccnt.dartSS.web.dwr;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl.SubjectManHtmAddIntroduct;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl.SubjectManHtmAddZtc;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl.SubjectManHtmlAddsubject;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl.SubjectManHtmlDeleteSubject;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl.SubjectManHtmlRecreateSubject;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl.SubjectManHtmlUserloggon;


/**
 * dwr的server端的主要入口之一,专题管理的server端实现
 * @author zhm
 */
public class SubjectManagement {
	static Logger mylogger = Logger
			.getLogger(SubjectManagement.class.getName());

	/**
	 * 根据专题管理的类型产生相应的界面
	 * @param Man 专题管理的类型
	 * @return
	 */
	public String GetPageHtmlByType(String Man) {
		try {
			String strHtml = "";
			if (Man.equals("用户登录")) {
				strHtml = new SubjectManHtmlUserloggon().setInnerHtml();
			} else if (Man.equals("添加专题")) {
				strHtml = new SubjectManHtmlAddsubject().setInnerHtml();
			} else if (Man.equals("上传专题图片")) {
				strHtml = new SubjectManHtmAddIntroduct().setInnerHtml();
			} else if (Man.equals("删除专题")) {
				strHtml = new SubjectManHtmlDeleteSubject().setInnerHtml();
			} else if (Man.equals("重新生成专题")) {
				strHtml = new SubjectManHtmlRecreateSubject().setInnerHtml();
			} else if (Man.equals("添加主题词")) {
				strHtml = new SubjectManHtmAddZtc().setInnerHtml();
			} else
				strHtml = "无效操作";
			return strHtml;
		} catch (Exception ec) {
			mylogger.info("专题管理发生异常:" + ec.getMessage()+"\r\n"+ec.toString());
			return "专题管理发生异常</br>" + ec.getMessage();
		}
	}

	/**
	 * 用户登录的相应，如果正确，则需要返回专题管理的菜单栏
	 * @param userName  用户名
	 * @param password  密码
	 * @return
	 */
	public String userLoggon(String userName, String password) {
		try {
			SubjectManHtmlUserloggon loggoning = new SubjectManHtmlUserloggon();
			loggoning.setUserName(userName);
			loggoning.setPassWord(password);
			return loggoning.SetHtmlAfterLogged();
		} catch (Exception ec) {
			mylogger.info("用户登录响应异常:" + ec.getMessage()+"\r\n"+ec.toString());
			return "用户登录响应异常</br>" + ec.getMessage();
		}
	}

	/**
	 * 添加主题词，供dwr调用的接口
	 * @param subjectName  专题名称
	 * @param zhuticiName   主题词
	 * @return
	 */
	public String AddZhutici(String subjectName, String zhuticiName) {
		try {
			SubjectManHtmAddZtc addZtc=new SubjectManHtmAddZtc();
			addZtc.setSubjectName(subjectName);
			addZtc.setZhuticiName(zhuticiName);
			return addZtc.addZhutici();
		} catch (Exception ec) {
			mylogger.info("用户登录响应异常:" + ec.getMessage()+"\r\n"+ec.toString());
			return "用户登录响应异常</br>" + ec.getMessage();
		}

	}

	/**
	 * 添加一个专题，供dwr调用的接口
	 * @param subjectName  专题名称
	 * @param subjectTag   专题编码
	 * @param subjectCondition	专题主题词的产生条件
	 * @param subjectIntroPic	专题介绍图片
	 * @param subjectNotes	专题备注信息
	 * @return
	 */
	public String AddNewSuject(String subjectName, String subjectTag,
			String subjectCondition, String subjectIntroPic, String subjectNotes,String subjectAddedZTC) {
		try {
			SubjectManHtmlAddsubject addSubject=new SubjectManHtmlAddsubject();
			return addSubject.SaveNewSubject(subjectName,subjectTag,
					subjectCondition,subjectIntroPic,subjectNotes,subjectAddedZTC);
		} catch (Exception ec) {
			mylogger.info("添加专题发生异常:" + ec.getMessage()+"\r\n"+ec.toString());
			return "添加专题发生异常</br>" + ec.getMessage();
		}
	}

	/**
	 * 删除一个专题，供dwr调用的接口
	 * 
	 * @param subjectName 专题名称
	 * @return
	 */
	public String DeleteSubject(String subjectName) {
		try {
			SubjectManHtmlDeleteSubject deleteSubject=new SubjectManHtmlDeleteSubject();
			return deleteSubject.deleteSubject(subjectName);
		} catch (Exception ec) {
			mylogger.info("删除专题发生异常:" + ec.getMessage()+"\r\n"+ec.toString());
			return "删除专题发生异常</br>" + ec.getMessage();
		}
	}

	/**
	 * 重建专题，供dwr调用的接口
	 * 
	 * @param subjectName  专题名称
	 * @return
	 */
	public String RecreateSubject(String subjectName) {
		try {
			SubjectManHtmlRecreateSubject recreateSubject=new SubjectManHtmlRecreateSubject();
			return recreateSubject.recreateSubject(subjectName);
		} catch (Exception ec) {
			mylogger.info("重建专题发生异常:" + ec.getMessage()+"\r\n"+ec.toString());
			return "重建专题发生异常</br>" + ec.getMessage();
		}
	}
}
