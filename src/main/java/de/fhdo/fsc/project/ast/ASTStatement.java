package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public abstract class ASTStatement extends ASTNode {
    public ASTStatement() {}
    public ASTStatement(Token start) {
        super(start);
    }
    public ASTStatement(Token start, Token end) {
        super(start, end);
    }
}
