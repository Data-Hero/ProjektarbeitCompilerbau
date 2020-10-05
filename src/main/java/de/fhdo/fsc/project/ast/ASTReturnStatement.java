package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.SymbolTable;

import java.util.LinkedList;

public class ASTReturnStatement extends ASTStatement {
    private ASTExpression expression;

    public ASTReturnStatement(Token start, ASTExpression expression, Token end){
        super(start, end);
        this.expression = expression;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);
    }
}