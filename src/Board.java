import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;


//class for chess board
public class Board implements Observer {

    private ChessCollection chessList;
    private FileManager fileManager;
    private final Player[] players = new Player[2];
    private static Board instance = null;
    private Player winner;

    private Board() {
        this.chessList = new ChessCollection();
    }

    public ChessCollection getChessList() {
        return chessList;
    }

    //Lau Yee Keen Calvin
    //singleton design pattern where there is just a board instance created during the game
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    //Lau Yee Keen Calvin
    //save the current state of the game
    public void saveState() throws IOException {
        fileManager = new FileManager(chessList);
        fileManager.writeToFile();
    }

    //Lau Yee Keen Calvin
    //Load the saved game file from directory
    public void loadState() throws FileNotFoundException { //players turn needs to be set asap
        fileManager = new FileManager();
        chessList = fileManager.loadSavedFile();

        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            chessPiece.setChessImage(chessPiece.getImgPath());
            if (!chessPiece.getChessOwner().getPlayerTurn()) {
                chessPiece.rotateImg();
            }

            if (chessPiece.getChessOwner().getPlayerID() == 1) {
                players[0] = chessPiece.getChessOwner();
            }

            if (chessPiece.getChessOwner().getPlayerID() == 2) {
                players[1] = chessPiece.getChessOwner();
            }
        }

    }

    //Lau Yee Keen Calvin
    //Fresh start a game
    //We need another color of img to represent the opponent cuz we only hav blue
    //import the img assets into the image directory
    //paste the path of the image into the "chessImg:" tag
    //paste it in the object with players[1] only
    //make sure the img u imported is 64x64
    public void reloadNewState() {
        chessList.getChessPiece().clear();
        for (int arrow = 0; arrow < 8; arrow += 2) {
            chessList.addChess(new Arrow("Blue Arrow", "image/BlueArrow.png", arrow,
                    1, players[1]));
        }
        for (int arrow = 0; arrow < 8; arrow += 2) {
            chessList.addChess(new Arrow("Red Arrow", "image/RedArrow.png", arrow,
                    6, players[0]));
        }

        chessList.addChess(new Sun("Blue Sun", "image/BlueSun.png", 3, 0, players[1]));
        chessList.addChess(new Sun("Red Sun", "image/RedSun.png", 3, 7, players[0]));

        chessList.addChess(new Plus("Blue Plus", "image/BluePlus.png", 0, 0, players[1]));
        chessList.addChess(new Plus("Blue Plus", "image/BluePlus.png", 6, 0, players[1]));
        chessList.addChess(new Plus("Red Plus", "image/RedPlus.png", 0, 7, players[0]));
        chessList.addChess(new Plus("Red Plus", "image/RedPlus.png", 6, 7, players[0]));

        chessList.addChess(new Triangle("Blue Triangle", "image/BlueTriangle.png", 1,
                0, players[1]));
        chessList.addChess(new Triangle("Blue Triangle", "image/BlueTriangle.png", 5,
                0, players[1]));
        chessList.addChess(new Triangle("Red Triangle", "image/RedTriangle.png", 1,
                7, players[0]));
        chessList.addChess(new Triangle("Red Triangle", "image/RedTriangle.png", 5,
                7, players[0]));

        chessList.addChess(new Chevron("Blue Chevron", "image/BlueChevron.png", 2,
                0, players[1]));
        chessList.addChess(new Chevron("Blue Chevron", "image/BlueChevron.png", 4,
                0, players[1]));
        chessList.addChess(new Chevron("Red Chevron", "image/RedChevron.png", 2,
                7, players[0]));
        chessList.addChess(new Chevron("Red Chevron", "image/RedChevron.png", 4,
                7, players[0]));
    }

    //Nicholas Chee Jian Shen, Chan Jin Xuan
    //move the chess position fromX, fromY to toX, toY
    //this is called from controller to move the chess
    //use the fromX, fromY coordinates to determine what chess is at that coordinate
    //Check possible move condition before hand. Use coordinate to check what piece, overwrite/change new position
    public void moveChess(int fromX, int fromY, int toX, int toY) {

        boolean pass = true;
        for (int i = 0; i < chessList.getChessPiece().size(); i++) {
            //ChessPiece currentChess = chessList.getChessPiece().get(i);
            //ChessPiece eatChess;
            if(pass) {
                if (chessList.getChessPiece().get(i).getChessPositionX() == fromX
                        && chessList.getChessPiece().get(i).getChessPositionY() == fromY) {
                    //Confirm is the chess piece we want
                    for (int[] elements : getPossibleMoves(fromX, fromY)) {
                        if (elements[0] == toX && elements[1] == toY) {
                            for (int j = 0; j < chessList.getChessPiece().size(); j++) {
                                if (chessList.getChessPiece().get(j).getChessPositionX() == toX
                                        && chessList.getChessPiece().get(j).getChessPositionY() == toY) {
                                    chessList.getChessPiece().get(j).setIsOnBoard(false);
                                    if(chessList.getChessPiece().get(j).getChessName().contains("Sun")){ //if the "Sun" is dead the chessOwner of the chess which ate the sun wins
                                        winner = chessList.getChessPiece().get(i).getChessOwner();
                                    }

                                }
                            }
                            chessList.getChessPiece().get(i).setChessPosition(toX, toY);
                            onChessMove();
                            pass = false;
                            chessList.updateList();
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
    }

    public Player getWinner() {
        return winner;
    }

    public void nullifyWinner() {
        winner = null;
    }

    //Nicholas Chee Jian Shen
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

    //Chan Jin Xuan
    //create and assign each player to both side of the chess
    public void addPlayer(String playerName, int index) {
        players[index] = new Player(playerName, index + 1);
        if (index == 0) {
            players[index].setPlayerTurn(true); // Player 1 is assigned Turn 1
        }
    }

    //Chan Jin Xuan
    //return back the current player's turn name
    public String getCurrentPlayerName() {
        for (Player player : players) {
            if (player.getPlayerTurn()) {
                return player.getPlayerName();
            }
        }
        return null;
    }

    //Nicholas Chee Jian Shen
    //return the actual img of the chess to be displayed on the JButton
    public BufferedImage getChessPieceImg(int x, int y) {
        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            if (chessPiece.getChessPositionX() == x && chessPiece.getChessPositionY() == y) {
                return chessPiece.getChessImg();
            }
        }
        return null;
    }

    //Nicholas Chee Jian Shen, Chan Jin Xuan
    //return the lists of possible move to controller to show the green boxes
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
