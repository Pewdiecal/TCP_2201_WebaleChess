import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Board implements Observer {

    private ChessCollection chessList;
    private FileManager fileManager;
    private final Player[] players = new Player[2];
    private static Board instance = null;

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
        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            if (chessPiece instanceof Arrow) {
                chessList.getArrows().add((Arrow) chessPiece);
            }
            if (chessPiece instanceof Sun) {
                chessList.getSun().add((Sun) chessPiece);
            }
            if (chessPiece instanceof Triangle) {
                chessList.getTriangles().add((Triangle) chessPiece);
            }
            if (chessPiece instanceof Plus) {
                chessList.getPluses().add((Plus) chessPiece);
            }
            if (chessPiece instanceof Chevron) {
                chessList.getChevrons().add((Chevron) chessPiece);
            }
        }
        fileManager.writeToFile();
    }

    //DONE
    //Load the saved game file from directory
    public void loadState() throws FileNotFoundException { //players turn needs to be set asap
        fileManager = new FileManager();
        chessList = fileManager.loadSavedFile();
        chessList.getChessPiece().clear();
        for (Arrow arrow : chessList.getArrows()) {
            chessList.getChessPiece().add(arrow);
        }
        for (Chevron chevron : chessList.getChevrons()) {
            chessList.getChessPiece().add(chevron);
        }
        for (Plus plus : chessList.getPluses()) {
            chessList.getChessPiece().add(plus);
        }
        for (Sun sun : chessList.getSun()) {
            chessList.getChessPiece().add(sun);
        }
        for (Triangle triangle : chessList.getTriangles()) {
            chessList.getChessPiece().add(triangle);
        }

        for (ChessPiece chessPiece : chessList.getChessPiece()) {
            chessPiece.setChessImage(chessPiece.getImgPath());
            if (!chessPiece.getChessOwner().getPlayerTurn()) {
                chessPiece.rotateImg();
            }

            if (players[0] == null) {
                players[0] = chessPiece.getChessOwner();
            }
            if (players[1] == null) {
                if (players[0].getPlayerID() != chessPiece.getChessOwner().getPlayerID()) {
                    players[1] = chessPiece.getChessOwner();
                }
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

    //DONE
    //move the chess position fromX, fromY to toX, toY
    //this is called from my controller to move the chess
    //use the fromX, fromY coordinates to determine what chess is at that coordinate
    //Check possible move condition before hand. Use coordinate to check what piece, overwrite/change new position
    public void moveChess(int fromX, int fromY, int toX, int toY) {

        int[][] possibleMoves = new int[0][];
        ChessPiece pieceToBeMoved = null;
        ChessPiece pieceToBeEaten = null;

        for(ChessPiece chessPiece : chessList.getChessPiece()){ //Check every chess piece
            if(chessPiece.getChessPositionX() == fromX && chessPiece.getChessPositionY() == fromY){
                for(int[] moves:getPossibleMoves(fromX, fromY)){
                    if (moves[0] == toX && moves[1] == toY) {
                        pieceToBeMoved = chessPiece;
                        break;
                    }
                }
            }

            if (chessPiece.getChessPositionX() == toX && chessPiece.getChessPositionY() == toY){
                pieceToBeEaten = chessPiece;
            }
        }

        if(pieceToBeMoved != null && pieceToBeEaten != null){
            chessList.getChessPiece().remove(pieceToBeEaten);
        }

        if(pieceToBeMoved != null){
            pieceToBeMoved.setChessPosition(toX, toY);
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
