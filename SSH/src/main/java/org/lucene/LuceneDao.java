/**
 * Project Name:SSH
 * File Name:LuceneDao.java
 * Package Name:org.lucene
 * Date:2017年11月19日下午4:40:07
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import Util.Constant;
import Util.lucene.ArcticleUtil;
import Util.lucene.LuceneUtil;
import Util.lucene.SynonymAnalyzerUtil;

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
public class LuceneDao {
    /*
     * 建立索引
     * */
    public void addIndex(Arcticle Arcticle) throws IOException {

        IndexWriter indexWriter = LuceneUtil.getIndexWriterOfSP();
        Document doc = ArcticleUtil.ArcticleToDocument(Arcticle);
        indexWriter.addDocument(doc);
//              indexWriter.forceMerge(10);//合并cfs文件。比如设定1，就是自动合并成一个索引cfs文件
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
    public void updateIndex(String fieldName,String fieldValue,Arcticle Arcticle) throws IOException {

        IndexWriter indexWriter = LuceneUtil.getIndexWriterOfSP();

        Term term = new Term(fieldName,fieldValue);

        Document document = ArcticleUtil.ArcticleToDocument(Arcticle);

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
    public List<Arcticle> findIndex(String keywords, int start, int rows) throws Exception {

        Directory directory = FSDirectory.open(Paths.get(Constant.INDEXURL_ALL));//索引创建在硬盘上。
        IndexSearcher indexSearcher =  LuceneUtil.getIndexSearcherOfSP();

        /**同义词处理*/
        String result ="";
//        String result = SynonymAnalyzerUtil.displayTokens(SynonymAnalyzerUtil.convertSynonym(SynonymAnalyzerUtil.analyzeChinese(keywords, true)));
//        Analyzer analyzer4 = new IKAnalyzer(false);// 普通简陋语意分词
//        String result = keywords;
        //需要根据哪几个字段进行检索...
        String fields[] = {"goodName"};

        //查询分析程序（查询解析）
        QueryParser queryParser = new MultiFieldQueryParser(fields, LuceneUtil.getAnalyzer());

        //不同的规则构造不同的子类...
        //title:keywords content:keywords
        Query query = queryParser.parse(result);

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
        List<Arcticle> Arcticlelist = new ArrayList<Arcticle>();
        //防止数组溢出
        int endResult = Math.min(scoreDoc.length, start+rows);
        Arcticle Arcticle = null;

        for(int i = start;i < endResult ;i++ ){
            Arcticle = new Arcticle();
            //docID lucene的索引库里面有很多的document，lucene为每个document定义了一个编号，唯一标识，自增长
            int docID = scoreDoc[i].doc;
            System.out.println("标识docID="+docID);
            Document document = indexSearcher.doc(docID);
            /**获取文字高亮的信息begin*/
            System.out.println("==========================");
            TokenStream tokenStream = LuceneUtil.getAnalyzer().tokenStream("goodName", new StringReader(document.get("goodName")));
            String goodName = highlighter.getBestFragment(tokenStream, document.get("goodName"));
            System.out.println("goodName="+goodName);
            System.out.println("==========================");
            /**获取文字高亮的信息end*/

            //备注：document.get("id")的返回值是String
            Arcticle.setTitle((document.get("id")));
            Arcticle.setDescription(goodName);
            Arcticlelist.add(Arcticle);
        }
        return Arcticlelist;
    }
}

