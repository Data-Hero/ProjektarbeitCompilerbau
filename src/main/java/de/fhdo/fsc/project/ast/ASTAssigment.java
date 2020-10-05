package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTAssigment extends ASTExpression {
    private Token identifier;
    private ASTExpression value;

    public ASTAssigment(Token ident, ASTExpression ausdruck) {
        super(ident, ausdruck.getEnd());
        identifier = ident;
        value = ausdruck;
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

        Type type = value.getType(errors, symbolTable);
        if (type == BasicType.errorType) {
            return BasicType.errorType;
        }

        if (!BasicType.implicit(type, result)) {
            errors.add(new SemanticError("incompatible types in assignment", getStart(), getEnd()));
            return BasicType.errorType;
        } else if (type != result) {
            value.setCastTo(result);
        }
        return result;
    }

    @Override
    public boolean isStatement() {
        return true;
    }
}