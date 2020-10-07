package de.fhdo.fsc.project.value;

import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.Type;

public abstract class BasicValue extends Value {
    protected BasicValue(Type type) {
        super(type);
    }

    public static Value upgrade(BasicValue v, BasicType t) {
        Value result = v;

        switch (v.type.toString()) {
            case BasicType.BOOLEAN_NAME:
                BooleanValue booleanValue = (BooleanValue) v;
                result = booleanValue;
                break;
            case BasicType.INTEGER_NAME:
                IntegerValue integerValue = (IntegerValue) v;
                switch (t.toString()) {
                    case BasicType.DOUBLE_NAME:
                        result = new DoubleValue(integerValue.value.doubleValue());
                        break;
                    case BasicType.STRING_NAME:
                        result = new StringValue(integerValue.toString());
                        break;
                }
                break;
            case BasicType.DOUBLE_NAME:
                DoubleValue doubleValue = (DoubleValue) v;
                if (BasicType.STRING_NAME.equals(t.toString())) {
                    result = new StringValue(doubleValue.toString());
                }
                break;
            case BasicType.CHARACTER_NAME:
                CharacterValue characterValue = (CharacterValue) v;
                switch (t.toString()) {
                    case BasicType.INTEGER_NAME:
                        result = new IntegerValue((int) characterValue.value);
                        break;
                    case BasicType.DOUBLE_NAME:
                        result = new DoubleValue((double) characterValue.value);
                        break;
                    case BasicType.STRING_NAME:
                        result = new StringValue(characterValue.value.toString());
                        break;
                }
                break;
            case BasicType.STRING_NAME:
                result = v;
        }

        return result;
    }

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
