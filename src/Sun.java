import java.util.ArrayList;

public class Sun extends ChessPiece {

    Sun(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        super.getPossibleMovesArray().clear();


        // move up down left right
        if (y - 1 >= 0) {
            super.getPossibleMovesArray().add(new int[]{x, y - 1});
            // algorithm to not eat teammates
        }
        if (y + 1 <= 7) {
            super.getPossibleMovesArray().add(new int[]{x, y + 1});
            // algorithm to not eat teammates
        }
        if (x - 1 >= 0) {
            super.getPossibleMovesArray().add(new int[]{x - 1, y});
            // algorithm to not eat teammates
        }
        if (x + 1 <= 6) {
            super.getPossibleMovesArray().add(new int[]{x + 1, y});
            // algorithm to not eat teammates
        }

        // move vertically
        if (x + 1 <= 6 && y - 1 >= 0){
            super.getPossibleMovesArray().add(new int[]{x + 1, y - 1});
        }
        if (x + 1 <= 6 && y + 1 <= 7){
            super.getPossibleMovesArray().add(new int[]{x + 1, y + 1});
        }
        if (x - 1 >= 0 && y - 1 >= 0){
            super.getPossibleMovesArray().add(new int[]{x - 1, y - 1});
        }
        if (x - 1 >= 0 && y + 1 <= 7){
            super.getPossibleMovesArray().add(new int[]{x - 1, y + 1});
        }


        return super.getPossibleMovesArray();
    }
}
