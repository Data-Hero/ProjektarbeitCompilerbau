package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

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

    @Override
    public Value upgrade(BasicType t) {
        if (t == BasicType.stringType) {
            return toStringValue();
        } else if (t == BasicType.doubleType) {
            return new DoubleValue(this.value.doubleValue());
        }

        return this;
    }

    public Integer getValue() {
        return value;
    }
}
