import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

    MainUI mainUI;
    JFrame viewHolder;
    Board board = Board.getInstance();
    int[][] currentSelectedPosition = {{-1, -1}};
    JButton[][] chessHolder;

    Controller(MainUI mainUI, JFrame viewHolder) {
        this.mainUI = mainUI;
        this.viewHolder = viewHolder;
    }

    public void setCurrentSelectedPosition(int x, int y) {
        currentSelectedPosition[0][0] = x;
        currentSelectedPosition[0][1] = y;
    }

    public int[][] getCurrentSelectedPosition() {
        return currentSelectedPosition;
    }

    public void setChessPosition(int x, int y) {
        board.moveChess(getCurrentSelectedPosition()[0][0], getCurrentSelectedPosition()[0][1], x, y);
        refreshGameBoard();
    }

    private void refreshGameBoard() {
        setCurrentSelectedPosition(-1, -1);
        for (JButton[] jButtons : chessHolder) {
            for (JButton jButton : jButtons) {
                jButton.setBackground(new Color(204, 255, 255));
            }
        }
        for (int height = 0; height < chessHolder.length; height++) {
            for (int width = 0; width < chessHolder[height].length; width++) {
                if (getChessImg(width, height) != null) {
                    Dimension size = chessHolder[height][width].getSize();
                    Insets insets = chessHolder[height][width].getInsets();
                    size.width -= insets.left + insets.right;
                    size.height -= insets.top + insets.bottom;
                    if (size.width > size.height) {
                        size.width = -1;
                    } else {
                        size.height = -1;
                    }
                    Image scaled = getChessImg(width, height)
                            .getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
                    chessHolder[height][width].setIcon(new ImageIcon(scaled));
                } else {
                    chessHolder[height][width].setIcon(null);
                }
            }
        }
    }

    public void createNewGame() {
        board.addPlayer(mainUI.displayDialog(viewHolder, 1));
        board.addPlayer(mainUI.displayDialog(viewHolder, 2));
        board.reloadNewState();
        chessHolder = mainUI.initGameView();
    }

    public void continueGame() {
        try {
            board.loadState();
            chessHolder = mainUI.initGameView();
        } catch (FileNotFoundException e) {
            mainUI.displayFileNotFound(viewHolder);
            createNewGame();
        }
    }

    public void saveState() {
        try {
            board.saveState();
        } catch (IOException e) {
            mainUI.displayFileNotFound(viewHolder);
        }
    }

    public BufferedImage getChessImg(int x, int y) {
        return board.getChessPieceImg(x, y);
    }

    public String getHelp() {
        return "";
    }

    public String getCurrentPlayerTurn() {
        return board.getCurrentPlayerName();
    }

    public int[][] getPossibleMoves(int x, int y) {
        return board.getPossibleMoves(x, y);
    }
}
