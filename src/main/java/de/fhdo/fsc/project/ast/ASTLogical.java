package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

public class ASTLogical extends ASTBinaryOperation {
    public ASTLogical(Token junktor, ASTExpression left, ASTExpression right) {
        super(junktor,left,right);
    }
}
