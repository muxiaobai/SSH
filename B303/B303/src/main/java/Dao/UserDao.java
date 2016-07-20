package Dao;

import Model.User;
import java.util.List;

/**
 * 用户User
 * 
 * @author zhang
 * @date 2016年6月2日 下午8:19:52
 * @doing TODO用户
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * //通过登录账号找
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findByName(String username);

}