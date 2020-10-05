package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTConvert extends ASTExpression {
    private Token type;
    private ASTExpression expression;

    public ASTConvert(Token start, Token end, Token type, ASTExpression expression) {
        super(start, end);
        this.type = type;
        this.expression = expression;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        return Type.resolve(type.image);
    }

    @Override
    public boolean isStatement() {
        return false;
    }
}
