package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.StringValue;
import de.fhdo.fsc.project.value.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class ASTRead extends ASTExpression {
    private ASTExpression expression;
    private String fileContent;

    public ASTRead(Token start, Token end, ASTExpression expression) {
        super(start, end);
        this.expression = expression;
    }

    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        Type expressionType = expression.computeType(errors, symbolTable);
        if (expressionType != BasicType.stringType) {
            CompilerError error = new CompilerError("Read parameter has to be a string, current type is " + expressionType.toString(), getStart(), getEnd());
            errors.add(error);
        }

        return BasicType.stringType;
    }

    @Override
    public boolean isStatement() {
        return false;
    }

    @Override
    public void run(LinkedList<CompilerError> errors) {
        try {
            fileContent = readFile(expression.getValue(errors).toStringValue().getValue());
        } catch (CompilerError error) {
            errors.add(error);
        }
    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        this.run(errors);
        return new StringValue(fileContent);
    }

    private String readFile(String pathStr) throws CompilerError {
        Path path = Paths.get(pathStr);

        StringBuilder builder = new StringBuilder();
        BufferedReader reader;
        String line;

        try {
            reader = Files.newBufferedReader(path);
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeError("Unable to read file " + pathStr + ": " + e.getMessage(), getStart(), getEnd());
        }

        return builder.toString();
    }
}
