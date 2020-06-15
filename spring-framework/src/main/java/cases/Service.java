package cases;

import javax.annotation.Resource;

public class Service {

  Store store;

  @Resource
  public void setStore(Store store) {
    this.store = store;
  }
}
