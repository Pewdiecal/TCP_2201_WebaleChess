import java.util.ArrayList;

public class ChessCollection implements Chess {
    private ArrayList<ChessPiece> chessPieces = new ArrayList<>();

    public void addChess(ChessPiece chessPiece) {
        chessPieces.add(chessPiece);
    }

    public ChessPiece getChessPiece() {
        return null;
    }

    @Override
    public void flipPosition() {

    }
}
