package persistance;

import model.AcademicHistory;

import java.io.*;

public class Writer {
    private FileWriter fileWriter;

    // EFFECTS: constructs a writer that will write data to file
    public Writer(File file) throws IOException {
        fileWriter = new FileWriter(file);
    }

    //MODIFIES: this
    //EFFECTS: writes json into file
    public void write(String json) throws IOException {
        fileWriter.write(json);
        fileWriter.flush();
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing data!
    public void close() throws IOException {
        fileWriter.close();
    }
}
