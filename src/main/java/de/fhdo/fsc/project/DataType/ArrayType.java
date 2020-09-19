package de.fhdo.fsc.project.DataType;

import java.util.ArrayList;

/**
 * Static array structure
 */
public class ArrayType extends Type{

    private Type baseTyp;

    public ArrayType(Type baseTyp) {
        super("");
        this.baseTyp = baseTyp;
    }


    @Override
    public boolean equals(TypeI t) {
        if (this == Types.errorType || t==Types.errorType) return true;
        return ((t instanceof ArrayType)
                && baseTyp.equals(((ArrayType)t).baseTyp));
    }

    @Override
    public boolean explicitTo(TypeI t) {
        if (this == Types.errorType || t==Types.errorType) return true;
        return ((t instanceof ArrayType)
                && baseTyp.explicitTo(((ArrayType)t).baseTyp));
    }

    @Override
    public boolean implicitTo(TypeI t) {
        if (this == Types.errorType || t==Types.errorType) return true;
        return ((t instanceof ArrayType)
                && baseTyp.implicitTo(((ArrayType)t).baseTyp));
    }

    public Type getBaseTyp() {
        return baseTyp;
    }

    public void setBaseTyp(Type baseTyp) {
        this.baseTyp = baseTyp;
    }

}
