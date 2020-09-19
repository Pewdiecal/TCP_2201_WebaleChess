import java.util.ArrayList;

/**
 * The Plus class defines the properties of a Plus chess piece.
 *
 * @author Muhammad Hidayat Bin Jauhari
 * @author Mohamad Faris Bin Harunasir
 */
public class Plus extends ChessPiece {

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
    Plus(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    /**
     * This is the method that will return the possible moves for the selected Plus chess piece.
     * @return The possible moves.
     * @author Muhammad Hidayat Bin Jauhari
     * @author Mohamad Faris Bin Harunasir
     */
    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        int yD = getChessPositionY() + 1;
        int yU = getChessPositionY() - 1;
        int xR = getChessPositionX() + 1;
        int xL = getChessPositionX() - 1;

        boolean stopU = false;
        boolean stopD = false;
        boolean stopR = false;
        boolean stopL = false;

        super.getPossibleMovesArray().clear();

        if(getChessOwner().getPlayerTurn()) {
            //Moving up
            while (yU >= 0 && !stopU) {
                int[] tempRef = new int[]{x, yU};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x
                            && chessPiece.getChessPositionY() == yU
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopU = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == x
                            && chessPiece.getChessPositionY() == yU
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopU = true;
                        break;
                    }
                }
                yU--;
            }

            //Moving down
            while (yD <= 7 && !stopD) {
                int[] tempRef = new int[]{x, yD};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x
                            && chessPiece.getChessPositionY() == yD
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopD = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == x
                            && chessPiece.getChessPositionY() == yD
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopD = true;
                        break;
                    }
                }
                yD++;
            }

            //Moving right
            while (xR <= 6 && !stopR) {
                int[] tempRef = new int[]{xR, y};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == xR
                            && chessPiece.getChessPositionY() == y
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopR = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == xR
                            && chessPiece.getChessPositionY() == y
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopR = true;
                        break;
                    }
                }
                xR++;
            }

            //Moving left
            while (xL >= 0 && !stopL) {
                int[] tempRef = new int[]{xL, y};
                super.getPossibleMovesArray().add(tempRef);

                //Allows moving to empty space & prevents eating own chess piece
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == xL
                            && chessPiece.getChessPositionY() == y
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                        stopL = true;
                        break;
                    } else if (chessPiece.getChessPositionX() == xL
                            && chessPiece.getChessPositionY() == y
                            && chessPiece.getChessOwner() != super.getChessOwner()) {
                        stopL = true;
                        break;
                    }
                }
                xL--;
            }
        }
        return super.getPossibleMovesArray();
    }
}
