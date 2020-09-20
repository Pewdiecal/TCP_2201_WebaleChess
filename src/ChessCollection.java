import java.util.ArrayList;

//class for a collection of ChessPiece in a list

/**
 * This is the ChessCollection class that acts as a collection of ChessPiece in a list.
 *
 * @author Chan Jin Xuan
 */
public class ChessCollection implements Chess {

    private ArrayList<ChessPiece> chessPieces = new ArrayList<>();

    /**
     * This is the method that adds and initializes all the chess pieces.
     *
     * @param chessPiece The chess piece object.
     * @author Chan Jin Xuan
     */
    public void addChess(ChessPiece chessPiece) {
        chessPieces.add(chessPiece);
    }

    /**
     * @return The list of chess pieces.
     */
    public ArrayList<ChessPiece> getChessPiece() {
        return chessPieces;
    }

    /**
     * This is the method that flips the position of the chess pieces on the board.
     *
     * @author Chan Jin Xuan
     */
    @Override
    public void flipPosition() {
        for (ChessPiece chessPiece : chessPieces) {
            chessPiece.flipPosition();
        }
    }

    /**
     * This is the method that updates the latest list of chess pieces.
     *
     * @author Chan Jin Xuan
     */
    public void updateList() {
        ArrayList<ChessPiece> temp = new ArrayList<>();
        for (int i = 0; i < chessPieces.size(); i++) {
            if (chessPieces.get(i).isOnBoard()) {
                temp.add(chessPieces.get(i));
            }
        }
        this.chessPieces.clear();
        this.chessPieces.addAll(temp);
    }
}
