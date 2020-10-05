package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.SymbolTable;

import java.util.LinkedList;

public class ASTExpressionStatement extends ASTStatement {
    private ASTExpression expression;

    public ASTExpressionStatement(ASTExpression ausdruck, Token end) {
        super(ausdruck.getStart(), end);
        this.expression = ausdruck;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);

        if (!expression.isStatement()) {
            errors.add(new SemanticError("an expression is only a valid statement if it is an inc, dec or assignment", getStart(), getEnd()));
        }
    }
}
