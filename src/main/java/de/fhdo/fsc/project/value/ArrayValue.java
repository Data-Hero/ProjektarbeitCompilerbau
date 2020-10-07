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

    public static Value operation(ArrayType returnType, String operation, ArrayValue leftValue, ArrayValue rightValue) {
        ArrayValue result = new ArrayValue(returnType.getBasicType(), returnType.dimensions);

        switch (operation) {
            case "+":
                result.value.addAll(leftValue.value);
                result.value.addAll(rightValue.value);
                break;
            case "-":
                result.value.addAll(leftValue.value);
                result.value.removeAll(rightValue.value);
                break;
        }

        return result;
    }

    public static Value upgrade(ArrayValue v, ArrayType t) {
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

    public ArrayValue copy(ArrayValue v) {
        this.value = v.value;
        this.type = v.type;
        return this;
    }
}
