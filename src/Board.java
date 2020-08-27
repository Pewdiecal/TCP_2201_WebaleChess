import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Board implements Observer {

    private ChessCollection chessList;
    private FileManager fileManager;
    private final Player[] players = new Player[2];

    Board() {
        this.chessList = new ChessCollection();
    }

    //save the current state of the game <<<< this part is done
    public void saveState() throws IOException {
        fileManager = new FileManager(chessList);
        fileManager.writeToFile();
    }

    //Load the saved game file from directory <<<<< this part is done
    public void loadState() throws FileNotFoundException { //players turn needs to be set asap
        fileManager = new FileManager();
        chessList = fileManager.loadSavedFile();
        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            chessPiece.setChessImage(chessPiece.getImgPath());
            if (!chessPiece.getChessOwner().getPlayerTurn()) {
                chessPiece.rotateImg();
            }
        }
    }

    //Fresh start a game
    //We need another color of img to represent the opponent cuz we only hav blue
    //import the img assets into the image directory
    //paste the path of the image into the "chessImg:" tag
    //paste it in the object with players[1] only
    //make sure the img u imported is 64x64
    public void reloadNewState() {
        //optimize this part of the code if u want to
        for (int arrow = 0; arrow < 8; arrow += 2) {
            chessList.addChess(new Arrow("Arrow", "image/ArrowPiece.png", arrow,
                    1, players[1]));
        }
        for (int arrow = 0; arrow < 8; arrow += 2) {
            chessList.addChess(new Arrow("Arrow", "image/ArrowPiece.png", arrow,
                    6, players[0]));
        }

        for (int sun = 0; sun < 2; sun++) {
            chessList.addChess(new Sun("Sun", "image/SunPiece.png", 3, 0, players[1]));
            chessList.addChess(new Sun("Sun", "image/SunPiece.png", 3, 7, players[0]));
        }

        for (int plus = 0; plus < 4; plus++) {
            chessList.addChess(new Plus("Plus", "image/CrossPiece.png", 0, 0, players[1]));
            chessList.addChess(new Plus("Plus", "image/CrossPiece.png", 6, 0, players[1]));
            chessList.addChess(new Plus("Plus", "image/CrossPiece.png", 0, 7, players[0]));
            chessList.addChess(new Plus("Plus", "image/CrossPiece.png", 6, 7, players[0]));
        }

        for (int triangle = 0; triangle < 4; triangle++) {
            chessList.addChess(new Triangle("Triangle", "image/TrianglePiece.png", 1,
                    0, players[1]));
            chessList.addChess(new Triangle("Triangle", "image/TrianglePiece.png", 5,
                    0, players[1]));
            chessList.addChess(new Triangle("Triangle", "image/TrianglePiece.png", 1,
                    7, players[0]));
            chessList.addChess(new Triangle("Triangle", "image/TrianglePiece.png", 5,
                    7, players[0]));
        }

        for (int chevron = 0; chevron < 4; chevron++) {
            chessList.addChess(new Chevron("Chevron", "image/ChevronPiece.png", 2,
                    0, players[1]));
            chessList.addChess(new Chevron("Chevron", "image/ChevronPiece.png", 4,
                    0, players[1]));
            chessList.addChess(new Chevron("Chevron", "image/ChevronPiece.png", 2,
                    7, players[0]));
            chessList.addChess(new Chevron("Chevron", "image/ChevronPiece.png", 4,
                    7, players[0]));
        }
    }

    //move the chess position fromX, fromY to toX, toY
    //this is called from my controller to move the chess
    //use the fromX, fromY coordinates to determine what chess is at that coordinate
    public void moveChess(int fromX, int fromY, int toX, int toY) {

    }

    //When a chess move is successful, this will get called
    @Override
    public void onChessMove() {
        //here u need to flip the whole board by calling chessCollection's flipPosition() method
        //u need to change the players turn as well
    }

    //create and assign each player to both side of the chess
    public void addPlayer(String playerName) { //players turn needs to be set bfore calling reloadNewState << solve this
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = new Player(playerName, i + 1);
            }
        }
    }

    //return back the current player's turn name
    //i will call this method from my controller class, u just need to provide the implementation below
    public String getCurrentPlayerName() {
        return "";
    }

    //return the actual img of the chess to be displayed on the JButton <<< its done
    public BufferedImage getChessPieceImg(int x, int y) {
        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            if (chessPiece.getChessPositionX() == x && chessPiece.getChessPositionY() == y) {
                return chessPiece.getChessImg();
            }
        }
        return null;
    }

    //return the lists of possible move to my controller so that i can show the green boxes
    //use the x,y coordinates to get that chess's possible move
    //since ur passing in arraylist, convert the arraylist by using toArray() to plain array bfore return it to me
    public int[][] getPossibleMoves(int x, int y) {
        return new int[][]{{0, 0}, {0, 1}, {0, 2}};//this is just dummy data, replace this with ur actual data
    }
}
