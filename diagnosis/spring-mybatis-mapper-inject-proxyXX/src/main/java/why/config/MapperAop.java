package why.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MapperAop {

  @Pointcut("execution(public * why.manager..*(..))")
  public void pointCut() {
  }

  @Before("pointCut()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    System.out.println("do before");
  }

  @AfterReturning(returning = "returnVal", pointcut = "pointCut()")
  public void doAfterReturning(Object returnVal) {
    System.out.println("do after");
  }
}