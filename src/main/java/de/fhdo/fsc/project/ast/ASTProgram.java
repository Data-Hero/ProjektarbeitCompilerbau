package de.fhdo.fsc.project.ast;

import de.fhdo.fsc.project.Token;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.type.BasicType;
import de.fhdo.fsc.project.type.SymbolTable;
import de.fhdo.fsc.project.value.ArrayValue;
import de.fhdo.fsc.project.value.StringValue;
import de.fhdo.fsc.project.value.Value;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ASTProgram extends ASTNode {
    private ASTBlock block;
    private List<String> args = new ArrayList<>();

    public ASTProgram(ASTBlock block) {
        this.block = block;
    }

    public ASTProgram(Token start, ASTBlock block) {
        super(start);
        this.block = block;
    }

    public ASTProgram(Token start, Token end, ASTBlock block) {
        super(start, end);
        this.block = block;
    }

    @Override
    public void semanticAnalysis(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        SymbolTable innerSymbolTable = new SymbolTable(symbolTable);

        this.args(errors, innerSymbolTable);

        block.semanticAnalysis(errors, innerSymbolTable);
    }

    @Override
    public void run(LinkedList<CompilerError> errors) {
        block.run(errors);
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    private void args(LinkedList<CompilerError> errors, SymbolTable symbolTable) {
        ASTParameterDeclaration argsParameter = new ASTParameterDeclaration(new Token(1, "string[]"), new Token(1, "args"));
        List<Value> argsValues = new ArrayList<>();

        for (String s : this.args) {
            argsValues.add(new StringValue(s));
        }

        argsParameter.bind(new ArrayValue(argsValues, BasicType.stringType, 1));

        argsParameter.semanticAnalysis(errors, symbolTable);
    }
}
