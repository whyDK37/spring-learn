package databind;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import pojo.User;

public class DataBindDemo {

  public static void main(String[] args) {
    User user = new User();

    DataBinder dataBinder = new DataBinder(user, "user");
    dataBinder.setAllowedFields("name");
    Map<String, Object> map = new HashMap<>();
    map.put("id", "2");
    map.put("name", "why");
    map.put("address.XX", "XX");
    PropertyValues propertyValues = new MutablePropertyValues(map);

    dataBinder.bind(propertyValues);
    System.out.println(user);
    BindingResult bindingResult = dataBinder.getBindingResult();
    System.out.println("bindingResult = " + bindingResult);

  }

}
