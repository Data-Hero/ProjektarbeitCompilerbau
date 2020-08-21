package de.fhdo.fsc.project.DataType;

public interface TypeI {
    public boolean equals(TypeI j);
    public boolean explicitTo(TypeI o);
    public boolean implicitTo(TypeI k);
    public boolean isNumericType();
    public String getName();
}
