package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTVariableDeclaration extends ASTDeclaration {
    private ASTExpression initializer;
    private ASTAssigment init;

    public ASTVariableDeclaration(Token type, Token identifier, ASTExpression init, Token end) {
        super(type, identifier, end);
        initializer = init;
    }

    private Type cachedType;

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

        if (initializer != null) {
            init = new ASTAssigment(id, initializer);
            init.semanticAnalysis(errors, symbolTable);
        }
    }

    @Override
    public Type getType() {
        return cachedType;
    }

    Value value;

    public void run(LinkedList<CompilerError> errors) {
        if (value == null) {
            value = Value.create(cachedType);
        }

        if (init != null) {
            init.getValue(errors);
        }
    }

    public Value getValue(LinkedList<CompilerError> errors) {
        return value;
    }
}