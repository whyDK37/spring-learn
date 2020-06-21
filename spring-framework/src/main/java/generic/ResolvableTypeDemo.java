package generic;

import java.util.HashMap;
import java.util.List;
import org.springframework.core.ResolvableType;

public class ResolvableTypeDemo {

  private HashMap<Integer, List<String>> myMap;

  public static void main(String[] args) throws NoSuchFieldException {

    ResolvableType t = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("myMap"));
    t.getSuperType(); // AbstractMap<Integer, List<String>>
    t.asMap(); // Map<Integer, List<String>>
    t.getGeneric(0).resolve(); // Integer
    t.getGeneric(1).resolve(); // List
    t.getGeneric(1); // List<String>
    t.resolveGeneric(1, 0); // String
    t.resolveGeneric(1, 1); // null

    // GenericB
    ResolvableType genericBType = ResolvableType.forClass(GenericB.class);
    genericBType.resolveGenerics();
    ResolvableType superType = genericBType.getSuperType();
    genericBType.getSuperType().resolve().getTypeParameters();
    Class<?> aClass = genericBType.getSuperType().resolveGeneric(0);


  }

}
