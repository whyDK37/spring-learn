package demo.controller;

import demo.service.ListService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
