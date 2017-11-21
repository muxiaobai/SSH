/**
 * Project Name:SSH
 * File Name:Luceneutil.java
 * Package Name:Util
 * Date:2017年11月21日下午2:14:29
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package Util;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.function.valuesource.IntFieldSource;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * ClassName:Luceneutil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年11月21日 下午2:14:29 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Luceneutil {
    public static void main(String[] args) {
        Luceneutil luceneutil = new  Luceneutil();
        try {
            luceneutil.createIndex();
            luceneutil.searchIndex();
        } catch (Exception e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
    }
    public void createIndex() throws Exception {   
        // 索引存放位置       
        Directory directory = FSDirectory.open(Paths.get(Constant.INDEXURL_ALL));  
        // 版本        
        Version version = Version.LUCENE_7_1_0;        
        // 分词器   
        Analyzer analyzer = new StandardAnalyzer();       
        // 索引配置       
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);        
        // 创建操作索引对象        
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);        
        // 创建document        
        Document document = new Document();        
//        IndexableField intField = new IntFieldSource("age", 123, Store.YES);       
        IndexableField strField = new StringField("name", "张三", Store.YES);        
        IndexableField txtField = new TextField("content", "张三是个教师!", Store.YES);        
//        document.add(intField);        
        document.add(strField);        
        document.add(txtField);        
        indexWriter.addDocument(document);        
        indexWriter.close();    
     }
     public void searchIndex() throws Exception {        
        // 索引存放位置       
        Directory directory = FSDirectory.open(Paths.get(Constant.INDEXURL_ALL));        
        IndexReader indexReader = DirectoryReader.open(directory);        
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);       
        Query query = new TermQuery(new Term("content", "教"));        
        TopDocs topDocs = indexSearcher.search(query, 10);        
        Long count = topDocs.totalHits;        
        System.out.println("count:" + count);        
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;       
        Document document = null;        
        for (ScoreDoc scoreDoc : scoreDocs) {            
            int docId = scoreDoc.doc;            
            document = indexSearcher.doc(docId);            
            String age = document.get("age");            
            String name = document.get("name");            
            String content = document.get("content");            
            System.out.println("age:" + age + ",name:" + name + ",content:"                    + content);       
        }   
   }
}

