import java.util.ArrayList;

public class ChessPiece implements Chess {
    private int chessPositionX;
    private int chessPositionY;
    private String chessName;
    private boolean isOnBoard;
    private Player chessOwner;
    private ArrayList<int[]> possibleMovesArray = new ArrayList<>();

    ChessPiece() {

    }

    public int getChessPositionX() {
        return this.chessPositionX;
    }

    public int getChessPositionY() {
        return this.chessPositionY;
    }

    public String getChessName() {
        return this.chessName;
    }

    public boolean isOnBoard() {
        return this.isOnBoard;
    }

    public void setIsOnBoard(boolean isOnBoard) {
        this.isOnBoard = isOnBoard;
    }

    public Player getChessOwner() {
        return this.chessOwner;
    }

    public void setChessOwner(Player player) {
        this.chessOwner = player;
    }

    public void setChessPositionX(int x) {
        this.chessPositionX = x;
    }

    public void setChessPositionY(int y) {
        this.chessPositionY = y;
    }

    public ArrayList<int[]> getPossibleMovesArray() {
        return this.possibleMovesArray;
    }

    @Override
    public void flipPosition() {

    }
}
