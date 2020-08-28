import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

abstract class ChessPiece implements Chess, Observable {
    private int chessPositionX;
    private int chessPositionY;
    private final String chessName;
    private String chessImgPath;
    private transient BufferedImage bufferedImage;
    private boolean isOnBoard = true;
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
        this.chessPositionX = x;
        this.chessPositionY = y;
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
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(180), w / 2, h / 2);
        graphic.drawImage(bufferedImage, null, 0, 0);
        graphic.dispose();
        bufferedImage = rotated;
    }

    public String getImgPath() {
        return this.chessImgPath;
    }

    public ArrayList<int[]> getPossibleMovesArray() {
        return this.possibleMovesArray;
    }

    public abstract ArrayList<int[]> setPossibleMoves();

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
