package why.springbootcache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SpringBootCacheApplication.class})
class SpringBootCacheApplicationTests {

    @Autowired
    private FooService foo;

    @Test
    void contextLoads() {

        String a = foo.echo("a");
        String echo = foo.echo("a");
        Assertions.assertTrue(a == echo);
    }
}
