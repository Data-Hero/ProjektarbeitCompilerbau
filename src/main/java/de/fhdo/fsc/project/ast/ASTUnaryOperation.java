package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.BasicValue;
import de.fhdo.fsc.project.value.BooleanValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTUnaryOperation extends ASTExpression {
    private Token operation;
    private ASTExpression expression;

    public ASTUnaryOperation(Token operation, ASTExpression expression) {
        super(operation, expression.getEnd());
        this.operation = operation;
        this.expression = expression;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type type = expression.getType(errors, symbolTable);

        if (type == BasicType.errorType) {
            return BasicType.errorType;
        }

        if (type instanceof BasicType) {
            BasicType basicType = (BasicType) type;

            if (operation.image.equals("!")) {
                if (basicType != BasicType.boolType) {
                    errors.add(new SemanticError("only a boolean can be negated", getStart(), getEnd()));
                    return BasicType.errorType;
                } else {
                    return BasicType.boolType;
                }
            } else { // it must be numeric
                if (!basicType.isNumeric()) {
                    errors.add(new SemanticError("only a numeric type is signed", getStart(), getEnd()));
                    return BasicType.errorType;
                } else {
                    return basicType;
                }
            }
        } else {
            return type;
        }
    }

    @Override
    public boolean isStatement() {
        return false;
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        Value result;

        if (operation.image.equals("!")) {
            result = BooleanValue.not((BooleanValue) expression.getValue(errors));
        } else {
            result = BasicValue.sign(operation.image, expression.getValue(errors));
        }

        return upgradeValue(result);
    }
}