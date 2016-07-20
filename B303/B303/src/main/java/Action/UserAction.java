package Action;

import java.util.HashMap;
import java.util.Map;
import Enum.NormalStatus;
import Model.User;
import Service.UserService;

public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;
	UserService userService;
	private Map<String, Object> jsonMap=new HashMap<String, Object>();
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public String checkUsername() throws Exception {
		User user=userService.findByUsername(model.getUsername());
		if (user == null) {
			//System.out.println("result:" + user);
			jsonMap.put("flag",false);
		} else {
			//System.out.println("result: 232323Null");
			jsonMap.put("flag", true);
		}
		return "checkUsername";
	}

	/**
	 * 新增Staff
	 * 
	 * @return
	 */
	public String save() {
		if (model.getId() == null || (model.getId() instanceof Integer && model.getId() == 0)) {
			model.setStatus(NormalStatus.NORMAL);
			userService.save(model);
		}
		//System.out.println("=====model:=========" + model);
		return "save";
	}
	/**
	 * 登录
	 * @return
	 */
	public String login() {
		if (userService.login(model)) {
			return "login";
		}else {
			request.setAttribute("flag", false);
			addActionError("用户名密码不匹配");
			return ERROR;
		}
	
	}

}
