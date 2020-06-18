package cases.constructorArgWithIndex;

import java.util.Date;

/**
 * @author wanghuanyu
 */
public class FactoryObject {

  public DmzService getDmz(String name, int age, Date birthDay, OrderService orderService) {
    return new DmzService(orderService, name);
  }

  public DmzService getDmz(String name, Date birthday) {
    return new DmzService(name);
  }

//  public DmzService getDmz(String name, OrderService orderService) {
//    return new DmzService(name);
//  }

  public DmzService getDmz(String name, int age) {
    return new DmzService(name);
  }

  public DmzService getDmz(String name) {
    return new DmzService(name);
  }

}