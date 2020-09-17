import java.util.ArrayList;

public class ChessCollection implements Chess {
    private ArrayList<ChessPiece> chessPieces = new ArrayList<>();

    //add all ChessPiece
    public void addChess(ChessPiece chessPiece) {
        chessPieces.add(chessPiece);
    }


    public ArrayList<ChessPiece> getChessPiece() {
        return chessPieces;
    }

    @Override
    public void flipPosition() {
        //Flip all position of the chess by iterating it through all of the elements in the ArrayList
        //While its iterating through the list, just call ChessPiece.flipPosition() since flipPosition in the
        //chessPiece is implemented to flip a single chess
        for(ChessPiece chessPiece: chessPieces){
            chessPiece.flipPosition();
        }
    }
}
