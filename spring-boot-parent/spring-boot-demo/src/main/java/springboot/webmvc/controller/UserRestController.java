package springboot.webmvc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;

/**
 * User {@link RestController}
 *
 * @author 小马哥
 * @since 2018/5/27
 */
@RestController
public class UserRestController {

  @PostMapping(value = "web/echo/user",
      consumes = "application/*;charset=UTF-8"
      , produces = "application/json;charset=UTF-8"
  )
  public User user(@RequestBody User user) {
    return user;
  }

}
