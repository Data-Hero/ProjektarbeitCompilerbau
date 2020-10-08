package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.StringValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ASTSmartSwitch extends ASTExpression {
    private ASTExpression expression;
    private LinkedHashMap<ASTExpression, ASTBlock> cases;

    public ASTSmartSwitch(Token start, Token end, ASTExpression expression, LinkedHashMap<ASTExpression, ASTBlock> cases) {
        super(start, end);
        this.expression = expression;
        this.cases = cases;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);

        for (ASTBlock block : cases.values()) {
            block.semanticAnalysis(errors, symbolTable);
        }

        for (ASTExpression e : cases.keySet()) {
            if (e.getType(errors, symbolTable) != BasicType.stringType) {
                SemanticError error = new SemanticError("Smart switch expression must be type string", getStart(), getEnd());
                errors.add(error);
            }
        }

        if (expression.getType(errors, symbolTable) != BasicType.stringType) {
            SemanticError error = new SemanticError("Smart switch expression must be type string", getStart(), getEnd());
            errors.add(error);
        }

        return BasicType.stringType;
    }

    @Override
    public boolean isStatement() {
        return false;
    }

    // "!" as prefix will be interpreted as inversion, escape ! by "\!"
    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        StringValue value = (StringValue) this.expression.getValue(errors);
        StringValue result = new StringValue("");

        for (Map.Entry<ASTExpression, ASTBlock> e : cases.entrySet()) {
            String regex = e.getKey().getValue(errors).toStringValue().value;
            boolean negate = false;

            if (regex.length() > 0) {
                negate = (regex.charAt(0) == '!');
                if (negate) {
                    regex = regex.substring(1);
                }
            }

            regex = regex.replace("\\!", "!");

            try {
                if (value.getValue().matches(regex) ^ negate) {
                    ASTBlock block = e.getValue();
                    block.run(errors);
                    ASTExpression returnExpression = block.getReturn();
                    Value returnValue = returnExpression.getValue(errors);

                    if (returnValue.type != BasicType.stringType) {
                        RuntimeError error = new RuntimeError("Smart switch return type must be type string", block.getStart(), block.getEnd());
                        errors.add(error);
                    } else {
                        StringValue returnStringValue = (StringValue) returnValue;
                        result.value += returnStringValue.value;
                    }
                }
            } catch (Exception ex) {
                SemanticError error = new SemanticError(ex.getMessage(), getStart(), getEnd());
                errors.add(error);
            }

        }

        return result;
    }
}
