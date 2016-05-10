package Factory;

import Dao.PersonDao;
import DaoImpl.PersonDaoImpl;

public class DaoImplFactory {
	public static PersonDao getPersonDaoInstance() {
		return new PersonDaoImpl();
	}
}
