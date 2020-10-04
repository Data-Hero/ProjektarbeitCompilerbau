package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTExpressionStatement extends ASTStatement {
    private ASTExpression expression;

    public ASTExpressionStatement(ASTExpression ausdruck, Token end) {
        super(ausdruck.getStart(), end);
        this.expression = ausdruck;
    }
}
