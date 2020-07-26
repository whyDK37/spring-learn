package me.springboottxlongtime;

import java.util.concurrent.CompletableFuture;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTxLongTimeApplication {

  @Autowired
  private FooService fooService;

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(
        SpringBootTxLongTimeApplication.class);
    springApplication.run(args);
  }

  @PostConstruct
  public void init() {

    CompletableFuture<String> cfQueryFromSina = CompletableFuture
        .supplyAsync(() -> fooService.doLongTime());
    CompletableFuture<String> cfQueryFrom163 = CompletableFuture
        .supplyAsync(() -> fooService.doLongTime());

    CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

  }
}
