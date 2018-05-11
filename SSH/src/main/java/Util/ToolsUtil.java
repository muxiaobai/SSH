package com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ToolsUtil {
	public  static String getJSONValue(JSONObject json,String key){
		if(json==null||key==null){
			return "";
		}
		if(json.has(key)){
			return  json.has(key)?json.getString(key):""; 
		}else{
			if(json.has(key.toUpperCase())){
				return  json.has(key.toUpperCase())?json.getString(key.toUpperCase()):""; 
			}else{
				if(json.has(key.toLowerCase())){
					return  json.has(key.toLowerCase())?json.getString(key.toLowerCase()):""; 
				}else{
					return "";
				}
			}
		}
	}
	public  static JSONObject getJSONObject(JSONObject json,String key){
		if(json==null||key==null){
			return null;
		}
		if(json.has(key)){
			return  json.has(key)?json.getJSONObject(key):new JSONObject(); 
		}else{
			if(json.has(key.toUpperCase())){
				return  json.has(key.toUpperCase())?json.getJSONObject(key.toUpperCase()):new JSONObject();
			}else{
				if(json.has(key.toLowerCase())){
					return  json.has(key.toLowerCase())?json.getJSONObject(key.toLowerCase()):new JSONObject(); 
				}else{
					return null;
				}
			}
		}
	}
	public  static JSONArray getJSONArray(JSONObject json,String key){
		if(json==null||key==null){
			return null;
		}
		if(json.has(key)){
			return  json.has(key)?json.getJSONArray(key):new JSONArray(); 
		}else{
			if(json.has(key.toUpperCase())){
				return  json.has(key.toUpperCase())?json.getJSONArray(key.toUpperCase()):new JSONArray();
			}else{
				if(json.has(key.toLowerCase())){
					return  json.has(key.toLowerCase())?json.getJSONArray(key.toLowerCase()):new JSONArray(); 
				}else{
					return null;
				}
			}
		}
	}
	public static Object JSONtoBean(JSONObject json, String bean){
		if(json==null||bean==null){
			return null;
		}
		Object resultObject = null ;
		try {
			Class clazz =Class.forName(bean);
			resultObject =  clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if(fields[i].getType().getName().equals("java.util.List")){
					if(fields[i].getName().equals("FORMS")){
						invokeSet(resultObject,fields[i].getName(),
								JSONtoList(getJSONArray(json,fields[i].getName()),"com.tjsoft.tysl.model.Form"));
					}
					if(fields[i].getName().equals("FILES")){
						invokeSet(resultObject,fields[i].getName(),
								JSONtoList(getJSONArray(json,fields[i].getName()),"com.tjsoft.tysl.model.File"));
					}
					if(fields[i].getName().equals("ZJSDXX")){
						invokeSet(resultObject,fields[i].getName(),
								JSONtoList(getJSONArray(json,fields[i].getName()),"com.tjsoft.tysl.model.Zjsdxx"));
					}
					if(fields[i].getName().equals("CERTINFO")){
						invokeSet(resultObject,fields[i].getName(),
								JSONtoList(getJSONArray(json,fields[i].getName()),"com.tjsoft.tysl.model.CertInfo"));
					}
					
					continue;
				}else if(fields[i].getType().getPackage().getName().equals("com.tjsoft.tysl.model")){
					invokeSet(resultObject,fields[i].getName(),
							JSONtoBean(getJSONObject(json,fields[i].getName()),fields[i].getType().getName()));
					continue;	
				}else{
					if("ID".equals(fields[i].getName())){
						invokeSet(resultObject,fields[i].getName(),CTools.getNewID());
					}else{
						invokeSet(resultObject,fields[i].getName(),getJSONValue(json,fields[i].getName()));
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	  
	public static Method getSetMethod(Class objectClass, String fieldName) {
	    try {       
	        Class[] parameterTypes = new Class[1];       
	        Field field = objectClass.getDeclaredField(fieldName);       
	        parameterTypes[0] = field.getType();       
	        StringBuffer sb = new StringBuffer();       
	        sb.append("set").append(fieldName);
	        Method method = objectClass.getMethod(sb.toString(), parameterTypes);       
	        return method;       
	    } catch (Exception e) {       
	        e.printStackTrace();       
	    }       
	    return null;       
	}
	public static void invokeSet(Object o, String fieldName, Object value) {       
	    Method method = getSetMethod(o.getClass(), fieldName);       
	    try {       
	        method.invoke(o, new Object[] { value });       
	    } catch (Exception e) {       
	        e.printStackTrace();       
	    }
	}
	public static java.util.List JSONtoList(JSONArray json, String bean){
		java.util.List result = new ArrayList();
		if(json.size()>0){
		 for(int i=0 ; i < json.size() ;i++){
		    //获取每一个JsonObject对象
		    JSONObject myjObject = json.getJSONObject(i);
		    result.add(JSONtoBean(myjObject, bean));
		}}
		return result;
	}
	public static Map<String, Object> getBean(String jsonStr,Map<String, String> beanmap){
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject json = JSONObject.fromObject(jsonStr);
        Set set =  beanmap.entrySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            Map.Entry<String, String> iter = ( Map.Entry) iterator.next();
            //object
            JSONObject jsonbean =  getJSONObject(json,iter.getKey()); 
            if(jsonbean!=null){
                Object object =JSONtoBean(jsonbean, iter.getValue());
                System.out.println(object);
                result.put(iter.getKey(), object);
            }
            //array
            JSONArray jsonSqclxssc =getJSONArray(json,iter.getKey());
            List Sqclxssc= JSONtoList(jsonSqclxssc, iter.getValue());
            if(jsonbean!=null){
                Object object =JSONtoBean(jsonbean, iter.getValue());
                System.out.println(object);
                result.put(iter.getKey(), object);
            }
        }
//        JSONObject jsonProcessNodeInfo =getJSONObject(json,"PROCESSNODEINFO");
//        ProcessNodeInfo processNodeInfo =(ProcessNodeInfo) JSONtoBean(jsonProcessNodeInfo,"com.tjsoft.tysl.model.ProcessNodeInfo");
//        System.out.println(processNodeInfo);
//        JSONObject jsonApplication =getJSONObject(json,"Application");
//        TInsBusinessinfoSqdj tInsBusinessinfoSqdj =(TInsBusinessinfoSqdj) JSONtoBean(jsonApplication,"com.tjsoft.tysl.model.TInsBusinessinfoSqdj");
//        System.out.println(tInsBusinessinfoSqdj);
//        JSONArray jsonmaterials =getJSONArray(json,"MATERIALS");
//        java.util.List materials= JSONtoList(jsonmaterials, "com.tjsoft.tysl.model.Material");
//        System.out.println(materials);
//        JSONArray jsonstandards =getJSONArray(json,"STANDARDS");
//        java.util.List Standard= JSONtoList(jsonstandards, "com.tjsoft.tysl.model.Standard");
//        System.out.println(Standard);
//        
//        JSONArray jsonSqclxssc =getJSONArray(json,"SQCLXSSC");
//        java.util.List Sqclxssc= JSONtoList(jsonSqclxssc, "com.tjsoft.tysl.model.Sqclxssc");
//        System.out.println(Sqclxssc);
//        
//        JSONObject jsonCheckinforegist =getJSONObject(json,"checkinforegist");
//        Checkinforegist Checkinforegist= (Checkinforegist) JSONtoBean(jsonCheckinforegist, "com.tjsoft.tysl.model.Checkinforegist");
//        System.out.println(Checkinforegist);
//        
//        JSONArray jsonSubcontext =getJSONArray(json,"SUBCONTEXTS");
//        java.util.List Subcontext= JSONtoList(jsonSqclxssc, "com.tjsoft.tysl.model.Subcontext");
//        System.out.println(Subcontext);
//        
//        JSONObject jsonZjsdqs =getJSONObject(json,"ZJSDQS");
//        Zjsdqs zjsdqs= (Zjsdqs)JSONtoBean(jsonZjsdqs, "com.tjsoft.tysl.model.Zjsdqs");
//        System.out.println(zjsdqs);
//
//        JSONObject jsonCertinforegist=getJSONObject(json, "CERTINFOREGIST");//getJSONObject(json,"certinforegist");
//        Certinforegist Certinforegist= (Certinforegist) JSONtoBean(jsonCertinforegist, "com.tjsoft.tysl.model.Certinforegist");
//        System.out.println(Certinforegist);
//        
//        
//        JSONArray jsonCensor =getJSONArray(json,"CENSOR");
//        java.util.List censor= JSONtoList(jsonCensor,"com.tjsoft.tysl.model.Censor");
//        System.out.println(censor);
//
//        JSONObject jsonIssueinfo=getJSONObject(json,"ISSUEINFO");
//        IssueInfo issueinfo= (IssueInfo) JSONtoBean(jsonIssueinfo, "com.tjsoft.tysl.model.IssueInfo");
//        System.out.println(issueinfo);
//        
//        JSONObject jsonycscinfo=getJSONObject(json,"YCSCINFO");
//        YcscInfo ycscinfo= (YcscInfo) JSONtoBean(jsonycscinfo, "com.tjsoft.tysl.model.YcscInfo");
//        System.out.println(ycscinfo);
//        
//        JSONObject jsonforminfo =getJSONObject(json,"FORMINFO");
//        FormInfo forminfo= (FormInfo) JSONtoBean(jsonforminfo, "com.tjsoft.tysl.model.FormInfo");
//        System.out.println(forminfo);
//        JSONObject jsonnodes =getJSONObject(json,"Nodes");
//        Nodes nodes= (Nodes) JSONtoBean(jsonnodes, "com.tjsoft.tysl.model.Nodes");
//        System.out.println(nodes);
        return result;
    }
	public static void main(String[] args) {
//		String jsonStr = "{\"PROCESSNODEINFO\":{\"processnum\":\"统一业务流水号\",\"Yxtywnum\":\"原系统业务流水号\",\"sourcetype\":\"1\",\"SSSXBM\":\"实施事项编码\",\"SSSXMC\":\"实施事项名称\",\"SSXXYWQXBM\":\"业务情形编号\",\"SSXXYWQXMC\":\"业务情形名称\",\"prevnodeactor\":\"上环节经办人\",\"prevnodeidea\":\"上环节办理意见\",\"handlerdate\":\"2018-02-12 12:03:11\",\"sxtimelimit\":1,\"sxlimitunit\":\"业务情形时限单位\",\"nodetimelimit\":1,\"nodelimitunit\":\"节点时限单位\",\"nodessgzr\":10,\"nodeactor\":\"本环节经办人\",\"AREA\":\"2\",\"HJJBCLX\":\"环节经办处类型码\",\"HJJBC_CODE\":\"环节经办处编号\",\"DEPARTMENTLX\":\"1\",\"DEPARTMENTID\":\"申请人申报事项所在单位代码编号\",\"COUNTER_CODE\":\"窗口编号,如果HJJBCLX填写了大厅编码，则该字段为必填\",\"SBLX\":\"1\",\"SBH\":\"申报号\",\"SJBBH\":\"数据版本号\",\"SXBBH\":\"事项版本号\",\"ZTLX\":\"1\",\"ZTSSHYLX\":\"主题所属行业类型\",\"ZTMC\":\"主题名称\",\"TZJSJDLX\":\"1\"},\"Application\":{\"sqrlx\":\"2\",\"sqrmc\":\"申请人名称\",\"sqrsfzmwj\":\"2\",\"sqrzjlxmc\":\"申请人证件类型名称\",\"cardid\":\"申请人证件号码\",\"sqrlxdh\":\"申请人联系电话\",\"sqrlxdz\":\"申请人联系地址\",\"fddbrxm\":\"法定代表人姓名\",\"fddbrzjlx\":\"2\",\"Fddbrzjlxmc\":\"其他法定代表人证件类型名称\",\"fddbrzjhm\":\"法定代表人证件号码\",\"sfwt\":\"是否委托\",\"JYZXM\":\"经营者姓名\",\"JYZZJLX\":\"1\",\"QTJYZZJLXMC\":\"其他经营者证件类型名称\",\"JYZZJHM\":\"经营者证件号码\",\"WTRXM\":\"委托人姓名\",\"WTRYX\":\"委托人邮箱\",\"WTRZJLX\":\"2\",\"WTRZJLXMC\":\"委托人证件类型名称\",\"WTRZJHM\":\"委托人证件号码\",\"WTRLXDH\":\"15088132571\",\"WTRLXDZ\":\"受委托联系地址\",\"SDFS\":\"送达方式\",\"postalcode\":\"邮政编码\",\"sddz\":\"送达地址\"},\"MATERIALS\":[{\"ID\":\"材料实例编号\",\"NO\":\"序号\",\"MATERIALID\":\"材料ID\",\"CODE\":\"材料目录编码\",\"MATERIALNAME\":\"材料名称\",\"STANDARD\":\"接件标准\",\"ORIGINAL\":\"原件(份)\",\"COPY\":\"复印件\",\"SFFH\":\"(审核)是否符合\",\"ISCLBZ\":\"是否材料补正\",\"CLBZYJ\":\"材料补正意见\",\"forms\":[{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"},{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"}],\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"材料实例编号\",\"NO\":\"序号\",\"MATERIALID\":\"材料ID\",\"CODE\":\"材料目录编码\",\"MATERIALNAME\":\"材料名称\",\"STANDARD\":\"接件标准\",\"ORIGINAL\":\"原件(份)\",\"COPY\":\"复印件\",\"SFFH\":\"(审核)是否符合\",\"ISCLBZ\":\"是否材料补正\",\"CLBZYJ\":\"材料补正意见\",\"forms\":[{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"},{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"}],\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"STANDARDS\":[{\"ID\":\"裁量id\",\"NO\":\"序号\",\"CLBZ\":\"裁量标准\",\"SFFH\":\"1\"},{\"ID\":\"裁量id\",\"NO\":\"序号\",\"CLBZ\":\"裁量标准\",\"SFFH\":\"1\"}],\"FORMS\":[{\"nodeid\":\"环节ID\",\"datajson\":\"表单json\",\"formid\":\"表单id\",\"formtype\":\"表单类型\",\"formver\":\"表单版本\",\"BDSLID\":\"表单实例id\"},{\"nodeid\":\"环节ID\",\"datajson\":\"表单json\",\"formid\":\"表单id\",\"formtype\":\"表单类型\",\"formver\":\"表单版本\",\"BDSLID\":\"表单实例id\"}],\"SQCLXSSC\":[{\"ID\":\"审查ID\",\"NO\":\"序号\",\"SCNR\":\"审查内容\",\"CONDITION\":\"条件要求\",\"STANDARD\":\"审查裁量标准\",\"MATERIALID\":\"审查材料ID\",\"MATERIALNAME\":\"材料名称\",\"SFFH\":\"1\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"审查ID\",\"NO\":\"序号\",\"SCNR\":\"审查内容\",\"CONDITION\":\"条件要求\",\"STANDARD\":\"审查裁量标准\",\"MATERIALID\":\"审查材料ID\",\"MATERIALNAME\":\"材料名称\",\"SFFH\":\"1\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"checkinforegist\":{\"HCFS\":\"1\",\"QTHCFS\":\"其他核查方式\",\"INSPECTORS\":[{\"NAME\":\"姓名\",\"XZZFZH\":\"行政执法证号\"},{\"NAME\":\"姓名\",\"XZZFZH\":\"行政执法证号\"}],\"HCSJ\":\"2018-02-12 12:03:11\",\"CSWZ\":\"现场核查场所位置\",\"TZSDFS\":\"2\",\"QTTZSDFS\":\"其他现场核实通知方式\",\"NOTICEDATA\":\"2018-02-12 12:03:11\"},\"SUBCONTEXTS\":[{\"ID\":\"实质内容核查id\",\"NO\":\"序号\",\"HCNR\":\"核查内容\",\"HCMD\":\"核查目的\",\"PDBZ\":\"判定标准\",\"HCFS\":\"1\",\"SFFH\":\"是否符合\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"实质内容核查id\",\"NO\":\"序号\",\"HCNR\":\"核查内容\",\"HCMD\":\"核查目的\",\"PDBZ\":\"判定标准\",\"HCFS\":\"1\",\"SFFH\":\"是否符合\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"YWJGWB\":{\"NRHC\":\"1\",\"INFOS\":[{\"ID\":\"文本id\",\"NO\":\"序号\",\"NAME\":\"文书名称\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"文本id\",\"NO\":\"序号\",\"NAME\":\"文书名称\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}]},\"FORMINFO\":{\"SQSLYJ\":\"受理审核意见\",\"SQSLJG\":\"1\",\"SQSLSCHZ\":\"受理审核回执\",\"TSCX\":\"1\",\"receipt_file\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},\"certificate_file\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FJSLID\":\"物料流转实例ID\",\"FILEPATH\":\"附件路径\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}},\"Nodes\":{\"LID\":\"UUID诚信公司制定的关于各区报送数据的规则\",\"NODENAME\":\"环节名称\",\"NODEPROPERTY\":\"1\",\"DEPARTMENT\":\"部门组织机构代码\",\"HANDLERDATE\":\"2018-02-12 12:03:11\",\"NEXTNODE\":\"下一个环节名称\",\"NEXTNODEPROPERTY\":\"2\",\"NEXTHANDLER\":\"下一环节处理人姓名\"},\"DZD\":\"大字段\"}";
		String jsonStr = "{\"PROCESSNODEINFO\":{\"processnum\":\"统一业务流水号\",\"Yxtywnum\":\"原系统业务流水号\",\"sourcetype\":\"1\",\"SSSXBM\":\"实施事项编码\",\"SSSXMC\":\"实施事项名称\",\"SSXXYWQXBM\":\"业务情形编号\",\"SSXXYWQXMC\":\"业务情形名称\",\"prevnodeactor\":\"上环节经办人\",\"prevnodeidea\":\"上环节办理意见\",\"handlerdate\":\"2018-02-12 12:03:11\",\"sxtimelimit\":1,\"sxlimitunit\":\"业务情形时限单位\",\"nodetimelimit\":1,\"nodelimitunit\":\"节点时限单位\",\"nodessgzr\":10,\"nodeactor\":\"本环节经办人\",\"AREA\":\"2\",\"HJJBCLX\":\"环节经办处类型码\",\"HJJBC_CODE\":\"环节经办处编号\",\"DEPARTMENTLX\":\"1\",\"DEPARTMENTID\":\"申请人申报事项所在单位代码编号\",\"COUNTER_CODE\":\"窗口编号,如果HJJBCLX填写了大厅编码，则该字段为必填\",\"SBLX\":\"1\",\"SBH\":\"申报号\",\"SJBBH\":\"数据版本号\",\"SXBBH\":\"事项版本号\",\"ZTLX\":\"1\",\"ZTSSHYLX\":\"主题所属行业类型\",\"ZTMC\":\"主题名称\",\"TZJSJDLX\":\"1\"},\"Application\":{\"sqrlx\":\"2\",\"sqrmc\":\"申请人名称\",\"sqrsfzmwj\":\"2\",\"sqrzjlxmc\":\"申请人证件类型名称\",\"cardid\":\"申请人证件号码\",\"sqrlxdh\":\"申请人联系电话\",\"sqrlxdz\":\"申请人联系地址\",\"fddbrxm\":\"法定代表人姓名\",\"fddbrzjlx\":\"2\",\"Fddbrzjlxmc\":\"其他法定代表人证件类型名称\",\"fddbrzjhm\":\"法定代表人证件号码\",\"sfwt\":\"是否委托\",\"JYZXM\":\"经营者姓名\",\"JYZZJLX\":\"1\",\"QTJYZZJLXMC\":\"其他经营者证件类型名称\",\"JYZZJHM\":\"经营者证件号码\",\"WTRXM\":\"委托人姓名\",\"WTRYX\":\"委托人邮箱\",\"WTRZJLX\":\"2\",\"WTRZJLXMC\":\"委托人证件类型名称\",\"WTRZJHM\":\"委托人证件号码\",\"WTRLXDH\":\"15088132571\",\"WTRLXDZ\":\"受委托联系地址\",\"SDFS\":\"送达方式\",\"postalcode\":\"邮政编码\",\"sddz\":\"送达地址\"},\"MATERIALS\":[{\"ID\":\"材料实例编号\",\"NO\":\"序号\",\"MATERIALID\":\"材料ID\",\"CODE\":\"材料目录编码\",\"MATERIALNAME\":\"材料名称\",\"STANDARD\":\"接件标准\",\"ORIGINAL\":\"原件(份)\",\"COPY\":\"复印件\",\"SFFH\":\"(审核)是否符合\",\"ISCLBZ\":\"是否材料补正\",\"CLBZYJ\":\"材料补正意见\",\"forms\":[{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"},{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"}],\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"材料实例编号\",\"NO\":\"序号\",\"MATERIALID\":\"材料ID\",\"CODE\":\"材料目录编码\",\"MATERIALNAME\":\"材料名称\",\"STANDARD\":\"接件标准\",\"ORIGINAL\":\"原件(份)\",\"COPY\":\"复印件\",\"SFFH\":\"(审核)是否符合\",\"ISCLBZ\":\"是否材料补正\",\"CLBZYJ\":\"材料补正意见\",\"forms\":[{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"},{\"BDID\":\"表单ID\",\"BDMC\":\"表单名称\",\"BDDLID\":\"表单实例ID\",\"CONTENT\":\"表单内容\"}],\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"STANDARDS\":[{\"ID\":\"裁量id\",\"NO\":\"序号\",\"CLBZ\":\"裁量标准\",\"SFFH\":\"1\"},{\"ID\":\"裁量id\",\"NO\":\"序号\",\"CLBZ\":\"裁量标准\",\"SFFH\":\"1\"}],\"FORMS\":[{\"nodeid\":\"环节ID\",\"datajson\":\"表单json\",\"formid\":\"表单id\",\"formtype\":\"表单类型\",\"formver\":\"表单版本\",\"BDSLID\":\"表单实例id\"},{\"nodeid\":\"环节ID\",\"datajson\":\"表单json\",\"formid\":\"表单id\",\"formtype\":\"表单类型\",\"formver\":\"表单版本\",\"BDSLID\":\"表单实例id\"}],\"SQCLXSSC\":[{\"ID\":\"审查ID\",\"NO\":\"序号\",\"SCNR\":\"审查内容\",\"CONDITION\":\"条件要求\",\"STANDARD\":\"审查裁量标准\",\"MATERIALID\":\"审查材料ID\",\"MATERIALNAME\":\"材料名称\",\"SFFH\":\"1\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"审查ID\",\"NO\":\"序号\",\"SCNR\":\"审查内容\",\"CONDITION\":\"条件要求\",\"STANDARD\":\"审查裁量标准\",\"MATERIALID\":\"审查材料ID\",\"MATERIALNAME\":\"材料名称\",\"SFFH\":\"1\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"checkinforegist\":{\"HCFS\":\"1\",\"QTHCFS\":\"其他核查方式\",\"INSPECTORS\":[{\"NAME\":\"姓名\",\"XZZFZH\":\"行政执法证号\"},{\"NAME\":\"姓名\",\"XZZFZH\":\"行政执法证号\"}],\"HCSJ\":\"2018-02-12 12:03:11\",\"CSWZ\":\"现场核查场所位置\",\"TZSDFS\":\"2\",\"QTTZSDFS\":\"其他现场核实通知方式\",\"NOTICEDATA\":\"2018-02-12 12:03:11\"},\"SUBCONTEXTS\":[{\"ID\":\"实质内容核查id\",\"NO\":\"序号\",\"HCNR\":\"核查内容\",\"HCMD\":\"核查目的\",\"PDBZ\":\"判定标准\",\"HCFS\":\"1\",\"SFFH\":\"是否符合\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"实质内容核查id\",\"NO\":\"序号\",\"HCNR\":\"核查内容\",\"HCMD\":\"核查目的\",\"PDBZ\":\"判定标准\",\"HCFS\":\"1\",\"SFFH\":\"是否符合\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"CENSOR\":[{\"NODEID\":\"环节ID\",\"NODENAME\":\"环节名称\",\"IDEA\":\"环节处理意见\",\"NODEACTOR\":\"环节处理人\",\"LID\":\"环节量化表\"},{\"NODEID\":\"环节ID\",\"NODENAME\":\"环节名称\",\"IDEA\":\"环节处理意见\",\"NODEACTOR\":\"环节处理人\",\"LID\":\"环节量化表\"}],\"ISSUEINFO\":{\"ISSUEACTOR\":\"签发人\",\"ISSUEDATA\":\"2018-02-12 12:03:11\",\"JDMC\":\"决定名称\",\"JDBH\":\"决定编号\",\"ZJMC\":\"证件名称\",\"ZJBH\":\"证件编号\",\"SFGK\":\"是否公开\",\"GKFS\":\"公开方式\",\"GKSM\":\"公开说明\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},\"CHARGEINFOREGISTER\":{\"CHARGEINFO\":[{\"CHARGEITEMID\":\"收费项目ID\",\"PAYNOTENO\":\"缴款通知书编号\",\"OUCODE\":\"执收单位编码\",\"OUNAME\":\"执收单位名称\",\"CHARGENODE\":\"收费环节\",\"CHARGETYPE\":\"收费方式\",\"CHARGEJM\":\"是否符合收费减免情形\",\"CHARGESTANDARS\":[{\"ID\":\"收费标准id\",\"NO\":\"序号\",\"STANDARD\":\"收费标准\",\"PRICE\":\"单价\",\"QUALITY\":\"数量\"},{\"ID\":\"收费标准id\",\"NO\":\"序号\",\"STANDARD\":\"收费标准\",\"PRICE\":\"单价\",\"QUALITY\":\"数量\"}]},{\"CHARGEITEMID\":\"收费项目ID\",\"PAYNOTENO\":\"缴款通知书编号\",\"OUCODE\":\"执收单位编码\",\"OUNAME\":\"执收单位名称\",\"CHARGENODE\":\"收费环节\",\"CHARGETYPE\":\"收费方式\",\"CHARGEJM\":\"是否符合收费减免情形\",\"CHARGESTANDARS\":[{\"ID\":\"收费标准id\",\"NO\":\"序号\",\"STANDARD\":\"收费标准\",\"PRICE\":\"单价\",\"QUALITY\":\"数量\"},{\"ID\":\"收费标准id\",\"NO\":\"序号\",\"STANDARD\":\"收费标准\",\"PRICE\":\"单价\",\"QUALITY\":\"数量\"}]}],\"ID\":\"收费标准id\",\"TOTALCHARGE\":\"收费标准id\",\"JMQX\":\"减免情形\",\"DERATECHARGE\":\"减免金额\",\"LASTCHARGE\":\"实收金额\",\"NOTICE\":\"1,3\",\"BANKER\":\"代收银行\",\"RECEIPT_FILE\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\"}},\"certinforegist\":{\"CERTINFO\":[{\"ID\":\"证件id\",\"CERTID\":\"电子证照编码\",\"NO\":\"序号\",\"ZJMC\":\"证件名称\",\"ISSUEACTOR\":\"签发人\",\"ISSUEDATA\":\"2017-12-01 12:03:14\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"证件id\",\"CERTID\":\"电子证照编码\",\"NO\":\"序号\",\"ZJMC\":\"证件名称\",\"ISSUEACTOR\":\"签发人\",\"ISSUEDATA\":\"2017-12-01 12:03:14\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"SDFS\":\"颁发/送达方式\",\"SDSJ\":\"2017-12-01 12:03:14\"},\"ZJSDQS\":{\"ZJSDXX\":[{\"ID\":\"签收id\",\"NO\":\"序号\",\"CERTINFOID\":\"证件ID\",\"ZJMC\":\"证件名称\",\"QJCL\":\"取件材料\",\"ISSUEACTOR\":\"签收人\",\"ISSUEDATA\":\"2017-12-01 12:03:14\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"签收id\",\"NO\":\"序号\",\"CERTINFOID\":\"证件ID\",\"ZJMC\":\"证件名称\",\"QJCL\":\"取件材料\",\"ISSUEACTOR\":\"签收人\",\"ISSUEDATA\":\"2017-12-01 12:03:14\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}],\"RECEIPT_FILE\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},\"CERTIFICATE_FILE\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},\"BJJSGDSJ\":\"办件结束归档时间\",\"QJCLXX\":[{\"NO\":\"序号\",\"MATERIALID\":\"材料ID\",\"MATERIALNAME\":\"材料名称\",\"CLSLID\":\"材料实例ID(数组)\",\"STANDARD\":\"接件标准\",\"ORIGINAL\":\"原件(份)\",\"COPY\":\"复印件(份)\",\"SFFH\":\"1\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"NO\":\"序号\",\"MATERIALID\":\"材料ID\",\"MATERIALNAME\":\"材料名称\",\"CLSLID\":\"材料实例ID(数组)\",\"STANDARD\":\"接件标准\",\"ORIGINAL\":\"原件(份)\",\"COPY\":\"复印件(份)\",\"SFFH\":\"1\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}]},\"YCSCINFO\":{\"CURRENTNODE\":\"当前审查环节\",\"YCSJ\":\"2017-12-01 12:03:14\",\"SCCQSJ\":\"2017-12-01 12:03:14\",\"REASON\":\"延长理由\",\"receipt_file\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}},\"REGISTER\":{\"CURRENTNODE\":\"当前审查环节\",\"REASONINFOS\":[{\"ID\":\"情形ID\",\"ZZQX\":\"终止情形\",\"NO\":\"序号\",\"STATUS\":\"状态\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"情形ID\",\"ZZQX\":\"终止情形\",\"NO\":\"序号\",\"STATUS\":\"状态\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}]},\"YWJGWB\":{\"NRHC\":\"1\",\"INFOS\":[{\"ID\":\"文本id\",\"NO\":\"序号\",\"NAME\":\"文书名称\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]},{\"ID\":\"文本id\",\"NO\":\"序号\",\"NAME\":\"文书名称\",\"FILES\":[{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}]}]},\"FORMINFO\":{\"SQSLYJ\":\"受理审核意见\",\"SQSLJG\":\"1\",\"SQSLSCHZ\":\"受理审核回执\",\"TSCX\":\"1\",\"receipt_file\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FILEPATH\":\"附件路径\",\"FJSLID\":\"接收附件实例ID\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"},\"certificate_file\":{\"FILEID\":\"附件ID\",\"FILENAME\":\"附件名称\",\"FJSLID\":\"物料流转实例ID\",\"FILEPATH\":\"附件路径\",\"TYPE\":\"1\",\"BDBCLX\":\"本地保存类型\",\"BDBC_ZH\":\"本地存储账号\",\"BDBC_MM\":\"本地存储密码\",\"BDBC_URL\":\"本地保存类型\"}},\"Nodes\":{\"LID\":\"UUID诚信公司制定的关于各区报送数据的规则\",\"NODENAME\":\"环节名称\",\"NODEPROPERTY\":\"1\",\"DEPARTMENT\":\"部门组织机构代码\",\"HANDLERDATE\":\"2018-02-12 12:03:11\",\"NEXTNODE\":\"下一个环节名称\",\"NEXTNODEPROPERTY\":\"2\",\"NEXTHANDLER\":\"下一环节处理人姓名\"},\"DZD\":\"大字段\"}";
		Map<String, String> beanName = new HashMap<String, String>();
		beanName.put("processnodeinfo","com.tjsoft.tysl.model.ProcessNodeInfo");
		beanName.put("application","com.tjsoft.tysl.model.TInsBusinessinfoSqdj");
		beanName.put("materials","com.tjsoft.tysl.model.Material");
		beanName.put("standards","com.tjsoft.tysl.model.Standard");
        beanName.put("sqclxssc","com.tjsoft.tysl.model.Sqclxssc");
        beanName.put("checkinforegist","com.tjsoft.tysl.model.Checkinforegist");
        beanName.put("subcontexts","com.tjsoft.tysl.model.Subcontext");
        beanName.put("zjsdqs","com.tjsoft.tysl.model.Zjsdqs");
        beanName.put("certinforegist","com.tjsoft.tysl.model.Certinforegist");
        beanName.put("censor","com.tjsoft.tysl.model.Censor");
        beanName.put("issueinfo","com.tjsoft.tysl.model.IssueInfo");
        beanName.put("ycscinfo","com.tjsoft.tysl.model.YcscInfo");
        beanName.put("forminfo","com.tjsoft.tysl.model.FormInfo");
        beanName.put("nodes","com.tjsoft.tysl.model.Nodes");
        
		Map<String, Object> beanMap =  getBean(jsonStr,beanName);
//        try {
//            System.out.println(URLEncoder.encode("s大锅饭","UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            
//        }
        
//		JSONObject jsona = JSONObject.fromObject(jsonStr);
//		
//		JSONObject jsonProcessNodeInfo =jsona.getJSONObject("PROCESSNODEINFO");
//		ProcessNodeInfo processNodeInfo =(ProcessNodeInfo) JSONtoBean(jsonProcessNodeInfo,"com.tjsoft.tysl.model.ProcessNodeInfo");
//		System.out.println(processNodeInfo);
//		JSONObject json =jsona.getJSONObject("Application");
//		TInsBusinessinfoSqdj tInsBusinessinfoSqdj =(TInsBusinessinfoSqdj) JSONtoBean(json,"com.tjsoft.tysl.model.TInsBusinessinfoSqdj");
//		System.out.println(tInsBusinessinfoSqdj);
//		JSONArray jsonmaterials =jsona.getJSONArray("MATERIALS");
//		java.util.List materials= JSONtoList(jsonmaterials, "com.tjsoft.tysl.model.Material");
//		System.out.println(materials);
//		JSONArray jsonstandards =jsona.getJSONArray("STANDARDS");
//		java.util.List Standard= JSONtoList(jsonstandards, "com.tjsoft.tysl.model.Standard");
//		System.out.println(Standard);
//		
//		JSONArray jsonSqclxssc =jsona.getJSONArray("SQCLXSSC");
//		java.util.List Sqclxssc= JSONtoList(jsonSqclxssc, "com.tjsoft.tysl.model.Sqclxssc");
//		System.out.println(Sqclxssc);
//		
//		JSONObject jsonCheckinforegist =jsona.getJSONObject("checkinforegist");
//		Checkinforegist Checkinforegist= (Checkinforegist) JSONtoBean(jsonCheckinforegist, "com.tjsoft.tysl.model.Checkinforegist");
//		System.out.println(Checkinforegist);
//		
//		JSONArray jsonSubcontext =jsona.getJSONArray("SUBCONTEXTS");
//		java.util.List Subcontext= JSONtoList(jsonSqclxssc, "com.tjsoft.tysl.model.Subcontext");
//		System.out.println(Subcontext);
//		
//		JSONObject jsonZjsdqs =jsona.getJSONObject("ZJSDQS");
//		Zjsdqs zjsdqs= (Zjsdqs)JSONtoBean(jsonZjsdqs, "com.tjsoft.tysl.model.Zjsdqs");
//		System.out.println(zjsdqs);
//
//		JSONObject jsonCertinforegist=getJSONObject(jsona, "CERTINFOREGIST");//jsona.getJSONObject("certinforegist");
//		Certinforegist Certinforegist= (Certinforegist) JSONtoBean(jsonCertinforegist, "com.tjsoft.tysl.model.Certinforegist");
//		System.out.println(Certinforegist);
//		
//		
//		JSONArray jsonCensor =jsona.getJSONArray("CENSOR");
//		java.util.List censor= JSONtoList(jsonCensor,"com.tjsoft.tysl.model.Censor");
//		System.out.println(censor);
//
//		JSONObject jsonIssueinfo=jsona.getJSONObject("ISSUEINFO");
//		IssueInfo issueinfo= (IssueInfo) JSONtoBean(jsonIssueinfo, "com.tjsoft.tysl.model.IssueInfo");
//		System.out.println(issueinfo);
//		
//		JSONObject jsonycscinfo=jsona.getJSONObject("YCSCINFO");
//		YcscInfo ycscinfo= (YcscInfo) JSONtoBean(jsonycscinfo, "com.tjsoft.tysl.model.YcscInfo");
//		System.out.println(ycscinfo);
//		
//		JSONObject jsonforminfo =jsona.getJSONObject("FORMINFO");
//		FormInfo forminfo= (FormInfo) JSONtoBean(jsonforminfo, "com.tjsoft.tysl.model.FormInfo");
//		System.out.println(forminfo);
//		JSONObject jsonnodes =jsona.getJSONObject("Nodes");
//		Nodes nodes= (Nodes) JSONtoBean(jsonnodes, "com.tjsoft.tysl.model.Nodes");
//		System.out.println(nodes);
		
		
	}
}
