package org.web.action;

/**
 * 用来请求转发的Action
 * @author zhang
 *
 */
public class SendAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public SendAction() {
	System.out.println("=====sendAction==========");	
}
	@Override
	public String execute() throws Exception {
	    System.out.println(session);
		return SUCCESS;
	}
}
