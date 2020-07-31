package why.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import why.manager.UserManager;

@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestBootController {

  @Autowired
  UserManager userManager;

  @RequestMapping("getuser")
  public List<User> getUser() {
    return userManager.getAllUser();
  }
}