package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTCompare extends ASTBinaryOperation {
    public ASTCompare(Token op, ASTExpression left, ASTExpression right) {
        super(op,left,right);
    }
}
