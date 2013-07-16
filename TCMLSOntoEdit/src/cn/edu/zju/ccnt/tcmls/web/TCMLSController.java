package cn.edu.zju.ccnt.tcmls.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.edu.zju.ccnt.tcmls.Factory.AccountInstance;
import cn.edu.zju.ccnt.tcmls.Factory.AliasInstance;
import cn.edu.zju.ccnt.tcmls.Factory.BookInstance;
import cn.edu.zju.ccnt.tcmls.Factory.ConceptInstance;
import cn.edu.zju.ccnt.tcmls.Factory.DefinitionInstance;
import cn.edu.zju.ccnt.tcmls.Factory.ExplanationInstance;
import cn.edu.zju.ccnt.tcmls.Factory.RelationInstance;
import cn.edu.zju.ccnt.tcmls.Factory.SmtRelaInstance;
import cn.edu.zju.ccnt.tcmls.Factory.SmtTypeInstance;
import cn.edu.zju.ccnt.tcmls.Model.Cls;
import cn.edu.zju.ccnt.tcmls.Model.DefaultCls;
import cn.edu.zju.ccnt.tcmls.Model.DefaultKnowledgeBase;
import cn.edu.zju.ccnt.tcmls.Model.KnowledgeBase;
import cn.edu.zju.ccnt.tcmls.Model.RootCls;

