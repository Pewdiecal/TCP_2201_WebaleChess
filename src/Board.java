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

    //DONE
    //save the current state of the game
    public void saveState() throws IOException {
        fileManager = new FileManager(chessList);
        fileManager.writeToFile();
    }

    //DONE
    //Load the saved game file from directory
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

    //DONE
    //Fresh start a game
    //We need another color of img to represent the opponent cuz we only hav blue
    //import the img assets into the image directory
    //paste the path of the image into the "chessImg:" tag
    //paste it in the object with players[1] only
    //make sure the img u imported is 64x64
    public void reloadNewState() {
        //optimize this part of the code if u want to
        for (int arrow = 0; arrow < 8; arrow += 2) {
            chessList.addChess(new Arrow("Arrow", "image/BlueArrow.png", arrow,
                    1, players[1]));
        }
        for (int arrow = 0; arrow < 8; arrow += 2) {
            chessList.addChess(new Arrow("Arrow", "image/RedArrow.png", arrow,
                    6, players[0]));
        }

        for (int sun = 0; sun < 2; sun++) {
            chessList.addChess(new Sun("Sun", "image/BlueSun.png", 3, 0, players[1]));
            chessList.addChess(new Sun("Sun", "image/RedSun.png", 3, 7, players[0]));
        }

        for (int plus = 0; plus < 4; plus++) {
            chessList.addChess(new Plus("Plus", "image/BluePlus.png", 0, 0, players[1]));
            chessList.addChess(new Plus("Plus", "image/BluePlus.png", 6, 0, players[1]));
            chessList.addChess(new Plus("Plus", "image/RedPlus.png", 0, 7, players[0]));
            chessList.addChess(new Plus("Plus", "image/RedPlus.png", 6, 7, players[0]));
        }

        for (int triangle = 0; triangle < 4; triangle++) {
            chessList.addChess(new Triangle("Triangle", "image/BlueTriangle.png", 1,
                    0, players[1]));
            chessList.addChess(new Triangle("Triangle", "image/BlueTriangle.png", 5,
                    0, players[1]));
            chessList.addChess(new Triangle("Triangle", "image/RedTriangle.png", 1,
                    7, players[0]));
            chessList.addChess(new Triangle("Triangle", "image/RedTriangle.png", 5,
                    7, players[0]));
        }

        for (int chevron = 0; chevron < 4; chevron++) {
            chessList.addChess(new Chevron("Chevron", "image/BlueChevron.png", 2,
                    0, players[1]));
            chessList.addChess(new Chevron("Chevron", "image/BlueChevron.png", 4,
                    0, players[1]));
            chessList.addChess(new Chevron("Chevron", "image/RedChevron.png", 2,
                    7, players[0]));
            chessList.addChess(new Chevron("Chevron", "image/RedChevron.png", 4,
                    7, players[0]));
        }
    }

    //DONE
    //move the chess position fromX, fromY to toX, toY
    //this is called from my controller to move the chess
    //use the fromX, fromY coordinates to determine what chess is at that coordinate
    //Check possible move condition before hand. Use coordinate to check what piece, overwrite/change new position
    public void moveChess(int fromX, int fromY, int toX, int toY) {
        for (ChessPiece chessPiece : chessList.getChessPiece()){ //Check every chess piece
            if(chessPiece.getChessPositionX() == fromX && chessPiece.getChessPositionY() == fromY){
                //Confirm is the chess piece we want
                for(int[] elements:getPossibleMoves(fromX, fromY)){
                    if(elements[0] == toX && elements[1] == toY){
                        chessPiece.setChessPosition(toX, toY);
                    }
                }
            }
        }
    }

    //DONE
    //When a chess move is successful, this will get called
    @Override
    public void onChessMove() {
        chessList.flipPosition();
        //here u need to flip the whole board by calling chessCollection's flipPosition() method
        //u need to change the players turn as well
        for (Player player : players) {
            player.setPlayerTurn(!player.getPlayerTurn());
        }
    }

    //DONE
    //create and assign each player to both side of the chess
    public void addPlayer(String playerName) { //players turn needs to be set before calling reloadNewState << solve this
        for(int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = new Player(playerName, i + 1);
                if(i == 0){
                    players[i].setPlayerTurn(true); // Player 1 is assigned Turn 1
                }
            }
        }
    }

    //DONE
    //return back the current player's turn name
    //i will call this method from my controller class, u just need to provide the implementation below
    public String getCurrentPlayerName() {
        for (Player player : players) {
            if (player.getPlayerTurn()) {
                return player.getPlayerName();
            }
        }
        return null;
    }

    //DONE
    //return the actual img of the chess to be displayed on the JButton <<< its done
    public BufferedImage getChessPieceImg(int x, int y) {
        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            if (chessPiece.getChessPositionX() == x && chessPiece.getChessPositionY() == y) {
                return chessPiece.getChessImg();
            }
        }
        return null;
    }

    //DONE
    //return the lists of possible move to my controller so that i can show the green boxes
    //use the x,y coordinates to get that chess's possible move
    //since ur passing in arraylist, convert the arraylist by using toArray() to plain array before return it to me
    //Transfer info from Chess to Controller
    public int[][] getPossibleMoves(int fromX, int fromY) {
        int[][] possibleMoves = new int[0][];
        for (ChessPiece chessPiece : chessList.getChessPiece()){ //Check every chess piece
            if(chessPiece.getChessPositionX() == fromX && chessPiece.getChessPositionY() == fromY){
                //Confirm is the chess piece we want
                return chessPiece.generatePossibleMoves().toArray(possibleMoves);
            }
        }
        return possibleMoves;
    }
}
