package scheduling.support;


import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

class ChronTriggerTest {

  String cron = "0/10 * * * * *";

  @Test
  void test() {
    SimpleTriggerContext simpleTriggerContext = new SimpleTriggerContext();

    CronTrigger trigger1 = new CronTrigger(cron);
    for (int i = 0; i < 10; i++) {
      System.out.println("trigger1.nextExecutionTime(simpleTriggerContext) = " + trigger1
          .nextExecutionTime(simpleTriggerContext));
      System.out.println("simpleTriggerContext.lastActualExecutionTime() = " + simpleTriggerContext
          .lastActualExecutionTime());
      System.out.println("simpleTriggerContext.lastCompletionTime() = " + simpleTriggerContext
          .lastCompletionTime());
      simpleTriggerContext.update(trigger1
          .nextExecutionTime(simpleTriggerContext), trigger1
          .nextExecutionTime(simpleTriggerContext), trigger1
          .nextExecutionTime(simpleTriggerContext));
      System.out.println();
    }
  }
}
