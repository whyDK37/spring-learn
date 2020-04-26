package springboot.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HelloWorld {@link Controller}
 *
 * @author 小马哥
 * @since 2018/5/20
 */
@Controller
public class HelloWorldController {

  @RequestMapping("/web/hello")
  public String hello(@RequestParam(required = false) Integer value, Model model) {
    System.out.println("web mvc hello");
    return "hello";
  }

  @RequestMapping("/web/rest")
  @ResponseBody
  public String test() {
    System.out.println("web mvc hello");
    return "hello";
  }

}
