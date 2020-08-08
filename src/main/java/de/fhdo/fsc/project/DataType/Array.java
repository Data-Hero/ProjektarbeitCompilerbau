package de.fhdo.fsc.project.DataType;

import java.util.ArrayList;

/**
 * Dynamic array structure
 * @param <E> Content of array
 */
public class Array<E> extends DataType<Array>{

    ArrayList<E> al;

    public Array(String name) {
        super(name);
        this.al = new ArrayList<>();
    }

    public int length() {
        return al.size();
    }



    @Override
    public boolean subtract(Array k) {
        return al.removeAll(k.al);
    }

    @Override
    public boolean divide(Array k) {
        return false;
    }

    @Override
    public boolean check(String k) {
        return false;
    }
}
