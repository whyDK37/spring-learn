package conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class String2PropertiesEditor extends PropertyEditorSupport {

  /**
   * 原始值
   */
  String text;
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    this.text = text;
    Properties properties = new Properties();

    try {
      properties.load(new StringReader(text));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

    setValue(properties);
  }

  @Override
  public String getAsText() {
    return this.text;
  }
}
