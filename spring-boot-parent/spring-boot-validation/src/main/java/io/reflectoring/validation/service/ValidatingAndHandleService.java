package io.reflectoring.validation.service;

import io.reflectoring.validation.Input;
import io.reflectoring.validation.aop.ErrorResultHandle;
import io.reflectoring.validation.result.Result;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
@ErrorResultHandle
class ValidatingAndHandleService {

  public Result<Input> validateInput(@Valid Input input) {
    // do something

    return new Result<Input>().setData(input);
  }

}
