package de.fhdo.fsc.project.DataType;

public class ExpressionType extends Type{

    String regex;

    public ExpressionType(String name, String regex) {
        super(name);
        this.regex = regex;
    }

    public boolean implicitTo(TypeI t){
        if (this == Types.errorType || t == Types.errorType) return true;
        if (this == t) return true;
        if (this instanceof ExpressionType && t instanceof ExpressionType) {
            ExpressionType et = (ExpressionType) t;
            return this.regex.equals(et.regex) ;
        }
        return false;
    }


    @Override
    public boolean explicitTo(TypeI t){
        if (this == Types.errorType || t == Types.errorType) return true;
        if (this == t) return true;
        if (this instanceof ExpressionType && t instanceof ExpressionType) {
            ExpressionType et = (ExpressionType) t;
            return this.regex.equals(et.regex) ;
        }
        return false;
    }

    @Override
    public boolean equals(TypeI t) {
        if (this == Types.errorType || t==Types.errorType) return true;
        if (! (t instanceof ExpressionType)) return false;
        return this.name.equals(t.getName()) && this.regex.equals(((ExpressionType)t).regex);
    }


}
