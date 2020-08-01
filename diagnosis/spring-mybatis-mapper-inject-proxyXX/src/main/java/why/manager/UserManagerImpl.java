package why.manager;

import java.util.List;
import org.springframework.stereotype.Component;
import pojo.User;
import why.mapper.UserMapper;

@Component("UserManager")
public class UserManagerImpl implements UserManager {

  private UserMapper userMapper;

  public UserManagerImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public List<User> getAllUser() {
    return userMapper.getById();
  }
}
