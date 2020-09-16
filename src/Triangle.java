import java.util.ArrayList;

public class Triangle extends ChessPiece {
    private boolean isTriangle;

    Triangle(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> setPossibleMoves() {

        int xUR = getChessPositionX() + 1;
        int yUR = getChessPositionX() + 1;
        int xDL = getChessPositionX() - 1;
        int yDL = getChessPositionX() - 1;
        int xUL = getChessPositionX() - 1;
        int yUL = getChessPositionX() + 1;
        int xDR = getChessPositionX() + 1;
        int yDR = getChessPositionX() - 1;

        //Up-Right
        while(xUR < 7 && yUR < 8) {
            super.getPossibleMovesArray().add(new int[]{xUR, yUR});
            xUR++;
            yUR++;
        }

        //Down-Left
        while(xDL >= 0 && yDL >= 0) {
            super.getPossibleMovesArray().add(new int[]{xDL, yDL});
            xDL--;
            yDL--;
        }

        //Up-Left
        while(xUL >= 0 && yUL < 8) {
            super.getPossibleMovesArray().add(new int[]{xUL, yUL});
            xUL--;
            yUL++;
        }

        //Down-Right
        while(xDR < 7 && yDR >= 0) {
            super.getPossibleMovesArray().add(new int[]{xDR, yDR});
            xDR++;
            yDR--;
        }
        return getPossibleMovesArray();
    }

    public void transform() {

    }
}
