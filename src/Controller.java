import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

/**
 * This is the Controller class that acts as the interface between the class pieces and the UI components
 * It processes the logic requests in the main menu screen and during gameplay.
 *
 * @author Lau Yee Keen Calvin
 */
public class Controller {

    private MainUI mainUI;
    private JFrame viewHolder;
    private Board board = Board.getInstance();
    private FileManager fileManager = new FileManager();
    private int[][] currentSelectedPosition = {{-1, -1}};
    private JButton[][] chessHolder;

    /**
     * This is the constructor that passes the initialized mainUI and viewHolder to its attributes.
     *
     * @param mainUI     The initialized mainUI object.
     * @param viewHolder The view holder.
     * @author Lau Yee Keen Calvin
     */
    Controller(MainUI mainUI, JFrame viewHolder) {
        this.mainUI = mainUI;
        this.viewHolder = viewHolder;
    }

    /**
     * @param x The x coordinate of position to set.
     * @param y The y coordinate of position to set.
     */
    public void setCurrentSelectedPosition(int x, int y) {
        currentSelectedPosition[0][0] = x;
        currentSelectedPosition[0][1] = y;
    }

    /**
     * @return The current selected position.
     */
    public int[][] getCurrentSelectedPosition() {
        return currentSelectedPosition;
    }

    /**
     * @param x The x coordinate of position to set.
     * @param y The y coordinate of position to set.
     */
    public void setChessPosition(int x, int y) {
        board.moveChess(getCurrentSelectedPosition()[0][0], getCurrentSelectedPosition()[0][1], x, y);
        refreshGameBoard();
    }

    //@author Lau Yee Keen Calvin
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

    /**
     * This is the method that starts a new game and prompts for the new players' names.
     *
     * @author Lau Yee Keen Calvin
     */
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

    /**
     * This is the method that starts a new game as a game option during gameplay.
     *
     * @author Lau Yee Keen Calvin
     */
    public void restartGame() {
        setCurrentSelectedPosition(-1, -1);
        createNewGame();
    }

    /**
     * This is the method that continues a saved game in the main menu.
     *
     * @author Lau Yee Keen Calvin
     */
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

    /**
     * This is the method that calls the Board class to save the current board state.
     *
     * @author Lau Yee Keen Calvin
     */
    public void saveState() {
        try {
            board.saveState();
        } catch (IOException e) {
            mainUI.displayFileNotFound(viewHolder);
        }
    }

    /**
     * This is the method that returns the chess piece's image based on the position.
     *
     * @param x The x coordinate position.
     * @param y The y coordinate position.
     * @return The chess piece's image.
     * @author Lau Yee Keen Calvin
     */
    public BufferedImage getChessImg(int x, int y) {
        return board.getChessPieceImg(x, y);
    }

    /**
     * This is the method that displays the help box's message.
     *
     * @return The help box's message.
     * @author Muhammad Hidayat Bin Jauhari
     */
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

    /**
     * This is the method that displays the winning player.
     *
     * @param viewHolder The view holder.
     * @author Lau Yee Keen Calvin
     */
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

    /**
     * This is the method that returns the player that is currently in turn.
     *
     * @return The name of the player that is currently in turn.
     */
    public String getCurrentPlayerTurn() {
        return board.getCurrentPlayerName();
    }

    /**
     * This is the method that returns the possible moves by a chess piece in a certain position.
     *
     * @param x The x coordinate position.
     * @param y The y coordinate position.
     * @return The possible moves.
     */
    public int[][] getPossibleMoves(int x, int y) {
        return board.getPossibleMoves(x, y);
    }

    /**
     * This is the method that delete all existing save files.
     *
     * @author Lau Yee Keen Calvin
     */
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
