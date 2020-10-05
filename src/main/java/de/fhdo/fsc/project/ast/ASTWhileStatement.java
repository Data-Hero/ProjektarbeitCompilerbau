package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTWhileStatement extends ASTStatement {
    private ASTExpression expression;
    private ASTStatement statement;

    public ASTWhileStatement(Token start, ASTExpression expression, ASTStatement statement) {
        super(start, statement.getEnd());
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);

        Type type = expression.getType(errors, symbolTable);

        if (!(type instanceof BasicType && ((BasicType) type).isBoolean())) {
            errors.add(new SemanticError("while expression must be of type boolean", expression.getStart(), expression.getEnd()));
        }

        statement.semanticAnalysis(errors, symbolTable);
    }
}
