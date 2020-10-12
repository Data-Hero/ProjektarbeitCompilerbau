package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

import java.util.Objects;

public class BooleanValue extends BasicValue {
    Boolean value;

    public BooleanValue(Boolean value) {
        this();
        this.value = value;
    }

    public BooleanValue() {
        super(BasicType.boolType);
        this.value = false;
    }

    @Override
    public StringValue toStringValue() {
        return new StringValue(value.toString());
    }

    public static BooleanValue operation(Type returnType, String operation, Value leftValue, Value rightValue) {
        BooleanValue result = new BooleanValue();

        if (leftValue.type == BasicType.intType) {
            switch (operation) {
                case "<":
                    result = new BooleanValue(((IntegerValue) leftValue).value < ((IntegerValue) rightValue).value);
                    break;
                case ">":
                    result = new BooleanValue(((IntegerValue) leftValue).value > ((IntegerValue) rightValue).value);
                    break;
                case "<=":
                    result = new BooleanValue(((IntegerValue) leftValue).value <= ((IntegerValue) rightValue).value);
                    break;
                case ">=":
                    result = new BooleanValue(((IntegerValue) leftValue).value >= ((IntegerValue) rightValue).value);
                    break;
                case "==":
                    result = new BooleanValue(((IntegerValue) leftValue).value == ((IntegerValue) rightValue).value);
                    break;
                case "!=":
                    result = new BooleanValue(((IntegerValue) leftValue).value != ((IntegerValue) rightValue).value);
                    break;
            }
        } else if (leftValue.type == BasicType.doubleType) {
            switch (operation) {
                case "<":
                    result = new BooleanValue(((DoubleValue) leftValue).value < ((DoubleValue) rightValue).value);
                    break;
                case ">":
                    result = new BooleanValue(((DoubleValue) leftValue).value > ((DoubleValue) rightValue).value);
                    break;
                case "<=":
                    result = new BooleanValue(((DoubleValue) leftValue).value <= ((DoubleValue) rightValue).value);
                    break;
                case ">=":
                    result = new BooleanValue(((DoubleValue) leftValue).value >= ((DoubleValue) rightValue).value);
                    break;
                case "==":
                    result = new BooleanValue(((DoubleValue) leftValue).value == ((DoubleValue) rightValue).value);
                    break;
                case "!=":
                    result = new BooleanValue(((DoubleValue) leftValue).value != ((DoubleValue) rightValue).value);
                    break;
            }
        } else if (leftValue.type == BasicType.stringType) {
            switch (operation) {
                case "==":
                    result = new BooleanValue(((StringValue) leftValue).value.equals(((StringValue) rightValue).value));
                    break;
                case "!=":
                    result = new BooleanValue(!((StringValue) leftValue).value.equals(((StringValue) rightValue).value));
                    break;
            }
        } else if (leftValue.type.isArray()) {
            switch (operation) {
                case "==":
                    result = new BooleanValue(((ArrayValue) leftValue).value.equals(((ArrayValue) rightValue).value));
                    break;
                case "!=":
                    result = new BooleanValue(!((ArrayValue) leftValue).value.equals(((ArrayValue) rightValue).value));
                    break;
            }
        } else {
            switch (operation) {
                case "&&":
                    result = new BooleanValue(((BooleanValue) leftValue).value && ((BooleanValue) rightValue).value);
                    break;
                case "||":
                    result = new BooleanValue(((BooleanValue) leftValue).value || ((BooleanValue) rightValue).value);
                    break;
            }
        }

        return result;
    }

    @Override
    public int length() {
        return 1;
    }

    public Boolean getValue() {
        return value;
    }

    public static BooleanValue not(BooleanValue v) {
        return new BooleanValue(!v.getValue());
    }

    public BooleanValue copy(BooleanValue v) {
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
        BooleanValue that = (BooleanValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
