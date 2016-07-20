package POI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import Model.User;

/** 
 * 
 * @author zhang
 * @Date  2016年7月17日 上午9:04:23
 * @doing 
 */
  
public class JSONIOTest {  
  
    /** 
     * @param args 
     * @throws JSONException 
     * @throws IOException 
     */  
    public static void main(String[] args) throws JSONException, IOException {  
    	//User user=(User) readJson("./src/main/java/user.json");
    	@SuppressWarnings("unchecked")
		List<User> users=JSON.parseObject(JSONIOUtil.readJson("./src/main/java/user.json"), List.class);
    	System.out.println(users);
    	//System.out.println(user.getAddress());
    	//writeJsonTest();
    }  
    /**
     * 测试
     * @param path "D:"+File.separator+"user.json"
     * @return
     */
    public static Object readJson(String path){
    	   User jsonuser = JSON.parseObject(JSONIOUtil.readJson(path), User.class); // deserializes json into model2
    	return jsonuser;
    }
    /**
     * 测试
     */
    public static void writeJsonTest(){
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
         String json = JSON.toJSONString(users); // serializes model to Json
         JSONIOUtil.writeJsonPath("./src/main/java/user.json", json);
        // JSONIOUtil.writeJsonPath("d:"+File.separator+"user.json", json);
    	// JSONIOUtil.writeJson( "d:", json, "user");
    }
    
   
}  