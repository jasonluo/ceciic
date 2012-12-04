package com.vangent.tap.app.batch.jmx;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.stereotype.Component;




@Component
@ManagedResource(objectName="com.vangent.tap.app.batch.jmx:name=scheduleManager", description="scheduleManager.")
public class ScheduleManagerImpl implements ScheduleManager, ApplicationContextAware, BeanFactoryPostProcessor 
{
    private static Logger log = Logger.getLogger(ScheduleManagerImpl.class);
    
    private static ApplicationContext ctx; 
    
    private ConfigurableListableBeanFactory factory; 

    
    
    @ManagedOperation(description="dynamically reschedule a job.")
    @ManagedOperationParameters({
    @ManagedOperationParameter(name="triggerName", description= "Triger Name"),
    @ManagedOperationParameter(name="jobDetailsName", description= "Job Details Name"),
    @ManagedOperationParameter(name="cronExpression", description= "Cron Expression")})
    public void changeSchedule(String triggerName, String jobDetailsName, String cronExpression)
    {
        changeSchedule(triggerName, "DEFAULT", jobDetailsName, "quartz-batch", cronExpression);
       
    }
    
    
    @ManagedOperation(description="dynamically reschedule a job")
    @ManagedOperationParameters({
    @ManagedOperationParameter(name="triggerName", description= "Triger Name"),
    @ManagedOperationParameter(name="triggerGroup", description= "Trigger Group"),
    @ManagedOperationParameter(name="jobDetailsName", description= "Job Details Name"),
    @ManagedOperationParameter(name="jobGroup", description= "Job Group"),
    @ManagedOperationParameter(name="cronExpression", description= "Cron Expression")})
    public void changeSchedule(String triggerName, String triggerGroup, String jobDetailsName, String jobGroup, String cronExpression)
    {
        log.debug("jobName=="+jobDetailsName);
    
        org.quartz.Scheduler scheduler = (org.quartz.Scheduler)ctx.getBean("jobScheduler");
       
        try
        {
            String [] triggergroup = scheduler.getTriggerGroupNames();
            
            for(int i =0; i<triggergroup.length; i++)
            {
                log.debug("trigger group=="+triggergroup[i]);
            }
            
            String [] jobgroup = scheduler.getJobGroupNames();
            
            for(int i =0; i<jobgroup.length; i++)
            {
                log.debug("job group=="+jobgroup[i]);
            }
            
            String [] jobnames = scheduler.getJobNames("quartz-batch");
            for(int i =0; i<jobnames.length; i++)
            {
                log.debug("job names=="+jobnames[i]);
            }
            
            
            //scheduler.unscheduleJob("studentHistoryLoadCronTrigger","DEFAULT");
            
            Trigger cronTrigger = null;
            try
            {
               // cronTrigger = new org.quartz.CronTrigger("hisCronTrigger","DEFAULT","studentHistoryLoadJobDetail","quartz-batch",cronExpression);
                
                cronTrigger = new org.quartz.CronTrigger(triggerName,triggerGroup,jobDetailsName,jobGroup,cronExpression);
            }
            catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            //scheduler.scheduleJob(cronTrigger);
            scheduler.rescheduleJob("studentHistoryLoadCronTrigger", "DEFAULT", cronTrigger);
            
            //scheduler.rescheduleJob("studentHistoryLoadCronTrigger", "DEFAULT", new org.quartz.CronTrigger("studentHistoryLoadCronTrigger2","studentHistoryLoadJob","quartz-batch","0/30 * * * * ?"));
        }
        catch (SchedulerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       

    }

    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        setStaticApplicationContext(context);
        
    }
    
    public static void setStaticApplicationContext(ApplicationContext applicationContext) { 
        
        ctx = applicationContext; 
//        XmlWebApplicationContext ctx  = new XmlWebApplicationContext();
//        ctx.getBeanFactory().
        log.debug("ctx=="+ctx);

    }


