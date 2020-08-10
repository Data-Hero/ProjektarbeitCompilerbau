package de.fhdo.fsc.project.DataType;

public class NumericType extends Type{

    int rank;

    public NumericType(String name, int rank) {
        super(name);
        this.rank = rank;
    }
}
