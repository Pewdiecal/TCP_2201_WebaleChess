import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

/**
 * This is the MainUI class that responsible to display
 * the main menu UI and the gameplay UI.
 *
 * @version 1.0
 * @since 2020-09-18
 */
public class MainUI {
    Controller controller;
    final int BOARD_WIDTH = 7;
    final int BOARD_HEIGHT = 8;

    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
        mainUI.initView(mainUI); //initialize the menu screen
    }

    /* Author: Lau Yee Keen Calvin
       Desc: initialize and inflate the main menu screen
     */
    public void initView(MainUI mainUI) {
        JFrame viewHolder = new JFrame();
        controller = new Controller(mainUI, viewHolder);
        GridBagConstraints constraints = new GridBagConstraints();
        viewHolder.setSize(400, 600);
        viewHolder.setLayout(new GridBagLayout());

        //set the layout
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 20;
        constraints.ipady = 20;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.insets = new Insets(10, 10, 10, 10);

        //Init the image icon for main menu.
        ImageIcon image = new ImageIcon("image/chess_menu.png");
        JLabel imageLogo = new JLabel();
        imageLogo.setIcon(image);
        imageLogo.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridy = 0;
        viewHolder.add(imageLogo, constraints);

        //Main menu title
        JLabel menuLabel = new JLabel("Webale Chess", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        constraints.gridy = 1;
        viewHolder.add(menuLabel, constraints);

        //New Game Button
        JButton newGameBtn = new JButton("New Game");
        constraints.gridy = 2;
        newGameBtn.addActionListener(e -> {
            controller.createNewGame();
            viewHolder.dispose();
        });
        viewHolder.add(newGameBtn, constraints);

        //Continue Game Button
        JButton continueGameBtn = new JButton("Continue Game");
        constraints.gridy = 3;
        continueGameBtn.addActionListener(e -> {
            controller.continueGame();
            viewHolder.dispose();
        });
        viewHolder.add(continueGameBtn, constraints);

        //Help button
        JButton gameInstrucBtn = new JButton("Help");
        constraints.gridy = 4;
        gameInstrucBtn.addActionListener(e -> displayHelp(viewHolder));
        viewHolder.add(gameInstrucBtn, constraints);

        viewHolder.setVisible(true);
    }

    /* Author: Lau Yee Keen Calvin
       Desc: initialize and inflate the gameplay UI.
     */
    public JButton[][] initGameView() {

        //Cross platform look and feel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame viewHolder = new JFrame();
        GridBagConstraints constraints = new GridBagConstraints();
        viewHolder.setSize(1100, 800);
        viewHolder.setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 5;
        constraints.ipady = 5;
        constraints.insets = new Insets(5, 5, 5, 5);


        JLabel userTurnName = new JLabel();
        userTurnName.setFont(new Font("Calibri", Font.BOLD, 30));
        userTurnName.setText("Current Turn : " + controller.getCurrentPlayerTurn());
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.weightx = 1;
        userTurnName.setMaximumSize(new Dimension(100, 100));
        viewHolder.add(userTurnName, constraints);

        JButton exitBtn = new JButton("Exit Game");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.gridwidth = 2;
        exitBtn.addActionListener(e -> {
            displayConfirmDialog(viewHolder);
        });
        viewHolder.add(exitBtn, constraints);

        JButton[][] chessHolder = new JButton[BOARD_HEIGHT][BOARD_WIDTH];
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.ipady = 100;
        constraints.ipadx = 10;
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int w = 0; w < BOARD_WIDTH; w++) {
                chessHolder[i][w] = new JButton();
                chessHolder[i][w].setMinimumSize(new Dimension(210, 210));
                chessHolder[i][w].setBackground(new Color(204, 255, 255));
                int finalX = w; //X
                int finalY = i; //Y
                chessHolder[i][w].addActionListener(e -> {

                    if (controller.getCurrentSelectedPosition()[0][0] == -1 ||
                            controller.getCurrentSelectedPosition()[0][1] == -1) {
                        controller.setCurrentSelectedPosition(finalX, finalY);
                        chessHolder[finalY][finalX].setBackground(new Color(153, 255, 153));
                        for (int[] positions : controller.getPossibleMoves(finalX, finalY)) {
                            chessHolder[positions[1]][positions[0]].setBackground(new Color(153, 255, 153));
                        }
                    } else {
                        controller.setChessPosition(finalX, finalY);
                        userTurnName.setText("Current Turn : " + controller.getCurrentPlayerTurn());
                        controller.checkGameWinner(viewHolder);
                    }

                });

                chessHolder[i][w].addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        super.componentResized(e);
                        JButton btn = (JButton) e.getComponent();
                        Dimension size = btn.getSize();
                        Insets insets = btn.getInsets();
                        size.width -= insets.left + insets.right;
                        size.height -= insets.top + insets.bottom;
                        if (size.width > size.height) {
                            size.width = -1;
                        } else {
                            size.height = -1;
                        }
                        if (controller.getChessImg(finalX, finalY) != null) {
                            Image scaled = controller.getChessImg(finalX, finalY)
                                    .getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
                            btn.setIcon(new ImageIcon(scaled));
                        } else {
                            btn.setIcon(null);
                        }
                    }
                });

                viewHolder.add(chessHolder[i][w], constraints);
                constraints.gridx++;
            }
            constraints.gridx = 0;
            constraints.gridy++;
        }
        viewHolder.setVisible(true);
        return chessHolder;
    }

    public String displayDialog(JFrame viewHolder, int i) {
        return JOptionPane.showInputDialog(viewHolder, "Enter Player " + i + " name");
    }

    public void displayHelp(JFrame viewHolder) {
        JOptionPane.showMessageDialog(viewHolder, controller.getHelp());
    }

    public void displayFileNotFound(JFrame viewHolder) {
        JOptionPane.showMessageDialog(viewHolder, "No game file found. Loading new game.");
    }

    private void displayConfirmDialog(JFrame viewHolder) {
        int a = JOptionPane.showConfirmDialog(viewHolder, "Do you want to save the progress?");
        if (a == JOptionPane.YES_OPTION) {
            controller.saveState();
        }
        viewHolder.dispatchEvent(new WindowEvent(viewHolder, WindowEvent.WINDOW_CLOSING));
    }
}
