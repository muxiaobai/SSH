package Action;
import Model.UploadFile;
import Util.UploadUtil;

public class UtilAction extends SuperAction<UploadFile> {
	private static final long serialVersionUID = 1L;
		public String upload() throws Exception{
			model.setSaveFilePath("E:");
			UploadUtil.uploadFile(model);
			return "upload";
		}
		//发邮件
//		public String sendMessage(){
//			SendEmailUtil.sendEmail(model);
//			return "sendMessage";
//		}
}
