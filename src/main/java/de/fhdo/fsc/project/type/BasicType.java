package de.fhdo.fsc.project.type;

public class BasicType extends Type {
    public static final String ERROR_NAME = "error";
    public static final String BOOLEAN_NAME = "bool";
    public static final String INTEGER_NAME = "int";
    public static final String DOUBLE_NAME = "double";
    public static final String STRING_NAME = "string";
    public static final String CHARACTER_NAME = "char";

    public static final Integer ERROR_RANK = -3;
    public static final Integer BOOLEAN_RANK = -2;
    public static final Integer CHARACTER_RANK = -1;
    public static final Integer INTEGER_RANK = 0;
    public static final Integer DOUBLE_RANK = 1;
    public static final Integer STRING_RANK = 2;

    public static BasicType errorType = new BasicType(ERROR_NAME, ERROR_RANK);
    public static BasicType boolType = new BasicType(BOOLEAN_NAME, BOOLEAN_RANK); // implicit use
    public static BasicType charType = new BasicType(CHARACTER_NAME, CHARACTER_RANK); // ToDo: Check position
    public static BasicType intType = new BasicType(INTEGER_NAME, INTEGER_RANK);
    public static BasicType doubleType = new BasicType(DOUBLE_NAME, DOUBLE_RANK);
    public static BasicType stringType = new BasicType(STRING_NAME, STRING_RANK);


    private final String name;
    private final int rank;

    public BasicType(String name, Integer rank) {
        this.name = name;
        this.rank = rank;
    }

    public boolean isNumeric() {  // errorType is everything
        return (this.rank != STRING_RANK && this.rank != BOOLEAN_RANK);
    }

    public boolean isBoolean() {  // errorType is everything
        return this.rank < 0;
    }

    public static boolean implicit(BasicType a, BasicType b) {
        if (a.rank == ERROR_RANK || b.rank == ERROR_RANK) {
            return true;
        }

        if (a == b) {
            return true;
        } else if (a.rank < 0 || b.rank < 0) {
            return false;
        } else {
            return a.rank < b.rank;
        }
    }

    public static BasicType getCommonType(BasicType a, BasicType b) {
        assert (a.isNumeric() && b.isNumeric() && a != errorType && b != errorType);

        if (a == b) {
            return a;
        } else if (a.rank < b.rank) {
            return b;
        } else {
            return a;
        }
    }

    public boolean isArray() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
}
