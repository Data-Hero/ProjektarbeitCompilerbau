package de.fhdo.fsc.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("./src/main/test/test1.na");BufferedReader br = new BufferedReader(fr)) {
            NewAwkParser parser = new NewAwkParser(new FileReader("./src/main/test/test1.na"));
            SimpleNode node = parser.Start();
            NewAwkParserVisitor visitor = new NewAwkVisitor();

            NewAwkParserTokenManager scanner = new NewAwkParserTokenManager(new SimpleCharStream(br));
            Token t = scanner.getNextToken();
            while (t.kind != NewAwkParserConstants.EOF) {
                System.out.println("Found: " + t.image + " (" + t.kind + ")");
                t = scanner.getNextToken();
            }
            traverseNodes(node, visitor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void traverseNodes(SimpleNode node, NewAwkParserVisitor visitor) {
        for(int i=0;i<node.jjtGetNumChildren();i++) {
            //System.out.println(node.value);
            //System.out.println(node.getClass().getSimpleName());
            SimpleNode simpleNode = (SimpleNode)node.jjtGetChild(i);
            simpleNode.jjtAccept(visitor, null);
            traverseNodes(simpleNode, visitor);
        }
    }
}