package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

public abstract class BasicValue extends Value {
    protected BasicValue(Type type) {
        super(type);
    }

    public abstract Value upgrade(BasicType t);

    public static Value sign(String operation, Value value) {
        Value result = Value.create(value.type);

        if (result.type == BasicType.intType) {
            IntegerValue intValue = (IntegerValue) value;
            switch (operation) {
                case "+":
                    ((IntegerValue) result).value = intValue.value;
                    break;
                case "-":
                    ((IntegerValue) result).value = -intValue.value;
                    break;
            }
        } else if (result.type == BasicType.doubleType) {
            DoubleValue doubleValue = (DoubleValue) value;
            switch (operation) {
                case "+":
                    ((DoubleValue) result).value = doubleValue.value;
                    break;
                case "-":
                    ((DoubleValue) result).value = -doubleValue.value;
                    break;
            }
        } else if (result.type == BasicType.charType) {
            CharacterValue characterValue = (CharacterValue) value;
            switch (operation) {
                case "+":
                    ((CharacterValue) result).value = characterValue.value;
                    break;
                case "-":
                    ((CharacterValue) result).value = (char) (-((int) characterValue.value));
                    break;
            }
        }

        return result;
    }

    public static Value preIncrease(Value value) {
        Value result = null;
        if (value.type == BasicType.intType) {
            IntegerValue tResult = new IntegerValue();
            IntegerValue tValue = (IntegerValue) value;
            tValue.value = ++tResult.value;
            result = tResult;
        } else if (value.type == BasicType.doubleType) {
            DoubleValue tResult = new DoubleValue();
            DoubleValue tValue = (DoubleValue) value;
            tValue.value = ++tResult.value;
        } else if (value.type == BasicType.charType) {
            CharacterValue tResult = new CharacterValue();
            CharacterValue tValue = (CharacterValue) value;
            tValue.value = ++tResult.value;
        }

        assert result != null;
        return result;
    }

    public static Value preDecrease(Value value) {
        Value result = null;
        if (value.type == BasicType.intType) {
            IntegerValue tResult = new IntegerValue();
            IntegerValue tValue = (IntegerValue) value;
            tValue.value = --tResult.value;
            result = tResult;
        } else if (value.type == BasicType.doubleType) {
            DoubleValue tResult = new DoubleValue();
            DoubleValue tValue = (DoubleValue) value;
            tValue.value = --tResult.value;
        } else if (value.type == BasicType.charType) {
            CharacterValue tResult = new CharacterValue();
            CharacterValue tValue = (CharacterValue) value;
            tValue.value = --tResult.value;
        }

        assert result != null;
        return result;
    }

    public static Value postIncrease(Value value) {
        Value result = null;
        if (value.type == BasicType.intType) {
            IntegerValue tResult = new IntegerValue();
            IntegerValue tValue = (IntegerValue) value;
            tValue.value = tResult.value++;
            result = tResult;
        } else if (value.type == BasicType.doubleType) {
            DoubleValue tResult = new DoubleValue();
            DoubleValue tValue = (DoubleValue) value;
            tValue.value = tResult.value++;
        } else if (value.type == BasicType.charType) {
            CharacterValue tResult = new CharacterValue();
            CharacterValue tValue = (CharacterValue) value;
            tValue.value = tResult.value++;
        }

        assert result != null;
        return result;
    }

    public static Value postDecrease(Value value) {
        Value result = null;
        if (value.type == BasicType.intType) {
            IntegerValue tResult = new IntegerValue();
            IntegerValue tValue = (IntegerValue) value;
            tValue.value = tResult.value--;
            result = tResult;
        } else if (value.type == BasicType.doubleType) {
            DoubleValue tResult = new DoubleValue();
            DoubleValue tValue = (DoubleValue) value;
            tValue.value = tResult.value--;
        } else if (value.type == BasicType.charType) {
            CharacterValue tResult = new CharacterValue();
            CharacterValue tValue = (CharacterValue) value;
            tValue.value = tResult.value--;
        }

        assert result != null;
        return result;
    }
}
