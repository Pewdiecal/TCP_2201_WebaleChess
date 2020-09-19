import java.util.ArrayList;

/**
 * The Sun class defines the properties of a Sun chess piece.
 *
 * @author Muhammad Hidayat Bin Jauhari
 * @author Mohamad Faris Bin Harunasir
 */
public class Sun extends ChessPiece {

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
    Sun(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    /**
     * This is the method that will return the possible moves for the selected Sun chess piece.
     * @return The possible moves.
     * @author Muhammad Hidayat Bin Jauhari
     * @author Mohamad Faris Bin Harunasir
     */
    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        super.getPossibleMovesArray().clear();

        //Move up down left right
        if(getChessOwner().getPlayerTurn()) {
            if (y - 1 >= 0) { //Move up
                int[] tempRef = new int[]{x, y - 1};

                //Allows moving to empty space & prevents eating own chess piece
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x
                            && chessPiece.getChessPositionY() == y - 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }

            }
            if (y + 1 <= 7) { //Move down
                int[] tempRef = new int[]{x, y + 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x
                            && chessPiece.getChessPositionY() == y + 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x - 1 >= 0) {
                int[] tempRef = new int[]{x - 1, y};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x - 1
                            && chessPiece.getChessPositionY() == y
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x + 1 <= 6) {
                int[] tempRef = new int[]{x + 1, y};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x + 1
                            && chessPiece.getChessPositionY() == y
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }

            //Move vertically
            if (x + 1 <= 6 && y - 1 >= 0) {
                int[] tempRef = new int[]{x + 1, y - 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x + 1
                            && chessPiece.getChessPositionY() == y - 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x + 1 <= 6 && y + 1 <= 7) {
                int[] tempRef = new int[]{x + 1, y + 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x + 1
                            && chessPiece.getChessPositionY() == y + 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x - 1 >= 0 && y - 1 >= 0) {
                int[] tempRef = new int[]{x - 1, y - 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x - 1
                            && chessPiece.getChessPositionY() == y - 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x - 1 >= 0 && y + 1 <= 7) {
                int[] tempRef = new int[]{x - 1, y + 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x - 1
                            && chessPiece.getChessPositionY() == y + 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
        }
        return super.getPossibleMovesArray();
    }
}
