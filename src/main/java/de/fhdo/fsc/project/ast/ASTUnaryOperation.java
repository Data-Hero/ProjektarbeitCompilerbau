package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTUnaryOperation extends ASTExpression {
    private Token operation;
    private ASTExpression expression;

    public ASTUnaryOperation(Token operation, ASTExpression expression) {
        super(operation, expression.getEnd());
        this.operation = operation;
        this.expression = expression;
    }
}