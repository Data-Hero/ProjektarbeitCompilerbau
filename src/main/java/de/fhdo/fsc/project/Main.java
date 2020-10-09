package de.fhdo.fsc.project;

import de.fhdo.fsc.project.ast.ASTProgram;
import de.fhdo.fsc.project.errors.CompilerError;
import de.fhdo.fsc.project.errors.SyntaxError;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList<CompilerError> errors = new LinkedList<>();
        ASTProgram root = null;
        int exitCode = 0;

        if (args.length < 1) {
            System.out.println("Missing source file");
            return;
        }

        try {
            NewAwkParser parser = new NewAwkParser(new BufferedInputStream(
                    new FileInputStream(args[0])
            ));

            try {
                root = parser.Program();
                errors = parser.getErrors();
            } catch (ParseException e) {
                errors.add(new SyntaxError(e.toString(), null, null));
            }

            if (errors.size() == 0) { // None errors in syntax analysis

                List<String> arr = new ArrayList<>();
                for (int i = 1; i < args.length; i++) {
                    arr.add(args[i]);
                }

                root.setArgs(arr);

                root.semanticAnalysis(errors, null);
            }

            if (errors.size() == 0) {
                root.run(errors);
            }

            if (errors.size() > 0) {
                int i = 1;
                System.out.println("errors found:");
                for (CompilerError error : errors) {
                    System.out.println(i++ + ".  " + error);
                }

                exitCode = 1;
            }

            System.exit(exitCode);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}