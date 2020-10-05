package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTLength extends ASTExpression {
    private ASTExpression expression;

    public ASTLength(Token start, Token end, ASTExpression expression) {
        super(start, end);
        this.expression = expression;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        return BasicType.intType;
    }

    @Override
    public boolean isStatement() {
        return false;
    }
}
