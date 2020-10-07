package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTParameterDeclaration extends ASTDeclaration {
    private Type cachedType;
    private Value value;

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

    public void bind(Value value) {
        this.value = value;
    }

    public void unbind() {
        this.value = null;
    }

    public boolean isBound() {
        return this.value != null;
    }

    public void run(LinkedList<CompilerError> errors) {
        if (value == null) {
            value = Value.create(cachedType);
        }
    }

    public Value getValue(LinkedList<CompilerError> errors) {
        return this.value;
    }
}
