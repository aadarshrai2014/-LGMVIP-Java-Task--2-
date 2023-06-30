import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[] buttons;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        currentPlayer = 'X';
        gameOver = false;

        initializeButtons();
        pack();
        setLocationRelativeTo(null); // Center the window
    }

    public void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton("-");
            button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 48));
            button.addActionListener(new ButtonClickListener());
            buttons[i] = button;
            add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            int index = -1;

            for (int i = 0; i < 9; i++) {
                if (buttons[i] == button) {
                    index = i;
                    break;
                }
            }

            if (!gameOver && button.getText().equals("-")) {
                button.setText(Character.toString(currentPlayer));
                button.setEnabled(false);
                checkWinner(currentPlayer);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    public void checkWinner(char player) {
        // Check rows
        if (checkRow(0, 1, 2, player) || checkRow(3, 4, 5, player) || checkRow(6, 7, 8, player)) {
            gameOver = true;
            displayWinner(player);
            return;
        }

        // Check columns
        if (checkRow(0, 3, 6, player) || checkRow(1, 4, 7, player) || checkRow(2, 5, 8, player)) {
            gameOver = true;
            displayWinner(player);
            return;
        }

        // Check diagonals
        if (checkRow(0, 4, 8, player) || checkRow(2, 4, 6, player)) {
            gameOver = true;
            displayWinner(player);
        }
    }

    public boolean checkRow(int i, int j, int k, char player) {
        String val1 = buttons[i].getText();
        String val2 = buttons[j].getText();
        String val3 = buttons[k].getText();

        return (val1.equals(val2) && val2.equals(val3) && val3.equals(Character.toString(player)));
    }

    public void displayWinner(char player) {
        JOptionPane.showMessageDialog(this, "Player " + player + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToeGUI game = new TicTacToeGUI();
                game.setVisible(true);
            }
        });
    }
}


