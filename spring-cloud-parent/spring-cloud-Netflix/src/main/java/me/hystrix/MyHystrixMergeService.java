package me.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyHystrixMergeService {

  private Logger logger = LoggerFactory.getLogger(MyHystrixMergeService.class);

  @Autowired
  private RestTemplate restTemplate;

  /**
   * 指定批处理的方法，设置合并200ms之内的请求
   *
   * @param id
   * @return
   * @see #getMerge
   */
  @HystrixCollapser(batchMethod = "getMerge", collapserProperties = {
      @HystrixProperty(name = "timerDelayInMilliseconds", value = "200")})
  public Future<String> merge(Integer id) {
    //不会进入这个方法体
    return null;
  }

  /**
   * @param ids
   * @return
   * @see #mergeFallback
   */
  @HystrixCommand(fallbackMethod = "mergeFallback")
  public List<String> getMerge(List<Integer> ids) {
    logger.info("合并的请求：" + ids.toString());
    String[] result = restTemplate
        .getForEntity("http://eureka-client/merge?id={1}", String[].class, StringUtils
            .join(ids, ",")).getBody();
    logger.info("合并后的结果" + result);
    return Arrays.asList(result);
  }


  /**
   * 降级方法的参数，返回值类型，返回值数量要和上面的方法对应
   *
   * @param ids
   * @return
   */
  public List<String> mergeFallback(List<Integer> ids) {
    List<String> list = new ArrayList<>();
    for (Integer id : ids) {
      list.add("请求合并失败:" + id);
    }
    return list;
  }
}