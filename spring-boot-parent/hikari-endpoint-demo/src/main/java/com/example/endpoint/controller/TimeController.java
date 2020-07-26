package com.example.endpoint.controller;

//import com.example.endpoint.service.ListService;

import io.micrometer.core.annotation.Timed;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

//  @Autowired
//  private ListService listService;

  /**
   * http://127.0.0.1:8080/actuator/metrics/all.people
   */
  @RequestMapping("/list")
  @Timed(value = "list.time", longTask = true)
  @ResponseBody
  public String list() throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(200);
    return "list";
//    return listService.list();
  }

  @RequestMapping(value = "/body", method = {RequestMethod.POST, RequestMethod.GET})
  public String body(String body, HttpServletRequest request) {
    request.getParameter("body");
    System.out.println("body.length() = " + body.getBytes().length / (1024.0 * 1024));
    return "{\"body\":" + System.currentTimeMillis() + "}";
  }
}
