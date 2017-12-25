package org.web.action;
import java.util.Map;

import Model.UploadFile;
import Util.UploadUtil;

public class UtilAction extends SuperAction<UploadFile> {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> jsonMap;
    
	public String upload() throws Exception{
			model.setSaveFilePath("E:");
			UploadUtil.uploadFile(model);
			return "upload";
		}
		public String luceneSearch() throws Exception{
		        //
            return "search";
        }
		//发邮件
//		public String sendMessage(){
//			SendEmailUtil.sendEmail(model);
//			return "sendMessage";
//		}
		
}
