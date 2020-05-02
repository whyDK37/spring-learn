package springboot.webmvc.controller;

import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link Properties} {@link RestController}
 *
 * @author 小马哥
 * @since 2018/5/27
 */
//@RestController
@Controller
public class PropertiesRestController {

  @PostMapping(value = "/web/props",
      consumes = "text/properties;charset=UTF-8" // Content-Type 过滤媒体类型
  )
  public Properties addProperties(
//            @RequestBody
      Properties properties) {
    return properties;
  }

}
