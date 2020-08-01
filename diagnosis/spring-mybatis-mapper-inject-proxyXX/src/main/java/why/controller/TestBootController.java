package why.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import why.manager.UserManager;

@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestBootController {

  @Resource(name = "UserManager")
  private UserManager userManager;

  @RequestMapping("getuser")
  public List<User> getUser() {
    return userManager.getAllUser();
  }
}