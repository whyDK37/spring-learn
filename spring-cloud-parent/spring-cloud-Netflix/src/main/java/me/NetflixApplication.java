package me;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author wanghuanyu10
 */
@EnableHystrix
@SpringBootApplication
public class NetflixApplication {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(NetflixApplication.class);
    springApplication.run(args);
  }

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    return new RestTemplate(factory);
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(5000);//ms
    factory.setConnectTimeout(15000);//ms
    return factory;
  }

}
