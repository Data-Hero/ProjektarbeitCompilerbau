package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTPreOperation extends ASTIncrementOperation {
    public ASTPreOperation(Token op, Token identifier) {
        super(op, identifier, true);
    }
}
