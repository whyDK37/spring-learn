package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

  private String name;
  private int age;

  public Person() {
  }
  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

}