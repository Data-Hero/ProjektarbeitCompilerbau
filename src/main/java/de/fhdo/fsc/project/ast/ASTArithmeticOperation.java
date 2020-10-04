package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTArithmeticOperation extends ASTBinaryOperation {
    public ASTArithmeticOperation(Token operator, ASTExpression left, ASTExpression right) {
        super(operator ,left, right);
    }
}
