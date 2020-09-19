import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Board class defines the properties of a board.
 *
 * @author Nicholas Chee Jian Shen
 * @author Chan Jin Xuan
 */
public class Board implements Observer {

    private ChessCollection chessList;
    private FileManager fileManager;
    private final Player[] players = new Player[2];
    private static Board instance = null;
    private Player winner;

    private Board() {
        this.chessList = new ChessCollection();
    }

    /**
     * @return The chess list which contains instances of the chess pieces.
     */
    public ChessCollection getChessList() {
        return chessList;
    }

    /**
     * This is the singleton design pattern where there is just a board instance created during the game.
     * @return The board instance.
     */
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /**
     * This is the method that saves the current state of the game
     * @author Lau Yee Keen Calvin
     */
    public void saveState() throws IOException {
        fileManager = new FileManager(chessList);
        fileManager.writeToFile();
    }

    /**
     * This is the method that loads the saved game file from directory.
     * @author Lau Yee Keen Calvin
     */
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

    /**
     * This is the method that initializes the chess pieces in chessList during the start of a new game.
     * @author Lau Yee Keen Calvin
     */
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

    /**
     * This is the method that handles a chess piece moving on the board.
     * @param fromX The x coordinate position of chess piece to be moved.
     * @param fromY The y coordinate position of chess piece to be moved.
     * @param toX The x coordinate position that the chess piece will move to.
     * @param toY The y coordinate position that the chess piece will move to.
     * @author Nicholas Chee Jian Shen
     * @author Chan Jin Xuan
     */
    public void moveChess(int fromX, int fromY, int toX, int toY) {

        boolean pass = true;
        for (int i = 0; i < chessList.getChessPiece().size(); i++) {
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
                                    if(chessList.getChessPiece().get(j).getChessName().contains("Sun")){
                                        //If the "Sun" is dead the chessOwner of the chess which ate the sun wins
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
            } else { break; }
        }
    }

    /**
     * @return The winning player.
     * @author Chan Jin Xuan
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * This is the method that resets the winning player to null.
     * @author Chan Jin Xuan
     */
    public void nullifyWinner() {
        winner = null;
    }

    /**
     * This is the method that will flip the board and change the players' turn when a chess move is successful.
     * @author Nicholas Chee Jian Shen
     */
    @Override
    public void onChessMove() {
        chessList.flipPosition();
        for (Player player : players) {
            player.setPlayerTurn(!player.getPlayerTurn());
        }
    }

    /**
     * This is the method that creates and assigns each player a turn.
     * @param playerName the player's name.
     * @param index The player's index number.
     * @author Chan Jin Xuan
     */
    public void addPlayer(String playerName, int index) {
        players[index] = new Player(playerName, index + 1);
        if (index == 0) {
            players[index].setPlayerTurn(true); // Player 1 is assigned Turn 1
        }
    }

    /**
     * This is the method that returns the current player's name.
     * @return Current player's name.
     * @author Chan Jin Xuan
     */
    public String getCurrentPlayerName() {
        for (Player player : players) {
            if (player.getPlayerTurn()) {
                return player.getPlayerName();
            }
        }
        return null;
    }

    /**
     * This is the method that returns the actual image of the chess to be displayed on the JButton.
     * @param x The x coordinate position.
     * @param y The y coordinate position.
     * @return The chess piece image.
     * @author Nicholas Chee Jian Shen
     */
    public BufferedImage getChessPieceImg(int x, int y) {
        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            if (chessPiece.getChessPositionX() == x && chessPiece.getChessPositionY() == y) {
                return chessPiece.getChessImg();
            }
        }
        return null;
    }

    /**
     * This is the method that returns the lists of possible moves to the controller to show the green boxes.
     * @param fromX The x coordinate position of the chess piece.
     * @param fromY The y coordinate position of the chess piece.
     * @return The possible moves.
     * @author Nicholas Chee Jian Shen
     * @author Chan Jin Xuan
     */
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
