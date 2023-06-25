import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends Frame implements ActionListener {
    public MenuButton startNewGame;
    public MenuButton continueGame;
    public MenuButton setting;
    public MenuButton exit;

    public GameMenu(int x,int y){
        super(x,y);

        startNewGame = new MenuButton((this.getWidth()/2)-(MenuButton.width/2),160,"Start New Game");
        continueGame = new MenuButton((this.getWidth()/2)-(MenuButton.width/2),50,"Continue Game");
        setting = new MenuButton((this.getWidth()/2)-(MenuButton.width/2),270,"Setting");
        exit = new MenuButton((this.getWidth()/2)-(MenuButton.width/2),380,"Exit");

        this.add(startNewGame);
        this.add(continueGame);
        this.add(setting);
        this.add(exit);

        startNewGame.addActionListener(this);
        continueGame.addActionListener(this);
        setting.addActionListener(this);
        exit.addActionListener(this);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startNewGame){
            dispose();
            new GameManager(this.getX(),this.getY());
            new ScoreBoard(this.getX()+this.width,this.getY());
            new MarketFrame(this.getX(),this.getY());
            ScoreBoard.player1Name.setBackground(Color.cyan);
        }else if (e.getSource()==continueGame){

        }else if (e.getSource()==setting){

        }else if (e.getSource()==exit){
            dispose();
        }
    }

    class MenuButton extends JButton{
        static int height = 80;
        static int width = 200;
        int x,y;
        String content;
        MenuButton(int x,int y,String content){
            this.x = x;
            this.y = y;
            this.content=content;
            this.setBounds(x,y,width,height);
            this.setText(content);
            this.setBackground(Color.CYAN);
            this.setForeground(Color.BLACK);
            this.setFocusable(false);
            this.setHorizontalTextPosition(CENTER);
            this.setVerticalTextPosition(CENTER);
        }
    }
}