    @ManagedOperation(description="dynamically reload")
    public void reloadSchedule()
    {
        //this is the bean to be re-created for the applcationContext.
       String beanName = "jobScheduleConfigurer";
       
       try{
           /**
            * Step 1. create "jobScheduleConfigurer" bean.
            * 
            * When jobScheduleConfigurer bean of type "BeanPropertyOverrideConfigurer" is created, it will override other bean properties just like
            * PropertyOverrideConfigurer does. However, PropertyOverrideConfigurer will only update Bean Definition and not update any bean already created.
            *  
            *  
            */
            BeanDefinitionRegistry registry = ((BeanDefinitionRegistry )factory);  
            
            BeanDefinition oldBeanDefinition = registry.getBeanDefinition("jobScheduleConfigurer");
            log.debug("old jobScheduleConfigurer==="+ctx.getBean("jobScheduleConfigurer"));
            
            //MutablePropertyValues propertyValues = new MutablePropertyValues() ; 
            //PropertyValue location = oldBeanDefinition.getPropertyValues().getPropertyValue("location");
            //propertyValues.addPropertyValue(location);
            
            MutablePropertyValues propertyValues = oldBeanDefinition.getPropertyValues();
           
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();  
            beanDefinition.setBeanClass(BeanPropertyOverrideConfigurer.class);  
            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setLazyInit(false);  
            beanDefinition.setAbstract(false);  
            beanDefinition.setAutowireCandidate(true);  
            beanDefinition.setScope(GenericBeanDefinition.SCOPE_SINGLETON);  
            
            //Optional?
            registry.removeBeanDefinition(beanName);
            //this will create a new instance of BeanPropertyOverrideConfigurer using its new bean definition
            registry.registerBeanDefinition(beanName,beanDefinition);  
            
            // Wrong!! do not create new instance of cron trigger bean.  just update its cronExpresion property.  see BeanPropertyOverrideConfigurer.
            //BeanDefinition bd = factory.getBeanDefinition("changeFormExtractCronTrigger");
            //registry.removeBeanDefinition("changeFormExtractCronTrigger");
            //registry.registerBeanDefinition("changeFormExtractCronTrigger", bd);
         
            BeanPropertyOverrideConfigurer beanPropertyOverrideConfigurer = (BeanPropertyOverrideConfigurer)ctx.getBean(beanName);
            log.debug("new jobScheduleConfigurer==="+beanPropertyOverrideConfigurer);
            beanPropertyOverrideConfigurer.postProcessBeanFactory(factory);
            
            
            /********Only for logging purpose ****/
            log.debug("overidden==="+beanPropertyOverrideConfigurer.hasPropertyOverridesFor("changeFormExtractCronTrigger"));
            CronTriggerBean cron= (CronTriggerBean)ctx.getBean("changeFormExtractCronTrigger");
            log.debug("Context: changeFormExtractCronTrigger cron expression==="+cron.getCronExpression());
            BeanDefinition cronTriggerBeanDefinition = factory.getBeanDefinition("changeFormExtractCronTrigger");
            log.debug("Bean Definition: changeFormExtractCronTrigger cron expression=="+cronTriggerBeanDefinition.getPropertyValues().getPropertyValue("cronExpression").getValue());
            /*************/
            
            /** 
             * Step 2. 
             * simply update cron trigger bean has no effect on job schedule. 
             * 
             * 
             * Must
             * 1. update the cron trigger bean
             * 2. update job schedule through "jobScheduler" using updated cron trigger bean(s).
             * 
             * 
             * */
            Map map = ctx.getBeansOfType(CronTriggerBean.class);
            Iterator key_it =map.keySet().iterator();
         
            while(key_it.hasNext())
            {
                Object key = key_it.next();
                CronTriggerBean ct = (CronTriggerBean)map.get(key);
               
                Trigger cronTrigger = null;
                //cronTrigger = new org.quartz.CronTrigger(ct.getName(),"DEFAULT",ct.getJobDetail().getName(),"quartz-batch",ct.getCronExpression());
                cronTrigger = new org.quartz.CronTrigger(ct.getName(),"DEFAULT",ct.getJobDetail().getName(),ct.getJobDetail().getGroup(),ct.getCronExpression());
                org.quartz.Scheduler scheduler = (org.quartz.Scheduler)ctx.getBean("jobScheduler");
                scheduler.rescheduleJob(ct.getName(), "DEFAULT", cronTrigger);
                     
            }
       
       }catch(Exception e)
       {
           log.error("Failed reloadSchedule: ",e);
       }
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException
    {
        log.debug("factory==="+factory);
        this.factory = factory; 
        
    }
}
