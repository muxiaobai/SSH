/**
 * Project Name:SSH
 * File Name:LunceneUtil.java
 * Package Name:Util
 * Date:2017年11月19日下午4:31:07
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package Util.lucene;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import Util.Constant;
/**
 * ClassName:LunceneUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年11月19日 下午4:31:07 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */


public class LuceneUtil {
    private static Directory directory_sp = null;

    private static IndexWriterConfig config = null;

    private static Version matchVersion = null;

    private static Analyzer analyzer = null;

    private static Directory ramDirectory = null;
    private  static SimpleHTMLFormatter htmlFormatter=new SimpleHTMLFormatter("<font color=\"red\">","</font>");  
    private static Logger logger = Logger.getLogger(LuceneUtil.class);   
    static {
        try {
            directory_sp = FSDirectory.open(Paths.get(Constant.INDEX_URL));
            matchVersion = Version.LUCENE_7_1_0;
            analyzer =  new StandardAnalyzer();
            config = new IndexWriterConfig( analyzer);
            System.out.println("directory_sp    " + directory_sp);
            // 创建内存索引库
            ramDirectory = new RAMDirectory();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("IO异常！");
        }
    }
    /*
     * 返回用于操作索引的对象
     * */
    public static IndexWriter getIndexWriterOfSP() throws IOException {

        IndexWriter indexWriter = new IndexWriter(directory_sp, config);

        return indexWriter;
    }


    /*
     * 返回用于读取索引的对象
     * */
    public static IndexSearcher getIndexSearcherOfSP() throws IOException {

        System.out.println("directory_sp:" + directory_sp);
        IndexReader indexReader = DirectoryReader.open(directory_sp);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        return indexSearcher;
    }

    /*
     * 获取lucene当前的版本
     * */
    public static Version getMatchVersion() {
        return matchVersion;
    }

    /*
     * 获取分词器
     * */
    public static Analyzer getAnalyzer() {
        return analyzer;
    }

}
