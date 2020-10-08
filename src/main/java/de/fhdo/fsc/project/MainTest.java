package de.fhdo.fsc.project;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
        String[] arguments = new String[1];
        StringBuilder sb = new StringBuilder();
        try {
            arguments[0] = "./src/main/test/testMultiDimArray.na";
            Main.main(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("testMultiDimArray passed? ").
                append(testOutput("./src/main/test/out.txt", "[\"a\", \"c\"]")).append("\n");

        try {
            arguments[0] = "./src/main/test/testForeach.na";
            Main.main(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("testForeach passed? ").
                append( testOutput("./src/main/test/out2.txt", "42")).append("\n");

        try {
            arguments[0] = "./src/main/test/testArrayIndex.na";
            Main.main(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("testArrayIndex passed? ").
                append( testOutput("./src/main/test/out3.txt", "45")).append("\n");

        System.out.println(sb.toString());
    }

    private static boolean testOutput(String pathToFile, String expectedValue) {
        File file = new File(pathToFile);
        String sout = "";
        try(FileInputStream fis = new FileInputStream(file); Scanner sc = new Scanner(fis)) {
            while(sc.hasNextLine())
                sout += sc.nextLine();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return sout.equals(expectedValue);
    }
}
