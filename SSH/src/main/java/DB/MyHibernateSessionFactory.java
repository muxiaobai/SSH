package DB;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class MyHibernateSessionFactory {
private static SessionFactory sessionFactory;
//构造方法私有化
	private MyHibernateSessionFactory(){
	}
//公有静态方法，获得会话工厂
	public static SessionFactory getSessionFactory(){
		if(sessionFactory==null){
			ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().configure().build();
			sessionFactory = new MetadataSources( serviceRegistry ).buildMetadata().buildSessionFactory();
			return sessionFactory;
		}
		else{
			return sessionFactory;
		}
	}
}