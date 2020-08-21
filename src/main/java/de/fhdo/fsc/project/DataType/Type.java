package de.fhdo.fsc.project.DataType;

import java.util.ArrayList;

public class Type implements TypeI{

    String name;

    public Type(String name) {
        this.name = name;
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



    public boolean isNumericType() {
        return this instanceof NumericType;
    }

    @Override
    public String getName() {
        return name;
    }


    /**
     *
     * @param t
     * @return
     */
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
