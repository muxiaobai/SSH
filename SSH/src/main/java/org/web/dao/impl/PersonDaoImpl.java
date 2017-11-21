package org.web.dao.impl;

import org.apache.log4j.Logger;
import org.web.dao.PersonDao;
import org.web.entity.Person;


public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao  {
	// 子类实例化的时候会调用父类的构造方法
    private static Logger logger = Logger.getLogger(PersonDaoImpl.class);  
		public PersonDaoImpl(){
			super();
			logger.info(getClass());
		}
}
