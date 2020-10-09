package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.ArrayType;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.ArrayValue;
import de.fhdo.fsc.project.value.StringValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ASTSmartSwitch extends ASTExpression {
    private ASTExpression expression;
    private LinkedHashMap<ASTExpression, ASTBlock> cases;
    private LinkedHashMap<ASTExpression, ASTParameterDeclaration> thisParameters = new LinkedHashMap<>();

    public ASTSmartSwitch(Token start, Token end, ASTExpression expression, LinkedHashMap<ASTExpression, ASTBlock> cases) {
        super(start, end);
        this.expression = expression;
        this.cases = cases;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);

        for (Map.Entry<ASTExpression, ASTBlock> e : cases.entrySet()) {
            SymbolTable blockSymbolTable = new SymbolTable(symbolTable);
            ASTParameterDeclaration thisParameter = new ASTParameterDeclaration(new Token(0, "string"), new Token(0, "this"));
            thisParameters.put(e.getKey(), thisParameter);

            thisParameter.semanticAnalysis(errors, blockSymbolTable);
            e.getValue().semanticAnalysis(errors, blockSymbolTable);

            if (e.getKey().getType(errors, symbolTable) != BasicType.stringType) {
                SemanticError error = new SemanticError("Smart switch expression must be type string", getStart(), getEnd());
                errors.add(error);
            }
        }

        if (expression.getType(errors, symbolTable) != BasicType.stringType) {
            SemanticError error = new SemanticError("Smart switch expression must be type string", getStart(), getEnd());
            errors.add(error);
        }

        return new ArrayType(BasicType.stringType, (cases.size() > 1) ? 2 : 1);
    }

    @Override
    public boolean isStatement() {
        return false;
    }

    // "!" as prefix will be interpreted as inversion, escape ! by "\!"
    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        StringValue value = (StringValue) this.expression.getValue(errors);
        ArrayValue result;

        if (cases.size() > 1) {
            result = new ArrayValue(BasicType.stringType, 2);
        } else {
            result = new ArrayValue(BasicType.stringType, 1);
        }

        for (Map.Entry<ASTExpression, ASTBlock> e : cases.entrySet()) {
            ArrayValue temp = new ArrayValue(BasicType.stringType, 1);

            String regex = e.getKey().getValue(errors).toStringValue().value;
            boolean negate = false;

            if (regex.length() > 0) {
                negate = (regex.charAt(0) == '!');
                if (negate) {
                    regex = regex.substring(1);
                }
            }

            regex = regex.replace("\\!", "!");

            if (negate) {
                regex = "(?!" + regex + ")";
            }

            regex = replacePosix(regex);
            regex = replaceCustom(regex);

            try {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value.getValue());

                while (matcher.find()) {
                    thisParameters.get(e.getKey()).bind(new StringValue(matcher.group()));

                    ASTBlock block = e.getValue();
                    block.run(errors);
                    ASTExpression returnExpression = block.getReturn();
                    Value returnValue = returnExpression.getValue(errors);

                    if (returnValue.type != BasicType.stringType) {
                        RuntimeError error = new RuntimeError("Smart switch return type must be type string", block.getStart(), block.getEnd());
                        errors.add(error);
                    } else {
                        StringValue returnStringValue = (StringValue) returnValue;
                        temp.add(returnStringValue);
                    }
                }
            } catch (Exception ex) {
                SemanticError error = new SemanticError(ex.getMessage(), getStart(), getEnd());
                errors.add(error);
            }

            if (cases.size() > 1) {
                result.add(temp);
            } else {
                result = temp;
            }
        }

        return result;
    }

    private String replacePosix(String s) {
        s = s.replace(":punct:", "\\p{Punct}");
        s = s.replace(":graph:", "\\p{Graph}");
        s = s.replace(":lower:", "\\p{Lower}");
        s = s.replace(":alpha:", "\\p{Alpha}");
        s = s.replace(":alnum:", "\\p{Alnum}");
        s = s.replace(":print:", "\\p{Print}");
        s = s.replace(":cntrl:", "\\p{Cntrl}");
        s = s.replace(":space:", "\\p{Space}");
        s = s.replace(":blank:", "\\p{Blank}");
        s = s.replace(":digit:", "\\p{XDigit}");
        s = s.replace(":ascii:", "\\p{ASCII}");
        s = s.replace(":word:", "\\w");

        return s;
    }

    private String replaceCustom(String s) {
        s = s.replace(":Integer:", "(\"+\" | \"-\")? [\"1\"-\"9\"] ([\"0\"-\"9\"])*");

        return s;
    }
}
