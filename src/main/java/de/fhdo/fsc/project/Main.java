package de.fhdo.fsc.project;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            NewAwkParser parser = new NewAwkParser(new BufferedInputStream(
                    new FileInputStream("./src/main/test/test1.na")
            ));
            try {
                parser.compilationUnit();
            } catch (de.fhdo.fsc.project.ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}