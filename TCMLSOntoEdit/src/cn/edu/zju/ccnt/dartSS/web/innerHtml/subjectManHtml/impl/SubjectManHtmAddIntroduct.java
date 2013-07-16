package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl;

import java.util.Calendar;

import org.apache.log4j.Logger;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.SubjectManHtmlFactory;

/**
 * 上传专题介绍图片
 * 
 * @author zhm
 * 
 */
public class SubjectManHtmAddIntroduct extends SubjectManHtmlFactory {
	static Logger myLogger = Logger.getLogger(SubjectManHtmAddIntroduct.class
			.getName());

	@Override
	public String setInnerHtml() {
		String htmlrStr = this.setHintHtml("<b>上传专题图片</b> ");
		return htmlrStr += SetAddZtcPage();
	}

	public String SetAddZtcPage() {

		Calendar calendar = Calendar.getInstance();
 		String filename =String.valueOf(calendar.getTimeInMillis());
 		if(filename.length()>7)
 		filename=filename.substring(filename.length()-7,filename.length());
 		
 		/*加上时间最为url后缀的目的：因为是在一个新窗口
 		中打开...../picUpload/文件，如果没有后缀，
 		用户在使用一次之后，没有及时关闭...../picUpload/文件，
 		然后继续上传图片，这是会导致submit操作不经servlet处理，
 		直接跳转到已经存在的...../picUpload/文件，
 		这样实际程序没有做任何事情。加了时间序列号后缀可以避免这一问题*/
 		
		return "<form action=\"picUpload/"+filename+"\" target=\"_blank\"  "
				+ "ENCTYPE=\"multipart/form-data\" method=\"POST\" ><table>"
				+ "<tr>" 
				+ "<td>选择专题:</td>" 
				+ "<td>" 
				+ this.zhuanTiOptions()
				+ "</td>" 
				+ "</tr>"
				+ "<tr>" 
				+ "<td>专题标题图片:</td>"
				+ "<td><input type=\"file\" name=\"file\" > </td>" 
				+ "</tr>"
				+ "<tr>" 
				+ "<td>专题介绍图片:</td>"
				+ "<td><input type=\"file\" name=\"file\" > </td>" 
				+ "</tr>"
				+ "<tr>"
				+ "<td>提示信息:</td>"
				+ "<td><font color=red>支持gif,png,jpg等格式</font></td>"
				+ "</tr>" 
				+ "<tr>" 
				+ "<td></td>"
				+ "<td><br/><input type=\"submit\" name=\"Submit\" "
				+ "value=\" 上传图片 \" /></td>" 
				+ "</tr>"
				+ "</table></form>";
	}

}
