package cn.edu.zju.ccnt.TFGW.innerHTML;

import java.util.ArrayList;

import cn.edu.zju.ccnt.TFGW.object.Expert;
import cn.edu.zju.ccnt.TFGW.object.Hospital;
import cn.edu.zju.ccnt.TFGW.object.YJYA;
import cn.edu.zju.ccnt.TFGW.object.ZCFG;
import cn.edu.zju.ccnt.TFGW.object.ZDFZ;
import cn.edu.zju.ccnt.TFGW.object.ZYBFZ;
import cn.edu.zju.ccnt.TFGW.object.ZYBZZLBZ;
import cn.edu.zju.ccnt.TFGW.object.jib.C_AMZL;
import cn.edu.zju.ccnt.TFGW.object.jib.C_QTZL;
import cn.edu.zju.ccnt.TFGW.object.jib.C_XYBY;
import cn.edu.zju.ccnt.TFGW.object.jib.C_XYZL;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZGZL;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZHENH;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZJZL;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZYBY;
import cn.edu.zju.ccnt.TFGW.object.jib.C_ZYZL;
import cn.edu.zju.ccnt.TFGW.object.jib.C_jib;
import cn.edu.zju.ccnt.TFGW.object.jmz.JMZ_JB;


public class CreateTabHTML {
	
