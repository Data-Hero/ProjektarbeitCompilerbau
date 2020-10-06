package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.beans.Expression;
import java.util.LinkedList;

public class ASTRead extends ASTExpression {
    private ASTExpression expression;

    public ASTRead(Token start, Token end, ASTExpression expression) {
        super(start, end);
        this.expression = expression;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        return BasicType.stringType;
    }

    @Override
    public boolean isStatement() {
        return true; // ToDo: Check if correct
    }
}
