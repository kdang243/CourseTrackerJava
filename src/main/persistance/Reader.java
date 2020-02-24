package persistance;

import com.google.gson.Gson;
import model.AcademicHistory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Reader {

    // EFFECTS: constructs a reader that will read file and return data to work with
    public static AcademicHistory readAHistory(File file) throws IOException {
        String json = readFile(file);
        Gson gson = new Gson();
        AcademicHistory ah = gson.fromJson(json, AcademicHistory.class);
        return ah;
    }

    private static String readFile(File file) throws IOException {
        return String.valueOf((Files.readAllLines(file.toPath())).get(0));
    }
}
