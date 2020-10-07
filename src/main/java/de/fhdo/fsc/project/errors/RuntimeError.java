package de.fhdo.fsc.project.errors;

import de.fhdo.fsc.project.Token;

public class RuntimeError extends CompilerError {
    public RuntimeError(String msg) {
        super(msg);
    }

    public RuntimeError(String msg, Token start, Token end) {
        super(msg, start, end);
    }
}
