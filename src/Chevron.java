import java.util.ArrayList;

public class Chevron extends ChessPiece {
    Chevron(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> setPossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        for (int i = 2; i > -3; i--) {
            for (int j = 2; j > -3; j--) {
                if(Math.abs(i) == 2 ^ Math.abs(j) == 2) {
                    if (i != 0 && j != 0) {
                        if(((x + i <= 7) && (x + i >= 0)) && ((y + j <= 8) && (y + j >= 0))) {
                            super.getPossibleMovesArray().add(new int[][]{{x + i, y + j}});
                        }
                    }
                }
            }
        }
        return null;
    }
}
