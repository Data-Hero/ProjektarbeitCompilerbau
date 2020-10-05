package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ASTFunctionCall extends ASTExpression {
    private List<ASTExpression> parameters;
    private Token identifier;

    public ASTFunctionCall(Token identifier, List<ASTExpression> parameters, Token end) {
        super(identifier, end);
        this.parameters = parameters;
        this.identifier = identifier;
    }

    public ASTFunctionCall(Token start, Token end, Token identifier) {
        super(start, end);
        this.parameters = new ArrayList<>();
        this.identifier = identifier;
    }

    ASTFunctionDeclaration declaration;

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type type;
        ASTDeclaration decl = symbolTable.find(identifier.image);

        if (decl == null) {
            errors.add(new SemanticError("unknown identifier " + identifier, identifier, end));
            type = BasicType.errorType;
        } else if (!(decl instanceof ASTFunctionDeclaration)) {
            errors.add(new SemanticError("wrong type " + identifier, identifier, end));
            type = BasicType.errorType;
        } else {
            declaration = (ASTFunctionDeclaration) decl;
            type = declaration.getType();

            // Check parameter count
            if (this.parameters.size() != declaration.getParameterList().size()) {
                errors.add(new SemanticError("Parameter count mismatch " + identifier, identifier, end));
            }

            // Check parameter types
            for (int i = 0; i < this.parameters.size(); i++) {
                ASTExpression expression = this.parameters.get(i);
                List<ASTParameterDeclaration> parameters = declaration.getParameterList();

                expression.semanticAnalysis(errors, symbolTable);

                Token parameterTypeToken = parameters.get(i).type;
                Type parameterType = Type.resolve(parameterTypeToken.image);
                Type expressionType = expression.getType(errors, symbolTable);

                if (!Type.implicit(expressionType, parameterType)) {
                    errors.add(new SemanticError("Parameter type mismatch " + identifier, expression.start, expression.end));
                }
            }
        }

        return type;
    }

    @Override
    public boolean isStatement() {
        return true; // ToDo: Check if correct
    }


}
