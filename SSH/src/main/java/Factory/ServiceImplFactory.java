package Factory;

import Service.PersonService;
import ServiceImpl.PersonServiceImpl;

public class ServiceImplFactory {
	public static PersonService getPersonServiceInstance() {
		return new PersonServiceImpl();
	}
}
