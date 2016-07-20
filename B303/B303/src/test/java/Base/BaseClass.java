package Base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseClass {
	private static ApplicationContext context;
	private BaseClass() {
		context=new ClassPathXmlApplicationContext(new String[] { "applicationContext-hibernate.xml", "applicationContext-struts2.xml","applicationContext.xml", "applicationContext-prop.xml" });
		System.out.println(context);
	}
	public static ApplicationContext LoadXML(){
		//ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext-hibernate.xml", "applicationContext-struts2.xml","applicationContext.xml", "applicationContext-prop.xml" });
		BaseClass baseClass=new BaseClass();
		return baseClass.context;
	//	return new ClassPathXmlApplicationContext(new String[] { "applicationContext-hibernate.xml", "applicationContext-struts2.xml","applicationContext.xml", "applicationContext-prop.xml" });
	}
	
	public static Session getSession(){
		return	((SessionFactory)BaseClass.LoadXML().getBean("SessionFactory")).getCurrentSession()==null?((SessionFactory)BaseClass.LoadXML().getBean("SessionFactory")).openSession():((SessionFactory)BaseClass.LoadXML().getBean("SessionFactory")).getCurrentSession();
	}
	
	
	
	
	
	
}
