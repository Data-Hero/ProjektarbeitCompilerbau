package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

import java.util.ArrayList;
import java.util.List;

public class ASTFunctionCall extends ASTExpression {
    private List<ASTExpression> parameter = new ArrayList<>();
    private Token identifier;

    public ASTFunctionCall(Token identifier, List<ASTExpression> parameter, Token end) {
        super(identifier, end);
        this.parameter = parameter;
        this.identifier = identifier;
    }
}
