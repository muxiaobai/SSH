package Util;

import org.junit.Test;
import Model.Email;

public class SendMailUtilTest {
	@Test
	public void sendEmail(){
		Email email=new Email();
		email.setTo("zpf12345678910@126.com");
		email.setContent("测试");
		SendEmailUtil.sendEmail(email);
	}
}
