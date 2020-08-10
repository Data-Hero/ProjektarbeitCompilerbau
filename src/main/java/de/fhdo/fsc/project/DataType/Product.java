package de.fhdo.fsc.project.DataType;


public class Product extends Type {
    private TypeI a;
    private TypeI w;

    public Product(Type a, Type b) {
        super("(" + a.name + ", " + b.name + ")");
        this.a = a;
        this.w = b;
    }

    public TypeI getA() {
        return a;
    }

    public void setA(TypeI a) {
        this.a = a;
    }

    public TypeI getW() {
        return w;
    }

    public void setW(TypeI w) {
        this.w = w;
    }
}
