package de.fhdo.fsc.project;

import de.fhdo.fsc.project.ast.ASTNode;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SyntaxError;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<CompilerError> errors = new LinkedList<>();
        ASTNode root = null;

        try {
            NewAwkParser parser = new NewAwkParser(new BufferedInputStream(
                    new FileInputStream("./src/main/test/test1.na")
            ));

            try {
                root = parser.compilationUnit();
            } catch (ParseException e) {
                errors.add(new SyntaxError(e.toString(), null, null));
            }

            if (errors.size() == 0) { // None errors in syntax analysis
                root.semanticAnalysis(errors, null);
            }

            if (errors.size() > 0) {
                int i = 1;
                System.out.println("errors found:");
                for (CompilerError error : errors) {
                    System.out.println(i++ + ".  " + error);
                }

            } else System.out.println("source code checked: OK");


        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}