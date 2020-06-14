package conversion;

import java.beans.PropertyEditor;

public class PropertyEditorDemo {

  public static void main(String[] args) {
    String text = "name=why";

    PropertyEditor propertyEditor = new String2PropertiesEditor();
    propertyEditor.setAsText(text);
    // 获取转换后的 property 对象
    System.out.println("propertyEditor.getValue() = " + propertyEditor.getValue());
    // 获取原始 text
    System.out.println("propertyEditor.getAsText() = " + propertyEditor.getAsText());

  }

}
