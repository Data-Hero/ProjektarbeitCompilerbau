package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTIfStatement extends ASTStatement {
    private ASTExpression expression;
    private ASTStatement statement;
    private ASTIfStatement elseIfStatement;
    private ASTStatement elseStatement;

    public ASTIfStatement(Token start, ASTExpression expression, ASTStatement statement, ASTIfStatement elseIfStatement, ASTStatement elseStatement) {
        super(start, statement.getEnd());
        this.expression = expression;
        this.statement = statement;
        this.elseIfStatement = elseIfStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);

        Type type = expression.getType(errors, symbolTable);

        if (!(type instanceof BasicType && ((BasicType) type).isBoolean())) {
            errors.add(new SemanticError("if expression must be of type boolean", expression.getStart(), expression.getEnd()));
        }

        statement.semanticAnalysis(errors, symbolTable);

        if (elseIfStatement != null) {
            elseIfStatement.semanticAnalysis(errors, symbolTable);
        }

        if (elseStatement != null) {
            elseStatement.semanticAnalysis(errors, symbolTable);
        }
    }
}
