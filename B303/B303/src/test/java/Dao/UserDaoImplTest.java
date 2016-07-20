package Dao;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import Base.DaoFactory;
import Enum.NormalStatus;
import Model.User;

/** 
 * 
 * @author zhang
 * @Date  2016年6月4日 下午3:46:43
 * @doing 
 */

public class UserDaoImplTest {
	private static UserDao userDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDao=(UserDao)DaoFactory.getUserDaoInstance();
	/*	supplierDao=(SupplierDao)DaoFactory.getSupplierDaoInstance();*/
	}
	@Test
	public void testSave() {
		User user=new User();
		user.setName("ZHEGNCHERNG");
		user.setStatus(NormalStatus.DELETE);
		userDao.save(user);
		User user2=new User();
		user2.setName("shanchu");
		user2.setStatus(NormalStatus.NORMAL);
		userDao.save(user2);
	}
	
	@Test
	public void testFindAll() {
		System.out.println("=================testFindAll======================");
		System.out.println(userDao.findAll());
	}
	@Test
	public void testByName() {
		System.out.println("==================testByName1=====================");
		System.out.println(userDao.findById(1));
		
		System.out.println("==================testByName2=====================");
		System.out.println(userDao.findById(2));
		
	}
	

}
