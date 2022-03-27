package io.reflectoring.validation.service;

import com.alibaba.fastjson.JSON;
import io.reflectoring.validation.Input;
import io.reflectoring.validation.result.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidatingAndHandleServiceTest {

  @Autowired
  ValidatingAndHandleService service;

  @Test
  void validateInput() {
    Input input = new Input();
    input.setNumberBetweenOneAndTen(0);
    input.setIpAddress("invalid");
    Result<Input> inputResult = service.validateInput(input);

    System.out.println(JSON.toJSONString(inputResult, true));
  }
}