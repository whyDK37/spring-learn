package pojo;

import java.util.List;

public class User {

  private Integer id;
  private String name;

  private CityEnum city;
  private List<CityEnum> workCities;

  public CityEnum getCity() {
    return city;
  }

  public void setCity(CityEnum city) {
    this.city = city;
  }

  public void setWorkCities(List<CityEnum> cities) {
    this.workCities = cities;
  }

  public List<CityEnum> getWorkCities() {
    return this.workCities;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
