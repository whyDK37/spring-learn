package why.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.User;
import why.mapper.UserMapper;

@Component("userManager")
public class UserManagerImpl implements UserManager {

  @Autowired
  private UserMapper userMapper;

  @Override
  public List<User> getAllUser() {
    return userMapper.getById();
  }
}
