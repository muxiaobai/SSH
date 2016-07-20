package Action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import Model.Email;
import Util.SendEmailUtil;

public class SendEmailAction extends BaseAction<Email> {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> jsonMap=new HashMap<String, Object>();
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}


	/**
	 * 发邮件
	 * @return
	 */
	public String send() {
		model.setSendDate(new Date());
		SendEmailUtil.sendEmail(model);
		System.out.println("=====model:=========" + model);
		return "send";
	}

}
