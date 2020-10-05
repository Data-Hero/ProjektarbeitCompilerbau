package de.fhdo.fsc.project.errors;

import de.fhdo.fsc.project.Token;

public class CompilerError extends Throwable {
    private String msg;
    private Token start, end;

    public CompilerError(String msg) {
        this.msg = msg;
    }

    public CompilerError(String msg, Token start, Token end) {
        this.msg = msg;
        setToken(start, end);
    }

    public void setToken(Token start, Token end) {
        this.start = start;
        this.end = end;
    }

    public String toString() {
        String loc = "";
        if (start != null) {
            loc = "(" + start.beginLine + ", " + start.beginColumn + ")";
        }
        return msg + " at " + loc;
    }
}
