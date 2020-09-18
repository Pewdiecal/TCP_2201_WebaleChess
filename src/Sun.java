import java.util.ArrayList;

// class for Sun chess that inherited from ChessPiece
public class Sun extends ChessPiece {

    Sun(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    // Muhammad Hidayat Bin Jauhari, Mohamad Faris Bin Harunasir
    // generate the possible moves for the selected sun
    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        super.getPossibleMovesArray().clear();

        // move up down left right
        if(getChessOwner().getPlayerTurn()) {
            if (y - 1 >= 0) { //Move up
                int[] tempRef = new int[]{x, y - 1};

                // allows moving to empty space & prevents eating own chess piece
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

            // move vertically
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
