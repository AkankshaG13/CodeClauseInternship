import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    
    public tictactoe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setLocationRelativeTo(null);
        
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        
        initializeBoard();
        
        setVisible(true);
    }
    
    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }
    
    private void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    private boolean hasWon(char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(player))
                    && buttons[row][1].getText().equals(String.valueOf(player))
                    && buttons[row][2].getText().equals(String.valueOf(player))) {
                return true;
            }
        }
        
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(player))
                    && buttons[1][col].getText().equals(String.valueOf(player))
                    && buttons[2][col].getText().equals(String.valueOf(player))) {
                return true;
            }
        }
        
        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(player))
                && buttons[1][1].getText().equals(String.valueOf(player))
                && buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }
        
        if (buttons[0][2].getText().equals(String.valueOf(player))
                && buttons[1][1].getText().equals(String.valueOf(player))
                && buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }
        
        return false;
    }
    
    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        
        if (button.getText().isEmpty()) {
            button.setText(String.valueOf(currentPlayer));
            
            if (hasWon(currentPlayer)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a tie!");
                resetGame();
            } else {
                togglePlayer();
            }
        }
    }
    
    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
 }
}