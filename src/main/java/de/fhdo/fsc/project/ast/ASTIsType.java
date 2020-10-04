package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTIsType extends ASTExpression {
    private Token type;
    private ASTExpression expression;

    public ASTIsType(Token start, Token end, Token type, ASTExpression expression) {
        super(start, end);
        this.type = type;
        this.expression = expression;
    }
}
