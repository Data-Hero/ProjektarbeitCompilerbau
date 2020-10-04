package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

import java.util.LinkedList;

public class ASTBlock extends ASTStatement {
    private LinkedList<ASTNode> elements = new LinkedList<>(); // Statements and declarations

    public ASTBlock() {}
    public ASTBlock(Token start) {
        super(start);
    }
    public ASTBlock(Token start, Token end) {
        super(start, end);
    }

    public void add(ASTNode node) { elements.add(node); }
}
