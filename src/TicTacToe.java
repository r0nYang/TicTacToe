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
    boolean xTurn;
    Random random = new Random();

    TicTacToe() {
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeLowerPanel();
        initializeTextLabel();
        initializeUpperPanel();

        this.setVisible(true);
        setUp();
    }

    private void initializeUpperPanel() {
        upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBackground(Color.black);
        upperPanel.setForeground(Color.white);
        upperPanel.setBounds(0, 0, 800, 100);
        upperPanel.add(textLabel);
        this.add(upperPanel, BorderLayout.NORTH);
    }

    private void setUp() {
        try { // Adds delay for "Tic-Tac-Toe" to show up before showing whose turns it is
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        xTurn = random.nextBoolean();
        if (xTurn) {
            textLabel.setText("X turn");
        } else {
            textLabel.setText("O turn");
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
        textLabel.setSize(800, 200); // NEED to set font size.
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
            lowerPanel.add(buttons[i]);
        }
        this.add(lowerPanel);
    }

    private void checkForGameOver() {
        if (buttons[0].getText().equals(buttons[1].getText())
                && buttons[0].getText().equals(buttons[2].getText())
                && !buttons[0].getText().equals("")) {
            if (buttons[0].getText().equals("X")) {
                xWin(0, 1, 2);
            } else if (buttons[0].getText().equals("O")) {
                oWin(0, 1, 2);
            }
        } else if (buttons[3].getText().equals(buttons[4].getText())
                && buttons[3].getText().equals(buttons[5].getText())
                && !buttons[3].getText().equals("")) {
            if (buttons[3].getText().equals("X")) {
                xWin(3, 4, 5);
            } else if (buttons[3].getText().equals("O")) {
                oWin(3, 4, 5);
            }
        } else if (buttons[6].getText().equals(buttons[7].getText())
                && buttons[6].getText().equals(buttons[8].getText())
                && !buttons[6].getText().equals("")) {
            if (buttons[6].getText().equals("X")) {
                xWin(6, 7, 8);
            } else if (buttons[6].getText().equals("O")) {
                oWin(6, 7, 8);
            }
        } else if (buttons[0].getText().equals(buttons[3].getText())
                && buttons[3].getText().equals(buttons[6].getText())
                && !buttons[0].getText().equals("")) {
            if (buttons[6].getText().equals("X")) {
                xWin(0, 3, 6);
            } else if (buttons[6].getText().equals("O")) {
                oWin(0, 3, 6);
            }
        } else if (buttons[1].getText().equals(buttons[4].getText())
                && buttons[4].getText().equals(buttons[7].getText())
                && !buttons[1].getText().equals("")) {
            if (buttons[4].getText().equals("X")) {
                xWin(1, 4, 7);
            } else if (buttons[4].getText().equals("O")) {
                oWin(1, 4, 7);
            }
        } else if (buttons[2].getText().equals(buttons[5].getText())
                && buttons[5].getText().equals(buttons[8].getText())
                && !buttons[2].getText().equals("")) {
            if (buttons[2].getText().equals("X")) {
                xWin(2,5,8);
            } else if (buttons[2].getText().equals("O")) {
                oWin(2,5,8);
            }
        } else if (buttons[0].getText().equals(buttons[4].getText())
                && buttons[4].getText().equals(buttons[8].getText())
                && !buttons[0].getText().equals("")) {
            if (buttons[4].getText().equals("X")) {
                xWin(0,4,8);
            } else if (buttons[4].getText().equals("O")) {
                oWin(0,4,8);
            }
        } else if (buttons[2].getText().equals(buttons[4].getText())
                && buttons[4].getText().equals(buttons[6].getText())
                && !buttons[2].getText().equals("")) {
            if (buttons[2].getText().equals("X")) {
                xWin(2,4,6);
            } else if (buttons[2].getText().equals("O")) {
                oWin(2,4,6);
            }
        }

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
            if (e.getSource() == buttons[i]) {
                if (xTurn) {
                    buttons[i].setText("X");
                    buttons[i].setForeground(Color.RED);
                    xTurn = false;
                    textLabel.setText("O turn");
                } else {
                    buttons[i].setText("O");
                    buttons[i].setForeground(Color.GREEN);
                    xTurn = true;
                    textLabel.setText("X turn");
                }
            }
        }
        checkForGameOver();
    }

}
