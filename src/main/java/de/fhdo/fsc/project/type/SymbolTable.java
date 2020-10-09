package de.fhdo.fsc.project.type;

import de.fhdo.fsc.project.ast.ASTDeclaration;
import de.fhdo.fsc.project.errors.SemanticError;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, ASTDeclaration> entries = new HashMap<>();
    private SymbolTable parent;

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
    }

    public ASTDeclaration find(String name) {
        if (entries.containsKey(name)) {
            return entries.get(name);
        } else if (parent == null) {
            return null;
        } else {
            return parent.find(name);
        }
    }

    public void add(String name, ASTDeclaration node) throws SemanticError {
        if (find(name) != null) {
            throw new SemanticError("Identifier " + name + " already defined");
        } else {
            entries.put(name, node);
        }
    }
}
