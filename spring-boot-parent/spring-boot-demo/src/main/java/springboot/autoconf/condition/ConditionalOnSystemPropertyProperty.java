package springboot.autoconf.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Conditional;

/**
 * Java 系统属性 条件判断
 *
 * @author 小马哥
 * @since 2018/5/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SystemPropertyCondition.class)
public @interface ConditionalOnSystemPropertyProperty {

  /**
   * Java 系统属性名称
   *
   * @return
   */
  String name();

  /**
   * Java 系统属性值
   *
   * @return
   */
  String value();
}