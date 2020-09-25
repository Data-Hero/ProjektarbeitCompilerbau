package de.fhdo.fsc.project.DataType;

public class FunctionType extends Type{

    private TypeI returnType;
    private TypeI[] parameterList;

    public FunctionType(String name, TypeI returnType, TypeI... parameterList ) {
        super(name);
        this.returnType = returnType;
        this.parameterList = parameterList;
    }

    public TypeI getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeI returnType) {
        this.returnType = returnType;
    }

    public TypeI[] getParameterList() {
        return parameterList;
    }

    public void setParameterList(TypeI[] parameterList) {
        this.parameterList = parameterList;
    }
}
