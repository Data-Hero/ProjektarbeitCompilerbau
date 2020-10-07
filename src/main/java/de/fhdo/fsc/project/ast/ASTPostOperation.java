package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.value.BasicValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTPostOperation extends ASTIncrementOperation {
    public ASTPostOperation(Token identifier, Token operation) {
        super(operation, identifier, false);
    }

    public Value getValue(LinkedList<CompilerError> errors) {
        Value value = declaration.getValue(errors);

        if (operation != null) switch (operation.image) {
            case "++":
                value = BasicValue.postIncrease(value);
            case "--":
                value = BasicValue.postDecrease(value);
        }

        return upgradeValue(value);
    }
}
