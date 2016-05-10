package DaoImpl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import Dao.BaseDao;

@SuppressWarnings({"unchecked","rawtypes"})
public class BaseDaoImpl<T> implements BaseDao<T> {
	// DAO组件进行持久化操作底层依赖的SessionFactory组件
	protected SessionFactory sessionFactory;
	private Class clazz;
	private String hql;
	public BaseDaoImpl() {
		// 获取子类对象
		//System.out.println(this);
		// 获取子类类信息
		//System.out.println(this.getClass());
		// 获取子类的直接父类,如果父类有参数类型可以同时获取到父类的参数类型
		// System.out.println(this.getClass().getGenericSuperclass());
		// 获取参数类型的参数,返回参数类型的数组
		//System.out.println(((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		// 把参数类型转化为具体的Class类型
		this.clazz = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.hql = "from " + this.clazz.getName();
	}

	// 依赖注入SessionFactory所需的setter方法
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("=====注入sessionFactory===========");

	}
	 /** 
     * 取得Session. 
     */  
    public Session getSession(){  
        return sessionFactory.getCurrentSession();  
    }  
  
	@Override
	public void save(T t) {
		Transaction transaction=this.getSession().beginTransaction();
		this.getSession().save(t);
		transaction.commit();
	}

	@Override
	public void delete(Integer id) {
		//sessionFactory.getCurrentSession().update(id);
		Transaction transaction=this.getSession().beginTransaction();
		this.getSession().delete(id);
		transaction.commit();
	}

	@Override
	public void update(T t) {
		this.getSession().update(t);
	}

	
	@Override
	public T findById(Integer id) {
		return (T) this.getSession().get(clazz, id);
	}
	@Override
	public List<T> findAll() {
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		List<T> result= session.createQuery("FROM " + clazz.getName()).list();
		transaction.commit();
		return result;
	}
}
