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

import java.util.LinkedList;

public class ASTForeachStatement extends ASTStatement {
    private ASTExpression expression;
    private ASTStatement statement;
    private Token id;
    private Token itemType;
    private ASTParameterDeclaration item;

    public ASTForeachStatement(Token start, Token id, ASTExpression expression, ASTStatement statement, Token itemType) {
        super(start, statement.getEnd());
        this.id = id;
        this.expression = expression;
        this.statement = statement;
        this.itemType = itemType;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        expression.semanticAnalysis(errors, symbolTable);

        Type eType = expression.getType(errors, symbolTable);
        ArrayType expressionType;

        if (!eType.isArray()) {
            errors.add(new SemanticError("for each expression must be of type array", expression.getStart(), expression.getEnd()));
            return;
        }

        expressionType = (ArrayType) eType;

        try {
            item = new ASTParameterDeclaration(itemType, id);
            symbolTable.add(id.image, item);
            Type iType = Type.resolve(this.itemType.image);
            int expressionDimensions = expressionType.dimensions;

            if (iType instanceof ArrayType) {
                ArrayType itemType = (ArrayType) iType;
                int itemDimensions = itemType.dimensions;
                if (itemDimensions + 1 != expressionDimensions) {
                    throw new SemanticError("Dimensions does not match for for loop. Item " + itemDimensions + ", Array " + expressionDimensions);
                }

                if (itemType.getBasicType() != expressionType.getBasicType()) {
                    throw new SemanticError("Types does not match for for loop");
                }
            } else if (iType instanceof BasicType) {
                BasicType itemType = (BasicType) iType;
                if (expressionDimensions != 1) {
                    throw new SemanticError("Dimensions does not match for for loop. Item 0 , Array " + expressionDimensions);
                }

                if (itemType != expressionType.getBasicType()) {
                    throw new SemanticError("Types does not match for for loop");
                }
            }
        } catch (SemanticError error) {
            error.setToken(getStart(), getEnd());
            errors.add(error);
        }

        statement.semanticAnalysis(errors, symbolTable);
    }

    @Override
    public void run(LinkedList<CompilerError> errors) {
        item.run(errors);

        ArrayValue value = (ArrayValue) expression.getValue(errors);

        for (int i = 0; i < value.length(); i++) {
            try {
                item.bind(value.get(i));
            } catch (RuntimeError error) {
                error.setToken(getStart(), getEnd());
                errors.add(error);
            }

            this.statement.run(errors);
        }
    }
}
