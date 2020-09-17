import java.util.ArrayList;

public class ChessCollection implements Chess {
    private ArrayList<ChessPiece> chessPieces = new ArrayList<>();
    private ArrayList<Arrow> arrows = new ArrayList<>();
    private ArrayList<Chevron> chevrons = new ArrayList<>();
    private ArrayList<Plus> pluses = new ArrayList<>();
    private ArrayList<Sun> sun = new ArrayList<>();

    public ArrayList<Arrow> getArrows() {
        return arrows;
    }

    public ArrayList<Chevron> getChevrons() {
        return chevrons;
    }

    public ArrayList<Plus> getPluses() {
        return pluses;
    }

    public ArrayList<Sun> getSun() {
        return sun;
    }

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    private ArrayList<Triangle> triangles = new ArrayList<>();

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
