import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ChessPiece implements Chess, Observable {
    private static int accumulator;

    public Board getBoard() {
        return board;
    }

    private final Board board = Board.getInstance();
    private int chessPositionX;
    private int chessPositionY;
    private final String chessName;
    private String chessImgPath;
    private transient BufferedImage bufferedImage;
    private boolean isOnBoard = true;
    private boolean arrowRotation = false;
    private Player chessOwner;
    private final ArrayList<int[]> possibleMovesArray = new ArrayList<>();

    ChessPiece(String chessName, String chessImgPath, int chessPositionX, int chessPositionY, Player chessOwner) {
        this.chessName = chessName;
        this.chessPositionX = chessPositionX;
        this.chessPositionY = chessPositionY;
        this.chessOwner = chessOwner;
        this.chessImgPath = chessImgPath;
        //read in the img of the chess
        try {
            bufferedImage = ImageIO.read(new File(chessImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //rotate the img from the opposite site for 180 degree
        if (!chessOwner.getPlayerTurn()) {
            rotateImg();
        }
    }

    public int getChessPositionX() {
        return this.chessPositionX;
    }

    public int getChessPositionY() {
        return this.chessPositionY;
    }

    public boolean getArrowRotation() {
        return this.arrowRotation;
    }

    public String getChessName() {
        return this.chessName;
    }

    public boolean isOnBoard() {
        return this.isOnBoard;
    }

    public void setIsOnBoard(boolean isOnBoard) {
        this.isOnBoard = isOnBoard;
    }

    public Player getChessOwner() {
        return this.chessOwner;
    }

    public void setChessOwner(Player player) {
        this.chessOwner = player;
    }

    public void setChessPosition(int x, int y) {
        ArrayList<ChessPiece> temp = new ArrayList<>();
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

        this.chessPositionX = x;
        this.chessPositionY = y;
        if (y == 7) {
            this.arrowRotation = false;
        } else if (y == 0) {
            this.arrowRotation = true;
        }
    }

    public void setChessImage(String imgPath) {
        this.chessImgPath = imgPath;
        try {
            bufferedImage = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getChessImg() {
        return this.bufferedImage;
    }

    //img rotation func << its done
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

    public String getImgPath() {
        return this.chessImgPath;
    }

    public ArrayList<int[]> getPossibleMovesArray() {
        return this.possibleMovesArray;
    }

    public ArrayList<int[]> generatePossibleMoves() {
        return null;
    }

    @Override
    public void flipPosition() {
        //Flip the position of this single chess when a successful chess move is detected
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObserver() {

    }
}
