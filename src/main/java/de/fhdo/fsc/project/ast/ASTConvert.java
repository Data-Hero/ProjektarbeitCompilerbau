package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.Value;

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
        expression.semanticAnalysis(errors, symbolTable);
        return Type.resolve(type.image);
    }

    @Override
    public boolean isStatement() {
        return false;
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        Type type = Type.resolve(this.type.image);
        return expression.getValue(errors).upgrade(type);
    }
}
