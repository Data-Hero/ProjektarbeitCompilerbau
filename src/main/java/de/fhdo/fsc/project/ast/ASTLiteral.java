package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.NewAwkParserConstants;
import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

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
            //case NewAwkParserConstants.ARRAY:     // ToDo: Fix array type
            //    return Type.arrayType;
        }

        return null; // ToDo: Add error on default: ?
    }

    @Override
    public boolean isStatement() {
        return false;
    }
}
