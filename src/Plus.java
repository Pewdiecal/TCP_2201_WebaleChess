import java.util.ArrayList;

public class Plus extends ChessPiece {
    private boolean isPlus;

    Plus(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[][]> setPossibleMoves() {
        return null;
    }

    public void transform() {

    }
}
