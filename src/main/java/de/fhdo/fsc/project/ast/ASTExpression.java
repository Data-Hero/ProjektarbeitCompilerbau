package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public abstract class ASTExpression extends ASTNode {
    private Type castTo;

    public ASTExpression(Token start, Token end) {
        super(start, end);
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        getType(errors, symbolTable);
    }

    public void setCastTo(Type t) {
        castTo = t;
    }

    private Type cachedType = null;

    protected abstract Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable);

    public Type getType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        if (cachedType != null) {
            return cachedType;
        } else {
            return cachedType = computeType(errors, symbolTable);
        }
    }

    public abstract boolean isStatement();
}
