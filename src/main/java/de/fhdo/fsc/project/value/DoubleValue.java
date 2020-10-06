package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

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

    @Override
    public Value upgrade(BasicType t) {
        if (t == BasicType.stringType) {
            return toStringValue();
        }

        return this;
    }

    public Double getValue() {
        return value;
    }
}
