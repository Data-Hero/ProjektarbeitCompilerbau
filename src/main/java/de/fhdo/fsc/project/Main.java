package de.fhdo.fsc.project;

import date.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("./src/main/test/test1.na");BufferedReader br = new BufferedReader(fr)) {
            NewAwkParser parser = new NewAwkParser(new FileReader("./src/main/test/test1.na"));
            SimpleNode node = parser.Start();
            node.dump("");

            NewAwkParserTokenManager scanner = new NewAwkParserTokenManager(new SimpleCharStream(br));
            Token t = scanner.getNextToken();
            while (t.kind != NewAwkParserConstants.EOF) {
                System.out.println("Found: " + t.image + " (" + t.kind + ")");
                t = scanner.getNextToken();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}