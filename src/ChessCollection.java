import java.util.ArrayList;

//class for a collection of ChessPiece in a list
public class ChessCollection implements Chess {
    private ArrayList<ChessPiece> chessPieces = new ArrayList<>();

    //add all ChessPiece
    public void addChess(ChessPiece chessPiece) {
        chessPieces.add(chessPiece);
    }


    public ArrayList<ChessPiece> getChessPiece() {
        return chessPieces;
    }

    //Chan Jin Xuan
    //Flip all position of the chess by iterating it through all of the elements in the ArrayList
    //While its iterating through the list, just call ChessPiece.flipPosition() since flipPosition in the
    //chessPiece is implemented to flip a single chess
    @Override
    public void flipPosition() {
        for(ChessPiece chessPiece: chessPieces){
            chessPiece.flipPosition();
        }
    }

    //Chan Jin Xuan
    //update the list after every valid movement
    public void updateList(){
        ArrayList<ChessPiece> temp = new ArrayList<>();
        for (int i = 0; i < chessPieces.size(); i++){
            if(chessPieces.get(i).isOnBoard()){
                temp.add(chessPieces.get(i));
            }
        }
        this.chessPieces.clear();
        this.chessPieces.addAll(temp);
    }
}
