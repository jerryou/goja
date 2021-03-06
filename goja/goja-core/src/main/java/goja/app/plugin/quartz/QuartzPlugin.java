/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
package goja.app.plugin.quartz;

import com.jfinal.plugin.IPlugin;
import goja.Logger;
import goja.init.ctxbox.ClassBox;
import goja.init.ctxbox.ClassType;
import goja.jobs.On;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Throwables.propagate;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzPlugin implements IPlugin {

    private final Scheduler sched;

    public QuartzPlugin() {
        Scheduler tmp_sched = null;
        try {
            tmp_sched = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            propagate(e);
        }
        this.sched = tmp_sched;
    }


    @Override
    public boolean start() {
        List<Class> jobClasses = ClassBox.getInstance().getClasses(ClassType.QUARTZ);
        if (jobClasses != null && !jobClasses.isEmpty()) {
            On on;
            for (Class jobClass : jobClasses) {
                on = (On) jobClass.getAnnotation(On.class);
                if (on != null) {
                    String jobCronExp = on.value();
                    addJob(jobClass, jobCronExp, jobClass.getName() + ".job");
                }
            }
        }
        return true;
    }

    private void addJob(Class<? extends Job> jobClass, String jobCronExp, String jobName) {
        JobDetail job = newJob(jobClass)
                .withIdentity(jobName, jobName + "group")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity(jobName, jobName + "group")
                .withSchedule(cronSchedule(jobCronExp))
                .startNow()
                .build();

        Date ft = null;
        try {
            ft = sched.scheduleJob(job, trigger);
            sched.start();
        } catch (SchedulerException e) {
            propagate(e);
        }
        if (Logger.isDebugEnabled()) {
            Logger.debug(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                    + jobCronExp);
        }
    }


    @Override
    public boolean stop() {
        try {
            sched.shutdown();
        } catch (SchedulerException e) {
            propagate(e);
        }
        return true;
    }

}
