package why.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;
import why.manager.UserManagerImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestBootController {

  @Lazy(true)
  @Resource
  private UserManagerImpl userManagerA;

  @RequestMapping("getuser")
  public List<User> getUser() {
    return userManagerA.getAllUser();
  }
}