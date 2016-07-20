package Base;

import Service.UserService;

/** 
 * 
 * @author zhang
 * @Date  2016年6月4日 下午12:26:55
 * @doing 
 */

public class ServiceFactory {
	
	public static UserService getUserServiceInstance(){
		return	(UserService) BaseClass.LoadXML().getBean("userServiceImpl");
	}
	
}
