package cases;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {

  Store store;

  @Autowired
  public void setStore(Store store) {
    this.store = store;
  }
}
