package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTDeclaration extends ASTNode {
    private Token variable;
    private Token type;
    private ASTNode initializer;

    public ASTDeclaration(Token type, Token ident, ASTNode init, Token end) {
        super(type, end);
        initializer = init;
        this.type = type;
        this.variable = ident;
    }
}