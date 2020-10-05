package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public abstract class ASTBinaryOperation extends ASTExpression {
    protected ASTExpression left, right;
    protected Token op;

    public ASTBinaryOperation(Token op, ASTExpression left, ASTExpression right) {
        super(left.getStart(), right.getEnd());
        this.op = op;
        this.left = left;
        this.right = right;
    }
}