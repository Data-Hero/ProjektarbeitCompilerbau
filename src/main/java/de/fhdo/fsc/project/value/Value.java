package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

public abstract class Value {
    public Type type;

    protected Value(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract StringValue toStringValue();

    public static Value operation(Type returnType, String operation, Value leftValue, Value rightValue) {
        if (returnType instanceof BasicType) {
            BasicType rType = (BasicType) returnType;

            switch (rType.getName()) {
                case BasicType.STRING_NAME:
                    return StringValue.operation(rType, operation, (StringValue) leftValue, (StringValue) rightValue);
                case BasicType.CHARACTER_NAME:
                    return CharacterValue.operation(rType, operation, (CharacterValue) leftValue, (CharacterValue) rightValue);
                case BasicType.INTEGER_NAME:
                    return IntegerValue.operation(rType, operation, (IntegerValue) leftValue, (IntegerValue) rightValue);
                case BasicType.DOUBLE_NAME:
                    return DoubleValue.operation(rType, operation, (DoubleValue) leftValue, (DoubleValue) rightValue);
                case BasicType.BOOLEAN_NAME:
                    return BooleanValue.operation(rType, operation, leftValue, rightValue);
            }
        } else if (returnType instanceof ArrayType) {
            return ArrayValue.operation((ArrayType) returnType, operation, (ArrayValue) leftValue, (ArrayValue) rightValue);
        }

        return null; // ToDo: Throw exception or leftValue?
    }

    public Value upgrade(Type t) {
        // ToDo: Check if the correct upgrade function will be called
        if (t instanceof BasicType) {
            return BasicValue.upgrade((BasicValue) this, (BasicType) t);
        } else if (t instanceof ArrayType) {
            return ArrayValue.upgrade((ArrayValue) this, (ArrayType) t);
        } else {
            return null;
        }
    }

    public static Value create(Type t) {
        Value result = null;

        if (t instanceof BasicType) {
            BasicType type = (BasicType) t;

            switch (type.getName()) {
                case BasicType.STRING_NAME:
                    result = new StringValue();
                    break;
                case BasicType.CHARACTER_NAME:
                    result = new CharacterValue();
                    break;
                case BasicType.INTEGER_NAME:
                    result = new IntegerValue();
                    break;
                case BasicType.DOUBLE_NAME:
                    result = new DoubleValue();
                    break;
                case BasicType.BOOLEAN_NAME:
                    result = new BooleanValue();
                    break;
            }
        } else if (t instanceof ArrayType) {
            ArrayType type = (ArrayType) t;
            result = new ArrayValue(type.getBasicType(), type.dimensions);
        }

        assert result != null;

        return result;
    }

    public abstract int length();

    public Value copy(Value v) {
        if (this.type instanceof BasicType) {
            BasicType type = (BasicType) this.type;

            if (this.type != v.type) {
                System.out.println("Value.java: Types does not match " + this.type + ", " + v.type);
            }

            switch (type.getName()) {
                case BasicType.STRING_NAME:
                    return ((StringValue) this).copy((StringValue) v);
                case BasicType.CHARACTER_NAME:
                    return ((CharacterValue) this).copy((CharacterValue) v);
                case BasicType.INTEGER_NAME:
                    return ((IntegerValue) this).copy((IntegerValue) v);
                case BasicType.DOUBLE_NAME:
                    return ((DoubleValue) this).copy((DoubleValue) v);
                case BasicType.BOOLEAN_NAME:
                    return ((BooleanValue) this).copy((BooleanValue) v);
            }
        } else if (this.type instanceof ArrayType) {
            return ((ArrayValue) this).copy(v);
        }

        return this;
    }
}
