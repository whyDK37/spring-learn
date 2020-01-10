package demo.controller;

import demo.service.ListService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TimeController {

    @Autowired
    private ListService listService;

    /**
     * http://127.0.0.1:8080/actuator/metrics/all.people
     *
     * @return
     */
    @RequestMapping("/list")
    @Timed(value = "list.time", longTask = true)
    @ResponseBody
    public String list() {
        return listService.list();
    }

    @RequestMapping(value = "/body", method = {RequestMethod.POST, RequestMethod.GET})
    public String body(String body, HttpServletRequest request) {
        request.getParameter("body");
        System.out.println("body.length() = " + body.getBytes().length / (1024.0 * 1024));
        return "{\"body\":" + System.currentTimeMillis() + "}";
    }
}
