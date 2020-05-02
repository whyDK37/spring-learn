package springboot.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorld {@link Controller}
 *
 * @author 小马哥
 * @since 2018/5/20
 */
@RestController
public class HelloWorldRestController {

  @RequestMapping("/web/rest")
  public String webRest() {
    System.out.println("web mvc hello");
    return "hello rest";
  }

}
