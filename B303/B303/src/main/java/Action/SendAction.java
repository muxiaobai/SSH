package Action;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 用来请求转发的Action
 * 
 * @author zhang
 *
 */
public class SendAction extends ActionSupport
		implements ApplicationContextAware, ServletRequestAware, ServletResponseAware, ServletContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;// 请求对象
	protected HttpServletResponse response;// 响应对象
	protected HttpSession session;// 会话对象
	protected ServletContext application;// 全局对象
	protected ApplicationContext ctx;
	private String  tip="";
	private Map<String, Object> jsonMap;
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	
}
