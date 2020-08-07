package de.fhdo.fsc.project.DataType;

import java.util.ArrayList;

public class Array<E> {

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
}
