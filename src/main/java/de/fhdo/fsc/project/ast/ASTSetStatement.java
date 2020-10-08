package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.value.ArrayValue;
import de.fhdo.fsc.project.value.IntegerValue;
import de.fhdo.fsc.project.value.StringValue;
import de.fhdo.fsc.project.value.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ASTSetStatement extends ASTStatement {
    private ASTExpression arrayExpression, indexExpression, elementExpression;

    public ASTSetStatement(Token start, Token end, ASTExpression arrayExpression, ASTExpression indexExpression, ASTExpression elementExpression) {
        super(start, end);
        this.arrayExpression = arrayExpression;
        this.indexExpression = indexExpression;
        this.elementExpression = elementExpression;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        if (arrayExpression != null) {
            arrayExpression.semanticAnalysis(errors, symbolTable);

            if (!(arrayExpression.getType(errors, symbolTable) instanceof ArrayType)) {
                errors.add(new SemanticError("Must be Array", getStart(), getEnd()));
            }
        }

        indexExpression.semanticAnalysis(errors, symbolTable);
    }

    @Override
    public void run(LinkedList<CompilerError> errors) {
        Integer indexValue = ((IntegerValue)indexExpression.getValue(errors)).getValue();
        Value elementExpression = this.elementExpression.getValue(errors);

        if (arrayExpression == null) {
            System.out.println("Array fehlt");
        } else {
            try {
                ArrayValue arrayValue = ((ArrayValue) arrayExpression.getValue(errors));
                arrayValue.set(indexValue, elementExpression);
            } catch (RuntimeError runtimeError) {
                errors.add(new CompilerError(runtimeError.getMessage()));
            } catch (Exception e) {
                errors.add(new CompilerError("Array Expression does not hold ArrayValue"));
            }
        }
    }

}
