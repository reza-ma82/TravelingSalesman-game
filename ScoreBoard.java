import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreBoard extends Frame implements ActionListener {

    static JLabel player1Name = new JLabel("Player 1");
    static JLabel player1Power =new JLabel();
    static JLabel player1Money =new JLabel();
    static JLabel player1Score = new JLabel();
    static JLabel player2Name = new JLabel("Player 2");
    static JLabel player2Power = new JLabel();
    static JLabel player2Money = new JLabel();
    static JLabel player2Score = new JLabel();
    static Stopwatch timer= new Stopwatch();
    static JLabel quest = new JLabel();
    static JLabel currentCell = new JLabel();
    static JLabel playerTurn= new JLabel();
    static JLabel completedQuests=new JLabel();

    public ScoreBoard(int x, int y) {
        super(x, y);

        setBounds(x,y,200,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        player1Name.setBounds(10,10,80,20);
        player1Score.setBounds(10,30,80,20);
        player1Money.setBounds(10,50,80,20);
        player1Power.setBounds(10,70,80,20);

        player2Name.setBounds(10,100,80,20);
        player2Score.setBounds(10,120,80,20);
        player2Money.setBounds(10,140,80,20);
        player2Power.setBounds(10,160,80,20);

        timer.timeLabel.setLocation(10,190);
        quest.setBounds(10,230,150,20);
        currentCell.setBounds(10,250,180,20);
        playerTurn.setBounds(10,270,150,20);
        completedQuests.setBounds(10,290,150,20);

        player1Score.setText("Score: "+ GameManager.player1.getScore());
        player1Money.setText("Money: "+ GameManager.player1.getMoney());
        player1Power.setText("Power: "+ GameManager.player1.getPower());

        player2Score.setText("Score: "+ GameManager.player2.getScore());
        player2Money.setText("Money: "+ GameManager.player2.getMoney());
        player2Power.setText("Power: "+ GameManager.player2.getPower());

        quest.setText("Quest: "+Board.questItems[Board.Treasure.num].name);
        playerTurn.setText("Turn: "+Player.turn);
        completedQuests.setText("Completed Quests: "+ (8-Board.Treasure.num));

        add(player1Name);
        add(player1Score);
        add(player1Money);
        add(player1Power);

        add(player2Name);
        add(player2Score);
        add(player2Money);
        add(player2Power);

        add(timer.timeLabel);
        add(quest);
        add(currentCell);
        add(playerTurn);
        add(completedQuests);

        GameManager.scoreBoard.addActionListener(this);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==GameManager.scoreBoard){
            dispose();
            e.setSource(null);
            new ScoreBoard(getX(),getY()).setVisible(true);
        }
    }
}