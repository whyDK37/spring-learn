package why.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import why.method.RequestJSONParamMethodArgumentResolver;

import java.util.List;

@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequestJSONParamMethodArgumentResolver(true));
    }

}