package de.fhdo.fsc.project.DataType;

public interface TypeI {
    public boolean equals(Type type);
    public boolean explicitTo(TypeI type);
    public boolean implicitTo(TypeI type);
    public boolean isNumericType();
}
