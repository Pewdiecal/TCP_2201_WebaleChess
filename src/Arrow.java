import java.util.ArrayList;

public class Arrow extends ChessPiece {
    Arrow(String chessName, String chessImg, int chessPositionX, int chessPositionY, Player chessOwner) {
        super(chessName, chessImg, chessPositionX, chessPositionY, chessOwner);
    }

    @Override
    public ArrayList<int[]> setPossibleMoves() {
        int x = getChessPositionX();
        int y = getChessPositionY();

        if(y == 7 && getArrowRotation() == false) {
            //rotate
            super.getPossibleMovesArray().add(new int[][]{{x, y - 1}});
            super.getPossibleMovesArray().add(new int[][]{{x, y - 2}});
        }
        else if(y == 1 && getArrowRotation() == false) {
            super.getPossibleMovesArray().add(new int[][]{{x, y - 1}});
        }
        else if(getArrowRotation() == false){
            super.getPossibleMovesArray().add(new int[][]{{x, y - 1}});
            super.getPossibleMovesArray().add(new int[][]{{x, y - 2}});
        }
        else if(y == 0 && getArrowRotation() == true) {
            //rotate
            super.getPossibleMovesArray().add(new int[][]{{x, y + 1}});
            super.getPossibleMovesArray().add(new int[][]{{x, y + 2}});
        }
        else if(y == 6 && getArrowRotation() == true) {
            super.getPossibleMovesArray().add(new int[][]{{x, y + 1}});
        }
        else if(getArrowRotation() == true){
            super.getPossibleMovesArray().add(new int[][]{{x, y + 1}});
            super.getPossibleMovesArray().add(new int[][]{{x, y + 2}});
        }
        return null;
    }

}
