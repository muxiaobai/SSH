/**
 * Project Name:SSH
 * File Name:Arcticle.java
 * Package Name:org.lucene
 * Date:2017年11月19日下午5:12:20
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.lucene;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ClassName:Arcticle <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年11月19日 下午5:12:20 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Arcticle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    private String title;  
    private String description;  
    private String url;  
    private String createTime;  
    private String filePath;  
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {  
        return title;  
    }  
    public void setTitle(String title) {  
        this.title = title;  
    }  
    public String getDescription() {  
        return description;  
    }  
    public void setDescription(String description) {  
        this.description = description;  
    }  
    public String getUrl() {  
        return url;  
    }  
    public void setUrl(String url) {  
        this.url = url;  
    }  
    public String getCreateTime() {  
        return createTime;  
    }  
    public void setCreateTime(String createTime) {  
        this.createTime = createTime;  
    }  
    public String getFilePath() {  
        return filePath;  
    }  
    public void setFilePath(String filePath) {  
        this.filePath = filePath;  
    }  
      
}

