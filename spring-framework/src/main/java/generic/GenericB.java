package generic;

public class GenericB extends Generic<Number> {

  private Number n;

  @Override
  public Number getData() {
    return this.n;
  }

  @Override
  public void setData(Number data) {
    this.n = data;
  }
}