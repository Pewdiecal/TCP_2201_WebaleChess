import java.util.ArrayList;

public class Arrow extends ChessPiece {
    Arrow(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> generatePossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        super.getPossibleMovesArray().clear();

        if(!getArrowRotation()) {
            // moving 1 or 2 steps upwards
            for (int i = 1; i < 3; i++) {
                if (y - i >= 0) {
                    super.getPossibleMovesArray().add(new int[]{x, y - i});
                    // algorithm to not eat teammates
                }
            }
        }else if (getArrowRotation()){
            // moving 1 or 2 steps downwards if rotated
            for (int i = 1; i<3; i++){
                if(y + i <= 7){
                    super.getPossibleMovesArray().add(new int[]{x, y + i});
                    // algorithm to not eat teammates
                }
            }
        }


        return super.getPossibleMovesArray();

    }

}
