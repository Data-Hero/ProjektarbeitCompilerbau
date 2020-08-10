package de.fhdo.fsc.project.DataType;

public class Sum<A extends DataType, W extends DataType> extends DataType{
    private A a;
    private W w;
    private boolean aChosen;

    public Sum(String name) {
        super(name);
    }

    public DataType getValue() {
        return aChosen?a:w;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void setW(W w) {
        this.w = w;
    }

    public void chooseA() {
        aChosen = true;
    }

    public void chooseW() {
        aChosen = false;
    }

    @Override
    public boolean subtract(DataType k) {
        return false;
    }

    @Override
    public boolean divide(DataType k) {
        return false;
    }

    @Override
    public boolean check(String k) {
        return a.check(k) ^ w.check(k);
    }
}
