package generic;

import java.util.ArrayList;
import java.util.Collection;

public class GenericDemo {

  public static void main(String[] args) {
    Collection<String> list = new ArrayList<>();
    list.add("hello");
    // 编译错误
//    list.add(1);
    // 泛型擦除
    Collection temp = list;
    // 编译通过
    temp.add(1);
    System.out.println(list);
    // ClassCastException
    list.forEach(System.out::println);
  }

}
