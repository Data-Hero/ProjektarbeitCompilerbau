package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.type.Type;

public abstract class ASTDeclaration extends ASTNode {
    protected Token id;
    protected Token type;

    public ASTDeclaration(Token type, Token id, Token end) {
        super(type, end);
        this.id = id;
        this.type = type;
    }

    public abstract Type getType();
}
