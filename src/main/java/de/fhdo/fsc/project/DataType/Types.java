package de.fhdo.fsc.project.DataType;


public class Types {
    public static final Type boolType = new Type("boolean");
    public static final Type stringType = new Type("String");

    public static final Type byteType = new NumericType("byte", 1);
    public static final Type shortType = new NumericType("short", 2);
    public static final Type charType = new NumericType("char", 3);
    public static final Type intType = new NumericType("int", 4);
    public static final Type longType = new NumericType("long", 5);
    public static final Type floatType = new NumericType("float", 6);
    public static final Type doubleType = new NumericType("double", 7);

    public static final Type voidType = new Type("void");
    public static final Type errorType = new Type("error");
}
