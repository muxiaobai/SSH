/**
 * Project Name:SSH
 * File Name:LuceneDao.java
 * Package Name:org.lucene
 * Date:2017年11月19日下午4:40:07
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.web.lucene;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.web.action.PersonAction;
import org.web.entity.Person;
import org.web.service.PersonService;

import Util.lucene.LuceneUtil;

/**
 * ClassName:LuceneDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年11月19日 下午4:40:07 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class LucenePerson {
    private  static Logger logger = Logger.getLogger(PersonAction.class);  
    private PersonService personService;
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    public PersonService getPersonService() {
        return personService;
    }
    public LucenePerson() {
        logger.info("实例化:"+LucenePerson.class);
        try {
            //It's not good ,because  It will create index every init.
//            List<Person> lists=personService.getPersonList();
//            for (Iterator iterator = lists.iterator(); iterator.hasNext();) {
//                Person person = (Person) iterator.next();
//                this.addIndex(person);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        LucenePerson lucenePerson = new LucenePerson();
        Person person = new Person();
        person.setName("zhang ");
        person.setPassword("password");
        try {
//            lucenePerson.addIndex(person);
            System.out.println(lucenePerson.findIndex("password", 0, 10));
        } catch (Exception e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
    }
    public static Document PersonToDocument(Person person) {
        Document document = new Document();
//      StringField idfield = new intField("id", person.getId(), Store.YES);
        TextField title = new TextField("name", person.getName(),Store.YES);
        TextField escription = new TextField("password", person.getPassword(),Store.YES);
        document.add(title);
        document.add(escription);
        return document;
        }
    /*
     * 建立索引
     * */
    public void addIndex(Person Person) throws IOException {

        IndexWriter indexWriter = LuceneUtil.getIndexWriterOfSP();
        Document doc = this.PersonToDocument(Person);
        indexWriter.addDocument(doc);
        indexWriter.forceMerge(10);//合并cfs文件。比如设定1，就是自动合并成一个索引cfs文件
        indexWriter.close();
    }


    /*
     * 删除索引，根据字段对应的值进行删除
     * */
    public void delIndex(String fieldName,String fieldValue) throws IOException {

        IndexWriter indexWriter = LuceneUtil.getIndexWriterOfSP();
        //term!!!
        Term term = new Term(fieldName,fieldValue);
        //根据字段对应的值删除索引
        indexWriter.deleteDocuments(term);
        indexWriter.close();
    }

    /*
     * 先删除符合条件的记录，再创建一个符合条件的记录
     * */
    public void updateIndex(String fieldName,String fieldValue,Person Person) throws IOException {
        IndexWriter indexWriter = LuceneUtil.getIndexWriterOfSP();
        Term term = new Term(fieldName,fieldValue);
        Document document = this.PersonToDocument(Person);

        /*
         * 1.设置更新的条件
         * 2.设置更新的内容的对象
         * */
        indexWriter.updateDocument(term, document);

        indexWriter.close();
    }

    /*
     * 分页：每页10条
     * */
    public List<Person> findIndex(String keywords, int start, int rows) throws Exception {
        IndexSearcher indexSearcher =  LuceneUtil.getIndexSearcherOfSP();
        /**同义词处理*/
//        String result ="";
//        String result = SynonymAnalyzerUtil.displayTokens(SynonymAnalyzerUtil.convertSynonym(SynonymAnalyzerUtil.analyzeChinese(keywords, true)));
//        Analyzer analyzer4 = new IKAnalyzer(false);// 普通简陋语意分词
        String result = keywords;
        //需要根据哪几个字段进行检索...
        String fields[] = {"name","password"};
        //查询分析程序（查询解析）
        QueryParser queryParser = new MultiFieldQueryParser(fields, LuceneUtil.getAnalyzer());
        //不同的规则构造不同的子类...
        //title:keywords content:keywords
        Query query = queryParser.parse(result);
//        Query query = new TermQuery(new Term("name", "l"));        
        //这里检索的是索引目录,会把整个索引目录都读取一遍
        //根据query查询，返回前N条
        TopDocs topDocs = indexSearcher.search(query, start+rows);
        System.out.println("总记录数="+topDocs.totalHits);
        ScoreDoc scoreDoc[] = topDocs.scoreDocs;
        /**添加设置文字高亮begin*/
        //htmly页面高亮显示的格式化，默认是<b></b>即加粗
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        //设置文字摘要（高亮的部分），此时摘要大小为10
        //int fragmentSize = 10;
        Fragmenter fragmenter = new SimpleFragmenter();
        highlighter.setTextFragmenter(fragmenter);
        /**添加设置文字高亮end*/
        List<Person> Personlist = new ArrayList<Person>();
        //防止数组溢出
        int endResult = Math.min(scoreDoc.length, start+rows);
        for(int i = start;i < endResult ;i++ ){
            Person  Person = new Person();
            //docID lucene的索引库里面有很多的document，lucene为每个document定义了一个编号，唯一标识，自增长
            int docID = scoreDoc[i].doc;
            System.out.println("标识docID="+docID);
            Document document = indexSearcher.doc(docID);
            /**获取文字高亮的信息begin*/
            System.out.println("==========================");
            TokenStream tokenStream = LuceneUtil.getAnalyzer().tokenStream("name", new StringReader(document.get("name")));
            String goodName = highlighter.getBestFragment(tokenStream, document.get("name"));
            System.out.println("name="+goodName);
            System.out.println("==========================");
            /**获取文字高亮的信息end*/

            //备注：document.get("id")的返回值是String
            Person.setName((document.get("name")));
            Person.setPassword(document.get("password"));
            Personlist.add(Person);
        }
        return Personlist;
    }
}

