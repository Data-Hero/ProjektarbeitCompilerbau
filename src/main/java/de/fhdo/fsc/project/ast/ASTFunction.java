package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

import java.util.Map;

public class ASTFunction extends ASTStatement {
    private Token returnType;
    private Token identifier;
    private Map<Token, Token> parameter;
    private ASTBlock block;

    public ASTFunction(Token returnType, Token identifier, Map<Token, Token> parameter, ASTBlock block) {
        super(returnType, block.getEnd());
        this.returnType = returnType;
        this.identifier = identifier;
        this.parameter = parameter;
        this.block = block;
    }
}
