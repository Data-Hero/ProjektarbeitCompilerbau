package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.BooleanValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTIsType extends ASTExpression {
    private Token type;
    private ASTExpression expression;

    public ASTIsType(Token start, Token end, Token type, ASTExpression expression) {
        super(start, end);
        this.type = type;
        this.expression = expression;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);
        return BasicType.boolType;
    }

    @Override
    public boolean isStatement() {
        return false; // ToDo: Check if correct
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        Type t = expression.getType(null, null);
        return new BooleanValue(t.equals(Type.resolve(type.image)));
    }
}
