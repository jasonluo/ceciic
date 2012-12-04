package com.vangent.tap.app.batch.jmx;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.CronTriggerBean;

public class BeanPropertyOverrideConfigurer extends PropertyOverrideConfigurer implements ApplicationContextAware
{
   private static Logger log = Logger.getLogger(BeanPropertyOverrideConfigurer.class);
            
   private ApplicationContext applicationContext;

   @Override
   protected void applyPropertyValue(ConfigurableListableBeanFactory factory, String beanName, String property, String value) 
   {
       super.applyPropertyValue(factory, beanName, property, value);
       
       Object obj = this.applicationContext.getBean(beanName);
       
       //TODO use reflection to update bean property
       if(obj instanceof CronTriggerBean)
       {
           CronTriggerBean cb = (CronTriggerBean)obj;
           
           try
           {
               log.debug("set cron expression: trigger name:"+beanName+ " trigger value:"+value);
               cb.setCronExpression(value);
           }
           catch (ParseException e)
           {
              log.error("",e);
           }
       }
   }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
        
    }

}
