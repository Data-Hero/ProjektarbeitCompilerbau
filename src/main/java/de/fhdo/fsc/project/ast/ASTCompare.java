package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTCompare extends ASTBinaryOperation {
    public ASTCompare(Token op, ASTExpression left, ASTExpression right) {
        super(op, left, right);
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type leftType = left.getType(errors, symbolTable);
        Type rightType = right.getType(errors, symbolTable);
        boolean typeError = false;

        if (!(leftType instanceof BasicType && ((BasicType) leftType).isNumeric())) {
            errors.add(new SemanticError("left operator in comparison operation must be numeric", left.getStart(), left.getEnd()));
            typeError = true;
        }

        if (!(rightType instanceof BasicType && ((BasicType) rightType).isNumeric())) {
            errors.add(new SemanticError("right operator in comparison operation must be numeric", right.getStart(), right.getEnd()));
            typeError = true;
        }

        if (typeError || leftType == BasicType.errorType || rightType == BasicType.errorType) {
            return BasicType.errorType;
        }

        Type result = Type.getCommonType(leftType, rightType);
        if (leftType != result) left.setCastTo(result);
        if (rightType != result) right.setCastTo(result);

        return BasicType.boolType;
    }

    @Override
    public boolean isStatement() {
        return false;
    }
}
