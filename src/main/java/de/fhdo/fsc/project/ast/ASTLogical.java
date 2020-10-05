package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTLogical extends ASTBinaryOperation {
    public ASTLogical(Token junktor, ASTExpression left, ASTExpression right) {
        super(junktor, left, right);
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type leftType = left.getType(errors, symbolTable);
        Type rightType = right.getType(errors, symbolTable);

        boolean typeError = false;

        if (!(leftType instanceof BasicType && ((BasicType) leftType).isBoolean())) {
            errors.add(new SemanticError("left operator in boolean operation must be boolean", left.getStart(), left.getEnd()));
            typeError = true;
        }

        if (!(rightType instanceof BasicType && ((BasicType) rightType).isBoolean())) {
            errors.add(new SemanticError("right operator in boolean operation must be boolean", right.getStart(), right.getEnd()));
            typeError = true;
        }

        if (typeError || leftType == BasicType.errorType || rightType == BasicType.errorType) {
            return BasicType.errorType;
        }

        return BasicType.boolType;
    }

    @Override
    public boolean isStatement() {
        return false;
    }
}
