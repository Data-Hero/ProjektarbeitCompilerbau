package de.fhdo.fsc.project.DataType;

public class Sum<A extends Type, W extends Type> extends Type{
    private A a;
    private W w;
    private boolean aChosen;

    public Sum(String name) {
        super(name);
    }

    public Type getValue() {
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

}
