package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ASTSmartSwitch extends ASTExpression {
    private Map<String, ASTBlock> cases = new HashMap<>();

    public ASTSmartSwitch(Token start, Map<String, ASTBlock> cases, Token end) {
        super(start, end);
        this.cases = cases;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        return BasicType.stringType;
    }

    @Override
    public boolean isStatement() {
        return false;
    }
}
