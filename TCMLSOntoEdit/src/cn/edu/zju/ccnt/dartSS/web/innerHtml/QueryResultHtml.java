package cn.edu.zju.ccnt.dartSS.web.innerHtml;

import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlGetDiseaseFromZZ;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlGetDiseaseFromZh;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlLcyjDetail;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlLcyjOfDW;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlLcyjOfQK;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlZHToLcyj;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlZZToLcyj;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlZZToZH;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.NavigateQueryHtmlZhToZZ;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlClinicCure;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlClinicOrderByQKMC;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlClinicOrderByQKRQ;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlClinicOrderByUnit;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlClinicResearch;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlDiseaesInfo;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlDiseaseSource;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlEmpty;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlRelatedDoc;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlSearch;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlStatistic;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlTreatment;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlTreatstandard;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlZhengHou;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtmlZhengzhuang;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtml_amlf;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtml_qtlf;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtml_xylf;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtml_ywcf;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtml_zjlf;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.impl.QueryResultHtml_zyyflf;

/**根据查询类型，分别调用不同的查询结果具体工厂类。
 * @author zhm
 * 
 */
public class QueryResultHtml {
	/**
	 * 专题名称
	 */
	public String subjectName;

	/**
	 * 专题编码
	 */
	public String subjectTitle;

	/**
	 * 查询类型
	 */
	public String queryType;

	/**
	 * 用于查询的关键字
	 */
	public String KeyWord;

	/**
	 * 当前第几页
	 */
	public int PageNo;

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	
	/**根据导航查询的类型，分别调用不同的查询结果具体工厂类。
	 * @return
	 */
	public String generateNavigateQueryHtml() {
		String resultHtml = "";
		QueryResultHtmlFactory ar;
		if (queryType.equals("临床研究详细信息")) {
			ar = new NavigateQueryHtmlLcyjDetail();
		} else if (queryType.equals("症状相关证候")) {
			ar = new NavigateQueryHtmlZZToZH();
		} else if (queryType.equals("证候相关症状")) {
			ar = new NavigateQueryHtmlZhToZZ();
		} else if (queryType.equals("症状相关临床研究")) {
			ar = new NavigateQueryHtmlZZToLcyj();
		} else if (queryType.equals("证候相关临床研究")) {
			ar = new NavigateQueryHtmlZHToLcyj();
		} else if (queryType.equals("症状相关疾病")) {
			ar = new NavigateQueryHtmlGetDiseaseFromZZ();
		} else if (queryType.equals("证候相关疾病")) {
			ar = new NavigateQueryHtmlGetDiseaseFromZh();
		} else if (queryType.equals("疾病基本信息")) {
			ar = new QueryResultHtmlDiseaesInfo();
			ar.setWithRkc(false);
		} else if (queryType.equals("期刊名称")) {
			ar = new NavigateQueryHtmlLcyjOfQK();
		} else if (queryType.equals("单位文献列表")) {
			ar = new NavigateQueryHtmlLcyjOfDW();
		} else
			ar = new QueryResultHtmlEmpty();
		ar.setSubjectName(subjectName);
		ar.setSubjectTitle(subjectTitle);
		ar.setQueryType(queryType);
		ar.setCurrentPage(PageNo);
		ar.setKeyWord(KeyWord);
		ar.InintialTitleHints();
		resultHtml = ar.generateQueryResult();
		return resultHtml;
	}

	/**
	 * 根据疾病关键字查询的类型，分别调用不同的查询结果具体工厂类。
	 * @return
	 */
	public String generateReusultHtml() {

		String resultHtml = "";
		QueryResultHtmlFactory ar;
		System.out.println(queryType);
		if (queryType.equals("基本信息")) {
			ar = new QueryResultHtmlDiseaesInfo();
			ar.setWithRkc(true);
		} else if (queryType.equals("临床研究")) {
			ar = new QueryResultHtmlClinicResearch();
		} else if (queryType.equals("相关症状")) {
			ar = new QueryResultHtmlZhengzhuang();
		} else if (queryType.equals("相关证候")) {
			ar = new QueryResultHtmlZhengHou();
		} else if (queryType.equals("相关病因")) {
			ar = new QueryResultHtmlDiseaseSource();
		} else if (queryType.equals("临床诊疗")) {
			ar = new QueryResultHtmlClinicCure();
		} else if (queryType.equals("治疗方案")) {
			ar = new QueryResultHtmlTreatment();
		} else if (queryType.equals("诊疗标准")) {
			ar = new QueryResultHtmlTreatstandard();
		} else if (queryType.equals("相关文献")) {
			ar = new QueryResultHtmlRelatedDoc();
		} else if (queryType.equals("汇总")) {
			ar = new QueryResultHtmlStatistic();
		} else if (queryType.equals("搜索")) {
			ar = new QueryResultHtmlSearch();
		} else if (queryType.equals("单位")) {
			ar = new QueryResultHtmlClinicOrderByUnit();
		} else if (queryType.equals("期刊")) {
			ar = new QueryResultHtmlClinicOrderByQKMC();
		} else if (queryType.equals("日期")) {
			ar = new QueryResultHtmlClinicOrderByQKRQ();
		}else if (queryType.equals("中药药方疗法")){
			ar = new QueryResultHtml_zyyflf();
		}else if (queryType.equals("西药疗法")){
			ar = new QueryResultHtml_xylf();
		}else if (queryType.equals("药物成分")){
			ar = new QueryResultHtml_ywcf();
		}else if (queryType.equals("按摩疗法")){
			ar = new QueryResultHtml_amlf();
		}else if (queryType.equals("针灸疗法")){
			ar = new QueryResultHtml_zjlf();
		}else if (queryType.equals("其他疗法")){
			ar = new QueryResultHtml_qtlf();
		} else
			ar = new QueryResultHtmlEmpty();

		ar.setSubjectName(subjectName);
		ar.setSubjectTitle(subjectTitle);
		ar.setQueryType(queryType);
		ar.setCurrentPage(PageNo);
		resultHtml = ar.generateQueryResult();
		return resultHtml;
	}

	public int getPageNo() {
		return PageNo;
	}

	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

	public String getKeyWord() {
		return KeyWord;
	}

	public void setKeyWord(String keyWord) {
		KeyWord = keyWord;
	}
}
