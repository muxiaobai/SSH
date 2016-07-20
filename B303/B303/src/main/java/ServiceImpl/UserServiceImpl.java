package ServiceImpl;

import java.io.Serializable;
import Dao.UserDao;
import Model.User;
import Service.UserService;

/** 
 * 
 * @author zhang
 * @Date  2016年6月3日 下午2:55:23
 * @doing 
 * 
 * 
 */
public class UserServiceImpl implements UserService{
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Serializable save(User user) {
		return userDao.save(user);
	}

	@Override
	public User findByUsername(String username) {
		System.out.println(userDao.findByName(username));
		if(!userDao.findByName(username).isEmpty()){
			return userDao.findByName(username).get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public Boolean login(User user) {
		return findByUsername(user.getUsername()).getPassword().equals(user.getPassword())? true:false;
	}

	
}