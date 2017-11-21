package org.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.web.entity.Person;
import org.web.service.PersonService;



public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
	public List<Person> findAll() {
//		return DaoImplFactory.getPersonDaoInstance().findAll();
		return new ArrayList<Person>();
	}
}
