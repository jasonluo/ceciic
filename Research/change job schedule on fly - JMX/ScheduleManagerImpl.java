package com.vangent.tap.app.batch.jmx;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.BeansException;
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
public class ScheduleManagerImpl implements ScheduleManager, ApplicationContextAware
{
    private static Logger log = Logger.getLogger(ScheduleManagerImpl.class);
    
    private static ApplicationContext ctx; 

    
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
        
        log.debug("ctx=="+ctx);

    } 
}
