package pojo;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CityEnum {
  BEIJING(1, "北京"),
  SHANGHAI(2, "上海"),
  HANGZHOU(3, "杭州"),
  @JsonEnumDefaultValue
  UNKNOW(-1, "未知"),
  ;

  @JsonValue
  private final int code;
  private final String desc;

  CityEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
