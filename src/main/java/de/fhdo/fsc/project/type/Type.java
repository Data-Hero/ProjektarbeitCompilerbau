package de.fhdo.fsc.project.type;

public abstract class Type {
    public abstract boolean isArray();

    public static Type resolve(String name) {
        switch (name) {
            case BasicType.DOUBLE_NAME:
                return BasicType.doubleType;
            case BasicType.INTEGER_NAME:
                return BasicType.intType;
            case BasicType.BOOLEAN_NAME:
                return BasicType.boolType;
            case BasicType.CHARACTER_NAME:
                return BasicType.charType;
            case BasicType.STRING_NAME:
                return BasicType.stringType;
        }

        if (name.matches(".+\\[\\]$")) {
            int dimensions = getDimensions(name);
            return new ArrayType((BasicType) resolve(name.substring(0, name.length() - (2 * dimensions))), dimensions);
        }

        return BasicType.errorType;
    }

    private static int getDimensions(String name) {
        if (name.matches(".+\\[\\]$")) {
            return 1 + getDimensions(name.substring(0, name.length() - 2));
        } else {
            return 0;
        }
    }

    public static boolean implicit(Type a, Type b) {
        if (a instanceof BasicType && b instanceof BasicType) {
            return BasicType.implicit((BasicType) a, (BasicType) b);
        } else if (a instanceof BasicType && b instanceof ArrayType && ((ArrayType) b).getDimensions() == 1) {
            return BasicType.implicit((BasicType) a, ((ArrayType) b).getBasicType());
        } else if (a instanceof ArrayType && b instanceof ArrayType) {
            return ArrayType.implicit((ArrayType) a, (ArrayType) b);
        } else {
            return false;
        }
    }

    public static Type getCommonType(Type a, Type b) {
        if (a instanceof BasicType && b instanceof BasicType) {
            return BasicType.getCommonType((BasicType) a, (BasicType) b);
        } else if (a instanceof ArrayType && b instanceof ArrayType) {
            return ArrayType.getCommonType((ArrayType) a, (ArrayType) b);
        } else if (a instanceof BasicType && b instanceof ArrayType) {
            return ArrayType.getCommonType(new ArrayType(((BasicType) a), 1), (ArrayType) b);
        } else if (a instanceof ArrayType && b instanceof BasicType) {
            return ArrayType.getCommonType((ArrayType) a, new ArrayType(((BasicType) b), 1));
        }

        return BasicType.errorType;
    }
}