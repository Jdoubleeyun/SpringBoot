package Exception;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main2 {
    public static void main(String[] args) {
        try(FileOutputStream out = new FileOutputStream("Test.txt")){
            out.write("Hello Sparta".getBytes());
            out.flush();
        } catch (IOException e) {
            System.out.println("IOException 발생" + e.getMessage());
            e.printStackTrace();
        }
    }
}
