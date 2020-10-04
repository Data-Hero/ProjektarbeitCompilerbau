package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTReturnStatement extends ASTStatement {
    private ASTExpression expression;

    public ASTReturnStatement(Token start, ASTExpression expression, Token end){
        super(start, end);
        this.expression = expression;
    }
}