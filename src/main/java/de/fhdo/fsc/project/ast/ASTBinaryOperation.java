package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public abstract class ASTBinaryOperation extends ASTExpression {
    protected ASTExpression left, right;
    protected Token op;

    public ASTBinaryOperation(Token op, ASTExpression left, ASTExpression right) {
        super(left.getStart(), right.getEnd());
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public Value getValue(LinkedList<CompilerError> errors) {
        Value value = Value.operation(getType(null, null), op.image, left.getValue(errors), right.getValue(errors));
        return upgradeValue(value);
    }
}