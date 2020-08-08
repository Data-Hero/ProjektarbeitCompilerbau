package de.fhdo.fsc.project.DataType;

public class Product<A extends DataType, W extends DataType> {
    private A a;
    private W w;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public W getW() {
        return w;
    }

    public void setW(W w) {
        this.w = w;
    }
}
