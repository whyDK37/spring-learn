package why.manager;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import pojo.User;
import why.mapper.UserMapper;

@Component
public class UserManager {

  @Resource
  UserMapper userMapperAA;

  public List<User> getAllUser() {
    return userMapperAA.getById();
  }
}
