package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

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

    public static Value operation(Type returnType, String operation, ArrayValue leftValue, ArrayValue rightValue) {
        ArrayType rType = (ArrayType) returnType;
        ArrayValue result = new ArrayValue(rType.getBasicType(), rType.dimensions);

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

    public Value upgrade(ArrayType t) {
        return this;
    }
}
