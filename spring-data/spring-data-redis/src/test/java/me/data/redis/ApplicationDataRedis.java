package me.data.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ApplicationData.class)
public class ApplicationDataRedis {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Test
  public void testRedis() {
    Assert.assertNotNull(redisTemplate);
    redisTemplate.opsForValue().set("abc", "總問");
    Assert.assertEquals("總問", redisTemplate.opsForValue().get("abc"));
  }
}
