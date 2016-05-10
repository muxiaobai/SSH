package ServiceImpl;

import java.util.List;

import Entity.Person;
import Factory.DaoImplFactory;
import Service.PersonService;

public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
	public List<Person> findAll() {
		return DaoImplFactory.getPersonDaoInstance().findAll();
	}
}
