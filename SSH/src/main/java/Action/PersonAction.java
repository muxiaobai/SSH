package Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import Dao.PersonDao;
import Entity.Person;

public class PersonAction extends SuperAction<Person> {
	public PersonAction() {
		super();
		System.out.println("====personAction实例化=========");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer page;
	private Integer rows;
	private String ids;
	private Map<String, Object> jsonMap;
	
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	@Override
	public String execute() throws Exception {
		return "";
	}

	public String query() {
		jsonMap = new HashMap<String, Object>();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		PersonDao personDaoImpl = (PersonDao) ctx.getBean("personDaoImpl");
		List<Person> persons = personDaoImpl.findAll();
		jsonMap.put("rows", persons);
		jsonMap.put("total",persons.size());
		System.out.println("page:" + page + ",rows:" + rows);
		return "query";
	}
//	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
//	PersonDao personDaoImpl = (PersonDao) ctx.getBean("personDaoImpl");

	public String delete() {
		System.out.println(ids);
		return "delete";
	}
	public String save() throws Exception {
		System.out.println(model);
		return "save";
	}
	public String update() {
		System.out.println(model);
		return "update";
	}
}
