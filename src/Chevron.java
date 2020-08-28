import java.util.ArrayList;

public class Chevron extends ChessPiece {
    Chevron(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> setPossibleMoves() {
        return null;
    }
}