public class TCMLSController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		KnowledgeBase kb = new DefaultKnowledgeBase();
		response.setCharacterEncoding("UTF-8");
		//获得指定类的子类
		if(type.equals("subclses")){
			ArrayList subClses = null;
			String isRoot = request.getParameter("isRoot");
			if((new Boolean(isRoot)).booleanValue())
				subClses = kb.getRootClses();
			else{
				String clsID = request.getParameter("clsid");
				Cls cls = kb.getCls((new Integer(clsID)).intValue());
				if(null != cls)
					subClses = cls.getDirectSubclasses(true);
			}
			if(null != subClses){
				Element ele = TripleToXML(subClses, "cls", new String[] {"clsID", "clsName", "isChecked"});
				xmlOutput(response, ele);
				response.setContentType("text/xml");
			}
		}
		//获得指定类的实例列表
		else if(type.equals("inslist")){
			Map<String, String> subInses = null;
			String clsID = request.getParameter("clsid");
			Cls cls = kb.getCls((new Integer(clsID)).intValue());
			if(null != cls){
				subInses = cls.getDirectInstance();
			}
			if(null != subInses){
				Element ele = MapToXML(subInses, "ins", false);
				xmlOutput(response, ele);
				response.setContentType("text/xml");
			}
		}
		//创建新的类或者给已有的类重命名
		else if(type.equals("setCls")){
			String clsID = request.getParameter("clsid");
			String clsName = request.getParameter("clsname");
			Cls cls = null;
			//表示该类已经存在
			if((clsID != null) && (!clsID.equals("")) && (!clsID.equals("-1"))){
				cls = new DefaultCls((new Integer(clsID)).intValue());
				if(null != cls){
					cls.setName(clsName);
				}
			}
			//表示新建一个类
			else{
				int fClsID = new Integer(request.getParameter("fclsid")).intValue(); 
				//非根节点
				if(fClsID != 0)
					cls = new DefaultCls(clsName, fClsID);
				//根节点
				else
					cls = new RootCls(clsName);
				xmlOutput(response, IDToXML(cls.getId()));
				response.setContentType("text/xml");
//				response.getWriter().write("<ID>"+ cls.getId() + "</ID>");
			}
		}
		//删除一个指定的类
		else if(type.equals("deleteCls")){
			String clsID = request.getParameter("clsid");
			kb.removeCls((new Integer(clsID)).intValue());
		}
		//删除一个指定的实例
		else if(type.equals("deleteIns")){
			String insID = request.getParameter("insid");
			String clsID = request.getParameter("clsid");
			kb.removeInstance(Integer.parseInt(clsID), (new Integer(insID)).intValue());
		}
		else if(type.equals("insdetail")){
			String insID = request.getParameter("insid");
			ConceptInstance instance = new ConceptInstance((new Integer(insID)).intValue());
			if(null != instance){
				Element ele = instance.toXML();
				xmlOutput(response, ele);
				response.setContentType("text/xml");
			}
		}
		//在指定类下创建或者修改一个新的类
		else if(type.equals("conceptIns")){
			String insID = request.getParameter("insid");
			String ins = request.getParameter("xml");
			//String ins = new String(request.getParameter("xml").getBytes("UTF-8"), "GBK");
//			ins = new String(ins.getBytes("ISO-8859-1"), "UTF-8");
			ConceptInstance instance = null;
			//表示对已有的类进行修改
			if((null != insID) && (!insID.equals("")) && (!insID.equals("-1"))){
				instance = new ConceptInstance((new Integer(insID)).intValue());
				instance.modify(ins);
			}
			//表示新建一个类
			else{
				String clsID = request.getParameter("clsid");
				instance = new ConceptInstance((new Integer(clsID)).intValue(), ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取语义类型列表
		else if(type.equals("typelist")){
			Map<String, String> types = kb.getAllSmtTypes();
			Element ele = MapToXML(types, "type", false);
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}
//		获取用户列表
		else if(type.equals("userlist")){
			Map<String, String> users = kb.getAllUsers();
			Element ele = MapToXML(users, "User", false);
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}
		//获取语义关系列表
		else if(type.equals("relalist")){
			Map<String, String> relas = kb.getAllSmtRelas();
			Element ele = MapToXML(relas, "rela", false);
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}
		//获取工具书列表
		else if(type.equals("booklist")){
			Map<String, String> books = kb.getAllBooks(true);
			Element ele = MapToXML(books, "book", true);
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}
		//创建或者修改一个新的概念定义
		else if(type.equals("defIns")){
			String defID = request.getParameter("defid");
			String ins = request.getParameter("xml");
			DefinitionInstance instance = null;
			//修改
			if((null != defID) && (!defID.equals("-1")) && (!defID.equals(""))){
				instance = new DefinitionInstance((new Integer(defID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new DefinitionInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取概念定义实例
		else if(type.equals("def")){
			String defID = request.getParameter("defid");
			DefinitionInstance instance = new DefinitionInstance((new Integer(defID)).intValue());
			if(null != instance){
				Element ele = instance.toXML();
				xmlOutput(response, ele);
				response.setContentType("text/xml");
			}
		}
		//创建或者修改一个新的概念释义
		else if(type.equals("expIns")){
			String expID = request.getParameter("expid");
			String ins = request.getParameter("xml");
			ExplanationInstance instance = null;
			//修改
			if((null != expID) && (!expID.equals(""))&& (!expID.equals("-1"))){
				instance = new ExplanationInstance((new Integer(expID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new ExplanationInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取概念释义实例
		else if(type.equals("exp")){
			String expID = request.getParameter("expid");
			ExplanationInstance instance = new ExplanationInstance((new Integer(expID)).intValue());
			if(null != instance){
				Element ele = instance.toXML();
				xmlOutput(response, ele);
				response.setContentType("text/xml");
			}
		}
		//创建或者修改一个新的各种相关概念词
		else if(type.equals("ceptsIns")){
			String ceptsID = request.getParameter("ceptsid");
			String ins = request.getParameter("xml");
			RelationInstance instance = null;
			//修改
			if((null != ceptsID) && (!ceptsID.equals("")) && (!ceptsID.equals("-1"))){
				instance = new RelationInstance((new Integer(ceptsID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new RelationInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取各种相关概念词实例
		else if(type.equals("cepts")){
			String ceptsID = request.getParameter("ceptsid");
			RelationInstance instance = new RelationInstance((new Integer(ceptsID)).intValue());
			if(null != instance){
				Element ele = instance.toXML();
				xmlOutput(response, ele);
				response.setContentType("text/xml");
			}
		}
		//创建或者修改一个新的名称字符类
		else if(type.equals("aliasIns")){
			String aliasID = request.getParameter("aliasid");
			String ins = request.getParameter("xml");
			AliasInstance instance = null;
			//修改
			if((null != aliasID) && (!aliasID.equals("")) && (!aliasID.equals("-1"))){
				instance = new AliasInstance((new Integer(aliasID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new AliasInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取名称字符类的实例
		else if(type.equals("alias")){
			String aliasID = request.getParameter("aliasid");
			AliasInstance instance = new AliasInstance((new Integer(aliasID)).intValue());
			if(null != instance){
				Element ele = instance.toXML();
				xmlOutput(response, ele);
				response.setContentType("text/xml");
			}
		}
		//实例查找
		else if(type.equals("insSrh")){
			String ins = request.getParameter("ins");
			String isFuzzy = request.getParameter("isFuzzy");
			Map<Integer, String> result = null;
			if((null != isFuzzy) && (isFuzzy.equals("精确")))
				result = kb.insSrh(ins);
			else if((null != isFuzzy) && (isFuzzy.equals("模糊")))
				result = kb.fuzzySearch(ins);
			
			Element root = MapToXML(result);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		//统计一体化语言系统收录的词条数目
		else if(type.equals("statistic")){
			int statistic = kb.getStatistic();
			Element root = IDToXML(statistic);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		//统计“已加工”和“已校验”的词条数目
		else if(type.equals("typeSrh")){
			Element root = null; 
			int statistic = 0;
			String srhType = request.getParameter("srhType");
			if(srhType.equals("已加工")){
				statistic = kb.getEdittedStatistic();
				root = IDToXML(statistic);
			}
			else if(srhType.equals("已校验")){
				statistic = kb.getCheckedStatistic();
				root = IDToXML(statistic);
			}
			else if(srhType.equals("按地区")){
	//			root = MapToXML(kb.affiliStatistic(), "Affili");
				root = TripleToXML(kb.affiliStatistic(), "Affili", new String[] {"Affili", "AffiliCount", "AffiliChecked"});
			}			
			else if(srhType.equals("按用户")){
	//			root = MapToXML(kb.accountStatistic(), "User");
				root = TripleToXML(kb.accountStatistic(), "User", new String[] {"User", "UserCount", "UserChecked"});
			}
			xmlOutput(response, root);
			response.setContentType("text/xml");			
		}
		//导航相关概念词
		else if(type.equals("navi")){
			String ins = request.getParameter("insName");
			Map<Integer, String> result = kb.getRelatedInstance(ins);
			Element root = MapToXML(result);
			xmlOutput(response, root);
			response.setContentType("text/xml");			
		}
		//创建或者修改一个新的帐户
		else if(type.equals("accountIns")){
			String accountID = request.getParameter("insID");
			String ins = request.getParameter("xml");
			AccountInstance instance = null;
			//修改
			if((null != accountID) && (!accountID.equals("")) && (!accountID.equals("null")) && (!accountID.equals("-1"))){
				instance = new AccountInstance((new Integer(accountID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new AccountInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取帐号
		else if(type.equals("account")){
			Element ele = null;
			String user = request.getParameter("username");
			if((null != user) && (!user.equals(""))){
				List<Integer> result = AccountInstance.accountSearch(user);
				if(result.size() > 0){
					AccountInstance instance = new AccountInstance(result.get(0));
					if(null != instance)
						ele = instance.toXML();
					else
						ele = IDToXML(-1);
				}
				else
					ele = IDToXML(-1);
			}
			else{
				user = request.getParameter("userid");
				AccountInstance instance = new AccountInstance(new Integer(user).intValue());
				if(null != instance)
					ele = instance.toXML();
				else
					ele = IDToXML(-1);
			}
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}
//		创建或者修改一个新的语义类型
		else if(type.equals("typeIns")){
			String typeID = request.getParameter("insID");
			String ins = request.getParameter("xml");
			SmtTypeInstance instance = null;
			//修改
			if((null != typeID) && (!typeID.equals("")) && (!typeID.equals("null")) && (!typeID.equals("-1"))){
				instance = new SmtTypeInstance((new Integer(typeID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new SmtTypeInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取语义类型
		else if(type.equals("type")){
			Element ele = null;
			String typename = request.getParameter("typename");
			if((null != typename) && (!typename.equals(""))){
				List<Integer> result = SmtTypeInstance.typeSearch(typename);
				if(result.size() > 0){
					SmtTypeInstance instance = new SmtTypeInstance(result.get(0));
					if(null != instance)
						ele = instance.toXML();
					else
						ele = IDToXML(-1);
				}
				else
					ele = IDToXML(-1);
			}
			else{
				typename = request.getParameter("typeid");
				SmtTypeInstance instance = new SmtTypeInstance(new Integer(typename).intValue());
				if(null != instance)
					ele = instance.toXML();
				else
					ele = IDToXML(-1);
			}
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}
//		创建或者修改一个新的语义关联
		else if(type.equals("relaIns")){
			String relaID = request.getParameter("insID");
			String ins = request.getParameter("xml");
			SmtRelaInstance instance = null;
			//修改
			if((null != relaID) && (!relaID.equals("")) && (!relaID.equals("null")) && (!relaID.equals("-1"))){
				instance = new SmtRelaInstance((new Integer(relaID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new SmtRelaInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取语义关联
		else if(type.equals("rela")){
			Element ele = null;
			String relaname = request.getParameter("relaname");
			if((null != relaname) && (!relaname.equals(""))){
				List<Integer> result = SmtRelaInstance.relaSearch(relaname);
				if(result.size() > 0){
					SmtRelaInstance instance = new SmtRelaInstance(result.get(0));
					if(null != instance)
						ele = instance.toXML();
					else
						ele = IDToXML(-1);
				}
				else
					ele = IDToXML(-1);
			}
			else{
				relaname = request.getParameter("relaid");
				SmtRelaInstance instance = new SmtRelaInstance(new Integer(relaname).intValue());
				if(null != instance)
					ele = instance.toXML();
				else
					ele = IDToXML(-1);
			}
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}

//		创建或者修改一个新的工具书
		else if(type.equals("bookIns")){
			String bookID = request.getParameter("insID");
			String ins = request.getParameter("xml");
			BookInstance instance = null;
			//修改
			if((null != bookID) && (!bookID.equals("")) && (!bookID.equals("null")) && (!bookID.equals("-1"))){
				instance = new BookInstance((new Integer(bookID)).intValue());
				instance.modify(ins);
			}
			//新创建
			else{
				instance = new BookInstance(ins);
				xmlOutput(response, IDToXML(instance.getId()));
				response.setContentType("text/xml");
			}
		}
		//获取工具书
		else if(type.equals("book")){
			Element ele = null;
			String book = request.getParameter("bookname");
			if((null != book) && (!book.equals(""))){
				List<Integer> result = BookInstance.bookSearch(book);
				if(result.size() > 0){
					BookInstance instance = new BookInstance(result.get(0));
					if(null != instance)
						ele = instance.toXML();
					else
						ele = IDToXML(-1);
				}
				else
					ele = IDToXML(-1);
			}
			else{
				book = request.getParameter("bookid");
				BookInstance instance = new BookInstance(new Integer(book).intValue());
				if(null != instance)
					ele = instance.toXML();
				else
					ele = IDToXML(-1);
			}
			xmlOutput(response, ele);
			response.setContentType("text/xml");
		}
		//类树的调整
		else if(type.equals("adjust")){
			String dest = request.getParameter("dest");
			String current = request.getParameter("current");
			if((null != dest) && (null != current)){
				Cls cls  = new DefaultCls(new Integer(current).intValue());
				cls.changeSuperCls(new DefaultCls(new Integer(dest).intValue()));
			}
		}
		//类树路径跟踪
		else if(type.equals("clsTrack")){
			String id = request.getParameter("id");
			String track = kb.clsTrack(new Integer(id).intValue());
			Element root = IDToXML(track);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		//统计具体类目下加工词条和校验词条数，三个合并统计，（类目数，加工的实例数，校验的实例数）
		else if(type.equals("clsStat")){
			String id = request.getParameter("id");
			Cls cls = new DefaultCls(new Integer(id).intValue());
			int result = cls.getInstanceCount();
			String res = "共加工词条：" + result; 
			result = cls.getCheckedInstanceCount();
			res += ("；共校验词条：" + result);
			Element root = IDToXML(res);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		else if(type.equals("clsCheck")){
			String cls = request.getParameter("clsname");
			boolean isExist = kb.isClsExist(cls);
			Element root = IDToXML(""+isExist);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		//实例调整位置
		else if(type.equals("insAdjust")){
			String insID = request.getParameter("current");
			String clsID = request.getParameter("dest");
			kb.moveInstance(new Integer(insID).intValue(), new Integer(clsID).intValue());
		}
		//枚举在特定时期特定状态由某一个加工人员加工的实例列表
		else if(type.equals("enum")){
			String date = request.getParameter("date");
			String status = request.getParameter("status");
			String editor = request.getParameter("editor");
			String affili = request.getParameter("affili");
			Map<Integer, String> list = kb.getStatisticList(date, status, editor, affili);
			Element root = MapToXML(list);
			xmlOutput(response, root);
			response.setContentType("text/xml");			
		}
		else if(type.equals("statis")){
			String date = request.getParameter("date");
			String status = request.getParameter("status");
			String editor = request.getParameter("editor");
			String affili = request.getParameter("affili");
			int count = kb.getSpecificStatistic(date, status, editor, affili);
			Element root = IDToXML(count);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		//获得所有帐号的信息
		else if(type.equals("accountinfo")){
			String affili = request.getParameter("affili");
			String user = request.getParameter("user");
			List<String> result = null;
			if((null !=  affili) && (!affili.equals("")))
				result = kb.getAllAffiliations();
			else if((null != user) && (!user.equals("")))
				result = kb.getAllColleague(user);
			Element root = ListToXML(result);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		else if(type.equals("insExist")){
			String ins = request.getParameter("ins");
			Map<Integer, String> result = kb.getSameNameInstance(ins);
			Element root = MapToXML(result);
			xmlOutput(response, root);
			response.setContentType("text/xml");
		}
		else if(type.equals("dupIns")){
			String insID = request.getParameter("insID");
			String clsID = request.getParameter("clsID");
			kb.createDupInstance(Integer.parseInt(clsID), Integer.parseInt(insID));
		}
		return null;
	}

	private Element MapToXML(Map<String, Integer> map, String type) {
		Element root = new Element("TCMLS");
		root.setAttribute("Type", type);
		for(Iterator<String> it=map.keySet().iterator(); it.hasNext(); ){
			String key = it.next();
			if(!key.endsWith("Tip")){
				Integer value = map.get(key);
				Element e1 = new Element(type).addContent(key);
				root.addContent(e1);
				Element e2 = new Element(type+"Count").addContent(""+value);
				root.addContent(e2);
			}
		}
		return root;
	}

	private Element MapToXML(Map<String, String> map, String pre, boolean isTips) throws NumberFormatException, SQLException{
		Element root = new Element("TCMLS");
		root.setAttribute("Type", pre);
		for(Iterator<String> it=map.keySet().iterator(); it.hasNext(); ){
			String key = it.next();
			if(!key.endsWith("Tip")){
				String value = map.get(key);
				Element e1 = new Element(pre+"ID").addContent(key);
				root.addContent(e1);
				Element e2 = new Element(pre+"Name").addContent(value);
				root.addContent(e2);
				if(isTips){
					Element e3 = new Element(pre + "Tip").addContent(map.get(key+"Tip"));
					root.addContent(e3);
				}
			}
		}
		return root;
	}
	
	public Element MapToXML(Map<Integer, String> srhResults){
		Element result = new Element("TCMLS");
		Element ele = null;
		if((null != srhResults) && (!srhResults.isEmpty())){
			for(Iterator<Integer> it = srhResults.keySet().iterator(); it.hasNext(); ){
				int id = it.next();
				String value = srhResults.get(id);
				ele = new Element("Instance").addContent(value);
				ele.setAttribute("ID", ""+id);
				result.addContent(ele);
			}
		}
		return result;
	}
		
	private void xmlOutput(HttpServletResponse response, Element ele) throws IOException{
		Document tcmls = new Document(ele);
		XMLOutputter outputter = new XMLOutputter();
		/*		outputter.setFormat(outputter.getFormat().setEncoding("GBK"));
		outputter.output(tcmls, System.out);*/
		outputter.setFormat(outputter.getFormat().setEncoding("UTF-8"));
	    outputter.output(tcmls, response.getWriter());
	}
	
	private Element IDToXML(int id){
		Element root = new Element("TCMLS");
		Element ele = new Element("ID").addContent(""+id);
		root.addContent(ele);
		return root;
	}
	
	private Element IDToXML(String id){
		Element root = new Element("TCMLS");
		Element ele = new Element("ID").addContent(""+id);
		root.addContent(ele);
		return root;
	}
	
	//<Key, Edited, Checked> or <ID, Name, isChecked>
	private Element TripleToXML(ArrayList result, String pre, String[] pres){
		Element root = new Element("TCMLS");
		root.setAttribute("Type", pre);
		for(int i = 0; i < result.size(); i++){
			ArrayList unit = (ArrayList) result.get(i);
			Element e1 = new Element(pres[0]).addContent(""+unit.get(0));
			root.addContent(e1);
			Element e2 = new Element(pres[1]).addContent(""+unit.get(1));
			root.addContent(e2);
			Element e3 = new Element(pres[2]).addContent(""+unit.get(2));
			root.addContent(e3);
		}
		return root;
	}
	
	private Element ListToXML(List<String> list){
		Element root = new Element("TCMLS");
		for(Iterator it = list.iterator(); it.hasNext(); ){
			root.addContent(new Element("item").addContent((String)it.next()));
		}
		return root;
	}
}
