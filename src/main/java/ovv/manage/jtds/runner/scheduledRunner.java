package ovv.manage.jtds.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ovv.manage.jtds.runner.task.payAccountTask;
import ovv.manage.jtds.utils.TimeUtil;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Component
//@Order(1)
public class scheduledRunner implements ApplicationRunner {

    ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("-------------->" + "定时器启动，now=" + new Date());
        initSchedule();
    }

    private void initSchedule() {
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay  = TimeUtil.getTimeMillis("18:47:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        executor.scheduleAtFixedRate(
                new payAccountTask(),
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);
    }
}
