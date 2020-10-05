package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.SymbolTable;

import java.util.LinkedList;

public class ASTForeachStatement extends ASTStatement {
    private ASTExpression expression;
    private ASTStatement statement;
    private Token id;

    public ASTForeachStatement(Token start, Token id, ASTExpression expression, ASTStatement statement) {
        super(start, statement.getEnd());
        this.id = id;
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);

        if (!expression.getType(errors, symbolTable).isArray()) {
            errors.add(new SemanticError("for each expression must be of type array", expression.getStart(), expression.getEnd()));
        }

        statement.semanticAnalysis(errors, symbolTable);
    }
}
