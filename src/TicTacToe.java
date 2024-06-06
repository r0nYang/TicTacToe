package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe extends JFrame implements ActionListener {
    JPanel upperPanel;
    JPanel lowerPanel;
    JLabel textLabel;
    JButton[] buttons;
    JButton restartButton;
    boolean xTurn;
    Random random = new Random();
    int cd = 1500;

    boolean infiniteMode=false;
    int numberOfPicks = 0;
    // TODO add checker for when game is over. Start screen to pick if you want infinite mode? If not infinite mode,
    // have end screen so user can press restart.

    TicTacToe() {
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeLowerPanel();
        initializeTextLabel();
        initializeRestartButton();
        initializeUpperPanel();

        this.setVisible(true);
        chooseStarter();
        UIManager.put("Button.select", Color.white); // When you click on the button, it no longer makes it blue.
    }

    private void initializeUpperPanel() {
        upperPanel = new JPanel();

        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBackground(Color.black);
        upperPanel.setForeground(Color.white);
        upperPanel.setBounds(0, 0, 800, 100);
        upperPanel.add(restartButton, BorderLayout.EAST);
        upperPanel.add(textLabel);
        this.add(upperPanel, BorderLayout.NORTH);
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
        lowerPanel.setSize(800, 600);

        // Visit https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/layout/visual.html
        // for other layouts.

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("AR Darling", Font.BOLD, 35));
            buttons[i].setBackground(Color.white);
            lowerPanel.add(buttons[i]);
        }
        this.add(lowerPanel);
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
            int firstNum = currentArray[0];
            int secondNum = currentArray[1];
            int thirdNum = currentArray[2];
            String a = buttons[firstNum].getText();
            String b = buttons[secondNum].getText();
            String c = buttons[thirdNum].getText();
            if (a.equals(b) && a.equals(c) && !a.isEmpty()) {
                if (a.equals("X")) {
                    xWin(firstNum,secondNum,thirdNum);
                } else {
                    oWin(firstNum,secondNum,thirdNum);
                }
                restartButton.setEnabled(true);
                return true;
            }
        }
        return false;
    }

    private void oWin(int i, int i1, int i2) {
        buttons[i].setBackground(Color.GREEN);
        buttons[i1].setBackground(Color.GREEN);
        buttons[i2].setBackground(Color.GREEN);
        textLabel.setText("O wins");
        buttonStatus(false);
    }

    private void xWin(int i, int i1, int i2) {
        buttons[i].setBackground(Color.RED);
        buttons[i1].setBackground(Color.RED);
        buttons[i2].setBackground(Color.RED);
        textLabel.setText("X wins");
        buttonStatus(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
