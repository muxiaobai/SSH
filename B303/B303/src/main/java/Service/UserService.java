package Service;

import java.io.Serializable;
import Model.User;

/** 
 * 
 * @author zhang
 * @Date  2016年6月3日 下午2:54:09
 * @doing 
 * 
 * 
 */

public interface UserService {
	
	/**
	 * 通过id查找用户
	 * @param id
	 * @return
	 */
	public User findByUsername(String username);
	
	
	/**
	 * 保存User，
	 * @param user要保存的User,用户
	 * @return 返回持久化ID
	 */
	public  Serializable save(User user);
	
	/**
	 * 登录
	 * @param 
	 * @return 
	 */
	public  Boolean login(User user);
	
}
