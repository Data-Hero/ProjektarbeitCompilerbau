package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.RuntimeError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.type.Type;
import de.fhdo.fsc.project.value.BooleanValue;
import de.fhdo.fsc.project.value.DoubleValue;
import de.fhdo.fsc.project.value.Value;

import java.util.LinkedList;
import java.util.Scanner;

public class ASTReadDouble extends ASTExpression {
    private ASTExpression expression;

    private Double input;


    public ASTReadDouble(Token start, Token end) {
        super(start, end);
    }



    @Override
    protected Type computeType(LinkedList<CompilerError> errors, SymbolTable symbolTable) {

        return BasicType.doubleType;
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
            //errors.add(new RuntimeError("Input for readDouble must be Double: 4.5 (for example)"));
            System.out.println();
        }

    }

    @Override
    public Value getValue(LinkedList<CompilerError> errors) {
        this.run(errors);
        return new DoubleValue(input);
    }


    private Double readFromCommandline() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        return Double.valueOf(result);
    }

}
