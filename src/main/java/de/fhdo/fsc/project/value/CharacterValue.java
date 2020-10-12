package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

import java.util.Objects;

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

    public Character getValue() {
        return value;
    }

    public CharacterValue copy(CharacterValue v) {
        this.value = v.value;
        return this;
    }

    @Override
    public String toString() {
        return "'" + value.toString() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterValue that = (CharacterValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
