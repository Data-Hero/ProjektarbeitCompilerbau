package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTFunctionDeclaration extends ASTDeclaration {
    private LinkedList<ASTParameterDeclaration> parameterList;
    private ASTBlock block;

    private Type cachedType;

    public ASTFunctionDeclaration(Token returnType, Token identifier, LinkedList<ASTParameterDeclaration> parameterList, ASTBlock block) {
        super(returnType, identifier, block.getEnd());
        this.parameterList = parameterList;
        this.block = block;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        SymbolTable functionSymbolTable = new SymbolTable(symbolTable);

        try {
            symbolTable.add(this.id.image, this);
            cachedType = Type.resolve(this.type.image);
        } catch (SemanticError error) {
            error.setToken(getStart(), getEnd());
            errors.add(error);
            cachedType = BasicType.errorType;
        }

        // Check parameters
        for (ASTParameterDeclaration parameter : parameterList) {
            parameter.semanticAnalysis(errors, functionSymbolTable);
        }

        block.semanticAnalysis(errors, functionSymbolTable);
    }

    @Override
    public Type getType() {
        return cachedType;
    }

    public LinkedList<ASTParameterDeclaration> getParameterList() {
        return parameterList;
    }
}
