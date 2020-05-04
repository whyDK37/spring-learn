package beans;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import pojo.User;

public class BeanWrapperDemo {

  public static void main(String[] args) {

    System.out.println("通過 MutablePropertyValues 設置 bean 屬性");
    MutablePropertyValues pvs = new MutablePropertyValues();
    pvs.add("id", 1);
    pvs.add("name", "我的名字");
    User user = new User();
    BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user);
    bw.setPropertyValues(pvs, true);
    System.out.println("設置屬性后的對象，bw.getWrappedInstance() = " + bw.getWrappedInstance());
  }
}
