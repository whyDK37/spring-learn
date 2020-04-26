package springboot.autoconf.condition;

import java.util.Map;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 系统属性条件判断
 *
 * @author 小马哥
 * @since 2018/5/15
 */
public class SystemPropertyCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

    Map<String, Object> attributes = metadata
        .getAnnotationAttributes(ConditionalOnSystemPropertyProperty.class.getName());

    String propertyName = String.valueOf(attributes.get("name"));

    String propertyValue = String.valueOf(attributes.get("value"));

    String javaPropertyValue = System.getProperty(propertyName);

    boolean equals = propertyValue.equals(javaPropertyValue);
    System.err.println(propertyName + ".equals(" + javaPropertyValue + ")=" + equals);
    return equals;
  }
}
