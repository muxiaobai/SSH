package org.web.dao;

import java.util.List;
public interface BaseDao<T> {
//--------------------------------------------------------------  
    /** 
     * 新增对象. 
     */ 
	public void save(T t);
	
	 /** 
     * 删除对象. 
     */  
	public void delete(Integer id);
	  /** 
     * 修改对象. 
     */  
	public void update(T t);
	
	//---------------------------------------------------------------  
	  
	 /** 
     * 按id获取对象. 
     */  
	public T findById(Integer id);

	public List<T> findAll() ;
}
