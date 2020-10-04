package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTBinaryOperation extends ASTExpression {
    private ASTExpression left, right;
    private Token op;

    public ASTBinaryOperation(Token op, ASTExpression left, ASTExpression right) {
        super(left.getStart(), right.getEnd());
        this.op = op;
        this.left = left;
        this.right = right;
    }
}