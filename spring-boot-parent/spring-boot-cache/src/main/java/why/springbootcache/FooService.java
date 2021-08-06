package why.springbootcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    @Cacheable(cacheNames = "cache", key = "#str")
    public String echo(String str) {
        return str + "...";
    }
}
