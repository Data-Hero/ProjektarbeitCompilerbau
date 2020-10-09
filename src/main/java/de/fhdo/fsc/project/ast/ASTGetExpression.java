package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.ArrayValue;
import de.fhdo.fsc.project.value.IntegerValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;

public class ASTGetExpression extends ASTExpression {
    private ASTExpression arrayExpression, indexExpression;

    public ASTGetExpression(Token start, Token end, ASTExpression arrayExpression, ASTExpression indexExpression) {
        super(start, end);
        this.arrayExpression = arrayExpression;
        this.indexExpression = indexExpression;
    }


    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type resultType = null;

        if (arrayExpression != null) {
            arrayExpression.semanticAnalysis(errors, symbolTable);

            if (!(arrayExpression.getType(errors, symbolTable) instanceof ArrayType)) {
                errors.add(new SemanticError("Array must be of type ArrayType", getStart(), getEnd()));
            }
        }
        indexExpression.semanticAnalysis(errors, symbolTable);

        Type type = arrayExpression.getType(errors, symbolTable);
        if (!type.isArray()) {
            errors.add(new SemanticError("Array must be of type Array", arrayExpression.getStart(), arrayExpression.getEnd()));
            return null;
        }

        ArrayType getExpressionType = (ArrayType) type;

        if (getExpressionType.dimensions > 1) {
            ArrayType newType = new ArrayType(getExpressionType);
            newType.dimensions = getExpressionType.dimensions-1;
            return newType;
        }

        return getExpressionType.type;

    }

    @Override
    public boolean isStatement() {
        return false;
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        Integer indexValue = ((IntegerValue)indexExpression.getValue(errors)).getValue();

        if (arrayExpression == null) {
            System.out.println("Array is null");
        } else {
            try {
                Value arrayValue = ((ArrayValue) arrayExpression.getValue(errors)).get(indexValue);
                return arrayValue;
            } catch (CompilerError error) {
                errors.add(error);
            }
        }

        return Value.create(getType(null, null));
    }


}
