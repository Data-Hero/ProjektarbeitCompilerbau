package de.fhdo.fsc.project;

import de.fhdo.fsc.project.DataType.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class NewAwkVisitor implements NewAwkParserVisitor {

    private LinkedList<Object> stack = new LinkedList<>();
    private HashMap<String, TypeI> symbolTable = new HashMap<>(); // Variablenname -> Type
    private List<TypeI> types = new ArrayList<>();
    public static final String SPECIAL_INVISIBLE_VARIABLE_SIGN = "!";

    public NewAwkVisitor() {
        types.add(Types.intType);
        types.add(Types.doubleType);
        types.add(Types.charType);
        types.add(Types.boolType);
        types.add(Types.stringType);
        types.add(Types.typeType);
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    /*@Override
    public Object visit(ASTDateTime node, Object data) {
        return null;
    }*/

    @Override
    public Object visit(ASTStart node, Object data) {
        node.childrenAccept(this, data);
        //System.out.println(data.toString());
        return symbolTable;
    }

    @Override
    public Object visit(ASTElement node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBlock node, Object data) {
        node.childrenAccept(this, data);
        String name = (String)node.data.get("name");
        String type = (String)node.data.get("type");
        System.out.println("Block: " + name);
        return null;
    }

    @Override
    public Object visit(ASTExpression node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTVariableDefinitionExpression node, Object data) {
        node.childrenAccept(this, data);

        String name = (String)node.data.get("name");
        String type = (String)node.data.get("type");

        TypeI typeI = this.findType(type);

        if (typeI != null) {
            symbolTable.put(name, typeI);
        }

        return null;
    }

    @Override
    public Object visit(ASTAssingmentExpression node, Object data) {
        node.childrenAccept(this, data);

        String value = (String)node.data.get("value");
        System.out.println("AssignmentExpr: " + value);

        if (value.charAt(0) == '>') {
            String typename = value.substring(2, value.length()-3);
            types.add(new Type(typename));
        }
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

    /**
     * Cases (commutative):
     * + Numeric + Numeric (add)
     * + Numeric + Expression (String concatenate)
     * + Numeric + Array (Array concatenate)
     * + Array + Array (Array concatenate)
     * - Expression + Expression (String concat)
     * - Expression + Array (concat if baseTyp = Expression, otherwise TypeError)
     **/
    @Override
    public Object visit(ASTAdditiveExpression node, Object data) {
        node.childrenAccept(this, data);
        String sign = (String)node.data.get("sign");
        System.out.println(sign);
        if (sign == null || !sign.equals("+"))
            return null;
        Object a = pop();  // see TODO in jjt
        Object b = pop();
        TypeI aType = symbolTable.get(a);
        TypeI bType = symbolTable.get(b);
        a = toValue(a.toString());
        b = toValue(b.toString());
        if(aType.isNumericType() && bType.isNumericType()) {                            // Integer, Double, Character, Boolean, String, Array (Konkatenation und Entfernen von gleichen Elementen)
            Double e = Double.parseDouble((String)a) + Double.parseDouble((String)b);   // TODO Save only int or char if its only int or char
            symbolTable.put(SPECIAL_INVISIBLE_VARIABLE_SIGN + e,
                    getTypeWithLargerRank((NumericType) aType,(NumericType) bType));
            stack.addFirst(SPECIAL_INVISIBLE_VARIABLE_SIGN + e);
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
                stack.addFirst(newList);                                                // Array name?
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
        }
        return null;
    }

    @Override
    public Object visit(ASTMultiplicativeExpression node, Object data) {
        node.childrenAccept(this, data);
        String sign = (String)node.data.get("sign");
        System.out.println(sign);
        if (sign == null)
            return null;
        Object a = pop();  // just int and double
        Object b = pop();
        try {
            Double doubleA = Double.valueOf(toValue(a.toString()));
            Double doubleB = Double.valueOf(toValue(b.toString()));
            Double result;

            if (sign.equals("*"))
                result = doubleA * doubleB;
            else if (sign.equals("/"))
                result = doubleA / doubleB;
            else
                throw new ArithmeticException();

            if (result.doubleValue()==result.intValue()) {
                Integer actualResult = result.intValue();
                stack.addFirst(SPECIAL_INVISIBLE_VARIABLE_SIGN + actualResult);
                symbolTable.put(SPECIAL_INVISIBLE_VARIABLE_SIGN + actualResult, Types.intType);
            } else {
                stack.addFirst(SPECIAL_INVISIBLE_VARIABLE_SIGN + result);
                symbolTable.put(SPECIAL_INVISIBLE_VARIABLE_SIGN + result, Types.doubleType);
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }catch (ArithmeticException ae) {
            ae.printStackTrace();
        }

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

        TypeI type = ((TypeI)node.data.get("type"));
        String tname;
        if(type != null)
            tname = type.getName();
        else
            tname = "void";

        System.out.println("ASTValueExpression Type: "+ tname);

        stack.addFirst(SPECIAL_INVISIBLE_VARIABLE_SIGN + node.data.get("value"));
        symbolTable.put(SPECIAL_INVISIBLE_VARIABLE_SIGN + node.data.get("value"),type);

        return null;
    }

    @Override
    public Object visit(ASTFunctionCallExpression node, Object data) {
        node.childrenAccept(this, data);
        String identifier = (String) node.data.get("identifier");
        FunctionType functionType = (FunctionType) symbolTable.get(identifier);

        // if counter (NumberOfParameters) doesnt exist return
        if (node.data.get("counter") == null)
            return null;

        int numberOfParameters = (int)node.data.get("counter");
        Object[] parameters = new Object[numberOfParameters];
        TypeI paramType;
        for (int i = 0; i < numberOfParameters; i++) {
            parameters[i] = stack.pop();                                // TODO they should be precomputed, how to read type?

            // Type Check
            paramType = functionType.getParameterList()[i];
            /**if (!paramType.equals()) {

            }**/
        }

        return null;
    }

    @Override
    public Object visit(ASTStringExpression node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        node.childrenAccept(this, data);

        System.out.println("Identifier: "+node.data.get("name"));
        return null;
    }

    @Override
    public Object visit(ASTFunctionReturnExpression node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFunctionBodyDefinition node, Object data) {
        node.childrenAccept(this, data);

        return null;
    }

    /**
     * Identifier into the stack, Type consists of Name (Identifier), returnType and Parameter (Array of Types)
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTFunctionHeaderDefinition node, Object data) {
        node.childrenAccept(this, data);
        TypeI returnType = new Type((String) node.data.get("returnType"));

        // if counter (NumberOfParameters) doesnt exist return
        if (node.data.get("counter") == null)
            return null;
        int numberOfParameters = (int)node.data.get("counter");
        TypeI[] parameterType = new TypeI[numberOfParameters];
        StringBuilder sb = new StringBuilder();
        System.out.println(node.data.toString());
        for (int i = 0; i < numberOfParameters; i++) {
            String s = (String)node.data.get(sb.append("param")
                    .append(i)
                    .append("Type").toString());
            if ( s == null) {
                continue;
            }
            parameterType[i] = new Type(s);
            sb.setLength(0);
        }
        String functionIdentifier = (String)node.data.get("identifier");
        TypeI functionType = new FunctionType(functionIdentifier, returnType, parameterType);
        System.out.println(functionType.toString());
        stack.add(functionIdentifier);
        symbolTable.put(functionIdentifier, functionType);
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
        node.childrenAccept(this, data);

        Object value = node.data.get("value");
        Boolean b = value instanceof Type;
        stack.add(b);
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

    private TypeI findType(String name) {
        return this.types.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    private String toValue(String input) {
        String result = input;
        if (result.charAt(0)=='!')
            result = result.substring(1);
        if (result.charAt(0)=='"' || result.charAt(0)=='>')
            result = result.substring(1,result.length()-1);
        return result;
    }

    private TypeI getTypeWithLargerRank(NumericType a, NumericType b) {
        return a.getRank() >= b.getRank()?a:b;
    }

}
