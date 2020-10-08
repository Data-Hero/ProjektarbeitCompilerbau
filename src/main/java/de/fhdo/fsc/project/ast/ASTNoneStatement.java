package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.SymbolTable;

import java.util.LinkedList;

public class ASTNoneStatement extends ASTStatement {
    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {

    }

    @Override
    public void run(LinkedList<CompilerError> errors) {

    }
}
