package Util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;

/** 
 * 
 * @author zhang
 * @Date  2016年7月10日 下午4:36:29
 * @doing 
 */

public class ConvertUTF8BOMUtil {
	private static void convert(File file) {
        if (file.isDirectory()) {
            File[] ch = file.listFiles();
            for (int i = 0; i < ch.length; i++) {
                convert(ch[i]);
            }

        } else {
            if (file.getName().endsWith("jsp")) {
                try {
                    String s = FileUtils.readFileToString(file, "GBK");
                    System.out.println("convert " + file.getPath());
                    FileUtils.writeStringToFile(file, s, "UTF-8");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }
	 /**
    *
    * @param resultFileName
    */
   public static Set getFileNamesFromCompileResult(String resultFileName)
           throws IOException {
       Set<String> set = new HashSet();
       BufferedReader reader = new BufferedReader(new FileReader(
               resultFileName));
       String start = "[javac] ";
       int startLen = start.length();
       String end = ".java:";
       int endLen = end.length();

       String errMsg = "\\65279";
       while (reader.ready()) {
           String line = reader.readLine();
           int indexStart = line.indexOf(start);

           if (line.indexOf(errMsg) == -1) {
               continue;
           }
           if (indexStart != -1) {
               int indexEnd = line.indexOf(end);
               if (indexEnd != -1) {
                   String name = line.substring(indexStart + startLen,
                           indexEnd + endLen - 1);
                   set.add(name.trim());
               }
           }
       }
       return set;

   }

   public static void DealSrcFiles(String path) {
       if (path.charAt(path.length() - 1) != '\\') {
           path += '\\';
       }
       File file = new File(path);
       if (!file.exists()) {
           System.out.println("Error: Path not Existed! Please Check it out!");
           return;
       }
       String[] filelist = file.list();
       for (int i = 0; i < filelist.length; i++) {
           File temp = new File(path + filelist[i]);
           if ((temp.isDirectory() && !temp.isHidden() && temp.exists())) {
               DealSrcFiles(path + filelist[i]);
           } else {
               if (filelist[i].endsWith(".java")) {
                   try {
                       trimBom(path + filelist[i]);
                   } catch (Exception eee) {
                   }
               }
           }
       }
   }

   /**
    * 读取流中前面的字符，看是否有bom，如果有bom，将bom头先读掉丢弃
    *
    * @param in
    * @return
    * @throws java.io.IOException
    */
   public static InputStream getInputStream(InputStream in) throws IOException {

       PushbackInputStream testin = new PushbackInputStream(in);
       int ch = testin.read();
       if (ch != 0xEF) {
           testin.unread(ch);
       } else if ((ch = testin.read()) != 0xBB) {
           testin.unread(ch);
           testin.unread(0xef);
       } else if ((ch = testin.read()) != 0xBF) {
           throw new IOException("错误的UTF-8格式文件");
       } else {
//不需要做，这里是bom头被读完了
//// System.out.println("still exist bom");
       }
       return testin;

   }

   /**
    * 根据一个文件名，读取完文件，干掉bom头。
    *
    * @param fileName
    * @throws java.io.IOException
    */
   public static void trimBom(String fileName) throws IOException {

       FileInputStream fin = new FileInputStream(fileName);
//开始写临时文件
       InputStream in = getInputStream(fin);
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       byte b[] = new byte[4096];

       int len = 0;
       while (in.available() > 0) {
           len = in.read(b, 0, 4096);
//           out.write(b, 0, len);
           bos.write(b, 0, len);
       }

       in.close();
       fin.close();
       bos.close();

//临时文件写完，开始将临时文件写回本文件。
       System.out.println("[" + fileName + "]");
       FileOutputStream out = new FileOutputStream(fileName);
       out.write(bos.toByteArray());
       out.close();
       System.out.println("处理文件" + fileName);
   }

   /**
    * 根据ant编译错误来去除bom
    *
    * @param resultFile
    * @throws java.io.IOException
    */
   static void trimBomByCompileResult(String resultFile) throws IOException {
       Set<String> set = getFileNamesFromCompileResult(resultFile);
       for (String fName : set) {
           trimBom(fName);
       }
   }

   public static void main(String[] args) throws IOException {
	   trimBom("D:/Project/SSH/src/main/java/Action/UserAction.java");
       //DealSrcFiles("D:\\Project\\SSH\\src\\main\\java\\DaoImpl\\PriceDaoImpl.java");
       //D:\\Project\\SSH\\src\\main\\java\\DaoImpl\\PriceDaoImpl.java
       //D:/Project/SSH/src/main/java/DaoImpl/PriceDaoImpl.java
   }
}
