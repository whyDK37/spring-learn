package me.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyHystrixMergeController {

  private Logger logger = LoggerFactory.getLogger(MyHystrixMergeController.class);

  @Autowired
  private MyHystrixMergeService myHystrixMergeService;

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/merge")
  public void merge() throws ExecutionException, InterruptedException {
    //进行初始化操作，不加这个会报错
    HystrixRequestContext context = HystrixRequestContext.initializeContext();
    MyHystrixCollapser mergeCollapser1 = new MyHystrixCollapser(1, restTemplate);
    MyHystrixCollapser mergeCollapser2 = new MyHystrixCollapser(2, restTemplate);
    MyHystrixCollapser mergeCollapser3 = new MyHystrixCollapser(3, restTemplate);

    //这里必须使用异步请求，否则不会合并
    Future<String> future1 = mergeCollapser1.queue();
    Future<String> future2 = mergeCollapser2.queue();
    Future<String> future3 = mergeCollapser3.queue();

    logger.info(future1.get());
    logger.info(future2.get());
    logger.info(future3.get());
  }


  @RequestMapping("/merge2")
  public List<String> merge2() throws ExecutionException, InterruptedException {
    HystrixRequestContext context = HystrixRequestContext.initializeContext();

    Future<String> future1 = myHystrixMergeService.merge(1);
    Future<String> future2 = myHystrixMergeService.merge(2);
    Future<String> future3 = myHystrixMergeService.merge(3);

    List<String> rs = new ArrayList<>();
    rs.add(future1.get());
    rs.add(future2.get());
    rs.add(future3.get());
    return rs;
  }
}