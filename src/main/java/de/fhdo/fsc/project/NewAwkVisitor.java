package de.fhdo.fsc.project;

import java.util.HashMap;
import java.util.LinkedList;

public class NewAwkVisitor implements NewAwkParserVisitor {

    private LinkedList stack = new LinkedList();
    private HashMap symbolTable = new HashMap();


    @Override
    public Object visit(SimpleNode node, Object data) {
        node.childrenAccept(this, data);
        return symbolTable;
    }

    @Override
    public Object visit(ASTDateTime node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTStart node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTAssingmentExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTLogicalExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTCompareExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTAdditiveExpression node, Object data) {
        node.childrenAccept(this, data);
        Object a = pop();
        Object b = pop();
        //stack.addFirst();
        return null;
    }

    @Override
    public Object visit(ASTMultiplicativeExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTPrefixExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTUnaryExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionCallExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTStringExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionReturnExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionBodyDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionHeaderDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSmartSwitchSelektor node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSmartSwitchConditionDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSmartSwitchCaseDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSmartSwitchDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIsDatatype node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTToDatatype node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTBasicDatatype node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTDatatype node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTReturn_Datatype node, Object data) {
        return null;
    }

    private Object pop() {
        return stack.removeFirst();
    }

}
