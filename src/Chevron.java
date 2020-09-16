import java.util.ArrayList;

public class Chevron extends ChessPiece {
    Chevron(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        super.getPossibleMovesArray().clear();

        //algorithm to not eat teammates
        if (x + 1 <= 6 && y - 2 >= 0){
            super.getPossibleMovesArray().add(new int[]{x + 1, y - 2});
        }
        if (x - 1 >= 0 && y - 2 >= 0){
            super.getPossibleMovesArray().add(new int[]{x - 1, y - 2});
        }
        if (x + 1 <= 6 && y + 2 <= 7){
            super.getPossibleMovesArray().add(new int[]{x + 1, y + 2});
        }
        if (x - 1 >= 0 && y + 2 <= 7){
            super.getPossibleMovesArray().add(new int[]{x - 1, y + 2});
        }
        if (x + 2 <= 6 && y - 1 >= 0){
            super.getPossibleMovesArray().add(new int[]{x + 2, y - 1});
        }
        if (x - 2 >= 0 && y - 1 >= 0){
            super.getPossibleMovesArray().add(new int[]{x - 2, y - 1});
        }
        if (x + 2 <= 6 && y + 1 <= 7){
            super.getPossibleMovesArray().add(new int[]{x + 2, y + 1});
        }
        if (x - 2 >= 0 && y + 1 <= 7){
            super.getPossibleMovesArray().add(new int[]{x - 2, y + 1});
        }



        return super.getPossibleMovesArray();
    }
}
