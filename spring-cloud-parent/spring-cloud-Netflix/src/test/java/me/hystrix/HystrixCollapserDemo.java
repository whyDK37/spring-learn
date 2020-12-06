package me.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

public class HystrixCollapserDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    List<Future<String>> futures = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      MyHystrixCollapser collapser = new MyHystrixCollapser(1, null);
      futures.add(collapser.queue());
    }
    for (Future<String> future : futures) {
      System.out.println(future.get());
    }
  }

  static class MyHystrixCollapser extends HystrixCollapser<List<String>, String, Integer> {

    private Integer id;
    private RestTemplate restTemplate;

    public MyHystrixCollapser(Integer id, RestTemplate restTemplate) {
      //合并 100ms 内的请求，withTimerDelayInMilliseconds 默认是10ms
      super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("collapser-demo"))
          .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter()
              .withMaxRequestsInBatch(3)
              .withTimerDelayInMilliseconds(10)));
      this.id = id;
      this.restTemplate = restTemplate;
    }

    /**
     * 获取参数
     *
     * @return 每一个参数
     */
    @Override
    public Integer getRequestArgument() {
      return id;
    }

    /**
     * 创建HystrixCommand
     *
     * @param collection 合并之后的参数
     * @return
     */
    @Override
    protected HystrixCommand<List<String>> createCommand(
        Collection<CollapsedRequest<String, Integer>> collection) {
      List<Integer> ids = new ArrayList<>(collection.size());
      for (CollapsedRequest<String, Integer> request : collection) {
        //将请求参数封装到list集合中
        ids.add(request.getArgument());
      }

      //将封装过的参数传入HystrixCommand类，由HystrixCommand类执行
      return new HystrixCommand(HystrixCommandGroupKey.Factory.asKey("MyHystrixCommandMerge")) {
        @Override
        protected List<String> run() throws Exception {
          System.out.println("参数为：" + ids.toString() + "--" + Thread.currentThread().getName());
          String[] result = restTemplate
              .getForEntity("http://eureka-client/merge?id={1}", String[].class, StringUtils
                  .join(ids, ",")).getBody();
          return Arrays.asList(result);
        }
      };
    }

    /**
     * 合并请求拿到结果，将请求结果按请求顺序分发给各个请求
     *
     * @param results    返回的结果集
     * @param collection 合并的请求集
     */
    @Override
    protected void mapResponseToRequests(List<String> results,
        Collection<CollapsedRequest<String, Integer>> collection) {
      int count = 0;
      for (CollapsedRequest<String, Integer> request : collection) {
        //分别拿到每个请求的结果
        String result = results.get(count++);
        //用该请求响应该结果（请求和结果对应）
        request.setResponse(result);
      }
    }
  }
}
