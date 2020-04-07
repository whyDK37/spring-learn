package demo.service;

import demo.metrics.JobMetrics;
import io.micrometer.core.annotation.Timed;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {

  int count1, count2;

  @Autowired
  private JobMetrics jobMetrics;

  @Timed(value = "service.list", longTask = true)
  public String list() {
    doSomething();
    doSomethingOther();
    jobMetrics.record(new Random().nextInt(100));
    return "service list";
  }

  public void doSomething() {
    count1++;
    jobMetrics.job1Counter.increment();
    jobMetrics.map.put("x", (double) count1);
    System.out.println("task1 count:" + count1);
  }

  public void doSomethingOther() {
    count2++;
    jobMetrics.job2Counter.increment();
    System.out.println("task2 count:" + count2);
  }

}
