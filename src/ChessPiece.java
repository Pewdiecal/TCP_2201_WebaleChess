import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The ChessPiece class defines the properties of a chess piece.
 *
 * @author Muhammad Hidayat Bin Jauhari
 * @author Mohammad Faris Bin Harunasir
 */
public class ChessPiece implements Chess, Observable {
    private static int accumulator;

    public Board getBoard() {
        return board;
    }

    private transient final Board board = Board.getInstance();
    private int chessPositionX;
    private int chessPositionY;
    private final String chessName;
    private String chessImgPath;
    private transient BufferedImage bufferedImage;
    private boolean isOnBoard = true;
    private boolean arrowRotation = false;
    private boolean triangleRotation = false;
    private Player chessOwner;
    private final ArrayList<int[]> possibleMovesArray = new ArrayList<>();

    /**
     * This is the constructor that passes the chess piece's name, image, position and owner to its attributes.
     * @param chessName The chess piece's name.
     * @param chessImgPath The chess piece's image path.
     * @param chessPositionX The chess piece's X coordinate position.
     * @param chessPositionY The chess piece's Y coordinate position.
     * @param chessOwner The chess piece's owner.
     * @author Lau Yee Keen Calvin
     */
    ChessPiece(String chessName, String chessImgPath, int chessPositionX, int chessPositionY, Player chessOwner) {
        this.chessName = chessName;
        this.chessPositionX = chessPositionX;
        this.chessPositionY = chessPositionY;
        this.chessOwner = chessOwner;
        this.chessImgPath = chessImgPath;
        //Read in the image of the chess
        try {
            bufferedImage = ImageIO.read(new File(chessImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Rotate the image from the opposite side for 180 degrees
        if (!chessOwner.getPlayerTurn()) {
            rotateImg();
        }
    }

    /**
     * @return The chess piece's X coordinate position.
     */
    public int getChessPositionX() {
        return this.chessPositionX;
    }

    /**
     * @return The chess piece's Y coordinate position.
     */
    public int getChessPositionY() {
        return this.chessPositionY;
    }

    /**
     * @return The chess piece's arrow rotation.
     */
    public boolean getArrowRotation() {
        return this.arrowRotation;
    }

    /**
     * @return The chess piece's name.
     */
    public String getChessName() {
        return this.chessName;
    }

    /**
     * @return Whether the chess piece is on the board.
     */
    public boolean isOnBoard() {
        return this.isOnBoard;
    }

    /**
     * @param isOnBoard To set whether the chess piece is on the board.
     */
    public void setIsOnBoard(boolean isOnBoard) {
        this.isOnBoard = isOnBoard;
    }

    /**
     * @return The chess piece's owner.
     */
    public Player getChessOwner() {
        return this.chessOwner;
    }

    /**
     * This is the method that moves the chess piece and
     * counts the movement made by the players to change the triangles and pluses.
     * @param x The x coordinate position.
     * @param y The y coordinate position.
     * @author Muhammad Hidayat Bin Jauhari
     * @author Mohamad Faris Bin Harunasir
     */
    public void setChessPosition(int x, int y) {
        ArrayList<ChessPiece> temp = new ArrayList<>();
        this.chessPositionX = x;
        this.chessPositionY = y;

        if (y == 7 && chessName.contains("Arrow")) {
            this.arrowRotation = false;
            rotateImg();
        } else if (y == 0 && chessName.contains("Arrow")) {
            this.arrowRotation = true;
            rotateImg();
        }

        accumulator++;

        if (accumulator % 4 == 0) {
            Iterator<ChessPiece> iterator = board.getChessList().getChessPiece().iterator();
            while (iterator.hasNext()) {
                ChessPiece cp = iterator.next();
                int positionX = cp.getChessPositionX();
                int positionY = cp.getChessPositionY();
                Player player = cp.getChessOwner();
                switch (cp.getChessName()) {
                    case "Blue Triangle":
                        iterator.remove();
                        temp.add(new Plus("Blue Plus", "image/BluePlus.png", positionX, positionY, player));
                        break;
                    case "Blue Plus":
                        iterator.remove();
                        temp.add(new Triangle("Blue Triangle", "image/BlueTriangle.png", positionX, positionY, player));
                        break;
                    case "Red Triangle":
                        iterator.remove();
                        temp.add(new Plus("Red Plus", "image/RedPlus.png", positionX, positionY, player));
                        break;
                    case "Red Plus":
                        iterator.remove();
                        temp.add(new Triangle("Red Triangle", "image/RedTriangle.png", positionX, positionY, player));
                        break;
                }
            }
            board.getChessList().getChessPiece().addAll(temp);
        }


    }

    /**
     * @param imgPath The chess piece's image path.
     */
    public void setChessImage(String imgPath) {
        this.chessImgPath = imgPath;
        try {
            bufferedImage = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return The chess piece's actual image.
     */
    public BufferedImage getChessImg() {
        return this.bufferedImage;
    }

    /**
     * This is the method that changes the chess piece's image to a rotated version or vice versa.
     * @author Nicholas Chee Jian Shen
     */
    public void rotateImg() {

        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bufferedImage.getType());

        if(!chessImgPath.contains("Rev")){ //If the image is not rotated
            chessImgPath = chessImgPath.substring(0, chessImgPath.length()-4) + "Rev.png";
        }
        else{ //If the image is rotated
            chessImgPath = chessImgPath.substring(0, chessImgPath.length()-7) + ".png";
        }

        try {
            rotated = ImageIO.read(new File(chessImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bufferedImage = rotated;

    }

    /**
     * @return The chess piece's image path.
     */
    public String getImgPath() {
        return this.chessImgPath;
    }

    /**
     * @return The chess piece's possible moves.
     */
    public ArrayList<int[]> getPossibleMovesArray() {
        return this.possibleMovesArray;
    }

    /**
     * This method is supposed to be abstract as it will be overridden in the subclasses.
     * However, JSON cannot support abstract class.
     * @return null
     */
    public ArrayList<int[]> generatePossibleMoves() {
        return null;
    }

    /**
     * This is the method that flips the position of the chess piece when a successful chess move is detected.
     * @author Chan Jin Xuan
     */
    @Override
    public void flipPosition() {
        this.chessPositionX = 6 - chessPositionX;
        this.chessPositionY = 7 - chessPositionY;
        rotateImg();
    }

    @Override
    public void addObserver(Observer observer) { }

    @Override
    public void removeObserver(Observer observer) { }

    @Override
    public void notifyObserver() { }
}
