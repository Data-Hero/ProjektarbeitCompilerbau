package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.SymbolTable;

import java.util.LinkedList;

public class ASTProgram extends ASTNode {
    private LinkedList<ASTFunctionDeclaration> functions = new LinkedList<>();
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

    public void addFunction(ASTFunctionDeclaration function) {
        functions.add(function);
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        SymbolTable innerSymbolTable = new SymbolTable(symbolTable);

        for (ASTFunctionDeclaration f : functions) {
            f.semanticAnalysis(errors, innerSymbolTable);
        }

        block.semanticAnalysis(errors, innerSymbolTable);
    }

    @Override
    public void run(LinkedList<CompilerError> errors) {
        block.run(errors);
    }
}
