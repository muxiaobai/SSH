package org.web.service;

import java.util.List;

import org.web.entity.Person;

public interface PersonService  {
    public List<Person> getPersonList() throws Exception;
    public Person getPerson() throws Exception;
    
}
