package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTWrite extends ASTExpression {
    private ASTExpression pathExpression, sourceExpression;

    public ASTWrite(Token start, Token end, ASTExpression pathExpression, ASTExpression sourceExpression) {
        super(start, end);
        this.pathExpression = pathExpression;
        this.sourceExpression = sourceExpression;

    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        return BasicType.voidType;
    }

    @Override
    public boolean isStatement() {
        return true; // ToDo: Check if correct
    }
}
