package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.SymbolTable;

import java.util.LinkedList;

public abstract class ASTNode {
    protected Token start;
    protected Token end;

    public ASTNode() {
    }

    public ASTNode(Token start) {
        this.start = start;
    }

    public ASTNode(Token start, Token end) {
        this.start = start;
        this.end = end;
    }

    public Token getStart() {
        return start;
    }

    public void setStart(Token start) {
        this.start = start;
    }

    public Token getEnd() {
        return end;
    }

    public void setEnd(Token end) {
        this.end = end;
    }

    public void setToken(Token start, Token end) {
        this.start = start;
        this.end = end;
    }

    public abstract void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable);
}
