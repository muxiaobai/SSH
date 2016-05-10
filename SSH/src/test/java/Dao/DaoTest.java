package Dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext-hibernate.xml", "applicationContext-struts2.xml",
						"applicationContext.xml", "applicationContext-prop.xml" });
		PersonDao personDaoImpl = (PersonDao) context.getBean("personDaoImpl");
		System.out.println("aaaa"+personDaoImpl.findById(25));
	}
}
