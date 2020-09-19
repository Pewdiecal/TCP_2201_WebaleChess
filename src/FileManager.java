import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This is the FileManager class that handles the saving and loading of a game state.
 *
 * @author Lau Yee Keen Calvin
 */
public class FileManager {
    private ChessCollection chessCollection;

    /**
     * This is an overloaded constructor that passes the initialized chessCollection to its attributes.
     * @param chessCollection The chess collection.
     * @author Lau Yee Keen Calvin
     */
    FileManager(ChessCollection chessCollection) {
        this.chessCollection = chessCollection;
    }

    FileManager() {
    }

    /**
     * This is the method that saves the current game state into a file.
     * @author Lau Yee Keen Calvin
     */
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

    /**
     * This is the method that loads a previously saved game state.
     * @return The chess collection of chess pieces.
     * @author Lau Yee Keen Calvin
     */
    public ChessCollection loadSavedFile() throws FileNotFoundException {
        Gson gson = new Gson();
        ArrayList<ChessPiece> tempList = new ArrayList<>();
        Player[] players = new Player[2];

        Reader reader = new FileReader("savedGameFile/savedProgress.txt");
        // Convert JSON File to Java Object
        chessCollection = gson.fromJson(reader, ChessCollection.class);

        for (ChessPiece chessPiece : chessCollection.getChessPiece()) {

            if (players[0] == null) {
                players[0] = chessPiece.getChessOwner();
            }
            if (players[1] == null) {
                if (players[0].getPlayerID() != chessPiece.getChessOwner().getPlayerID()) {
                    players[1] = chessPiece.getChessOwner();
                }
            }
        }

        for (ChessPiece chessPiece : chessCollection.getChessPiece()) {
            int index;

            for (index = 0; index < players.length; index++) {
                if (chessPiece.getChessOwner().getPlayerID() == players[index].getPlayerID()) {
                    break;
                }
            }

            if (chessPiece.getChessName().contains("Arrow")) {
                tempList.add(new Arrow(chessPiece.getChessName(), chessPiece.getImgPath(), chessPiece.getChessPositionX()
                        , chessPiece.getChessPositionY(), players[index]));
            } else if (chessPiece.getChessName().contains("Sun")) {
                tempList.add(new Sun(chessPiece.getChessName(), chessPiece.getImgPath(), chessPiece.getChessPositionX()
                        , chessPiece.getChessPositionY(), players[index]));
            } else if (chessPiece.getChessName().contains("Triangle")) {
                tempList.add(new Triangle(chessPiece.getChessName(), chessPiece.getImgPath(), chessPiece.getChessPositionX()
                        , chessPiece.getChessPositionY(), players[index]));
            } else if (chessPiece.getChessName().contains("Chevron")) {
                tempList.add(new Chevron(chessPiece.getChessName(), chessPiece.getImgPath(), chessPiece.getChessPositionX()
                        , chessPiece.getChessPositionY(), players[index]));
            } else if (chessPiece.getChessName().contains("Plus")) {
                tempList.add(new Plus(chessPiece.getChessName(), chessPiece.getImgPath(), chessPiece.getChessPositionX()
                        , chessPiece.getChessPositionY(), players[index]));
            }
        }

        chessCollection.getChessPiece().clear();
        chessCollection.getChessPiece().addAll(tempList);

        return chessCollection;
    }

    /**
     * This is the method that delete all the saved files.
     * @author Lau Yee Keen Calvin
     */
    public void deleteAllSaveFile() throws IOException, NoSuchFileException {
        Path path = Paths.get("savedGameFile/savedProgress.txt");
        Files.delete(path);
    }

}
