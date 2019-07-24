package corruption;

import java.util.Scanner;
import java.io.*;
import java.nio.file.*;


public class Main {
    public static void main(String[] args) {

        // DOC: practice from https://hyperskill.org/projects/58?goal=7 */

        Scanner scan = new Scanner(System.in);
        String input = "nobody likes pineapple pizza";
        String mode = "uwu";

        String sendTXT = "send.txt";
        String encodeTXT = "encoded.txt";
        String receivedTXT = "received.txt";
        String decodeTXT = "decoded.txt";

        writeFile(sendTXT, input);
        viewFile(sendTXT);

        String encoded = Encode.hammingEncoder(readFile(sendTXT));
        String corrupted = Corrupt.hamCorruption(encoded);
        String decoded = Decode.hammingDecoder(corrupted);

        do {
            System.out.println("\n- - - - - - - - - -\nwrite file | encode | send | decode | all\nWrite a mode: ");
            mode = scan.nextLine();

            switch (mode) {
                case "write file":
                    System.out.println("your text: ");
                    writeFile(sendTXT, scan.nextLine());
                    viewFile(sendTXT);
                    break;
                case "encode":
                    writeFile(encodeTXT, encoded);
                    viewFile(encodeTXT);
                    break;
                case "send":
                    writeFile(receivedTXT, corrupted);
                    viewFile(receivedTXT);
                    break;
                case "decode":
                    writeFile(decodeTXT, decoded);
                    viewFile(decodeTXT);
                    break;
                case "all":
                    viewFile(sendTXT);
                    writeFile(encodeTXT, encoded);
                    viewFile(encodeTXT);
                    writeFile(receivedTXT, corrupted);
                    viewFile(receivedTXT);
                    writeFile(decodeTXT, decoded);
                    viewFile(decodeTXT);
                    break;
                default:
                    break;
            }
        } while (mode != "x");

    }

    static String readFile(String filePath) {
        try {
            return readFileAsString(filePath);
        } catch (IOException e) {
            return "ERROR: file could not be found";
        }
    }

    static void viewFile(String filePath) {
        try {
            System.out.println("\n" + filePath + ":\ntext view: " + readFileAsString(filePath));
            System.out.println("hex view: " + Helper.toHex(readFileAsString(filePath)));
        } catch (IOException e) {
            System.out.println("ERROR: cannot read file");
        }
    }


    static void writeFile(String filePath, String output) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(output);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
