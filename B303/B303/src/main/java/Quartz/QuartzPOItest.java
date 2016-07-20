package Quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.User;
import Util.DateUtil;
import Util.POIWriteUtil;

/** 
 * 
 * @author zhang
 * @Date  2016年7月18日 下午4:17:16
 * @doing 
 */

public class QuartzPOItest {
	public void filePOI(){
		List<User> users=new ArrayList<>();
		User user=new User();
        user.setCity("徐州");
        user.setAddress("中国矿业大学南湖校区");
        user.setProvince("江苏");
        //user.setDate(new Date());
        user.setId(23233);
        user.setPassword("摩斯密码");
        user.setName("dfsssssss");
        users.add(user);
        User user2=new User();
        user2.setCity("徐州");
        user2.setAddress("中国矿业大学南湖校区");
        user2.setProvince("江苏");
        //user.setDate(new Date());
        user2.setId(2322233);
        user2.setPassword("摩斯密码");
        user2.setName("dfsssssss");
        users.add(user2);
		String fileName=POIWriteUtil.makeBalance(users,"",DateUtil.format("yyyyMMddHHmmss", new Date())+".xls");
		System.out.println(fileName);
	}
	public static void main(String[] args) {
		QuartzPOItest quartzPOItest=new QuartzPOItest();
		quartzPOItest.filePOI();
	}
}
