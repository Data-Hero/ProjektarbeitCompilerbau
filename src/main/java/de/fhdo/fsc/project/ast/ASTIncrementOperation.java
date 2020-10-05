package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTIncrementOperation extends ASTExpression {
    Token operation, identifier;

    public ASTIncrementOperation(Token operation, Token identifier, boolean pre) {
        super(pre ? operation : identifier, pre ? identifier : operation);
        this.operation = operation;
        this.identifier = identifier;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type type;
        ASTDeclaration decl = symbolTable.find(identifier.image);

        if (decl == null) {
            errors.add(new SemanticError("unknown identifier " + identifier, identifier, identifier));
            return BasicType.errorType;
        } else if (decl instanceof ASTVariableDeclaration) {
            ASTVariableDeclaration declaration;
            declaration = (ASTVariableDeclaration) decl;
            type = declaration.getType();
        } else if (decl instanceof ASTParameterDeclaration) {
            ASTParameterDeclaration declaration;
            declaration = (ASTParameterDeclaration) decl;
            type = declaration.getType();
        } else {
            errors.add(new SemanticError("wrong type " + identifier, identifier, identifier));
            return BasicType.errorType;
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
