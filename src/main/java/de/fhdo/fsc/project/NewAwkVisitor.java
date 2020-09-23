package de.fhdo.fsc.project;

import de.fhdo.fsc.project.DataType.ArrayType;
import de.fhdo.fsc.project.DataType.TypeError;
import de.fhdo.fsc.project.DataType.TypeI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class NewAwkVisitor implements NewAwkParserVisitor {

    private LinkedList<Object> stack = new LinkedList<>();
    private HashMap<String, TypeI> symbolTable = new HashMap<>(); // Variablenname -> Type
    
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
        //System.out.println(data.toString());
        return null;
    }

    @Override
    public Object visit(ASTElement node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTBlock node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTVariableDefinitionExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTAssingmentExpression node, Object data) {
        node.childrenAccept(this, data);
        //System.out.println("AssignmentExpr: "+node.data.get("name"));
        //Object a = pop();
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
        /*Object a = pop();
        Object b = pop();
        TypeI aType = symbolTable.get(a);
        TypeI bType = symbolTable.get(b);
        /**
         * Cases (commutative):
         * + Numeric + Numeric (add)
         * + Numeric + Expression (String concatenate)
         * + Numeric + Array (Array concatenate)
         * + Array + Array (Array concatenate)
         * - Expression + Expression (String concat)
         * - Expression + Array (concat if baseTyp = Expression, otherwise TypeError)

        if(aType.isNumericType() && symbolTable.get(b).isNumericType()) {// Integer, Double, Character, Boolean, String, Array (Konkatenation und Entfernen von gleichen Elementen)
            Double e = ((Double) a) + ((Double) b); //TODO Save only int or char if its only int or char
            stack.addFirst(e);                      //TODO do i have to save e into my symbolTable?
        } else if (aType.isNumericType() && !bType.isArrayType()
                || !aType.isArrayType() && bType.isNumericType()) {     // Numeric Type and non-array, non-numeric type
            String e = String.valueOf(a) + String.valueOf(b);
            stack.addFirst(e);
        } else if (aType.isNumericType() && bType.isArrayType()) {      // Numeric Type and array type (prepend a to b)
            arrayAddition(a,b);
        } else if (aType.isArrayType() && bType.isNumericType()) {
            arrayAddition(b,a);
        } else if (aType.isArrayType() && bType.isArrayType()) {
            TypeI aBaseType = ((ArrayType)aType).getBaseTyp();
            TypeI bBaseType = ((ArrayType)bType).getBaseTyp();
            if(aBaseType.equals(bBaseType)) {
                List<Object> newList = new ArrayList<>();
                newList.addAll((List<Object>)a);
                newList.addAll((List<Object>)b);
            } else {
                throw new TypeError();
            }
        } else if (isExpression(aType) && isExpression(bType)) {
            String e = String.valueOf(a) + String.valueOf(b);
            stack.addFirst(e);
        } else if (bType.isArrayType()){ // Expression + Array (concat if baseTyp = Expression, otherwise TypeError)
            arrayAddition(a,b);
        } else {
            arrayAddition(b,a);
        }*/
        return null;
    }

    @Override
    public Object visit(ASTMultiplicativeExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTPowerExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTModuloExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTPrefixExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTUnaryExpression node, Object data) {
        //System.out.println("Unary: "+node.data.get("value"));
        return null;
    }

    @Override
    public Object visit(ASTValueExpression node, Object data) {
        System.out.println("ASTValueExpression: "+node.data.get("value"));
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
        System.out.println("Identifier: "+node.data.get("name"));
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
    public Object visit(ASTForeachLoopDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTWhileLoopDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIfDefinition node, Object data) {
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
    public Object visit(ASTLengthDatatype node, Object data) { return null; }

    @Override
    public Object visit(ASTBasicDatatype node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTDatatype node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTReturnDatatype node, Object data) {
        return null;
    }

    private Object pop() {
        return stack.removeFirst();
    }

    private void arrayAddition(Object a, Object b) {
        TypeI aType = symbolTable.get(a);
        TypeI bType = symbolTable.get(b);
        ArrayType oldArrayType = (ArrayType)bType;
        if (aType.equals(oldArrayType.getBaseTyp())) {
            ArrayType newArray = new ArrayType(oldArrayType.getBaseTyp());
            List<Object> array = new ArrayList<>(); // TODO use String instead of Object?
            array.add(a);
            array.addAll((List<Object>) b);
            stack.addFirst(array);
        } else
            throw new TypeError();
    }

    private boolean isExpression(TypeI t) {
        return !t.isArrayType() && !t.isNumericType();
    }

}
