import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Controller {

    private MainUI mainUI;
    private JFrame viewHolder;
    private Board board = Board.getInstance();
    private FileManager fileManager = new FileManager();
    private int[][] currentSelectedPosition = {{-1, -1}};
    private JButton[][] chessHolder;

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
        String player1 = mainUI.displayDialog(viewHolder, 1);
        String player2;
        if (player1 != null) {
            player2 = mainUI.displayDialog(viewHolder, 2);
            if (player2 != null) {
                board.addPlayer(player1, 0);
                board.addPlayer(player2, 1);
                board.reloadNewState();
                chessHolder = mainUI.initGameView();
                viewHolder.dispose();
            }
        }
    }

    public void restartGame() {
        setCurrentSelectedPosition(-1, -1);
        createNewGame();
    }

    public void continueGame() {
        try {
            board.loadState();
            viewHolder.dispose();
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
        return "Sun          - It can only move one step in any " +
                "direction. The game\n                   ends when the Sun is captured by the other side.\n" +
                "\n" +
                "Chevron  - It moves in an L shape: 2 steps in one direction then\n                    " +
                "1 step perpendicular to it. It is the only piece that can\n                    skip over the " +
                "other pieces.\n" +
                "\n" +
                "Triangle   - It can move any number of steps diagonally.\n" +
                "\n" +
                "Plus          - It can move any number of steps up and down, or left and right.\n" +
                "\n" +
                "Arrow        - It can only move 1 or 2 steps forward each time, but when it " +
                "reaches\n                    the other edge of the board, it turns around and heads back in " +
                "the\n                    opposite direction.\n";
    }

    public void checkGameWinner(JFrame viewHolder) {

        if (board.getWinner() != null) {
            JOptionPane.showMessageDialog(this.viewHolder, "Winner is " + board.getWinner().getPlayerName());
            try {
                fileManager.deleteAllSaveFile();
            } catch (NoSuchFileException noSuchFileException) {
                System.out.println("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            viewHolder.dispatchEvent(new WindowEvent(viewHolder, WindowEvent.WINDOW_CLOSING));
            board.nullifyWinner();
            mainUI.initView(mainUI);
        }
    }

    public String getCurrentPlayerTurn() {
        return board.getCurrentPlayerName();
    }

    public int[][] getPossibleMoves(int x, int y) {
        return board.getPossibleMoves(x, y);
    }

    public void deleteAllSaved() {
        try {
            fileManager.deleteAllSaveFile();
        } catch (NoSuchFileException noSuchFileException) {
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
