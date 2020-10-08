package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.BasicType;

import java.util.ArrayList;
import java.util.List;

public class ArrayValue extends Value {
    List<Value> value;

    public ArrayValue(List<Value> value, BasicType innerType, int dimensions) {
        this(innerType, dimensions);
        this.value = value;
    }

    public ArrayValue(BasicType innerType, int dimensions) {
        super(new ArrayType(innerType, dimensions));
        value = new ArrayList<>();
    }

    @Override
    public StringValue toStringValue() {
        return new StringValue(value.toString());
    }

    @Override
    public int length() {
        return value.size();
    }

    public static Value operation(ArrayType returnType, String operation, ArrayValue leftValue, Value rightValue) {
        ArrayValue result = new ArrayValue(returnType.getBasicType(), returnType.dimensions);

        if (rightValue instanceof ArrayValue) {
            ArrayValue rValue = (ArrayValue) rightValue;
            switch (operation) {
                case "+":
                    result.value.addAll(leftValue.value);
                    result.value.addAll(rValue.value);
                    break;
                case "-":
                    result.value.addAll(leftValue.value);
                    result.value.removeAll(rValue.value);
                    break;
            }
        } else {
            switch (operation) {
                case "+":
                    result.value.addAll(leftValue.value);
                    ArrayValue addValue = new ArrayValue((BasicType) rightValue.type, 1);
                    addValue.value.add(rightValue);
                    result.value.addAll(addValue.value);
                    break;
                case "-":
                    result.value.addAll(leftValue.value);
                    ArrayValue removeValue = new ArrayValue((BasicType) rightValue.type, 1);
                    removeValue.value.add(rightValue);
                    result.value.removeAll(removeValue.value);
                    break;
            }
        }

        return result;
    }

    public static Value upgrade(ArrayValue v, ArrayType t) {
        if (t.dimensions > ((ArrayType) v.type).dimensions) {
            ((ArrayType) v.type).dimensions = t.dimensions;
            v.value = new ArrayList<>(v.value);
            return v;
        }

        return v;
    }

    public Value get(int i) throws RuntimeError {
        if (i >= value.size()) {
            throw new RuntimeError("Index " + i + " out of bound " + value.size());
        }

        return value.get(i);
    }

    public Value set(int i, Value v) throws RuntimeError {
        if (i >= value.size()) {
            throw new RuntimeError("Index " + i + " out of bound " + value.size());
        }

        return value.set(i, v);
    }

    public ArrayValue copy(Value v) {
        if (v instanceof ArrayValue) {
            ArrayValue value = (ArrayValue) v;
            this.value = value.value;
            this.type = value.type;
        } else if (v instanceof BasicValue) {
            BasicValue value = (BasicValue) v;
            this.value = new ArrayList<>();
            this.value.add(value);
            this.type = new ArrayType((BasicType) value.type, 1);
        }

        return this;
    }

    public void add(Value v) {
        value.add(v);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
