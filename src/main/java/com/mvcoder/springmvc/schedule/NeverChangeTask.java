package com.mvcoder.springmvc.schedule;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by mvcoder on 2020/4/15.
 */
@Component
public class NeverChangeTask {

    //这里演示的是一个每5秒执行一次的定时器任务，这种方式是没办法变更执行时间的，比如改成10秒之类的。。
    //@Scheduled(cron ="0/5 * *  * * ? ")
    @Scheduled(cron ="0 0 1  * * ? ")//每天凌晨1点执行
    public void task(){
        System.out.println("execute task : " + NeverChangeTask.class.getSimpleName() + " , time : " + DateUtil.now());
    }

}
