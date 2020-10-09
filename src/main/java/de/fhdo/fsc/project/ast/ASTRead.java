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
import java.util.Scanner;

public class ASTRead extends ASTExpression {
    private ASTExpression expression;

    private String fileContent;
    private Boolean line;


    public ASTRead(Token start, Token end, ASTExpression expression) {
        super(start, end);
        this.expression = expression;
    }

    public ASTRead(Token start, Token end) {
        super(start, end);
        this.line = line;
    }



    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        if (expression == null) // read from Commandline
            return BasicType.stringType;
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
        if (expression == null) {
            // read from Commandline
            fileContent = readFromCommandline();
        }

        else {
            try {
                fileContent = readFile(expression.getValue(errors).toStringValue().getValue());
            } catch (CompilerError error) {
                errors.add(error);
            }
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

    private String readFromCommandline() {
        Scanner scanner = new Scanner(System.in);

        StringBuilder builder = new StringBuilder();

        builder.append(scanner.nextLine());

        return builder.toString();
    }

}
