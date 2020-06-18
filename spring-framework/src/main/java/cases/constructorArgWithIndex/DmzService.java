package cases.constructorArgWithIndex;

/**
 * @author wanghuanyu
 */
public class DmzService {

  private String name;
  private OrderService orderService;

  public DmzService(OrderService orderService, String name) {
    this.orderService = orderService;
    this.name = name;
  }

  public DmzService(String name) {
    this.name = name;
  }
}
