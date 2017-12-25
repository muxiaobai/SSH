 package org.web.lucene;
/**
 * Project Name:SSH
 * File Name:Lucenedemo.java
 * Package Name:org.web.lucene
 * Date:2017年12月25日上午8:54:51
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

/**
 * ClassName:Lucenedemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月25日 上午8:54:51 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.aspectj.weaver.ast.Var;
import org.hibernate.internal.util.xml.XmlDocument;
import org.web.action.PersonAction;

import Util.Constant;
import Util.lucene.LuceneUtil;


public class LuceneDemo {
    private  static Logger logger = Logger.getLogger(PersonAction.class);  
    public LuceneDemo() {
        logger.info("实例化:"+LuceneDemo.class);
        try {
//                this.index();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        
        
        
        LuceneDemo demo = new LuceneDemo();
        demo.getLink("<li><a href=\"info/1013/5343446.htm\" target=\"_blank\">【教授】追寻红记忆赓续红色血脉</a></li><li><a href=\"info/1013/50246.htm\" target=\"_blank\">【走近老教授】追寻红记忆 赓续红色血脉</a></li><li><a href=\"info/1013/50247.htm\" target=\"_blank\">【走近老教授】“外院学子与老党员同志面对面”主...</a></li>");
//                demo.index();
//                demo.searchLink(Constant.SEARCH_FILE);
//                demo.seach("网",0,10);
    }
    public void index() throws  Exception{//创建索引
         IndexWriter writer=null;
         try {
             //1.创建directory,保存索引,可以保存在内存中也可以保存在硬盘上
             //保存在内存中使用Directory directory=new RAMDirectory();
             Directory directory= FSDirectory.open(Paths.get(Constant.INDEX_URL));
              //2.1创建indexwriterConfig,并指定分词器版本
             new ChineseAnalyzer();
             IndexWriterConfig config=new IndexWriterConfig(new SimpleAnalyzer());
             //2,创建IndexWriter,indexWriter,需要使用indexConfig,
             writer= new IndexWriter(directory, config);
             //3.创建document
//             searchFile(writer,Constant.SEARCH_PATH);
             searchLink(writer,Constant.SEARCH_FILE);
         }catch (Exception e){
             throw e;
         }finally {
             if(writer!=null)writer.close();
         }
     }
      public  void searchFile(IndexWriter index,String file) throws IOException{
          File docDirectory=new File(file);
          for(File files: docDirectory.listFiles()){
              if(files.isDirectory()){
                  this.searchFile(index,files.getPath());
              }
              if(files.isFile()){
                  Document doc=null;
                  doc=new Document();
                  //创建搜索域,并说明是否进行分词
                  doc.add(new TextField("content",new FileReader(files)));
                  doc.add(new StringField("filename", files.getName(), Store.YES));
                  doc.add(new StringField("path",files.getAbsolutePath(), Store.YES));
                  //写入文档
                  index.addDocument(doc);
             }
          }
      }
      public  void searchLink(IndexWriter index,String file) throws IOException{
          String  str = this.readfile(file);
         List<Map<String , String>> cont =  getLink(str);
          for (Iterator iterator1 = cont.iterator(); iterator1.hasNext();) {
            Map<String, String> map = (Map<String, String>) iterator1.next();
            Document doc=null;
            doc=new Document();
            //创建搜索域,并说明是否进行分词
            doc.add(new TextField("href",map.get("href"), Store.YES));
            doc.add(new StringField("title", map.get("title"), Store.YES));
            //写入文档
            index.addDocument(doc);
        }
      }
      public void searchLink(String file) throws FileNotFoundException{
          String  str = this.readfile(file);
          getLink(str);
      }
      public String readfile(String filePath){
          File file = new File(filePath);  
          InputStream input = null;
          try {
              input = new FileInputStream(file);
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }  
          StringBuffer buffer = new StringBuffer();  
          byte[] bytes = new byte[1024];
          try {
              for(int n ; (n = input.read(bytes))!=-1 ; ){  
                  buffer.append(new String(bytes,0,n,"UTF-8"));  
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
          System.out.println(buffer);
          return buffer.toString();
      }
      //正则未完成
       public List<Map<String, String>> getLink(String html) {
//          String regExp = "<a[^>]*>([^<]*)</a>";
            String regExp = "<a[^>]+.*?>([^<]*)</a>";
//          String regExp = " <a[\\s]+href[\\s]*=[\\s]*\"([^<\"]+)\"";
//          String regExp = "<a\\s+.*?href=\"\"([^\"\"]*)\"\"\\s+.*?title=\"\"([^\"\"]*)\"\".*?>";
//            <a[^>]*>([^<]*)</a>
            Pattern p=Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
            Matcher matcher=p.matcher(html);
            List<Map<String, String>> resultList = new ArrayList<Map<String,String>>();
            System.out.println(matcher.groupCount());
            while(matcher.find()){
                    String a=matcher.group(0);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("href", matcher.group(0));
                    map.put("title", matcher.group(0));
                    resultList.add(map);
            }
//            while(matcher.find()){
//                for(int i =0;i<matcher.groupCount();i++){
//                    System.out.println(matcher.group(0));
//                    String a=matcher.group(0);
//                    Map<String, String> map = new HashMap<String, String>();
//                    map.put("href", matcher.group(i));
//                    map.put("title", matcher.group(i));
//                    resultList.add(map);
//                }
//             }
            System.out.println(resultList);
            return resultList;
      }
     public List<String> seach(String keywords, int start, int rows){//搜索索引
         IndexReader indexReader=null;
         List<String> result = new ArrayList<String>();
         try {
             //.1创建索引在的文件夹
             Directory indexDirectory=FSDirectory.open(Paths.get(Constant.INDEX_URL));
             //2.创建indexReader
             indexReader=DirectoryReader.open(indexDirectory);
             //3:根据indexReader创建indexSeacher
             IndexSearcher searcher=new IndexSearcher(indexReader);
             //4创建搜索用的query,指定搜索域
             String fields[] = {"name","content"};
//             QueryParser parser=new QueryParser(Version.LUCENE_7_1_0,"content",
//                     new SimpleAnalyzer(Version.LUCENE_40));
             QueryParser parser = new MultiFieldQueryParser(fields, LuceneUtil.getAnalyzer());

             Query query=parser.parse(keywords);
             TopScoreDocCollector collector = TopScoreDocCollector.create(10);
             //5,根据seacher搜索
            searcher.search(query,collector);
             ScoreDoc[] scoreDocs=collector.topDocs().scoreDocs;

             for (ScoreDoc sd:scoreDocs){
                 //6:根据seacher和scoredoc对象获取具体的document对象
                 Document doc=searcher.doc(sd.doc);
                 System.out.println(doc.get("filename")+"---------"+doc.get("path"));
                 result.add(doc.get("path"));
             }
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             if(indexReader!=null) try {
                 indexReader.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return result;
         }

     }
     
}