package de.fhdo.fsc.project.DataType;


public class Type implements TypeI{

    String name;

    public Type(String name) {
        this.name = name;
    }


    public boolean isNumericType() {
        return this instanceof NumericType;
    }

    public boolean isArrayType() {
        return this instanceof ArrayType;
    }

    @Override
    public String getName() {
        return name;
    }


    /**
     * Check if implcit type cast is possible
     * @param t Type we want to cast
     * @return True iif t can be casted implicitly to this
     */
    public boolean implicitTo(TypeI t){
        if (this == Types.errorType || t == Types.errorType) return true;
        if (this == t) return true;
        if (isNumericType() && t.isNumericType()) {
            NumericType nt = (NumericType) t;
            return ((NumericType) this).rank <= nt.rank;
        }
        return false;
    }


    @Override
    public boolean explicitTo(TypeI t){
        if (this == Types.errorType || t == Types.errorType) return true;
        if (this == t) return true;
        if (isNumericType() && t.isNumericType()) {
            NumericType nt = (NumericType) t;
            return ((NumericType) this).rank >= nt.rank;
        }
        return false;


    }

    @Override
    public boolean equals(TypeI t) {
        if (this == Types.errorType || t==Types.errorType) return true;
        return this.name.equals(t.getName());
    }

}
