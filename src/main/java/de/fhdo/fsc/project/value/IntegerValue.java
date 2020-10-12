package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

import java.util.Objects;

public class IntegerValue extends BasicValue {
    Integer value;

    public IntegerValue(Integer value) {
        this();
        this.value = value;
    }

    public IntegerValue() {
        super(BasicType.intType);
        this.value = 0;
    }

    public StringValue toStringValue() {
        return new StringValue(value.toString());
    }

    @Override
    public int length() {
        return 1;
    }

    public static IntegerValue operation(Type returnType, String operation, IntegerValue leftValue, IntegerValue rightValue) {
        IntegerValue result = new IntegerValue();

        switch (operation) {
            case "+":
                result = new IntegerValue(leftValue.value + rightValue.value);
                break;
            case "-":
                result = new IntegerValue(leftValue.value - rightValue.value);
                break;
            case "*":
                result = new IntegerValue(leftValue.value * rightValue.value);
                break;
            case "/":
                result = new IntegerValue(leftValue.value / rightValue.value);
                break;
            case "%":
                result = new IntegerValue(leftValue.value % rightValue.value);
                break;
        }

        return result;
    }

    public Integer getValue() {
        return value;
    }

    public IntegerValue copy(IntegerValue v) {
        this.value = v.value;
        return this;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerValue that = (IntegerValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
