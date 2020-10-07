package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;
import java.util.List;

public class ASTArrayExpression extends ASTExpression {
    private Type castTo;
    private List<ASTExpression> elements;

    public ASTArrayExpression(List<ASTExpression> elements) {
        super(elements.get(0).start, elements.get(elements.size()-1).getEnd());
        this.elements = elements;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        getType(errors, symbolTable);
    }

    public void setCastTo(Type t) {
        castTo = t;
    }

    private Type cachedType = null;

    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable){
        return new ArrayType(BasicType.voidType, 1);
    };

    public Type getType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        if (cachedType != null) {
            return cachedType;
        } else {
            return cachedType = computeType(errors, symbolTable);
        }
    }

    public boolean isStatement() { return false; }

    public List<ASTExpression> getElements() {
        return elements;
    }
}
