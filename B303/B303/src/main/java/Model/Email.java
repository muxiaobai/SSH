package Model;

import java.io.Serializable;
import java.util.Date;
/**
 * 发送Email模型
 * @author zhang
 *
 */
public class Email implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String from;//发送email地址
	private String fromPassword;//发送email密码
	private String fromHost;//发送email主机
	private String fromPort;//发送email端口
	private String to;//收信email
	private Date sendDate;//发送时间
	private String content;//发送内容
	private String subject;//发送内容
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getFromPassword() {
		return fromPassword;
	}
	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}
	
	public String getFromHost() {
		return fromHost;
	}
	public void setFromHost(String fromHost) {
		this.fromHost = fromHost;
	}
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Email [from=" + from + ", fromPassword=" + fromPassword + ", fromHost=" + fromHost + ", fromPort="
				+ fromPort + ", to=" + to + ", sendDate=" + sendDate + ", content=" + content + ", subject=" + subject
				+ "]";
	}
	
}
