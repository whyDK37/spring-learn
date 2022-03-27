package io.reflectoring.validation.aop;

import io.reflectoring.validation.result.Result;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 处理hsf接口调用返回的异常
 */
@Aspect
@Component
@Slf4j
public class HSFExceptionAspect {

  @Autowired
  private Environment environment;

  @Pointcut("@within(ErrorResultHandle) && execution(public * *(..))")
  public void pointcut() {
  }

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

    Object response;
    try {
      response = joinPoint.proceed();
    } catch (Throwable e) {
      response = handleException(joinPoint, e);
      if (response == null) {
        throw e;
      }
    }
    return response;
  }

  private Object handleException(ProceedingJoinPoint joinPoint, Throwable e) {
    MethodSignature ms = (MethodSignature) joinPoint.getSignature();
    Class returnType = ms.getReturnType();

    if (e instanceof ConstraintViolationException) {
      log.warn("BIZ EXCEPTION : " + e.getMessage());
      //在Debug的时候，对于BizException也打印堆栈
      if (log.isDebugEnabled()) {
        log.error(e.getMessage(), e);
      }
      ConstraintViolationException e1 = (ConstraintViolationException) e;
      Set<ConstraintViolation<?>> violationSet = e1.getConstraintViolations();

      return new Result().setMsg(String.format("参数校验失败。[%s]",
          violationSet.stream().map(ConstraintViolation::getMessage)
              .collect(Collectors.joining("；"))));
    }

    log.error("UNKNOWN EXCEPTION :");
    log.error(e.getMessage(), e);

    return null;
  }
}