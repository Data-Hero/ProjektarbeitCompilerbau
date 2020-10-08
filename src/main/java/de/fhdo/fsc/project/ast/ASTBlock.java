package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.LinkedList;

public class ASTBlock extends ASTStatement {
    private LinkedList<ASTNode> elements = new LinkedList<>(); // Statements and declarations

    public ASTBlock() {
    }

    public ASTBlock(Token start) {
        super(start);
    }

    public ASTBlock(Token start, Token end) {
        super(start, end);
    }

    public void add(ASTNode node) {
        elements.add(node);
    }

    Type cachedType;

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        SymbolTable innerSymbolTable = new SymbolTable(symbolTable);

        for (ASTNode node : elements) {
            if (node instanceof ASTReturnStatement) {
                ASTReturnStatement returnStatement = (ASTReturnStatement) node;

                if (returnStatement.expression != null) {
                    returnStatement.semanticAnalysis(errors, innerSymbolTable);
                    cachedType = returnStatement.expression.getType(errors, innerSymbolTable);
                } else {
                    cachedType = BasicType.voidType;
                }

                return;
            }

            node.semanticAnalysis(errors, innerSymbolTable);
        }

        if (cachedType == null) {
            cachedType = BasicType.voidType;
        }
    }

    private ASTExpression returnExpression;

    @Override
    public void run(LinkedList<CompilerError> errors) {
        for (ASTNode node : elements) {
            if (node instanceof ASTReturnStatement) {
                ASTReturnStatement returnStatement = (ASTReturnStatement) node;
                returnStatement.run(errors);
                returnExpression = returnStatement.expression;
                return;
            } else if (node instanceof ASTFunctionDeclaration) {
                continue;
            }

            node.run(errors);
        }
    }

    public Type getReturnType() {
        return cachedType;
    }

    public ASTExpression getReturn() {
        return returnExpression;
    }
}
