package conversion;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import pojo.User;

public class DIYPropertyEditorRegistrar implements PropertyEditorRegistrar {

  @Override
  public void registerCustomEditors(PropertyEditorRegistry registry) {
    // 1 类型转换器
    String2PropertiesEditor propertyEditor = new String2PropertiesEditor();
    // 2 注册属性转换
    registry.registerCustomEditor(User.class, "context", propertyEditor);
    // 3 DIYPropertyEditorRegistrar 定义为 spring bean 对象
    //
  }
}
