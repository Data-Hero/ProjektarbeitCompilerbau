package de.fhdo.fsc.project.DataType;

import javax.xml.crypto.Data;

/**
 * NewAwk knows 4 forms of Types: Numeric, Strings, Functions, Types
 * @param <J>
 */
public abstract class DataType<J extends DataType> {
    String name;

    public DataType(String name) {
        this.name = name;
    }

    public Sum<DataType, J> add(J k) {
        Sum<DataType,J> sum = new Sum<>();
        sum.setA(this);
        sum.setW(k);
        return sum;
    }

    public abstract boolean subtract(J k);

    public boolean multiply(J k) {
        return false;
    }

    public abstract boolean divide(J k);

    public abstract boolean check(String k);
}
