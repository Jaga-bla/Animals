package pibd.jagoda.animals.model.manager;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterManager {
    public static void write(String string) {
        String file = "output.txt";
        FileWriter fileWriter;
        {
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(string);
                fileWriter.write("\n");
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
