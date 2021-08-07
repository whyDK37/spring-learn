package why.springbootcache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

@SpringBootTest(classes = {SpringBootCacheApplication.class})
class SpringBootCacheApplicationTests {

    @Autowired
    private FooService foo;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void contextLoads() {

        String a = foo.echo("a");
        String echo = foo.echo("a");
        Assertions.assertTrue(a == echo);

        Cache cache = cacheManager.getCache("cache");
        System.out.println("cache.getClass() = " + cache.getClass());
        CaffeineCache caffeineCache = (CaffeineCache) cache;
    }
}