	public String CreateC_JIBTab(ArrayList<C_jib> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>中医疾病数据库</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>异名</th>" +
				"<th>发病方式</th>" +
				"<th>原发病</th>" +
				"<th>并发症</th>" +
				"<th>症状名称</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).jbmc + "</td>" +
					"<td>" + jib.get(i).ym + "</td>" +
					"<td>" + jib.get(i).fbfs + "</td>" +
					"<td>" + jib.get(i).yfb + "</td>" +
					"<td>" + jib.get(i).bfz + "</td>" +
					"<td>" + jib.get(i).zzmc + "</td>" +
					"<td>" + jib.get(i).bz + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	/**
	 * 中医疾病数据库关联显示中医病因
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateZYBYTab(ArrayList<C_ZYBY> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>中医病因</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病因名称</th>" +
				"<th>致病特点</th>" +
				"<th>致病途径</th>" +
				"<th>转化</th>" +
				"<th>其他致病因素</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBYMC() + "</td>" +
					"<td>" + jib.get(i).getZBTD() + "</td>" +
					"<td>" + jib.get(i).getZBTJ() + "</td>" +
					"<td>" + jib.get(i).getZH() + "</td>" +
					"<td>" + jib.get(i).getQTZBYS() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	/**
	 * 西医病因数据库
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateXYBYTab(ArrayList<C_XYBY> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>西医病因</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病因名称</th>" +
				"<th>致病特点</th>" +
				"<th>传染源</th>" +
				"<th>传染途径</th>" +
				"<th>发病人群</th>" +		
				"<th>发病季节</th>" +
				"<th>发病率</th>" +
				"<th>发病相关因素</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBYMC() + "</td>" +
					"<td>" + jib.get(i).getZBTD() + "</td>" +
					"<td>" + jib.get(i).getCRY() + "</td>" +
					"<td>" + jib.get(i).getCRTJ() + "</td>" +
					"<td>" + jib.get(i).getFBRQ() + "</td>" +
					"<td>" + jib.get(i).getFBJJ() + "</td>" +
					"<td>" + jib.get(i).getFBL() + "</td>" +
					"<td>" + jib.get(i).getFBXGYS() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	/**
	 * 中医疾病数据库关联显示中医治疗
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateZYZLTab(ArrayList<C_ZYZL> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>中药治疗</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病证名称</th>" +
				"<th>方名</th>" +
				"<th>药方剂型</th>" +
				"<th>剂量</th>" +
				"<th>剂量单位</th>" +		
				"<th>给药方式</th>" +
				"<th>治则</th>" +
				"<th>药物信息</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBZMC() + "</td>" +
					"<td>" + jib.get(i).getFM() + "</td>" +
					"<td>" + jib.get(i).getYFJX() + "</td>" +
					"<td>" + jib.get(i).getJL() + "</td>" +
					"<td>" + jib.get(i).getJLDW() + "</td>" +
					"<td>" + jib.get(i).getGYFS() + "</td>" +
					"<td>" + jib.get(i).getZZ() + "</td>" +
					"<td>" + jib.get(i).getYWXX() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	/**
	 * 中医疾病数据库关联显示西药治疗
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateXYZLTab(ArrayList<C_XYZL> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>西药治疗</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病证名称</th>" +
				"<th>药物名称</th>" +
				"<th>药物剂型</th>" +
				"<th>给药方式</th>" +
				"<th>给药时间</th>" +		
				"<th>药物成分信息</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBZMC() + "</td>" +
					"<td>" + jib.get(i).getYWMC() + "</td>" +
					"<td>" + jib.get(i).getYWJX() + "</td>" +
					"<td>" + jib.get(i).getGYFS() + "</td>" +
					"<td>" + jib.get(i).getGYSJ() + "</td>" +
					"<td>" + jib.get(i).getYWCFXX() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	/**
	 * 中医疾病数据库关联显示按摩治疗治疗
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateAMZLTab(ArrayList<C_AMZL> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>中医治疗</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病证名称</th>" +
				"<th>手法</th>" +
				"<th>方法</th>" +
				"<th>时间</th>" +
				"<th>次数</th>" +		
				"<th>经络</th>" +
				"<th>穴位名称</th>" +
				"<th>治则</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBZMC() + "</td>" +
					"<td>" + jib.get(i).getSF() + "</td>" +
					"<td>" + jib.get(i).getFF() + "</td>" +
					"<td>" + jib.get(i).getSJ() + "</td>" +
					"<td>" + jib.get(i).getCS() + "</td>" +
					"<td>" + jib.get(i).getJL() + "</td>" +
					"<td>" + jib.get(i).getXWMC() + "</td>" +
					"<td>" + jib.get(i).getZZ() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	/**
	 * 中医疾病数据库关联显示正骨治疗
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateZGZLTab(ArrayList<C_ZGZL> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>中医治疗</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病证名称</th>" +
				"<th>手法</th>" +
				"<th>方法</th>" +
				"<th>时间</th>" +
				"<th>次数</th>" +		
				"<th>治则</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBZMC() + "</td>" +
					"<td>" + jib.get(i).getSF() + "</td>" +
					"<td>" + jib.get(i).getFF() + "</td>" +
					"<td>" + jib.get(i).getSJ() + "</td>" +
					"<td>" + jib.get(i).getCS() + "</td>" +
					"<td>" + jib.get(i).getZZ() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	/**
	 * 中医疾病数据库关联显示针灸治疗
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateZJZLTab(ArrayList<C_ZJZL> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>针灸治疗</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病证名称</th>" +
				"<th>手法</th>" +
				"<th>方法</th>" +
				"<th>器械</th>" +
				"<th>次数</th>" +		
				"<th>经络</th>" +
				"<th>穴位名称</th>" +	
				"<th>治则</th>" +		
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBZMC() + "</td>" +
					"<td>" + jib.get(i).getSF() + "</td>" +
					"<td>" + jib.get(i).getFF() + "</td>" +
					"<td>" + jib.get(i).getQX() + "</td>" +
					"<td>" + jib.get(i).getCS() + "</td>" +
					"<td>" + jib.get(i).getJL() + "</td>" +
					"<td>" + jib.get(i).getXWMC() + "</td>" +
					"<td>" + jib.get(i).getZZ() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	/**
	 * 中医疾病数据库关联显示其它治疗
	 * @param jib
	 * @param num
	 * @return
	 */
	public String CreateQTZLTab(ArrayList<C_QTZL> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>其他治疗</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>疾病名称</th>" +
				"<th>病证名称</th>" +
				"<th>疗法描述</th>" +
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).getJBMC() + "</td>" +
					"<td>" + jib.get(i).getBZMC() + "</td>" +
					"<td>" + jib.get(i).getLFMS() + "</td>" +
					"<td>" + jib.get(i).getBZ() + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	public String CreateC_ZhenHTab(ArrayList<C_ZHENH> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>症候信息</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>症候名称</th>" +
				"<th>性质</th>" +
				"<th>症状名称</th>" +
				"<th>疾病名称</th>" +	
				"<th>备注</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}	
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).ZHMC + "</td>" +
					"<td>" + jib.get(i).BJMC + "</td>" +
					"<td>" + jib.get(i).ZZMC + "</td>" +
					"<td>" + jib.get(i).JBMC + "</td>" +
					"<td>" + jib.get(i).BZ + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	public String CreateJMZ_JBTab(ArrayList<JMZ_JB> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
		
		table.append(
				"<table class=\"infTable\">" +
				"<caption>发热数据库</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +
				"<th>疾病名称</th>" +
				"<th>原发病</th>" +
				"<th>并发症</th>" +
				"<th>症状</th>" +		
				"<th>症候</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td>" + (i+ 1 + num) + "</td>" +				
					"<td>" + jib.get(i).JBMC + "</td>" +
					"<td>" + jib.get(i).YFB + "</td>" +
					"<td>" + jib.get(i).BFZ + "</td>" +
					"<td>" + jib.get(i).ZZ + "</td>" +
					"<td>" + jib.get(i).ZH + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		return table.toString();
	}	
	
	public String CreateZDFZTab(ArrayList<ZDFZ> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
		
		table.append(
				"<table class=\"infTable\">" +
				"<caption>常见中毒防治数据库</caption>");
		
		for(int i = 0; i < jib.size(); i++){		
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td><table>" +
					"<tr><th>序号</th><td>" + (i+ 1 + num) + "</td>" +				
					"<tr><th>疾病名称</th><td>" + jib.get(i).JIBMC + "</td>" +
					"<tr><th>中毒物</th><td>" + jib.get(i).ZHONGDW + "</td>" +
					"<tr><th>发病原因</th><td>" + jib.get(i).FABYY + "</td>" +
					"<tr><th>预防与控制</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZDFZ\",\"ID\",\"YUFYKZ\",\"" + 
							 jib.get(i).ID + 
							 "\")></img>" + 
							 "查看" + 
					"</td>" +
					"<tr><th>诊断</th><td>" + jib.get(i).ZHEND + "</td>" +
					"<tr><th>治疗</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZDFZ\",\"ID\",\"ZHIL\",\"" + 
					 		 jib.get(i).ID + 
					 		 "\")></img>" + 
					 		 "查看" + 
			        "</td>" +
			        "<tr><th>症状与体征</th><td>" + jib.get(i).ZHENGZYTZ + "</td>" +
			        "<tr><th>中毒分类</th><td>" + jib.get(i).ZHONGDFL + "</td>" +
				"</table></td></tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	public String CreateZYBFZTab(ArrayList<ZYBFZ> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
		
		table.append(
				"<table class=\"infTable\">" +
				"<caption>常见职业病防治数据库</caption>");
		
		for(int i = 0; i < jib.size(); i++){		
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td><table>" +
					"<tr><th>序号</th><td>" + (i + 1 + num) + "</td></tr>" +				
					"<tr><th>疾病名称</th><td>" + jib.get(i).JIBMC + "</td></tr>" +
					"<tr><th>文题</th><td>" + jib.get(i).WENT + "</td></tr>" +
					"<tr><th>发病原因</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZYBFZ\",\"ID\",\"FABYY\",\"" + 
							 jib.get(i).ID + 
							 "\")></img>" + 
							 "查看" + 
					"</td></tr>" +
					"<tr><th>预防与控制</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZYBFZ\",\"ID\",\"YUFYKZ\",\"" + 
					 		 jib.get(i).ID + 
					 		 "\")></img>" + 
					 		 "查看" + 
			        "</td></tr>" +
					"<tr><th>诊断</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZYBFZ\",\"ID\",\"ZHEND\",\"" + 
					 		 jib.get(i).ID + 
					 		 "\")></img>" + 
					 		 "查看" + 
			 		 "</td></tr>" +
					 "<tr><th>治疗</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZYBFZ\",\"ID\",\"ZHIL\",\"" + 
				 	 		 jib.get(i).ID + 
				 		     "\")></img>" + 
				 		     "查看" + 
		 		     "</td></tr>" +		
					 "<tr><th>症状与体征</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZYBFZ\",\"ID\",\"ZHENGZYTZ\",\"" + 
		 	 		 		 jib.get(i).ID + 
		 	 		 		 "\") />" + 
		 	 		 		 "查看" + 
 		             "</td></tr>" +	
				"</table></td></tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}	
	
	public String CreateZCFGTab(ArrayList<ZCFG> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
		
		table.append(
				"<table class=\"infTable\">" +
				"<caption>突发公卫政策法规数据库</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +
				"<th>疫病名称</th>" +					
				"<th>文题</th>" +		
				"<th>内容</th>" +					
				"<th>批准文号</th>" +		
				"<th>批准机构</th>" +
				"<th>发病日期</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){		
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).YIBMC + "</td>" +
					"<td>" + jib.get(i).WENT + "</td>" +
					"<td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_ZCFG\",\"ID\",\"NEIR\",\"" + 
							 jib.get(i).ID + 
							 "\")>" + 
							 "查看" + 
					"</td>" +
					"<td>" + jib.get(i).PIZWH + "</td>" +
					"<td>" + jib.get(i).PIZJG + "</td>" +
					"<td>" + jib.get(i).FABRQ + "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}	
	
	public String CreateYJYATab(ArrayList<YJYA> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
		
		table.append(
				"<table class=\"infTable\">" +
				"<caption>突发公卫应急预案数据库</caption>");
		
		for(int i = 0; i < jib.size(); i++){		
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td><table>" +
					"<tr><th>序号</th><td>" + (i + 1 + num) + "</td></tr>" +				
					"<tr><th>疾病名称</th><td>" + jib.get(i).JIBMC + "</td></tr>" +
					"<tr><th>问题</th><td>" + jib.get(i).WENT + "</td></tr>" +
					"<tr><th>发病日期</th><td>" + jib.get(i).FABRQ + "</td></tr>" +
					"<tr><th>症状体征</th><td>" + jib.get(i).ZHANGZTZ + "</td></tr>" +
					"<tr><th>发病原因</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_YJYA\",\"ID\",\"FABYY\",\"" + 
							 jib.get(i).ID + 
							 "\")></img>" + 
							 "查看" + 
					"</td></tr>" +
					"<tr><th>应急救治</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_YJYA\",\"ID\",\"YINGJJZ\",\"" + 
					 		 jib.get(i).ID + 
					 		 "\")></img>" + 
					 		 "查看" + 
			        "</td></tr>" +
			        "<tr><th>预防与控制</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"TFGW_YJYA\",\"ID\",\"YUFYKZ\",\"" + 
					 		 jib.get(i).ID + 
					 		 "\")></img>" + 
					 		 "查看" + 
			 		 "</td></tr>" +
				"</table></td></tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}	
	
	public String CreateZYBZZLBZTab(ArrayList<ZYBZZLBZ> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
		
		table.append(
				"<table class=\"infTable\">" +
				"<caption>中医疾病诊疗标准数据库</caption>");
		
		for(int i = 0; i < jib.size(); i++){		
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td><table>" +
					"<tr><th>序号</th><td>" + (i + 1 + num) + "</td></tr>" +				
					"<tr><th>病症名称</th><td>" + jib.get(i).BINGZMC + "</td></tr>" +
					"<tr><th>病因病机</th><td>" + jib.get(i).BINGYBJ + "</td></tr>" +
					"<tr><th>诊断依据</th><td>" + jib.get(i).ZHENDYJ + "</td></tr>" +
					"<tr><th>症候分类</th><td>" + jib.get(i).ZHENGHFL+ "</td></tr>" +
					"<tr><th>疗效评定-治愈</th><td>" + jib.get(i).LXPD_ZY + "</td></tr>" +
					"<tr><th>疗效评定-好转</th><td>" + jib.get(i).LXPD_HZ + "</td></tr>" +
					"<tr><th>疗效评定-未愈</th><td>" + jib.get(i).LXPD_WY + "</td></tr>" +
					"<tr><th>中医分科</th><td>" + jib.get(i).ZHONGYFK + "</td></tr>" +
					"<tr><th>疾病分类</th><td>" + jib.get(i).JIBFL + "</td></tr>" +
				"</table></td></tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}		
	
	public String CreateExpertTab(ArrayList<Expert> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
		table.append("<table class=\"infTable\">" +
				"<caption>名医专家数据库</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>姓名</th>" +
				"<th>所属医院</th>" +
				"<th>科室</th>" +
				"<th>职称</th>" +
				"<th>门诊时间</th>" +
				"<th>主治疾病</th>" +		
				"<th>专家介绍</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).XINGM + "</td>" +
					"<td>" + jib.get(i).SUOSYY + "</td>" +
					"<td>" + jib.get(i).SUOZKS + "</td>" +
					"<td>" + jib.get(i).ZHIC + "</td>" +
					"<td>" + jib.get(i).MENZSJ + "</td>" +
					"<td>" + jib.get(i).ZHUZJB + "</td>" +
					"<td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"BJWSZY_RENYUAN\",\"ID\",\"ZHUANJJS\",\"" + 
				 		 jib.get(i).ID + 
				 		 "\")></img>" + 
				 		 "查看" + 
			 		 "</td>" +
				"</tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
	
	public String CreateHospitalTab(ArrayList<Hospital> jib, int num){
		StringBuffer table = new StringBuffer("");
		String trOdd = "<tr class=\"trOdd\">";
		String trEven = "<tr class=\"trEven\">";
	
	/*	table.append("<table class=\"infTable\">" +
				"<caption>医院数据库</caption>" +
				"<tr bgColor=\"#78F06F\">" +
				"<th>序号</th>" +				
				"<th>医院名</th>" +
				"<th>地址</th>" +
				"<th>电话</th>" +
				"<th>床位</th>" +
				"<th>门诊量</th>" +
				"<th>等级</th>" +		
				"<th>院长</th>" +
				"<th>特色专科</th>" +
				"<th>医院介绍</th>" +		
				"<th>链接</th>" +
				"</tr>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td>" + (i + 1 + num) + "</td>" +				
					"<td>" + jib.get(i).YIYMC + "</td>" +
					"<td>" + jib.get(i).YIYDZ + "</td>" +
					"<td>" + jib.get(i).LIANXDH + "</td>" +
					"<td>" + jib.get(i).CHUANGWS + "</td>" +
					"<td>" + jib.get(i).RIMZL + "</td>" +
					"<td>" + jib.get(i).YIYDJ + "</td>" +
					"<td>" + jib.get(i).YUANZ + "</td>" +
					"<td>" + jib.get(i).TESZK + "</td>" +
					"<td>" + jib.get(i).WANGZ + "</td>" +
					"<td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"BJWSZY_YIYUAN\",\"ID\",\"YIYJS\",\"" + 
				 		 jib.get(i).ID + 
				 		 "\")></img>" + 
				 		 "查看" + 
			 		 "</td>" +
				"</tr>");
		}*/
		
		table.append("<table class=\"infTable\">" +
				"<caption>医院数据库</caption>");
		
		for(int i = 0; i < jib.size(); i++){
			if(i%2 == 0){
				table.append(trEven);
			}
			else{
				table.append(trOdd);
			}
			table.append(
					"<td><table>" +
					"<tr><th>id</th><td>" + (i + 1 + num) + "</td></tr>" +
					"<tr><th>医院名</th><td>" + jib.get(i).YIYMC + "</td></tr>" +
					"<tr><th>地址</th><td>" + jib.get(i).YIYDZ + "</td></tr>" +
					"<tr><th>电话</th><td>" + jib.get(i).LIANXDH + "</td></tr>" +
					"<tr><th>床位</th><td>" + jib.get(i).CHUANGWS + "</td></tr>" +
					"<tr><th>门诊量</th><td>" + jib.get(i).RIMZL + "</td></tr>" +
					"<tr><th>医院等级</th><td>" + jib.get(i).YIYDJ + "</td></tr>" +
					"<tr><th>院长</th><td>" + jib.get(i).YUANZ + "</td></tr>" +
					"<tr><th>特色专科</th><td>" + jib.get(i).TESZK + "</td></tr>" +
					"<tr><th>网址</th><td>" + jib.get(i).WANGZ + "</td></tr>" +
					"<tr><th>医院介绍</th><td>" + "<img class=\"clobChecker\" align=\"absmiddle\" src=\"../images/gosearch.gif\" OnClick=searchClob(\"BJWSZY_YIYUAN\",\"ID\",\"YIYJS\",\"" + 
				 		 jib.get(i).ID + 
				 		 "\")></img>" + 
				 		 "查看" + 
			 		 "</td></tr>" +
				"</table></td></tr>");
		}
		
		table.append("</table><br>");
		
		table.insert(0, "<tr valign=\"top\"><td>");
		table.append("</td></tr>");
		
		return table.toString();
	}
}
