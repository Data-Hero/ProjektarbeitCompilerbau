package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.BooleanValue;
import de.fhdo.fsc.project.value.IntegerValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;
import java.util.Scanner;

public class ASTReadInteger extends ASTExpression {
    private ASTExpression expression;

    private Integer input;


    public ASTReadInteger(Token start, Token end) {
        super(start, end);
    }



    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {

        return BasicType.intType;
    }

    @Override
    public boolean isStatement() {
        return false;
    }

    @Override
    public void run(LinkedList<CompilerError> errors) {
        try {
            input = readFromCommandline();
        } catch (Exception e) {
            errors.add(new RuntimeError("Input for readInteger must be Integer: 9 (for example)"));
        }

    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        this.run(errors);
        return new IntegerValue(input);
    }


    private Integer readFromCommandline() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        return Integer.valueOf(result);
    }

}
