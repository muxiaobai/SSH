package DaoImpl;

import javax.persistence.Cacheable;

import Dao.PersonDao;
import Entity.Person;
@Cacheable
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao  {
	// 子类实例化的时候会调用父类的构造方法
		public PersonDaoImpl(){
			super();
		}
}
