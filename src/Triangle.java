import java.util.ArrayList;

public class Triangle extends ChessPiece {
    private boolean isTriangle;

    Triangle(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> getPossibleMovesArray() {
        return super.getPossibleMovesArray();
    }

    public void transform() {

    }
}
