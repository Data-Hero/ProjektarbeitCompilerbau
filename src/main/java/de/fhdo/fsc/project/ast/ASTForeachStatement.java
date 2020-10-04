package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTForeachStatement extends ASTStatement {
    private ASTExpression expression;
    private ASTStatement statement;
    private Token id;

    public ASTForeachStatement(Token start, Token id, ASTExpression expression, ASTStatement statement) {
        super(start, statement.getEnd());
        this.id = id;
        this.expression = expression;
        this.statement = statement;
    }
}
