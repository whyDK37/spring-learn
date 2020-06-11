package jackson;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import java.util.Date;
import pojo.CityEnum;
import pojo.User;

/**
 * @author why
 */
public class JacksonDemo {

  public static void main(String[] args) throws JsonProcessingException {
    final JsonFactoryBuilder jsonFactoryBuilder = new JsonFactoryBuilder();
    jsonFactoryBuilder.enable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES);
    jsonFactoryBuilder.disable(JsonFactory.Feature.INTERN_FIELD_NAMES);
    jsonFactoryBuilder.configure(JsonWriteFeature.ESCAPE_NON_ASCII, true);
    //
    ObjectMapper objectMapper = new ObjectMapper(jsonFactoryBuilder.build());

    final User user = new User();
    user.setBirthday(new Date()).setName("why");
    System.out.println(
        "mapper.writeValueAsString(new User()) = " + objectMapper.writeValueAsString(user));

    System.out.println("---------------------------------------------------------------------");
    // write enums index
    objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
    System.out.println("objectMapper.writeValueAsString(Color.BLUE) = " + objectMapper
        .writeValueAsString(Color.BLUE));

    System.out.println("objectMapper.writeValueAsString(\"string value\") = " + objectMapper
        .writeValueAsString("string value"));

    System.out.println("---------------------------------------------------------------------");
    // 未知属性，不报错 properties
    // 方法1 设置 FAIL_ON_UNKNOWN_PROPERTIES属性
    // 方法2 @JsonIgnoreProperties(ignoreUnknown = true)
    String json = null;
    json = "{\"version\":1}";
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.readValue(json, User.class);

    System.out.println("---------------------------------------------------------------------");
    System.out.println("[ERROR]枚举反序列化，序列化的 city 使用 code， 反序列化使用的是 ordinal， 2.11.0 存在此bug");
    user.setWorkCities(Lists.newArrayList(CityEnum.BEIJING, CityEnum.HANGZHOU));
    System.out.println(objectMapper.writeValueAsString(user));
    try {
      System.out.println(objectMapper.readValue(objectMapper.writeValueAsString(user), User.class));
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("---------------------------------------------------------------------");
    System.out.println(
        "未知枚举值，在设置 @JsonEnumDefaultValue 之后，需要开启 READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE 配置");
    objectMapper.configure(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
    json = "{\"workCities\":[9]}";
    System.out.println(objectMapper.readValue(json, User.class));

    System.out.println("---------------------------------------------------------------------");
    System.out.println("忽略 null 值");
    objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //属性为NULL不序列化
    System.out.println(objectMapper.writeValueAsString(new User().setName("why")));
  }

  enum Color {
    BLUE, GREEN
  }
}
