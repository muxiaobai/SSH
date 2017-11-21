package Entity;

import java.util.Date;
import java.util.EnumSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.web.dao.PersonDao;
import org.web.entity.Person;

public class SchemaTest {
	public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-hibernate.xml","applicationContext-struts2.xml","applicationContext.xml"});
				//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
				PersonDao personDaoImpl = (PersonDao) context.getBean("personDaoImpl");
				Session session = sessionFactory.getCurrentSession();
				//Transaction transaction=session.beginTransaction();
				Person person = new Person();
				person.setName("zhang1");
				person.setDate(new Date());
				//session.save(person);
				personDaoImpl.save(person);
				//transaction.commit();
			
	}
	@Test
	public void testHibernate_spring() {
		 ApplicationContext context = new ClassPathXmlApplicationContext(new
		 String[]
		 {"applicationContext-hibernate.xml","applicationContext-struts2.xml",
		 "applicationContext.xml","applicationContext-prop.xml"});
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		PersonDao personDaoImpl = (PersonDao) context.getBean("personDaoImpl");
		Session session = sessionFactory.getCurrentSession();
		// Transaction transaction=session.beginTransaction();
		Person person = new Person();
		person.setName("zhang1");
		person.setDate(new Date());
		// session.save(person);
		personDaoImpl.save(person);
		// transaction.commit();
	}

	@Test
	public void testScemaExport() {
//		 ServiceRegistry serviceRegistry=new
//		 StandardServiceRegistryBuilder().configure().build();
//		 Metadata metadata=new
//		 MetadataSources(serviceRegistry).buildMetadata();
//		 SchemaExport export=new SchemaExport();
//		 export.create(EnumSet.of(TargetType.DATABASE), metadata);
	ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().configure().build(); 
	Metadata metadata=new MetadataSources(serviceRegistry).buildMetadata(); 
	SchemaExport export=new SchemaExport(); 
	export.create(EnumSet.of(TargetType.DATABASE), metadata); 

		
	}

	@Test
	public void testSavePerson() {
		// opensession 需要显式关闭,手动事务.
		Configuration conf = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
		// ServiceRegistry serviceRegistry = new
		// StandardServiceRegistryBuilder().configure().build();
		// SessionFactory sessionFactory = new
		// MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Person person = new Person();
			person.setName("zhang1");
			session.save(person);
			transaction.commit();
			session.close();
		} catch (Throwable th) {
			System.err.println("Init SessionFactory creation failed");
			System.err.println(th);
			throw new ExceptionInInitializerError(th);
		} finally {

		}
	}
}
