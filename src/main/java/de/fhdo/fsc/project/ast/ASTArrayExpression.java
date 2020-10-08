package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.ArrayValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;
import java.util.List;

public class ASTArrayExpression extends ASTExpression {
    private Type castTo;
    private List<ASTExpression> elements;

    public ASTArrayExpression(List<ASTExpression> elements, Token start, Token end) {
        super(start, end);
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

    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        if (!elements.isEmpty()) {
            for (ASTExpression e : elements) {
                e.getType(errors, symbolTable);
            }

            ASTExpression expression = elements.get(0);
            Type expressionType = expression.getType(errors, symbolTable);

            if (expressionType.isArray()) {
                ArrayType arrayType = (ArrayType) expressionType;
                cachedType = new ArrayType(arrayType.getBasicType(), arrayType.dimensions + 1);
            } else {
                cachedType = new ArrayType((BasicType) expressionType, 1);
            }
        } else {
            return new ArrayType((BasicType) castTo, 1);
        }

        return cachedType;
    }

    public Type getType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        if (cachedType != null) {
            return cachedType;
        } else {
            return cachedType = computeType(errors, symbolTable);
        }
    }

    public boolean isStatement() {
        return false;
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        ArrayType arrayType = (ArrayType) cachedType;
        if (!elements.isEmpty()) {
            ArrayValue value = new ArrayValue(arrayType.getBasicType(), arrayType.dimensions);

            for (ASTExpression e : elements) {
                value.add(e.getValue(errors));
            }

            return value;
        } else {
            return new ArrayValue(arrayType.getBasicType(), arrayType.dimensions);
        }
    }

    public List<ASTExpression> getElements() {
        return elements;
    }
}
