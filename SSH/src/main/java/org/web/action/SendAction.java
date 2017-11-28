package org.web.action;

import org.apache.log4j.Logger;

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
	private static Logger logger = Logger.getLogger(SendAction.class);  
    public SendAction() {
        logger.info("sendAction:"+SendAction.class);
    }
	@Override
	public String execute() throws Exception {
	    System.out.println(session);
		return SUCCESS;
	}
}
