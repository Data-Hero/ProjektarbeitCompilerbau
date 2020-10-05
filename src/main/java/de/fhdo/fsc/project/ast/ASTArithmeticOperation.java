package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTArithmeticOperation extends ASTBinaryOperation {
    public ASTArithmeticOperation(Token operator, ASTExpression left, ASTExpression right) {
        super(operator, left, right);
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type leftType = left.getType(errors, symbolTable);
        Type rightType = right.getType(errors, symbolTable);
        boolean typeError = false;

        if (typeError || leftType == BasicType.errorType || rightType == BasicType.errorType) {
            return BasicType.errorType;
        }

        Type result = Type.getCommonType(leftType, rightType);

        if (leftType != result) {
            left.setCastTo(result);
        }

        if (rightType != result) {
            right.setCastTo(result);
        }

        return result;
    }

    @Override
    public boolean isStatement() {
        return false;
    }
}
