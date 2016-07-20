package Model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import Enum.NormalStatus;

/**
 * 用户
 * @author zhang
 * @date  2016年5月28日 下午5:34:21
 * @doing TODO
 * 用户：用户名，密码，头像，手机号 身份证号，军官证号，护照号信息，
 * （收藏和用户常用联系人信息）是类，关联用户，身份信息，注册时间,
 * 赞<List<线路点评>>,被关注<List<用户>>,关注<List<用户>>，收藏嗨记<list<嗨记>>。
 */
@Entity
//连接子类的映射策略
//@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="t_user")
//@Where(clause="status <> 0 ")     
@DynamicInsert
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	//用户名
	private String username;
	private String password;
	private String mobile;
	private String sex;
	private Integer age;
	private String name;
	private Date date;
	private String address;
	private  String tel;
	private String province;
	private String city;
	private String street;
	@Column(name = "status", columnDefinition = "INT default 1")
	@Enumerated(EnumType.ORDINAL)
	private NormalStatus status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public NormalStatus getStatus() {
		return status;
	}
	public void setStatus(NormalStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", mobile=" + mobile + ", sex="
				+ sex + ", age=" + age + ", name=" + name + ", date=" + date + ", address=" + address + ", tel=" + tel
				+ ", province=" + province + ", city=" + city + ", street=" + street + ", status=" + status + "]";
	}
	
	
	
	

	
}
