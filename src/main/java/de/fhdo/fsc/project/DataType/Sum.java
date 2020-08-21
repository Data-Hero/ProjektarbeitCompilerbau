package de.fhdo.fsc.project.DataType;

public class Sum<A extends TypeI, W extends TypeI> extends Type{
    private A a;
    private W w;
    private boolean aChosen;

    public Sum(String name) {
        super(name); // TODO handle error
    }

    public TypeI getValue() {
        return aChosen?a:w;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void setW(W w) {
        this.w = w;
    }

    public A getA() {
        return a;
    }

    public W getW() {
        return w;
    }

    public void chooseA() {
        aChosen = true;
    }

    public void chooseW() {
        aChosen = false;
    }

    public boolean getAChosen() { return aChosen; }

    @Override
    public boolean implicitTo(TypeI t){
        if (this == Types.errorType || t == Types.errorType) return true;
        if (this == t) return true;
        return (aChosen?this.a:this.w).implicitTo(t);
    }


    @Override
    public boolean explicitTo(TypeI t){
        if (this == Types.errorType || t == Types.errorType) return true;
        if (this == t) return true;
        return (aChosen?this.a:this.w).explicitTo(t);
    }

    @Override
    public boolean equals(TypeI t) {
        if (this == Types.errorType || t==Types.errorType) return true;
        if (!(t instanceof Sum)) return false;
        Sum st = (Sum)t;
        return (
                (
                        this.getA().equals(st.getA()) && this.getW().equals(st.getW())
                )|| (
                        this.getW().equals(st.getA()) && this.getA().equals(st.getW())
                )
        ); // A==t.A und  W==t.W ODER A==t.W und W==t.A sprich AW ist kommutativ
    }
}
