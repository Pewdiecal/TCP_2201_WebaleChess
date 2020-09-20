import java.util.ArrayList;

/**
 * The Arrow class defines the properties of an Arrow chess piece.
 *
 * @author Muhammad Hidayat Bin Jauhari
 * @author Mohammad Faris Bin Harunasir
 */
public class Arrow extends ChessPiece {

    /**
     * This is the constructor that passes the arrow chess piece's name, image, position and owner to its attributes.
     *
     * @param chessName      The chess piece's name.
     * @param chessImg       The chess piece's image path.
     * @param chessPositionX The chess piece's X coordinate position.
     * @param chessPositionY The chess piece's Y coordinate position.
     * @param chessOwner     The chess piece's owner.
     * @author Muhammad Hidayat Bin Jauhari
     * @author Mohamad Faris Bin Harunasir
     */
    Arrow(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    /**
     * This is the method that will return the possible moves for the selected Arrow chess piece.
     *
     * @return The possible moves.
     * @author Muhammad Hidayat Bin Jauhari
     * @author Mohamad Faris Bin Harunasir
     */
    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        boolean stopMove = false;

        super.getPossibleMovesArray().clear();

        if (getChessOwner().getPlayerTurn()) {
            if (!getArrowRotation()) {
                //Moving 1 or 2 steps upwards
                for (int i = 1; i < 3; i++) {
                    if (y - i >= 0 && !stopMove) {
                        int[] tempRef = new int[]{x, y - i};
                        super.getPossibleMovesArray().add(tempRef);
                        for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                            if (chessPiece.getChessPositionX() == x
                                    && chessPiece.getChessPositionY() == y - i
                                    && chessPiece.getChessOwner() == super.getChessOwner()) {
                                super.getPossibleMovesArray().remove(tempRef);
                                stopMove = true;
                                break;
                            } else if (chessPiece.getChessPositionX() == x
                                    && chessPiece.getChessPositionY() == y - 1
                                    && chessPiece.getChessOwner() != super.getChessOwner()) {
                                stopMove = true;
                                break;
                            }
                        }

                    }
                }
            } else if (getArrowRotation()) {
                //Moving 1 or 2 steps downwards if rotated
                for (int i = 1; i < 3; i++) {
                    if (y + i <= 7 && !stopMove) {
                        int[] tempRef = new int[]{x, y + i};
                        super.getPossibleMovesArray().add(tempRef);
                        for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                            if (chessPiece.getChessPositionX() == x
                                    && chessPiece.getChessPositionY() == y + i
                                    && chessPiece.getChessOwner() == super.getChessOwner()) {
                                super.getPossibleMovesArray().remove(tempRef);
                                stopMove = true;
                                break;
                            } else if (chessPiece.getChessPositionX() == x
                                    && chessPiece.getChessPositionY() == y + 1
                                    && chessPiece.getChessOwner() != super.getChessOwner()) {
                                stopMove = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return super.getPossibleMovesArray();
    }
}
