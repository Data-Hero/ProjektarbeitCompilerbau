package de.fhdo.fsc.project.DataType;

public interface TypeI {
    public boolean equals(TypeI type);
    public boolean explicitTo(TypeI type);
    public boolean implicitTo(TypeI type);
    public boolean isNumericType();
    public String getName();
}
