package scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Component
@EnableScheduling
public class TaskCronChange implements SchedulingConfigurer {

    public static void main(String[] args) throws InterruptedException, IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TaskCronChange.class, args);
        TaskCronChange bean = applicationContext.getBean(TaskCronChange.class);
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, UTF_8))
                    .readLine();
            if (!StringUtils.isEmpty(input) && input.trim().equalsIgnoreCase("q")) {
                System.exit(0);
            }

            if ("beans".equals(input)) {
                for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
                    System.out.println(beanDefinitionName + "->" + applicationContext.getBean(beanDefinitionName));
                }
            } else if ("list".equals(input)) {
//                List<CronTask> cronTaskList = bean.scheduledTaskRegistrar.getCronTaskList();
//                for (CronTask cronTask : cronTaskList) {
//                    System.out.println(cronTask.getExpression() + "->" + cronTask.getTrigger());
//                }
            }
        }
    }

    public static String cron;

    public TaskCronChange() {

        //默认情况是：每5秒执行一次.
        cron = "0/5 * * * * *";
        // 开启新线程模拟外部更改了任务执行周期.
        new Thread(() -> {
            try {
                // 让线程睡眠 15秒.
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //修改为：每10秒执行一次.
            cron = "0/10 * * * * *";
            System.err.println("cron change to:" + cron);
        }).start();
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable task = () -> {
            //任务逻辑代码部分.
            System.out.println("TaskCronChange task is running ... " + new Date());
        };

        Trigger trigger = triggerContext -> {
            //任务触发，可修改任务的执行周期.
            CronTrigger trigger1 = new CronTrigger(cron);
            return trigger1.nextExecutionTime(triggerContext);
        };
        taskRegistrar.addTriggerTask(task, trigger);
    }
}