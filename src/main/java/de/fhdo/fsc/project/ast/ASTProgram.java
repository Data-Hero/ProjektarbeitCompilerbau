package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

import java.util.LinkedList;

public class ASTProgram extends ASTNode {
    private LinkedList<ASTFunction> functions = new LinkedList<>();
    private ASTBlock block;

    public ASTProgram(ASTBlock block) {
        this.block = block;
    }

    public ASTProgram(Token start, ASTBlock block) {
        super(start);
        this.block = block;
    }

    public ASTProgram(Token start, Token end, ASTBlock block) {
        super(start, end);
        this.block = block;
    }

    public void addFunction(ASTFunction function) {
        functions.add(function);
    }
}
