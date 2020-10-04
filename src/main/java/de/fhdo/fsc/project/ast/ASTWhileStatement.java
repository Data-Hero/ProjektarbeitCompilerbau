package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTWhileStatement extends ASTStatement {
    private ASTExpression expression;
    private ASTStatement statement;

    public ASTWhileStatement(Token start, ASTExpression expression, ASTStatement statement) {
        super(start, statement.getEnd());
        this.expression = expression;
        this.statement = statement;
    }
}
