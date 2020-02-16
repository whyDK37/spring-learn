package me.data.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class ApplicationData {


  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(ApplicationData.class);
    springApplication.run(args);
  }
}
