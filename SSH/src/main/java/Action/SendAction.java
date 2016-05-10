package Action;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 用来请求转发的Action
 * @author zhang
 *
 */
public class SendAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public SendAction() {
	System.out.println("=====sendAction==========");	
}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
