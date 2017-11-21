package Factory;

import org.web.dao.PersonDao;
import org.web.dao.impl.PersonDaoImpl;

public class DaoImplFactory {
	public static PersonDao getPersonDaoInstance() {
		return new PersonDaoImpl();
	}
}
