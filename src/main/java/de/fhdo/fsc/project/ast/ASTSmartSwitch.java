package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;

import java.util.HashMap;
import java.util.Map;

public class ASTSmartSwitch extends ASTExpression {
    private Map<String, ASTBlock> cases = new HashMap<>();

    public ASTSmartSwitch(Token start, Map<String, ASTBlock> cases, Token end) {
        super(start, end);
        this.cases = cases;
    }
}
