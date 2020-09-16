import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private ChessCollection chessCollection;

    FileManager(ChessCollection chessCollection) {
        this.chessCollection = chessCollection;
    }

    FileManager() {
        this.chessCollection = new ChessCollection();
    }

    public void writeToFile() throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(chessCollection);
        System.out.println(json);

        Path path = Paths.get("savedGameFile");
        File file = new File("savedGameFile");
        if (!Files.exists(path)) {
            file.mkdir();
        }
        FileOutputStream outputStream;
        outputStream = new FileOutputStream("savedGameFile/savedProgress.txt");

        byte[] strToBytes = json.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();


    }

    public ChessCollection loadSavedFile() throws FileNotFoundException {
        Gson gson = new Gson();

        Reader reader = new FileReader("savedGameFile/savedProgress.txt");
        // Convert JSON File to Java Object
        chessCollection = gson.fromJson(reader, ChessCollection.class);


        return chessCollection;
    }

}
