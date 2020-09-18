import java.util.ArrayList;

public class Plus extends ChessPiece {

    Plus(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

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
            //moving up
            while (yU >= 0 && !stopU) {
                int[] tempRef = new int[]{x, yU};
                super.getPossibleMovesArray().add(tempRef);

                // allows moving to empty space & prevents eating own chess piece
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

            //moving down
            while (yD <= 7 && !stopD) {
                int[] tempRef = new int[]{x, yD};
                super.getPossibleMovesArray().add(tempRef);

                // allows moving to empty space & prevents eating own chess piece
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

            //moving right
            while (xR <= 6 && !stopR) {
                int[] tempRef = new int[]{xR, y};
                super.getPossibleMovesArray().add(tempRef);

                // allows moving to empty space & prevents eating own chess piece
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

            //moving left
            while (xL >= 0 && !stopL) {
                int[] tempRef = new int[]{xL, y};
                super.getPossibleMovesArray().add(tempRef);

                // allows moving to empty space & prevents eating own chess piece
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
