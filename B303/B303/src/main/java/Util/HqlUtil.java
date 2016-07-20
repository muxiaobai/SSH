package Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Month;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Enum.NormalStatus;
import Model.User;
import SQLModel.Between;
import SQLModel.ColumnIn;
import SQLModel.OrderBy;
import SQLModel.Pager;

public class HqlUtil {
	public static String getHQL(Object entry){
		StringBuffer sql = new StringBuffer();
		Class clazz=entry.getClass();
		sql.append(" FROM ").append(clazz.getSimpleName());
	//	sql.append( " "+clazz.getSimpleName().substring(0, 1).toUpperCase()+clazz.getSimpleName().substring(1));
			//单表查询
			sql.append(" WHERE 1=1");
			try {
				// 获取字段名 
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
						String column = field.getName();
						String getMethodName = "get"
								+ column.substring(0, 1).toUpperCase()
								+ column.substring(1);
						
						Method getMethod =clazz.getMethod(getMethodName);
						//System.out.println(getMethodName);
						Object fieldValue =getMethod.invoke(entry);
						if(fieldValue==null||(fieldValue instanceof Integer&&(Integer)fieldValue==0)||(fieldValue instanceof Double&&(double)fieldValue==0.00)){continue;}
						if(fieldValue instanceof Integer||fieldValue instanceof Double||fieldValue instanceof Float){
							sql.append(" and ").append(column).append("=").append( fieldValue);			
						}else if(fieldValue instanceof String){
								sql.append(" and ").append(column).append(" like ").append("\"%").append(fieldValue).append("%\"");			
						}else if(fieldValue instanceof Enum) {
							Class EnumClazz=fieldValue.getClass();
							sql.append(" and ").append(column).append("= ").append(Enum.valueOf(EnumClazz,fieldValue.toString()).ordinal());	
						}else if (field.getType().getPackage().getName().equals("Model")) {
							sql.append(getWhereHQL(fieldValue));
						//	System.out.println(getWhereHQL(fieldValue));
						}
						
					}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	
		return sql.toString();
	}
	public static String getWhereHQL(Object entry){
		StringBuffer sql = new StringBuffer();
		Class clazz=entry.getClass();
		String Lower=clazz.getSimpleName().toLowerCase();
			try {
				// 获取字段名 
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
						String column = field.getName();
						String getMethodName = "get"
								+ column.substring(0, 1).toUpperCase()
								+ column.substring(1);
						
						Method getMethod =clazz.getMethod(getMethodName);
						//System.out.println(getMethodName);
						Object fieldValue =getMethod.invoke(entry);
						if(fieldValue==null||(fieldValue instanceof Integer&&(Integer)fieldValue==0)||(fieldValue instanceof Double&&(double)fieldValue==0.00)){continue;}
						if(fieldValue instanceof Integer||fieldValue instanceof Double||fieldValue instanceof Float){
							sql.append(" and ").append(Lower).append(".").append(column).append("=").append( fieldValue);			
						}else if(fieldValue instanceof String){
								sql.append(" and ").append(Lower).append(".").append(column).append(" like ").append("\"%").append(fieldValue).append("%\"");			
						}else if(fieldValue instanceof Enum) {
							Class EnumClazz=fieldValue.getClass();
							sql.append(" and ").append(Lower).append(".").append(column).append("= ").append(Enum.valueOf(EnumClazz,fieldValue.toString()).ordinal());	
						}else if (field.getType().getPackage().getName().equals("Model")) {
							sql.append(getWhereHQL(fieldValue));
							System.out.println(getWhereHQL(fieldValue));
						}
						
					}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	
		return sql.toString();
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public static String getHQL( Pager pager){
		StringBuffer sql = new StringBuffer();
		if(pager.getEntry()==null){
			return "";
		}
		Class clazz=pager.getEntry().getClass();
		sql.append(" FROM ").append(clazz.getSimpleName());
		
			//单表查询
			sql.append(" WHERE 1=1");
			try {
				// 获取字段名 
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
						String column = field.getName();
						String getMethodName = "get"
								+ column.substring(0, 1).toUpperCase()
								+ column.substring(1);
						
						Method getMethod =clazz.getMethod(getMethodName);
						//System.out.println(getMethodName);
						Object fieldValue =getMethod.invoke(pager.getEntry());
						if(fieldValue==null||(fieldValue instanceof Integer&&(Integer)fieldValue==0)||(fieldValue instanceof Double&&(double)fieldValue==0.00)){continue;}
						if(fieldValue instanceof Integer||fieldValue instanceof Double||fieldValue instanceof Float){
							sql.append(" and ").append(column).append("=").append( fieldValue);			
						}else if(field.getType().getPackage().getName().equals("Enum")){
							Class EnumClazz=fieldValue.getClass();
							//System.out.println(Enum.valueOf(EnumClazz,(String) fieldValue.toString()));
							//System.out.println(Enum.valueOf(EnumClazz,fieldValue.toString()).ordinal());
							sql.append(" and ").append(column).append("= ").append(Enum.valueOf(EnumClazz,fieldValue.toString()).ordinal());	
						}else if(fieldValue instanceof String){
								sql.append(" and ").append(column).append(" like ").append("\"%").append(fieldValue).append("%\"");			
						}
						
					}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	
		return sql.toString();
	}
	
	
	
	/**
	 * 传递SQLParamObject参数返回sql语句
	 * @param obj Map参数包含必须searchModel
	 * 其他键为可选有 pageNum从第几个开始 pageSize本页显示的数据量 columnIn （in） between （ between and ）orderBy （order by）
	 * @return select and order by in between and 》 《  limit查询语句
	 */
	public static String getHQL(Map<String, Object> obj){
			StringBuffer sql = new StringBuffer();
			Class<? extends Object> class1=obj.get("searchModel").getClass();
			sql.append(" FROM ").append(class1.getSimpleName().substring(0, 1).toLowerCase()+class1.getSimpleName().substring(1));
			
				//单表查询
				sql.append(" WHERE 1=1");
				try {
					// 获取字段名 
					Field[] fields = class1.getDeclaredFields();
					for (Field field : fields) {
							String column = field.getName();
							String getMethodName = "get"
									+ column.substring(0, 1).toUpperCase()
									+ column.substring(1);
							Method getMethod =class1.getMethod(getMethodName);
							//System.out.println(getMethodName);
							Object fieldValue =getMethod.invoke(obj.get("searchModel"));
							if(fieldValue==null||(fieldValue instanceof Integer&&(Integer)fieldValue==0)||(fieldValue instanceof Double&&(double)fieldValue==0.00)){continue;}
							if(fieldValue instanceof Integer){
								sql.append(" and ").append(column).append("=").append( fieldValue);			
							}else if(fieldValue instanceof Double){
								sql.append(" and ").append(column).append("=").append( fieldValue);	
							}else if(fieldValue instanceof Float){
								sql.append(" and ").append(column).append("=").append( fieldValue);	
							}else if(fieldValue instanceof String){
									sql.append(" and ").append(column).append(" like ").append("\"%").append(fieldValue).append("%\"");			
							}
							
						}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				//单表查询
			System.out.println(sql.toString());	
			
			//增加 BETWEEN 'Adams' AND 'Carter'
			if(obj.get("between")!=null&&obj.get("between") instanceof Between){
				Between between=(Between)obj.get("between");
				if(between.getBegin()!=null||between.getEnd()!=null){
					StringBuffer betweenssql=new StringBuffer();
					if(between.getBegin()!=null&&between.getEnd()==null){
						betweenssql.append(" and ").append(" ( ").append(between.getColumn()).append(" > '").append(between.getBegin()).append("' ) ");
					}else if(between.getBegin()==null&&between.getEnd()!=null) {
						betweenssql.append(" and ").append(" ( ").append(between.getColumn()).append(" < '").append(between.getEnd()).append("' ) ");
					}else {
						betweenssql.append(" and ").append(" ( ").append(between.getColumn()).append(" between '").append(between.getBegin()).append("' and '").append(between.getEnd()).append("' ) ");
					}
					sql.append(betweenssql);
				}

				//sql.append(" and ").append(" ( ").append(between.getColumn()).append(" between ").append(between.getBegin()).append(" and ").append(between.getEnd()).append(" ) ");
			}else if(obj.get("between")!=null&&obj.get("between") instanceof List<?>) {
				List betweens=(List)obj.get("between");
				Iterator<Between> iterator=betweens.iterator();
					while (iterator.hasNext()) {
					Between iter = (Between) iterator.next();
						if(iter.getBegin()!=null||iter.getEnd()!=null){
							StringBuffer betweenssql=new StringBuffer();
							if(iter.getBegin()!=null&&iter.getEnd()==null){
								betweenssql.append(" and ").append(" ( ").append(iter.getColumn()).append(" > '").append(iter.getBegin()).append("' ) ");
							}else if(iter.getBegin()==null&&iter.getEnd()!=null) {
								betweenssql.append(" and ").append(" ( ").append(iter.getColumn()).append(" < '").append(iter.getEnd()).append("' ) ");
							}else {
								betweenssql.append(" and ").append(" ( ").append(iter.getColumn()).append(" between '").append(iter.getBegin()).append("' and '").append(iter.getEnd()).append("' ) ");
							}
							sql.append(betweenssql);
						}
					}
			}
			//between
			//columnIn
			StringBuffer sqlcolumnIn=new StringBuffer();
			
			if(obj.get("columnIn")!=null&&obj.get("columnIn") instanceof ColumnIn){
				ColumnIn columnIn=(ColumnIn) obj.get("columnIn");
					if(columnIn.getColumnIn()!=null){
							sqlcolumnIn.append(" and ").append(columnIn.getColumn()).append(" in(");
							Iterator<?> iterator=columnIn.getColumnIn().iterator();
							while (iterator.hasNext()) {
								Object object = (Object) iterator.next();
								sqlcolumnIn.append("'").append(object).append("',");
							}
							sqlcolumnIn.deleteCharAt(sqlcolumnIn.length()-1).append(")");
							sql.append(sqlcolumnIn);
						}
				}else if(obj.get("columnIn")!=null&&obj.get("columnIn") instanceof List<?>) {
				Iterator<ColumnIn> iterator=(Iterator<ColumnIn>) ((List<?>) obj.get("columnIn")).iterator();
					while (iterator.hasNext()) {
						ColumnIn iter = (ColumnIn) iterator.next();
						if(iter.getColumn()!=null&&iter.getColumnIn()!=null){
							sqlcolumnIn.append(" and ").append(iter.getColumn()).append(" in(");
							Iterator<?> ite=iter.getColumnIn().iterator();
							while (ite.hasNext()) {
								Object object = (Object) ite.next();
								sqlcolumnIn.append("'").append(object).append("',");
							}
							sqlcolumnIn.deleteCharAt(sqlcolumnIn.length()-1).append(")");
						}
					}
				sql.append(sqlcolumnIn);
			}
			
			//columnIn
			//OrderBy
			StringBuffer sqlorderby=new StringBuffer();
			sqlorderby.append(" order by ");
		
			if(obj.get("orderBy")!=null&&obj.get("orderBy") instanceof OrderBy){
				OrderBy orderby=(OrderBy) obj.get("orderBy");
				sqlorderby.append(orderby.getColumn()).append(" ").append(orderby.getOrderBy());
				sql.append(sqlorderby);
			}else if (obj.get("orderBy")!=null&&obj.get("orderBy") instanceof List<?>) {
				Iterator<OrderBy> iterator=(Iterator<OrderBy>) ((List<?>) obj.get("orderBy")).iterator();
				while (iterator.hasNext()) {
					OrderBy orderBy = (OrderBy) iterator.next();
					sqlorderby.append(orderBy.getColumn()).append(" ").append(orderBy.getOrderBy()).append(",");
				}
				sqlorderby.deleteCharAt(sqlorderby.length()-1);
				sql.append(sqlorderby);
			}
			
			//OrderBy
			System.out.println(sql);
			return sql.toString();
		}
		public static void main(String[] args) {
			
		}

}
