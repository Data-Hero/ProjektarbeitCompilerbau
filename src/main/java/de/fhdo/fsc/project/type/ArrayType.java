package de.fhdo.fsc.project.type;

public class ArrayType extends Type {
    private BasicType type;
    private int dimensions;

    public ArrayType(BasicType type, int dimensions) {
        this.type = type;
        this.dimensions = dimensions;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    public static boolean implicit(ArrayType a, ArrayType b) {
        if (a.dimensions == b.dimensions) {
            return BasicType.implicit(a.type, b.type);
        } else if (a.dimensions + 1 == b.dimensions) {
            return BasicType.implicit(a.type, b.type);
        } else {
            return false;
        }
    }

    public BasicType getBasicType() {
        return type;
    }

    public int getDimensions() {
        return dimensions;
    }

    public static Type getCommonType(ArrayType a, ArrayType b) {
        return new ArrayType(BasicType.getCommonType(a.type, b.type), Math.max(a.dimensions, b.dimensions));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(type.toString());

        for (int i = 0; i < dimensions; i++) {
            builder.append("[]");
        }

        return builder.toString();
    }
}
