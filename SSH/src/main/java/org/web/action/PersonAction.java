package org.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.web.dao.PersonDao;
import org.web.entity.Person;
import org.web.lucene.LucenePerson;

/**
 *  Person_xxx
 * ClassName: PersonAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年11月28日 下午7:44:05 <br/>
 *
 * @author  Mu Xiaobai
 * @version 
 * @since JDK 1.6
 */
public class PersonAction extends SuperAction<Person> {
	public PersonAction() {
		super();
		getLogger().info("实例化:"+PersonAction.class);
	}

	private static final long serialVersionUID = 1L;
	 
	private Integer page;
	private Integer rows;
	private String ids;
	private Map<String, Object> jsonMap;
	private LucenePerson lucenePerson;
	public LucenePerson getLucenePerson() {
        return lucenePerson;
    }
	public void setLucenePerson(LucenePerson lucenePerson) {
        this.lucenePerson = lucenePerson;
    }
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
	public String luceneSearch() {
        jsonMap = new HashMap<String, Object>();
        String content = request.getParameter("content");
        System.out.println(content);
        List<Person> persons = new ArrayList<Person>();
        try {
            persons = lucenePerson.findIndex(content, 0,10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonMap.put("rows", persons);
        jsonMap.put("total",persons.size());
//        System.out.println("page:" + page + ",rows:" + rows);
        return "luceneSearch";
    }
	public String query() {
		jsonMap = new HashMap<String, Object>();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		PersonDao personDaoImpl = (PersonDao) ctx.getBean("PersonDao");
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
