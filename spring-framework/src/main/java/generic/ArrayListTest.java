package generic;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

  static List<String> list = new ArrayList<String>(){{

  }};

  public static void main(String[] args)
      throws NoSuchFieldException, InstantiationException, IllegalAccessException {
    Field listField = ArrayListTest.class.getDeclaredField("list");
    Type genericType = listField.getGenericType();


    System.out.println(listField.getType());

    System.out.println("ArrayListTest.list.getClass() = " + ArrayListTest.list.getClass());
  }
}