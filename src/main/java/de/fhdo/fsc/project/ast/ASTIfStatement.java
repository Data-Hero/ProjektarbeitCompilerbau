package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTIfStatement extends ASTStatement {
    private ASTExpression expression;
    private ASTStatement statement;
    private ASTIfStatement elseIfStatement;
    private ASTStatement elseStatement;

    public ASTIfStatement(Token start, ASTExpression expression, ASTStatement statement, ASTIfStatement elseIfStatement, ASTStatement elseStatement) {
        super(start, statement.getEnd());
        this.expression = expression;
        this.statement = statement;
        this.elseIfStatement = elseIfStatement;
        this.elseStatement = elseStatement;
    }
}
