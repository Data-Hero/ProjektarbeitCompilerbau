package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTAssigment extends ASTExpression {
    private Token identifier;
    private ASTExpression value;

    public ASTAssigment(Token ident, ASTExpression ausdruck) {
        super(ident, ausdruck.getEnd());
        identifier = ident;
        value = ausdruck;
    }
}