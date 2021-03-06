package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

import java.util.Objects;

public class DoubleValue extends BasicValue {
    Double value;

    public DoubleValue(Double value) {
        this();
        this.value = value;
    }

    public DoubleValue() {
        super(BasicType.doubleType);
        this.value = 0.0;
    }

    @Override
    public StringValue toStringValue() {
        return new StringValue(value.toString());
    }

    @Override
    public int length() {
        return 1;
    }

    public static DoubleValue operation(Type returnType, String operation, DoubleValue leftValue, DoubleValue rightValue) {
        DoubleValue result = new DoubleValue();

        switch (operation) {
            case "+":
                result = new DoubleValue(leftValue.value + rightValue.value);
                break;
            case "-":
                result = new DoubleValue(leftValue.value - rightValue.value);
                break;
            case "*":
                result = new DoubleValue(leftValue.value * rightValue.value);
                break;
            case "/":
                result = new DoubleValue(leftValue.value / rightValue.value);
                break;
            case "%":
                result = new DoubleValue(leftValue.value % rightValue.value);
                break;
        }

        return result;
    }

    public Double getValue() {
        return value;
    }

    public DoubleValue copy(DoubleValue v) {
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
        DoubleValue that = (DoubleValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
