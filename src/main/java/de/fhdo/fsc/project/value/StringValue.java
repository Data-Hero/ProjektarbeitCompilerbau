package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

import java.util.Objects;

public class StringValue extends BasicValue {
    public String value;

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

    public String getValue() {
        return value;
    }

    public StringValue copy(StringValue v) {
        this.value = v.value;
        return this;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
