package com.vangent.tap.app.batch;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 
 * add this entry to web.xml
 * 
 * <context-param>
        <param-name>contextClass</param-name>
        <param-value>
            com.vangent.tap.app.batch.UniqueBeanIdContextLoader
        </param-value>
    </context-param>
 *
 */

public class UniqueBeanIdContextLoader extends XmlWebApplicationContext
{
    private static Logger log = Logger.getLogger(UniqueBeanIdContextLoader.class);

   public UniqueBeanIdContextLoader()
   {
       super();
       log.debug("helolo....");
       /**
        * set this property to true will allow duplicate bean id from different configuration file.
        * set it to false will cause exception if duplicate id exists.
        * 
        * Seeems no duplicate id allowed from any single configuration file.
        * 
        */
       this.setAllowBeanDefinitionOverriding(true);
   }

}
