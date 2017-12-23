/**
 * Project Name:TyspV3.0DevelopProject_FS
 * File Name:TomcatListener.java
 * Package Name:com.tjsoft.system.listener
 * Date:2017年12月15日上午9:32:59
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package system.listener;
/**
 * Tomcat 的启动和关闭情况通知
 * ClassName:TomcatListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月15日 上午9:32:59 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class TomcatListener implements ServletContextListener{
     private static Logger logger = Logger.getLogger(TomcatListener.class);  

    /**
     * 监听web容器关闭
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("web容器关闭");
        logger.warn(sce.getSource());
        logger.info("close Tomcat Service!");

    }

    /**
     * 监听web容器启动
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("web容器启动");
        logger.warn(sce.getSource());
        logger.info("start Tomcat Service!"+logger.toString());

    }

}