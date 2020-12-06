package me.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

public class MyHystrixCommandMerge extends HystrixCommand<List<String>> {
    private RestTemplate restTemplate;
    private List<Integer> ids;

    protected MyHystrixCommandMerge(RestTemplate restTemplate, List<Integer> ids) {
        super(HystrixCommandGroupKey.Factory.asKey("MyHystrixCommandMerge"));
        this.restTemplate = restTemplate;
        this.ids = ids;
    }

    @Override
    protected List<String> run() throws Exception {
        System.out.println("参数为：" + ids.toString() + "--" + Thread.currentThread().getName());
        String[] result = restTemplate.getForEntity("http://eureka-client/merge?id={1}", String[].class, StringUtils
            .join(ids, ",")).getBody();
        return Arrays.asList(result);
    }

    @Override
    protected List<String> getFallback() {
        List<String> list = new ArrayList<>();
        list.add("合并请求出错");
        return list;
    }
}