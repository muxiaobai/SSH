/**
 * Project Name:SSH
 * File Name:Archile.java
 * Package Name:Util.lucene
 * Date:2017年11月21日上午10:19:19
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package Util.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.lucene.Arcticle;

/**
 * ClassName:Archile <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年11月21日 上午10:19:19 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ArcticleUtil {
    
    public static Document ArcticleToDocument(Arcticle arcticle) {
    Document document = new Document();
    StringField idfield = new StringField("id", arcticle.getId(), Store.YES);
    TextField title = new TextField("title", arcticle.getTitle(),Store.YES);
    TextField escription = new TextField("title", arcticle.getDescription(),Store.YES);
    document.add(idfield);
    document.add(title);
    document.add(escription);
    return document;
    }
}

