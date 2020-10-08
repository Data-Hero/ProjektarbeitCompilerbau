package de.fhdo.fsc.project;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
        String[] arguments = new String[1];

        try {
            arguments[0] = "./src/main/test/testMultiDimArray.na";
            Main.main(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("testMultiDimArray passed? " +
                testOutput("./src/main/test/out.txt", "[\"a\", \"c\"]"));

        try {
            arguments[0] = "./src/main/test/testArrayIndex.na";
            Main.main(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("testArrayIndex passed? " +
                testOutput("./src/main/test/out2.txt", "42"));

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
