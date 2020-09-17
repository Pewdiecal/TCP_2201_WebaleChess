import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Board implements Observer {

    private ChessCollection chessList;
    private FileManager fileManager;
    private final Player[] players = new Player[2];
    private static Board instance = null;
    ChessPiece pieceToBeMoved = null;
    ChessPiece pieceToBeEaten = null;
    private String winner;

    private Board() {
        this.chessList = new ChessCollection();
    }

    public ChessCollection getChessList() {
        return chessList;
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
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

            if (chessPiece.getChessOwner().getPlayerID() == 1) {
                players[0] = chessPiece.getChessOwner();
            }

            if (chessPiece.getChessOwner().getPlayerID() == 2) {
                players[1] = chessPiece.getChessOwner();
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

    public boolean isValidMove(int fromX, int fromY, int toX, int toY){
        //CURRENT BUG: ChessPiece can choose to eat itself first, then when moving normally, that chess piece actually gets eaten
        boolean isValidMove = false;

        for(ChessPiece chessPiece : chessList.getChessPiece()){ //Check every chess piece
            if(chessPiece.getChessPositionX() == fromX && chessPiece.getChessPositionY() == fromY){
                pieceToBeMoved = chessPiece;
                for(int[] moves:getPossibleMoves(fromX, fromY)){
                    if (moves[0] == toX && moves[1] == toY
                            && players[0].getPlayerTurn() //If red's turn
                            && pieceToBeMoved.getChessOwner()==players[0]
                            && pieceToBeMoved != null) {
                        pieceToBeMoved = chessPiece;
                        isValidMove = true;
                    }
                    else if (moves[0] == toX && moves[1] == toY
                            && players[1].getPlayerTurn() //If blue's turn
                            && pieceToBeMoved.getChessOwner()==players[1]
                            && pieceToBeMoved != null) {
                        pieceToBeMoved = chessPiece;
                        isValidMove = true;
                    }
                }
            }
            if (chessPiece.getChessPositionX() == toX && chessPiece.getChessPositionY() == toY){
                pieceToBeEaten = chessPiece;
            }
        }
        return isValidMove;
    }
    //DONE
    //move the chess position fromX, fromY to toX, toY
    //this is called from my controller to move the chess
    //use the fromX, fromY coordinates to determine what chess is at that coordinate
    //Check possible move condition before hand. Use coordinate to check what piece, overwrite/change new position
    public void moveChess(int toX, int toY) {
        System.out.println(pieceToBeMoved);
        System.out.println(pieceToBeEaten);
        if (pieceToBeMoved != null && pieceToBeEaten != null) {
//            if (pieceToBeEaten.getChessName().equals("Blue Sun")) {
//                System.out.println("Red wins!");
//                winner = "Red";
//            }
//            if(pieceToBeEaten.getChessName().equals("Red Sun")){
//                System.out.println("Blue wins!");
//                winner = "Blue";
//            }
            chessList.getChessPiece().remove(pieceToBeEaten);
        }
        pieceToBeMoved.setChessPosition(toX, toY);
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
    public void addPlayer(String playerName, int index) { //players turn needs to be set before calling reloadNewState << solve this
        players[index] = new Player(playerName, index + 1);
        if (index == 0) {
            players[index].setPlayerTurn(true); // Player 1 is assigned Turn 1
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

    public String getWinner(){
        return winner;
    }
}
