package com.vangent.tap.app.batch.jmx;

public interface ScheduleManager
{
    public void reloadSchedule();
    public void changeSchedule(String triggerName, String jobDetailsName, String cronExpression);
    public void changeSchedule(String triggerName, String triggerGroup, String jobDetailsName, String jobGroup, String cronExpression);
}
