package de.fhdo.fsc.project.errors;

import de.fhdo.fsc.project.Token;

public class SyntaxError extends CompilerError {
    public SyntaxError(String msg, Token start, Token end) {
        super(msg, start, end);
    }

    public SyntaxError(String msg) {
        super(msg);
    }
}
