package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    JPanel upperPanel = new JPanel();
    JPanel lowerPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JButton[] buttons = new JButton[9];

    TicTacToe() {
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBackground(Color.black);
        upperPanel.setForeground(Color.white);
        upperPanel.setBounds(0,0,800,100);

        lowerPanel.setLayout(new GridLayout(3,3));
        lowerPanel.setSize(800, 600);

        // Visit https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/layout/visual.html
        // for other layouts.


        for (int i=0;i<9;i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            lowerPanel.add(buttons[i]);
        }

        textLabel.setText("Tic-Tac-Toe");
        textLabel.setFont(new Font("AR Darling",Font.BOLD, 60));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true); // Makes the background opaque.
        textLabel.setSize(800,200);

        upperPanel.add(textLabel);
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(lowerPanel);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
