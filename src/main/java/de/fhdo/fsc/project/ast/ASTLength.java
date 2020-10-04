package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTLength extends ASTExpression {
    private ASTExpression expression;

    public ASTLength(Token start, Token end, ASTExpression expression) {
        super(start, end);
        this.expression = expression;
    }
}
