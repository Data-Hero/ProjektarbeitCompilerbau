package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

public class StringValue extends BasicValue {
    String value;

    public StringValue(String value) {
        this();
        this.value = value;
    }

    public StringValue() {
        super(BasicType.stringType);
        this.value = "";
    }

    @Override
    public StringValue toStringValue() {
        return this;
    }

    @Override
    public int length() {
        return value.length();
    }

    public static StringValue operation(Type returnType, String operation, StringValue leftValue, StringValue rightValue) {
        StringValue result = new StringValue();

        if ("+".equals(operation)) {
            result = new StringValue(leftValue.value + rightValue.value);
        }

        return result;
    }

    @Override
    public Value upgrade(BasicType t) {
        // Nothing is upgradable to String
        return this;
    }

    public String getValue() {
        return value;
    }
}
