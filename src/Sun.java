import java.util.ArrayList;

public class Sun extends ChessPiece {

    Sun(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (!(i == 0 && j == 0)) {
                    if (((x + i <= 7) && (x + i >= 0)) && ((y + j <= 8) && (y + j >= 0))) {
                        super.getPossibleMovesArray().add(new int[]{x + i, y + j});
                    }
                }
            }
        }
        return super.getPossibleMovesArray();
    }
}
