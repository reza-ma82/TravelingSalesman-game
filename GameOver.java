import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends Frame implements ActionListener {
    JLabel message = new JLabel();
    JLabel win = new JLabel();
    JButton restartGame = new JButton();
    JButton returnMainMenu = new JButton();
    JButton exitGame = new JButton();
    GameOver(int x, int y) {
        super(x, y);

        message.setSize(514,60);
        message.setText("Game is Over!");
        message.setFont(new Font("Cooper Black",Font.BOLD,45));
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setLocation(message.getX(),50);

        win.setSize(514,60);
        if (Player.winner==1)
            win.setText(GameManager.player1.getName()+" won");
        else if (Player.winner==2)
            win.setText(GameManager.player1.getName()+" won");
        win.setFont(new Font("Cooper Black",Font.BOLD,40));
        win.setHorizontalAlignment(SwingConstants.CENTER);
        win.setLocation(win.getX(),130);

        restartGame.setSize(300,60);
        restartGame.setText("Restart the Game");
        restartGame.setFont(new Font("Cooper Black",Font.BOLD,25));
        restartGame.setLocation(getWidth()/2 - 150,260);
        restartGame.setFocusable(false);
        restartGame.addActionListener(this);

        returnMainMenu.setSize(300,60);
        returnMainMenu.setText("Return to MainMenu");
        returnMainMenu.setFont(new Font("Cooper Black",Font.BOLD,23));
        returnMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
        returnMainMenu.setLocation(getWidth()/2 - 150,330);
        returnMainMenu.setFocusable(false);
        returnMainMenu.addActionListener(this);

        exitGame.setSize(300,60);
        exitGame.setText("Exit Game");
        exitGame.setFont(new Font("Cooper Black",Font.BOLD,25));
        exitGame.setHorizontalAlignment(SwingConstants.CENTER);
        exitGame.setLocation(getWidth()/2 - 150,400);
        exitGame.setFocusable(false);
        exitGame.addActionListener(this);

        add(message);
        add(win);
        add(restartGame);
        add(returnMainMenu);
        add(exitGame);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==restartGame){
            dispose();
            GameManager.resetValues();
            new GameManager(getX(),getY());
            new ScoreBoard(this.getX()+this.width,this.getY());
            new MarketFrame(this.getX(),this.getY());
            ScoreBoard.player1Name.setBackground(Color.cyan);
        }
        else if (e.getSource()==returnMainMenu){
            dispose();
            e.setSource(null);
            new GameMenu(getX(),getY());
        }
        else if (e.getSource()==exitGame){
            dispose();
        }
    }
}
