package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTLiteral extends ASTExpression {
    private Token literal;

    public ASTLiteral(Token literal) {
        super(literal, literal);
        this.literal = literal;
    }
}
