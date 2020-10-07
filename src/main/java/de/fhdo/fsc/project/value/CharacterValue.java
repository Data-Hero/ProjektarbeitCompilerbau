package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

public class CharacterValue extends BasicValue {
    Character value;

    public CharacterValue(Character value) {
        this();
        this.value = value;
    }

    public CharacterValue() {
        super(BasicType.charType);
        this.value = 0;
    }

    @Override
    public StringValue toStringValue() {
        return new StringValue(value.toString());
    }

    @Override
    public int length() {
        return 1;
    }

    public static CharacterValue operation(Type returnType, String operation, CharacterValue leftValue, CharacterValue rightValue) {
        CharacterValue result = new CharacterValue();

        switch (operation) {
            case "+":
                result = new CharacterValue((char) (leftValue.value + rightValue.value));
                break;
            case "-":
                result = new CharacterValue((char) (leftValue.value - rightValue.value));
                break;
            case "*":
                result = new CharacterValue((char) (leftValue.value * rightValue.value));
                break;
            case "/":
                result = new CharacterValue((char) (leftValue.value / rightValue.value));
                break;
            case "%":
                result = new CharacterValue((char) (leftValue.value % rightValue.value));
                break;
        }

        return result;
    }

    @Override
    public Value upgrade(BasicType t) {
        if (t == BasicType.stringType) {
            return toStringValue();
        } else if (t == BasicType.doubleType) {
            return new DoubleValue((double) this.value);
        } else if (t == BasicType.intType) {
            return new IntegerValue((int) this.value);
        }

        return this;
    }

    public Character getValue() {
        return value;
    }
}