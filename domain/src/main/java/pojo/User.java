package pojo;

import java.util.List;

public class User {

  private Integer id;
  private String name;

  private CityEnum city;
  private List<CityEnum> workCities;

  public Integer getId() {
    return id;
  }

  public User setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public User setName(String name) {
    this.name = name;
    return this;
  }

  public CityEnum getCity() {
    return city;
  }

  public User setCity(CityEnum city) {
    this.city = city;
    return this;
  }

  public List<CityEnum> getWorkCities() {
    return workCities;
  }

  public User setWorkCities(List<CityEnum> workCities) {
    this.workCities = workCities;
    return this;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", city=" + city +
        ", workCities=" + workCities +
        '}';
  }
}
