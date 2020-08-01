package why.manager;

import java.util.List;
import org.springframework.stereotype.Component;
import pojo.User;
import why.mapper.UserMapper;

@Component
public class UserManagerImpl2 implements UserManager {

  private UserMapper userMapper;

  public UserManagerImpl2(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public List<User> getAllUser() {
    return userMapper.getById();
  }
}
