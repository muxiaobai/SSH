package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * 
 * @author zhang
 * @Date  2016年7月17日 上午10:56:45
 * @doing 
 */

public class OrderNumberUtil {
	public static String getOrderNumber(){
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS");  
		return df.format(new Date());
	}
}
