package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTConvert extends ASTExpression {
    private Token type;
    private ASTExpression expression;

    public ASTConvert(Token start, Token end, Token type, ASTExpression expression) {
        super(start, end);
        this.type = type;
        this.expression = expression;
    }
}
