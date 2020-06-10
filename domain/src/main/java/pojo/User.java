package pojo;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class User {

  private Integer id;
  private String name;

  private CityEnum city;
  private List<CityEnum> workCities;

  private Date birthday;

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

  public Date getBirthday() {
    return birthday;
  }

  public User setBirthday(Date birthday) {
    this.birthday = birthday;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("name='" + name + "'")
        .add("city=" + city)
        .add("workCities=" + workCities)
        .add("birthday=" + birthday)
        .toString();
  }
}
