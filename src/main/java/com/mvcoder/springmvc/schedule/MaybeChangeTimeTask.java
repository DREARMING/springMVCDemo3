package com.mvcoder.springmvc.schedule;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by mvcoder on 2020/4/15.
 */
@Scope("singleton")
@Component
public class MaybeChangeTimeTask implements Runnable{

    private ScheduledFuture<?> future;

    private ThreadPoolTaskScheduler executor;

    private volatile String cron;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Autowired
    @Qualifier("task_scheduler")
    public void setExecutor(ThreadPoolTaskScheduler executor) {
        this.executor = executor;
    }

    public MaybeChangeTimeTask() throws IOException {
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream("/config/ScheduleTaskConfig.properties");
            Properties properties = new Properties();
            properties.load(is);
            String cron = properties.getProperty("change_time_task_cron");
            System.out.println("cron is : " + cron);
            this.cron = cron;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is != null) is.close();
        }
    }

    @PostConstruct
    public void init(){
        startTask();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    changeTaskTime("0 0 2  * * ?");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void startTask() {
        if(future != null && !future.isCancelled()) return;
        future = executor.schedule(this, new Trigger() {
            @Nullable
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                return new CronTrigger(cron).nextExecutionTime(triggerContext);
            }
        });
    }

    public void stopTask(){
        if(future != null && !future.isCancelled()) {
            future.cancel(true);
            future = null;
        }
    }

    public void changeTaskTime(String cron){
        stopTask();
        this.cron = cron;
        startTask();
    }

    @Override
    public void run() {
        System.out.println("execute task : " + MaybeChangeTimeTask.class.getSimpleName() + ", " + DateUtil.now());
    }
}
