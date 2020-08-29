import java.util.ArrayList;

public class Plus extends ChessPiece {
    private boolean isPlus;

    Plus(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[][]> setPossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        //for up-down direction
        for (int i = 0; i < 8; i++) {
            if(i != y) {
                super.getPossibleMovesArray().add(new int[][]{{x, i}});
            }
        }

        //for left-right direction
        for (int i = 0; i < 7; i++) {
            if(i != x) {
                super.getPossibleMovesArray().add(new int[][]{{i, y}});
            }
        }
        return null;
    }

    public void transform() {

    }
}
