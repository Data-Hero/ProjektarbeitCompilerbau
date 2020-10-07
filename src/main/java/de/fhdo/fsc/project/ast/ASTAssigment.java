package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTAssigment extends ASTExpression {
    private Token identifier;
    private ASTExpression expression;

    public ASTAssigment(Token ident, ASTExpression expression) {
        super(ident, expression.getEnd());
        identifier = ident;
        this.expression = expression;
    }

    ASTVariableDeclaration declaration;

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type result;
        ASTDeclaration decl = symbolTable.find(identifier.image);

        if (decl == null) {
            errors.add(new SemanticError("unknown identifier " + identifier, identifier, identifier));
            result = BasicType.errorType;
        } else if (!(decl instanceof ASTVariableDeclaration)) {
            errors.add(new SemanticError("wrong type " + identifier, identifier, identifier));
            result = BasicType.errorType;
        } else {
            declaration = (ASTVariableDeclaration) decl;
            result = declaration.getType();
        }

        Type type = expression.getType(errors, symbolTable);
        if (type == BasicType.errorType) {
            return BasicType.errorType;
        }

        if (!BasicType.implicit(type, result)) {
            errors.add(new SemanticError("incompatible types in assignment", getStart(), getEnd()));
            return BasicType.errorType;
        } else if (type != result) {
            expression.setCastTo(result);
        }
        return result;
    }

    @Override
    public boolean isStatement() {
        return true;
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        Value v = expression.getValue(errors);
        Value result = Value.create(v.type);
        Value declarationValue = declaration.getValue(errors);
        declarationValue.copy(v);

        return upgradeValue(result);
    }
}