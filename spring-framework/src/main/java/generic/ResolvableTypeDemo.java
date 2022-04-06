package generic;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.core.ResolvableType;
import pojo.User;

public class ResolvableTypeDemo {

  private HashMap<Integer, List<String>> myMap = new HashMap<>();

  public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {

    ResolvableType t = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("myMap"));
    t.getSuperType(); // AbstractMap<Integer, List<String>>
    t.getSuperType().resolve(); // AbstractMap<Integer, List<String>>
    t.asMap(); // Map<Integer, List<String>>
    System.out.println("t.getGeneric(0).resolve() = " + t.getGeneric(0).resolve()); // Integer
    System.out.println("t.getGeneric(1).resolve() = " + t.getGeneric(1).resolve()); // List
    System.out.println("t.getGeneric(1) = " + t.getGeneric(1)); // List<String>
    System.out.println("t.resolveGeneric(1, 0) = " + t.resolveGeneric(1, 0)); // String
    System.out.println("t.resolveGeneric(1, 1) = " + t.resolveGeneric(1, 1)); // null

    // GenericB
    ResolvableType genericBType = ResolvableType.forClass(GenericB.class);
    genericBType.resolveGenerics();
    ResolvableType superType = genericBType.getSuperType();
    genericBType.getSuperType().resolve().getTypeParameters();
    Class<?> aClass = genericBType.getSuperType().resolveGeneric(0);

    // setUsers method
    Method setUsers = ResolvableTypeDemo.class.getMethod("setUsers", List.class);
    ResolvableType methodParameter = ResolvableType.forMethodParameter(setUsers, 0);
    Type[] genericParameterTypes = setUsers.getGenericParameterTypes();
  }

  public void setUsers(List<Consumer<User>> users) {
  }

}
