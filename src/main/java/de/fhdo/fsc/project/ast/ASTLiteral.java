package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.NewAwkParserConstants;
import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.*;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.LinkedList;

public class ASTLiteral extends ASTExpression {
    private Token literal;

    public ASTLiteral(Token literal) {
        super(literal, literal);
        this.literal = literal;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        switch (literal.kind) {
            case NewAwkParserConstants.FLOATING_POINT_LITERAL:
                return BasicType.doubleType;
            case NewAwkParserConstants.INTEGER_LITERAL:
                return BasicType.intType;
            case NewAwkParserConstants.BOOLEAN_LITERAL:
                return BasicType.boolType;
            case NewAwkParserConstants.CHARACTER_LITERAL:
                return BasicType.charType;
            case NewAwkParserConstants.STRING_LITERAL:
                return BasicType.stringType;
        }

        return null;
    }

    @Override
    public boolean isStatement() {
        return false;
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        Type type = getType(null, null);

        Value value = null;

        switch (literal.kind) {
            case NewAwkParserConstants.FLOATING_POINT_LITERAL:
                value = new DoubleValue(Double.parseDouble(literal.image));
                break;
            case NewAwkParserConstants.INTEGER_LITERAL:
                value = new IntegerValue(Integer.parseInt(literal.image));
                break;
            case NewAwkParserConstants.BOOLEAN_LITERAL:
                value = new BooleanValue(Boolean.parseBoolean(literal.image));
                break;
            case NewAwkParserConstants.CHARACTER_LITERAL:
                value = new CharacterValue(literal.image.charAt(1));
                break;
            case NewAwkParserConstants.STRING_LITERAL:
                value = new StringValue(StringEscapeUtils.unescapeJava(literal.image.substring(1, literal.image.length() - 1)));
                break;
        }

        if (value != null) {
            if (type != value.type) {
                System.out.println("Types does not match");
            }
        } else {
            System.out.println("Value is null");
        }

        return upgradeValue(value);
    }
}
