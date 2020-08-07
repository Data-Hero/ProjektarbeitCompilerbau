package de.fhdo.fsc.project.DataType;

import java.util.ArrayList;

public class Array<E> implements DataType{

    ArrayList<E> al;

    public Array() {
        this.al = new ArrayList<>();
    }

    public boolean add(Array<E> b) {
        return al.addAll(b.al);
    }

    public boolean subtract(Array<E> b) {
        return al.removeAll(b.al);
    }

    @Override
    public boolean add(Object b) {
        return false;
    }

    @Override
    public boolean subtract(Object b) {
        return false;
    }

    @Override
    public boolean multiply(Object b) {
        return false;
    }

    @Override
    public boolean divide(Object b) {
        return false;
    }
}
