import java.util.ArrayList;

public class Arrow extends ChessPiece {
    Arrow(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        boolean stopMove = false;

        super.getPossibleMovesArray().clear();

        if(!getArrowRotation()) {
            // moving 1 or 2 steps upwards
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
                        }
                    }

                }
            }
        }else if (getArrowRotation()){
            // moving 1 or 2 steps downwards if rotated
            for (int i = 1; i<3; i++){
                if(y + i <= 7 && !stopMove){
                    int[] tempRef = new int[]{x, y + i};
                    super.getPossibleMovesArray().add(tempRef);
                    for (ChessPiece chessPiece : super.getBoard().getChessList().getChessPiece()) {
                        if (chessPiece.getChessPositionX() == x
                                && chessPiece.getChessPositionY() == y + 1
                                && chessPiece.getChessOwner() == super.getChessOwner()) {
                            super.getPossibleMovesArray().remove(tempRef);
                            stopMove = true;
                            break;
                        }
                    }
                }
            }
        }


        return super.getPossibleMovesArray();

    }

}
