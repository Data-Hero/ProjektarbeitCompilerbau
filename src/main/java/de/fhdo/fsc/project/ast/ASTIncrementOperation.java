package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public abstract class ASTIncrementOperation extends ASTExpression {
    Token operation, identifier;
    ASTDeclaration declaration;

    public ASTIncrementOperation(Token operation, Token identifier, boolean pre) {
        super(pre ? operation : identifier, pre ? identifier : operation);
        this.operation = operation;
        this.identifier = identifier;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type type;
        declaration = symbolTable.find(identifier.image);

        if (declaration == null) {
            errors.add(new SemanticError("unknown identifier " + identifier, identifier, identifier));
            return BasicType.errorType;
        } else {
            type = declaration.getType();
        }

        if (type instanceof BasicType) {
            BasicType basicType = (BasicType) type;

            if (operation != null && !basicType.isNumeric()) {
                errors.add(new SemanticError("only a numeric type can be in- or decremented", getStart(), getEnd()));
                return BasicType.errorType;
            } else {
                return basicType;
            }
        } else {
            return type;
        }
    }

    @Override
    public boolean isStatement() {
        return operation != null;
    }
}
