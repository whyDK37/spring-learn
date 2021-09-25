package why.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Person;
import why.web.bind.annotation.RequestJSONParam;

import java.util.List;

@RestController
public class Controller {

    /**
     * POST http://localhost:8080/say HTTP/1.1
     * content-type: multipart/form-data; boundary=----WebKitFormBoundary1kUQB3KDNwasFSFC
     * <p>
     * ------WebKitFormBoundary1kUQB3KDNwasFSFC
     * Content-Disposition: form-data; name="names"
     * <p>
     * "111","222"
     * ------WebKitFormBoundary1kUQB3KDNwasFSFC--
     *
     * @param names
     * @return
     */
    @RequestMapping("/say")
    public String say(
            @RequestParam(name = "names", required = false) List<String> names,
            @RequestParam(name = "persons", required = false) List<Person> persons) {
        System.out.println("names = " + names);
        System.out.println("persons = " + persons);
        return "ok";
    }

    @RequestMapping("/sayPersons")
    public String sayPersons(
            @RequestJSONParam(name = "persons", required = false) List<Person> persons) {
        System.out.println("persons = " + persons);
        return "ok";
    }


    /**
     * POST http://localhost:8080/sayBody HTTP/1.1
     * Content-type: application/json
     * <p>
     * {"name":"body"}
     *
     * @param person
     * @return
     */
    @RequestMapping("/sayBody")
    public String sayBody(
            @RequestBody(required = false) Person person) {
        System.out.println("person = " + person);
        return "ok";
    }

}
