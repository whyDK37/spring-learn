package pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.validation.constraints.NotNull;

public class User {

  @NotNull(message = "用户id为空")
  private Integer id;
  @JsonAlias("nm")
  private String name;

  private CityEnum city;
  private List<CityEnum> workCities;

  private Date birthday;


  private Address address;

  private Properties context;

  public Properties getContext() {
    return context;
  }

  public void setContext(Properties context) {
    this.context = context;
  }

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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", city=" + city +
        ", workCities=" + workCities +
        ", birthday=" + birthday +
        ", address=" + address +
        ", context=" + context +
        '}';
  }
}
