package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTIncrementOperation extends ASTExpression {
    Token operation, identifier;
    public ASTIncrementOperation(Token operation, Token identifier, boolean pre) {
        super(pre? operation :identifier, pre? identifier: operation);
        this.operation = operation;
        this.identifier = identifier;
    }
}
