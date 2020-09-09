package cglib.beans;

import org.springframework.cglib.beans.BeanCopier;
import pojo.Person;

public class BeanCopierDemo {

  public static void main(String[] args) {
    BeanCopier beanCopier = BeanCopier.create(Person.class, Person.class, false);

    Person person = Person.builder()
        .name("但莫")
        .age(1).build();

    Person target = Person.builder().build();

    beanCopier.copy(person, target, null);

    System.out.println(target);

  }

}
