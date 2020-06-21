package generic;

public class GenericC extends Generic {

    private Number n;

    @Override
    public Number getData() {
        return this.n;
    }

    public void setData(Number data) {
        this.n = data;
    }
}