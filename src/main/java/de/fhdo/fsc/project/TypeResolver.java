package de.fhdo.fsc.project;

public class TypeResolver {
    public static Type resolve(String t, long dimensions) {

        String basic = t.substring(0, t.indexOf("["));

        switch (t) {
            case "char":
                return new Type("char", false, dimensions);
            case "string":
                return new Type("string", false, dimensions);
            case "boolean":
                return new Type("boolean", true, dimensions);
            case "int":
                return new Type("int", true, dimensions);
            case "double":
                return new Type("double", true, dimensions);
            default:
                return new Type("error", true, dimensions);
        }
    }

    public static Type resolve(Token t, long dimensions) {
        return resolve(t.image, dimensions);
    }
}
