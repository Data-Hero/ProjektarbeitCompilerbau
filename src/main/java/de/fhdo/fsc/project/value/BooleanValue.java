package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

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

    @Override
    public Value upgrade(BasicType t) {
        return this;
    }

    public Boolean getValue() {
        return value;
    }

    public static BooleanValue not(BooleanValue v) {
        return new BooleanValue(!v.getValue());
    }
}
