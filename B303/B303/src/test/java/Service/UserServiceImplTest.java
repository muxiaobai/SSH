package Service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Base.DaoFactory;
import Base.ServiceFactory;
import Enum.NormalStatus;
import Model.User;

/** 
 * 
 * @author zhang
 * @Date  2016年6月4日 下午12:31:28
 * @doing 
 */

public class UserServiceImplTest {
	private static UserService userService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userService=(UserService) ServiceFactory.getUserServiceInstance();
	}
	@Test
	public void saveTest(){
		User user=new User();
		user.setAge(12);
		user.setMobile("122121323231");
		user.setStatus(NormalStatus.NORMAL);
		Assert.assertNotNull(userService.save(user));
	}
}
