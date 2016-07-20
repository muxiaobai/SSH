package Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/** 
 * Merge 方法反射调用
 * @author zhang
 * @Date  2016年6月23日 下午3:32:12
 * @doing 对象中数据合并
 */
@SuppressWarnings({"rawtypes", "unused","unchecked"})
public class MergeUtil {
	/**
	 * 调用反射merge方法, 合并数据，以tmp为主 ，当save有数据且不等于tmp中的数据时，合并到tmp中
	 * model中有merge方法
	 * @param save
	 * @param tmp
	 * @return tmp
	 */
	@Deprecated
	public static <T> T merge(T save, T tmp) {
		Class clazz=save.getClass();
		Method method = null;
		try {
	    	method = clazz.getDeclaredMethod("merge", Object.class);
			return (T) method.invoke(save,tmp);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}
	/**
	 * Field 合并数据,以tmp为主 ，当save有数据且不等于tmp中的数据时，合并到tmp中
	 * 推荐使用
	 * @param save
	 * @param tmp
	 * @return tmp
	 */
	
	public static <T> T mergeField(T save, T tmp) {
		Class clazz=save.getClass();
		Field[] fields=clazz.getDeclaredFields();
	        for (Field field : fields) {  
		        try {
					Method	getMethod=clazz.getDeclaredMethod("get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1));
		        	Method	setMethod=clazz.getDeclaredMethod("set"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1),field.getType());
		        	Object saveValue=getMethod.invoke(save);
		        	Object tmpValue=getMethod.invoke(tmp);
//		        	System.out.println(field.getType().getName());
//		        	System.out.println(field.getType().getPackage().getName().equals("java.lang"));
//		        	System.out.println("Model:"+field.getType().getPackage().equals("Model"));
		        	if(saveValue==null){
		        		continue;
		        	}
//		        	else if (saveValue=="") {
//		        		continue;
//					}
		        	else if (saveValue.equals("")) {
		        		continue;
					}
		        	else {
			        	if(tmpValue==null){
			        		setMethod.invoke(tmp,saveValue);
			        		continue;
			        	}
		        		if(field.getType().getPackage().getName().equals("java.lang")||field.getType().getName().equals("java.util.Date")||field.getType().getPackage().getName().equals("Enum")){//简单类型和日期类型
		        			if(!saveValue.equals(tmpValue)){
		        				setMethod.invoke(tmp,saveValue);
//			        			System.out.println("tmp:"+tmp);
//			        			System.out.println("save:"+save);
//			        			System.out.println("tmpValue:"+tmpValue+"saveValue:"+saveValue);
		        			}
		        		}else if(field.getType().getPackage().getName().equals("Model")){//复杂类型
//		        			System.out.println(saveValue.getClass());
//		        			System.out.println(tmpValue.getClass());
		        			setMethod.invoke(tmp,mergeField(saveValue, tmpValue));
		        			//mergeField(saveValue, tmpValue);
		        		}
		        		else if (Collection.class.isAssignableFrom(saveValue.getClass())) { //List集合类
//	        					System.out.println("------------------------------");
//	        	                System.out.println("type:" + saveValue.getClass());
	        	                Iterator saveIterator = ((Collection)saveValue).iterator();
	        	                Iterator tmpIterator = ((Collection)tmpValue).iterator();
	        	                List tmpList=new ArrayList<>();
	        	                while (tmpIterator.hasNext()) {
	        	                	Object tmpIter = tmpIterator.next();
									Method tmpMethod=tmpIter.getClass().getMethod("getId");
									//System.out.println(tmpMethod.invoke(tmpIter));
									Boolean flag=true;
	        	                	 	while (saveIterator.hasNext()) {
	        	                	 		Object saveIter=saveIterator.next();
//	    	        	                	System.out.println("class:"+saveValue.getClass()+"  "+"saveIter:"+saveIter);
	    	        	                	Method saveMethod=saveIter.getClass().getMethod("getId");
//	    	        	                	System.out.println(saveMethod.invoke(saveIter));
//	    	        	                	if(saveMethod.invoke(saveIter)==null){
//	    										tmpList.add(saveIter);
//	    										continue;
//	    									}
	    	        	                	if(saveMethod.invoke(saveIter)==tmpMethod.invoke(tmpIter)){
	    	        	                		tmpList.add(mergeField(saveIter, tmpIter));
	    	        	                		flag=false;
												break;
											}
										}
	        	                	 	if(flag){
	        	                	 		tmpList.add(tmpIter);
	        	                	 	}
	        	                }
	        	                setMethod.invoke(tmp,tmpList);
	        	                
		        		 }

		        		
		    		}
				}  catch (Exception e) {
					e.printStackTrace();
				}
	        }
	return tmp;  
		
	}
	public static <T> Integer getIdField(T entity){
		Class clazz=entity.getClass();
		Integer integer=null;
		try {
			Method	getMethod=clazz.getDeclaredMethod("getId");
			integer=(Integer) getMethod.invoke(entity);
			if(integer==0){
				throw new Exception("数据库中没有此ID对应的数据");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return integer;    
	}
	
	/**
	 * 合并数据，以obj为主 ，当自己有数据且不等于obj中的数据时，合并自己到obj中
	 * @param obj 传入obj
	 * @return 传出obj
	 */
//	public Object merge(Object obj) {
//		Person other = (Person) obj;
//		if(name!=null&&!(name=="")&&!name.equals("")&&!name.equals(other.name)){
//			other.setName(name);
//		}
//		if (password!=null||!password.equals(other.password)){
//			other.setPassword(password);
//		}
//		return other;
//	}
}
