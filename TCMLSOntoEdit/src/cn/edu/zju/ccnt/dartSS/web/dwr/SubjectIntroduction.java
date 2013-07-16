package cn.edu.zju.ccnt.dartSS.web.dwr;

import org.apache.log4j.Logger;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.SubjectIntroductionHtml;

/**
 * dwr的server端的主要入口之一,取得专题的介绍信息.
 * @author zhm
 */
public class SubjectIntroduction {
	static Logger logger = Logger
			.getLogger(SubjectIntroduction.class.getName());

	/**根据专题名称，取专题的介绍信息。如果专题没有上传介绍图片，则取默认的介绍图片。
	 * @param SubjectName 专题名称
	 * @return 一段html代码
	 */
	public String getSubjectIntro(String SubjectName) {
		try {

			SubjectIntroductionHtml subjectIntrodutionHtml = new SubjectIntroductionHtml();
			subjectIntrodutionHtml.setSubjectName(SubjectName);
			logger.info("subject Introduction:subjectName/" + SubjectName);
			return subjectIntrodutionHtml.generateSubjectIntroHtml();
		} catch (Exception ec) {
			logger.error("生成专题介绍发生错误：" + ec.getMessage()+"\r\n"+ec.toString());
			return "生成专题介绍发生错误：" + ec.getMessage();
		}
	}
}
