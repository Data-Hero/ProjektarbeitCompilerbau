package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.value.BasicValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTPreOperation extends ASTIncrementOperation {
    public ASTPreOperation(Token op, Token identifier) {
        super(op, identifier, true);
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        Value value = declaration.getValue(errors);

        if (operation != null) switch (operation.image) {
            case "++":
                value = BasicValue.preIncrease(value);
            case "--":
                value = BasicValue.preDecrease(value);
        }

        return upgradeValue(value);
    }
}
