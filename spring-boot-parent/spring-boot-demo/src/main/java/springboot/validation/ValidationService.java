package springboot.validation;

import javax.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pojo.User;

@Service
@Validated
public class ValidationService {

  public User findByCodeAndAuthor(@Size(min = 8, max = 10) String code,
      User user) {
    return user;
  }

}