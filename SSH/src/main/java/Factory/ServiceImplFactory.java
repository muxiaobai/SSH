package Factory;

import org.web.service.PersonService;
import org.web.service.impl.PersonServiceImpl;

public class ServiceImplFactory {
	public static PersonService getPersonServiceInstance() {
		return new PersonServiceImpl();
	}
}
