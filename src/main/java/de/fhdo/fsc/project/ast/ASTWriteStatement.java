package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.errors.SemanticError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.value.StringValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ASTWriteStatement extends ASTStatement {
    private ASTExpression pathExpression, sourceExpression;

    public ASTWriteStatement(Token start, Token end, ASTExpression pathExpression, ASTExpression sourceExpression) {
        super(start, end);
        this.pathExpression = pathExpression;
        this.sourceExpression = sourceExpression;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        if (pathExpression != null) {
            pathExpression.semanticAnalysis(errors, symbolTable);

            if (pathExpression.getType(errors, symbolTable) != BasicType.stringType) {
                errors.add(new SemanticError("Path must be a string", getStart(), getEnd()));
            }
        }

        sourceExpression.semanticAnalysis(errors, symbolTable);
    }

    @Override
    public void run(LinkedList<CompilerError> errors) {
        String valueString = sourceExpression.getValue(errors).toStringValue().getValue();

        if (pathExpression == null) {
            System.out.println(valueString);
        } else {
            try {
                String pathString = ((StringValue) pathExpression.getValue(errors)).getValue();
                writeFile(pathString, valueString);
            } catch (CompilerError error) {
                errors.add(error);
            }
        }
    }

    private void writeFile(String pathStr, String fileContent) throws CompilerError {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(pathStr));
            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeError("Unable to write file " + pathStr + " (Content: " + fileContent + "): " + e.getMessage(), getStart(), getEnd());
        }
    }
}
