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

        if(getChessOwner().getPlayerTurn()) {
            //algorithm to not eat teammates
            if (x + 1 <= 6 && y - 2 >= 0) {
                int[] tempRef = new int[]{x + 1, y - 2};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x + 1
                            && chessPiece.getChessPositionY() == y - 2
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x - 1 >= 0 && y - 2 >= 0) {
                int[] tempRef = new int[]{x - 1, y - 2};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x - 1
                            && chessPiece.getChessPositionY() == y - 2
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x + 1 <= 6 && y + 2 <= 7) {
                int[] tempRef = new int[]{x + 1, y + 2};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x + 1
                            && chessPiece.getChessPositionY() == y + 2
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x - 1 >= 0 && y + 2 <= 7) {
                int[] tempRef = new int[]{x - 1, y + 2};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x - 1
                            && chessPiece.getChessPositionY() == y + 2
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x + 2 <= 6 && y - 1 >= 0) {
                int[] tempRef = new int[]{x + 2, y - 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x + 2
                            && chessPiece.getChessPositionY() == y - 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x - 2 >= 0 && y - 1 >= 0) {
                int[] tempRef = new int[]{x - 2, y - 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x - 2
                            && chessPiece.getChessPositionY() == y - 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x + 2 <= 6 && y + 1 <= 7) {
                int[] tempRef = new int[]{x + 2, y + 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x + 2
                            && chessPiece.getChessPositionY() == y + 1
                            && chessPiece.getChessOwner() == super.getChessOwner()) {
                        super.getPossibleMovesArray().remove(tempRef);
                    }
                }
            }
            if (x - 2 >= 0 && y + 1 <= 7) {
                int[] tempRef = new int[]{x - 2, y + 1};
                super.getPossibleMovesArray().add(tempRef);
                for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                    if (chessPiece.getChessPositionX() == x - 2
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
