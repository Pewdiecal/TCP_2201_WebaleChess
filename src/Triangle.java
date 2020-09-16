import java.util.ArrayList;

public class Triangle extends ChessPiece {
    private boolean isTriangle;

    Triangle(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> generatePossibleMoves() {

        int xDR = getChessPositionX() + 1;
        int yDR = getChessPositionY() + 1;
        int xUL = getChessPositionX() - 1;
        int yUL = getChessPositionY() - 1;
        int xDL = getChessPositionX() - 1;
        int yDL = getChessPositionY() + 1;
        int xUR = getChessPositionX() + 1;
        int yUR = getChessPositionY() - 1;

        super.getPossibleMovesArray().clear();

        //Down-Right
        while(xDR < 7 && yDR < 8) {
            super.getPossibleMovesArray().add(new int[]{xDR, yDR});
            xDR++;
            yDR++;
        }

        //Up-Left
        while(xUL >= 0 && yUL >= 0) {
            super.getPossibleMovesArray().add(new int[]{xUL, yUL});
            xUL--;
            yUL--;
        }

        //Down-Left
        while(xDL >= 0 && yDL < 8) {
            super.getPossibleMovesArray().add(new int[]{xDL, yDL});
            xDL--;
            yDL++;
        }

        //Up-Right
        while(xUR < 7 && yUR >= 0) {
            super.getPossibleMovesArray().add(new int[]{xUR, yUR});
            xUR++;
            yUR--;
        }
        return super.getPossibleMovesArray();
    }

    public void transform() {

    }
}
