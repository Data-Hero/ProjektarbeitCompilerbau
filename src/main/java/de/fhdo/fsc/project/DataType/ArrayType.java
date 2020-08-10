package de.fhdo.fsc.project.DataType;

import java.util.ArrayList;

/**
 * Static array structure
 */
public class ArrayType extends Type{

    Type baseTyp;
    int dim;

    public ArrayType(Type baseTyp, int dim) {
        super("");
        this.baseTyp = baseTyp;
        this.dim = dim;
    }

    public int size() {
        return dim;
    }
}
