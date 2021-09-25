package pojo;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class Person {
  public Person() {
  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  private String name;
  private int age;

}