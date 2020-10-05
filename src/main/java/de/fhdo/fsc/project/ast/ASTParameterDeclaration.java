package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTParameterDeclaration extends ASTDeclaration {
    private Type cachedType;

    public ASTParameterDeclaration(Token type, Token id) {
        super(type, id, id);
    }

    @Override
    public Type getType() {
        return cachedType;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        try {
            symbolTable.add(id.image, this);
            cachedType = Type.resolve(type.image);
        } catch (SemanticError error) {
            error.setToken(getStart(), getEnd());
            errors.add(error);
            cachedType = BasicType.errorType;
        }
    }
}
