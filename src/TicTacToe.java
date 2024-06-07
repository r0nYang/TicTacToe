package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe extends JFrame implements ActionListener {
    CardLayout cl;
    JPanel panelContainer;
    JPanel gamePanel;
    JPanel menuScreen;
    JPanel upperPanel;
    JPanel lowerPanel;
    JLabel textLabel;
    JButton[] buttons;
    JButton restartButton;
    JButton playButton;
    boolean xTurn;
    Random random = new Random();
    int cd = 1500;
    int numberOfPicks = 0;
    // TODO add checker for when game is over. Start screen to pick if you want infinite mode? If not infinite mode,
    // have end screen so user can press restart.
    // TODO ADD A BUTTON TO RETURN TO MENU

    TicTacToe() {
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeMenu();
        gamePanel = new JPanel();
        panelContainer = new JPanel();
        cl = new CardLayout();
        gamePanel.setLayout(new BorderLayout());
        panelContainer.setLayout(cl);
        panelContainer.add(gamePanel, "2");
        panelContainer.add(menuScreen, "1");

        initializeLowerPanel();
        initializeTextLabel();
        initializeRestartButton();
        initializeUpperPanel();

        cl.show(panelContainer, "1");
        this.setVisible(true);
        this.add(panelContainer);
        chooseStarter();
        UIManager.put("Button.select", Color.white); // When you click on the button, it no longer makes it blue.
    }

    private void initializeMenu() {
        menuScreen = new JPanel();
        menuScreen.setBackground(Color.white);
        playButton = new JButton();
        playButton.setPreferredSize(new Dimension(150,50));
        playButton.setText("Regular Mode");
        playButton.setFocusable(false);
        // https://stackoverflow.com/questions/5714214/set-size-wont-work-in-java for why setSize didn't work.
        playButton.addActionListener(this);
        menuScreen.add(playButton);
    }

    private void initializeUpperPanel() {
        upperPanel = new JPanel();

        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBackground(Color.black);
        upperPanel.setForeground(Color.white);
        upperPanel.setBounds(0, 0, 800, 100);
        upperPanel.add(restartButton, BorderLayout.EAST);
        upperPanel.add(textLabel);
        gamePanel.add(upperPanel, BorderLayout.NORTH);
    }


    private void initializeRestartButton() {
        restartButton = new JButton();
        restartButton.setText("Restart Game");
        restartButton.addActionListener(this);
        restartButton.setSize(125,25);
        restartButton.setFocusable(false);
        restartButton.setFocusPainted(false);
        restartButton.setBorderPainted(false);
        restartButton.setBackground(Color.white);
    }

    private void chooseStarter() {
        restartButton.setEnabled(false);
        try { // Adds delay for "Tic-Tac-Toe" to show up before showing whose turns it is
            Thread.sleep(cd);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        xTurn = random.nextBoolean();
        if (xTurn) {
            textLabel.setText("X's turn");
        } else {
            textLabel.setText("O's turn");
        }
        buttonStatus(true);
    }

    private void buttonStatus(boolean b) {
        for (int i=0;i<9;i++) {
            buttons[i].setEnabled(b);
        }
    }

    private void initializeTextLabel() {
        textLabel = new JLabel();
        textLabel.setBackground(Color.white);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setFont(new Font("AR Darling", Font.BOLD, 60));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true); // Makes the background opaque.
    }

    //EFFECTS: Creates a panel with 9 buttons added to it.
    private void initializeLowerPanel() {
        lowerPanel = new JPanel();
        buttons = new JButton[9];
        lowerPanel.setLayout(new GridLayout(3, 3));

        // Visit https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/layout/visual.html
        // for other layouts.

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("AR Darling", Font.BOLD, 60));
            buttons[i].setBackground(Color.white);
            lowerPanel.add(buttons[i]);
        }
        gamePanel.add(lowerPanel);
    }

    private boolean checkForGameWin() {
        int[][] winningCombinations = new int[][]{
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
        };

        for (int i=0;i<8;i++) {
            int[] currentArray = winningCombinations[i];
            String a = buttons[currentArray[0]].getText();
            String b = buttons[currentArray[1]].getText();
            String c = buttons[currentArray[2]].getText();
            if (a.equals(b) && a.equals(c) && !a.isEmpty()) {
                if (a.equals("X")) {
                    xWin(currentArray);
                } else {
                    oWin(currentArray);
                }
                restartButton.setEnabled(true);
                return true;
            }
        }
        return false;
    }

    private void oWin(int[] currentArray) {
        int i = currentArray[0];
        int i1 = currentArray[1];
        int i2 = currentArray[2];
        buttons[i].setBackground(Color.GREEN);
        buttons[i1].setBackground(Color.GREEN);
        buttons[i2].setBackground(Color.GREEN);
        textLabel.setText("O wins");
        buttonStatus(false);
    }

    private void xWin(int[] currentArray) {
        int i = currentArray[0];
        int i1 = currentArray[1];
        int i2 = currentArray[2];
        buttons[i].setBackground(Color.RED);
        buttons[i1].setBackground(Color.RED);
        buttons[i2].setBackground(Color.RED);
        textLabel.setText("X wins");
        buttonStatus(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            cl.show(panelContainer, "2");
        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText().isEmpty()) {
                if (xTurn) {
                    buttons[i].setText("X");
                    buttons[i].setForeground(Color.RED);
                    xTurn = false;
                    textLabel.setText("O's turn");
                } else {
                    buttons[i].setText("O");
                    buttons[i].setForeground(Color.GREEN);
                    xTurn = true;
                    textLabel.setText("X's turn");
                }
                numberOfPicks++;
            }
        }
        if(!checkForGameWin() && numberOfPicks == 9) {
            textLabel.setText("Tie");
            buttonStatus(false);
            restartButton.setEnabled(true);
        }
        if (e.getSource()== restartButton) {
            restartGame();
        }
    }

    private void restartGame() {
        for (int i = 0;i<9;i++) {
            buttons[i].setText("");
            buttons[i].setBackground(Color.white);
        }
        cd=300;
        chooseStarter();
        numberOfPicks=0;
    }
}
