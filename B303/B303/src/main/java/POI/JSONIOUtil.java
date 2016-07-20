package POI;
 

import java.io.*;

/** 
 * 
 * @author zhang
 * @Date  2016年7月17日 上午9:15:00
 * @doing 
 */

public class JSONIOUtil {
    
	/**
	 * //从给定位置读取Json文件
	 * @param path 文件路径
	 * @return data String
	 */
    public static String readJson(String path){
        //从给定位置获取文件
        File file = new File(path);
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件流
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
    /**
     * 
     * @param path 文件路径
     * @param json data数据
     */
    public static void writeJsonPath(String path,Object json){
    	  BufferedWriter writer = null;
          // System.out.println("json Data:"+json.toString());
          // System.out.println("json Path:"+path+File.separator+fileName+".json");
           File file = new File(path);
           //如果文件不存在，则新建一个
           if(!file.exists()){
               try {
                   file.createNewFile();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           //写入
           try {
               writer = new BufferedWriter(new FileWriter(file));
               writer.write(json.toString());
           } catch (IOException e) {
               e.printStackTrace();
           }finally {
               try {
                   if(writer != null){
                       writer.close();
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           System.out.println("文件写入成功！");
    }
    /**
     * //给定路径与Json文件，存储到硬盘
     * @param path 文件路径
     * @param json 数据
     * @param fileName 文件名
     */
    public static void writeJson(String path,Object json,String fileName){
    	
    	writeJsonPath("path" +File.separator+ fileName + ".json" ,json);
    	
    }
}