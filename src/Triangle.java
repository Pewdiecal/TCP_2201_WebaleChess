import java.util.ArrayList;

public class Triangle extends ChessPiece {

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

        boolean stopDR = false;
        boolean stopUR = false;
        boolean stopUL = false;
        boolean stopDL = false;

        super.getPossibleMovesArray().clear();

        //Down-Right
        while(xDR < 7 && yDR < 8 && !stopDR) {
            int[] tempRef = new int[]{xDR, yDR};
            super.getPossibleMovesArray().add(tempRef);

            // allows moving to empty space & prevents eating own chess piece
            for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                if (chessPiece.getChessPositionX() == xDR
                        && chessPiece.getChessPositionY() == yDR
                        && chessPiece.getChessOwner() == super.getChessOwner()) {
                    super.getPossibleMovesArray().remove(tempRef);
                    stopDR = true;
                    break;
                }
            }
            xDR++;
            yDR++;
        }

        //Up-Left
        while(xUL >= 0 && yUL >= 0 && !stopUL) {
            int[] tempRef = new int[]{xUL, yUL};
            super.getPossibleMovesArray().add(tempRef);

            // allows moving to empty space & prevents eating own chess piece
            for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                if (chessPiece.getChessPositionX() == xUL
                        && chessPiece.getChessPositionY() == yUL
                        && chessPiece.getChessOwner() == super.getChessOwner()) {
                    super.getPossibleMovesArray().remove(tempRef);
                    stopUL = true;
                    break;
                }
            }

            xUL--;
            yUL--;
        }

        //Down-Left
        while(xDL >= 0 && yDL < 8 && !stopDL) {
            int[] tempRef = new int[]{xDL, yDL};
            super.getPossibleMovesArray().add(tempRef);

            // allows moving to empty space & prevents eating own chess piece
            for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                if (chessPiece.getChessPositionX() == xDL
                        && chessPiece.getChessPositionY() == yDL
                        && chessPiece.getChessOwner() == super.getChessOwner()) {
                    super.getPossibleMovesArray().remove(tempRef);
                    stopDL = true;
                    break;
                }
            }
            xDL--;
            yDL++;
        }

        //Up-Right
        while(xUR < 7 && yUR >= 0 && !stopUR) {
            int[] tempRef = new int[]{xUR, yUR};
            super.getPossibleMovesArray().add(tempRef);

            // allows moving to empty space & prevents eating own chess piece
            for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                if (chessPiece.getChessPositionX() == xUR
                        && chessPiece.getChessPositionY() == yUR
                        && chessPiece.getChessOwner() == super.getChessOwner()) {
                    super.getPossibleMovesArray().remove(tempRef);
                    stopUR = true;
                    break;
                }
            }
            xUR++;
            yUR--;
        }

        return super.getPossibleMovesArray();
    }

}
