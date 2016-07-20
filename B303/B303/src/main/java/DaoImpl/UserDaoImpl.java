package DaoImpl;

import java.util.List;
import Dao.UserDao;
import Model.User;

/** 
 * 
 *  User的实现类 extends BaseDaoImpl<User> implements UserDao,
 * 该实现类既包含Base方法又包含User方法
 * @author zhang
 * @Date  2016年6月2日 下午8:50:48
 * @doing User特有的方法，对Service层提供支持
 * 
 */

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public List<User> findByName(String username) { 
		return	findByHql(" from User where username="+username);
	}


}
