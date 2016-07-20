package Base;

import Base.BaseClass;
import Dao.UserDao;

public class DaoFactory {

	public static UserDao getUserDaoInstance(){
		return	(UserDao) BaseClass.LoadXML().getBean("userDaoImpl");
	}
}
