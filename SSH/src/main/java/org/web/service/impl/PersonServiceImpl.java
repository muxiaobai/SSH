package org.web.service.impl;

import java.util.List;

import org.web.dao.PersonDao;
import org.web.entity.Person;
import org.web.service.PersonService;



public class PersonServiceImpl implements PersonService {
    private PersonDao personDao;
    public PersonDao getPersonDao() {
        return personDao;
    }
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
	public List<Person> findAll() {
	    personDao.findAll();
		return personDao.findAll();
	}

    @Override
    public List<Person> getPersonList() throws Exception {
        return personDao.findAll();
    }

    @Override
    public Person getPerson() throws Exception {
        
        // TODO Auto-generated method stub
        return null;
    }
}
