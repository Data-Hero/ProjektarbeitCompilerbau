package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTPostOperation extends ASTIncrementOperation {
    public ASTPostOperation(Token identifier, Token operation) {
        super(operation, identifier, false);
    }
}
