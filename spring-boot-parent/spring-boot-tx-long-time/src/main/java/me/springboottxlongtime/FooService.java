package me.springboottxlongtime;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FooService {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Transactional(timeout = 1000)
  public String doLongTime() {
    System.out.println(Thread.currentThread().getName() + ":doLongTime...");
    try {
      TimeUnit.HOURS.sleep(1L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "";
  }

}
