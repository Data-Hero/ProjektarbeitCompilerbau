package de.fhdo.fsc.project;

import date.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("./src/main/test/test1.csv");BufferedReader br = new BufferedReader(fr)) {
            NewAwkParserTokenManager scanner = new NewAwkParserTokenManager(new SimpleCharStream(br));
            Token t = scanner.getNextToken();
            while (t.kind != NewAwkParserConstants.EOF) {
                System.out.println("Found: " + t.image + "(" + t.kind + ")");
                t = scanner.getNextToken();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
