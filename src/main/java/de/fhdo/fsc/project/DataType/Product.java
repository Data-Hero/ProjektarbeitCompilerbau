package de.fhdo.fsc.project.DataType;


public class Product<A extends TypeI, W extends TypeI> extends Type {
    private A a;
    private W w;

    public Product(String name, A a, W b) {
        super(name);
        this.a = a;
        this.w = b;
    }

    public TypeI getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public TypeI getW() {
        return w;
    }

    public void setW(W w) {
        this.w = w;
    }

    @Override
    public boolean equals(TypeI t) {
        if (this == Types.errorType || t==Types.errorType) return true;
        if (!(t instanceof Product)) return false;
        Product st = (Product)t;
        return (
                (
                        this.getA().equals(st.getA()) && this.getW().equals(st.getW())
                )|| (
                        this.getW().equals(st.getA()) && this.getA().equals(st.getW())
                )
        ); // A==t.A und  W==t.W ODER A==t.W und W==t.A sprich AW ist kommutativ
    }

}
