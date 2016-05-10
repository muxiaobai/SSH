package Util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Model.Email;

public class SendEmailUtil {
	public static final String EMAIL_FROM = "zpf12345678910@qq.com";
	public static final String EMAIL_FROMHOST = "smtp.qq.com";
	public static final String EMAIL_FROMPORT = "465";
	public static final String EMAIL_FROMPASSWORD = "wiqrwybnkgjibaha";
	public static final String EMAIL_SUBJECT = "wiqrwybnkgjibaha";
	
	public static void sendEmail(Email email) {
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", (email.getFromHost()==null||"".equals(email.getFromHost())) ? EMAIL_FROMHOST : email.getFromHost());
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.port", (email.getFromPort()==null||"".equals(email.getFromPort())) ? EMAIL_FROMPORT : email.getFromPort());
		prop.setProperty("mail.smtp.socketFactory.port",
				(email.getFromPort()==null||"".equals(email.getFromPort())) ? EMAIL_FROMPORT : email.getFromPort());
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication((email.getFrom()==null||"".equals(email.getFrom())) ? EMAIL_FROM : email.getFrom(),
						(email.getFromPassword()==null||"".equals(email.getFromPassword())) ? EMAIL_FROMPASSWORD : email.getFromPassword());
			}
		});
		Message message = new MimeMessage(session);
		try {
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
			message.setFrom(new InternetAddress((email.getFrom()==null||"".equals(email.getFrom())) ? EMAIL_FROM : email.getFrom()));
			message.setText(email.getContent());
			message.setSubject((email.getSubject()==null||"".equals(email.getSubject()))?EMAIL_SUBJECT:email.getSubject());
			message.setSentDate((email.getSendDate()==null||"".equals(email.getSendDate()))?new Date():email.getSendDate());
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
