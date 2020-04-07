package me.data.redis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationData {


  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(ApplicationData.class);
    springApplication.run(args);
  }
}
