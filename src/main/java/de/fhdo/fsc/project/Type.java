package de.fhdo.fsc.project;

public class Type {
    private final String name;
    private final long dimensions;
    private final boolean isNumeric;

    public Type(String name, boolean isNumeric, long dimensions) {
        this.name = name;
        this.isNumeric = isNumeric;
        this.dimensions = dimensions;
    }

    public Type(String name, boolean isNumeric) {
        this(name, isNumeric, 0);
    }
}
