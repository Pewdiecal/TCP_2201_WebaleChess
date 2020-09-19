import java.util.ArrayList;

/**
 * The Triangle class defines the properties of an Triangle chess piece.
 *
 * @author Muhammad Hidayat Bin Jauhari
 * @author Mohamad Faris Bin Harunasir
 */
public class Triangle extends ChessPiece {

    /**
     * This is the constructor that passes the arrow chess piece's name, image, position and owner to its attributes.
     * @param chessName The chess piece's name.
     * @param chessImg The chess piece's image path.
     * @param chessPositionX The chess piece's X coordinate position.
     * @param chessPositionY The chess piece's Y coordinate position.
     * @param chessOwner The chess piece's owner.
     * @author Muhammad Hidayat Bin Jauhari
     * @author Mohamad Faris Bin Harunasir
     */
    Triangle(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    /**
     * This is the method that will return the possible moves for the selected Triangle chess piece.
     * @return The possible moves.
     * @author Muhammad Hidayat Bin Jauhari
     * @author Mohamad Faris Bin Harunasir
     */
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

        if(getChessOwner().getPlayerTurn()) {
            //Down-Right
            while (xDR < 7 && yDR < 8 && !stopDR) {
                int[] tempRef = new int[]{xDR, yDR};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == xDR
                            && chessPiece.getChessPositionY() == yDR
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopDR = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == xDR
                            && chessPiece.getChessPositionY() == yDR
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopDR = true;
                        break;
                    }
                }
                xDR++;
                yDR++;
            }

            //Up-Left
            while (xUL >= 0 && yUL >= 0 && !stopUL) {
                int[] tempRef = new int[]{xUL, yUL};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == xUL
                            && chessPiece.getChessPositionY() == yUL
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopUL = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == xUL
                            && chessPiece.getChessPositionY() == yUL
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopUL = true;
                        break;
                    }
                }

                xUL--;
                yUL--;
            }

            //Down-Left
            while (xDL >= 0 && yDL < 8 && !stopDL) {
                int[] tempRef = new int[]{xDL, yDL};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == xDL
                            && chessPiece.getChessPositionY() == yDL
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopDL = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == xDL
                            && chessPiece.getChessPositionY() == yDL
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopDL = true;
                        break;
                    }
                }
                xDL--;
                yDL++;
            }

            //Up-Right
            while (xUR < 7 && yUR >= 0 && !stopUR) {
                int[] tempRef = new int[]{xUR, yUR};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == xUR
                            && chessPiece.getChessPositionY() == yUR
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopUR = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == xUR
                            && chessPiece.getChessPositionY() == yUR
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopUR = true;
                        break;
                    }
                }
                xUR++;
                yUR--;
            }
        }
        return super.getPossibleMovesArray();
    }
}
