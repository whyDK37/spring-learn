package springboot.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HelloWorld {@link Controller}
 *
 * @author 小马哥
 * @since 2018/5/20
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/web/hello")
    public String hello(@RequestParam int value, Model model) {
        System.out.println("web mvc hello");
        return "hello";
    }

}
