package Dao;

import java.io.Serializable;
import java.util.List;

import SQLModel.Pager;

/**
 * BaseDao基类，基本的方法CRUD
 * 
 * @author zhang
 * @date 2016年5月21日 下午7:01:39
 * @doing 提供模板
 * @param <T>为泛型Model
 */
public interface BaseDao<T> {
	/**
	 * 增加：持久化对象
	 * 
	 * @param entity
	 * @return 持久化的ID
	 */
	public Serializable save(T entity);

	/**
	 * 删除：删除对象
	 * 
	 * @param id
	 */
	public void delete(T entity);

	/**
	 * 修改：修改Model对象，并持久化
	 * 
	 * @param entity
	 * @return 
	 */
	public void update(T entity);
	/**
	 * 修改或保存：修改Model对象，并持久化
	 * 
	 * @param entity
	 */
	
	public void update_G(T entity);
	/**
	 * 修改或保存：修改Model对象，并持久化
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	/**
	 * 查询：通过id查询对象
	 * 
	 * @param id
	 * @return Model实例
	 */
	public T findById(Integer id);
	
	/**
	 * 获取实体总数
	 * @param entityClazz
	 * @return long 该表中的数量
	 */
	public long findCount(Class<T> entityClazz);
	/**
	 * 查询：查询所有的对象
	 * 
	 * @return List<T>
	 */
	public List<T> findAll();
	/**
	 * 根据HQL语句查询实体(不带参数)
	 * 
	 * @param hql语句
	 * @return List集合包括要显示的数据
	 */
	public List<T> findByHql(String hql);
	/**
	 * 根据带占位符参数HQL语句查询实体(带参数的)
	 * 
	 * @param hql
	 * @param params
	 * @return List集合包括要显示的数据
	 */
	public List<T> findByHql(String hql, Object... params);
	/**
	 * 根据SQL语句查询实体(不带参数)
	 * 
	 * @param sql语句
	 * @return List集合包括要显示的数据
	 */
	public List<T> findBySql(String hql);
	/**
	 * 根据带占位符参数SQL语句查询实体(带参数的)
	 * 
	 * @param sql
	 * @param params
	 * @return List集合包括要显示的数据
	 */
	public List<T> findBySql(String hql, Object... params);
	/**
	 * 根据HQL语句分页查询实体(不带参数)
	 * @param hql
	 * @param pageNo当前第几页数据
	 * @param pageSize每页显示多少条记录
	 * @return List集合包括要显示的数据
	 */
	public List<T> findByPage(String hql, int pageNo, int pageSize);
	/**
	 * 根据带占位符参数HQL语句分页查询实体(带参数的)
	 * @param hql
	 * @param pageNo当前第几页数据
	 * @param pageSize每页显示多少条记录
	 * @return List集合包括要显示的数据
	 */
	public List<T> findByPage(String hql, int pageNo, int pageSize, Object... params);
	
	/**
	 * 根据Pager中的entry对象生成SQL语句，并返回数据
	 * @param pager(必须有entry对象)
	 * @return 含有数据的Pager<T>
	 */
	public Pager<T> findByPager(Pager<T> pager);

	public void saveList(List<T> entities);

}