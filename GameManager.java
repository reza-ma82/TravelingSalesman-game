import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager extends Frame implements ActionListener {

    static Player player1 = new Player("Player1", new Board(true), new Piece(1, Color.blue),0,1000,400);
    static Player player2 = new Player("player2", new Board(true), new Piece(2, Color.green),0,1000,400);
    static Board map = new Board(false);
    static JButton nextTurn = new JButton();
    static JButton scoreBoard = new JButton();
    static JButton market = new JButton();

    public static void resetValues(){
        player1 = null;
        player2 = null;
        map = null;
        player1 = new Player("Player1", new Board(true), new Piece(1, Color.blue),0,1000,400);
        player2 = new Player("player2", new Board(true), new Piece(2, Color.green),0,1000,400);
        map = new Board(false);
    }
    public GameManager(int x, int y) {
        super(x, y);


        nextTurn.setBounds(300, 500, 100, 34);
        nextTurn.setText("Next Turn");
        nextTurn.setFont(new Font("Book Antiqua",Font.BOLD,13));
        nextTurn.setBackground(Color.lightGray);
        nextTurn.setFocusable(false);
        nextTurn.addActionListener(this);
        this.add(nextTurn);

        scoreBoard.setBounds(100, 500, 100, 34);
        scoreBoard.setText("Score Board");
        scoreBoard.setBackground(Color.lightGray);
        scoreBoard.setFocusable(false);
        scoreBoard.setFont(new Font("Book Antiqua",Font.BOLD,12));
        this.add(scoreBoard);

        market.setBounds(200, 500, 100, 34);
        market.setText("Open Market");
        market.setBackground(Color.lightGray);
        market.setFocusable(false);
        market.setEnabled(false);
        market.setFont(new Font("Book Antiqua",Font.BOLD,10));
        this.add(market);

        ScoreBoard.timer.start();

        if (Player.turn == 1) {
            this.add(player1.getPiece());
            this.remove(player2.getPiece());
            this.addKeyListener(player1.getPiece());
            this.removeKeyListener(player2.getPiece());
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    this.add(player1.getMap().getMapPlayer()[i][j].getPanel());
                    this.add(Board.getMap()[i][j].getPanel());
                }
            }
            this.add(player1.getDice().getDiceButton());
            this.add(player1.getDice().getDicePanel());
            this.remove(player2.getDice().getDiceButton());
            this.remove(player2.getDice().getDicePanel());
            player1.getDice().getDiceButton().setEnabled(true);
        } else if (Player.turn == 2) {
            this.add(player2.getPiece());
            this.remove(player1.getPiece());
            this.addKeyListener(player2.getPiece());
            this.removeKeyListener(player1.getPiece());
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    this.add(player2.getMap().getMapPlayer()[i][j].getPanel());
                    this.add(Board.getMap()[i][j].getPanel());
                }
            }
            this.add(player2.getDice().getDiceButton());
            this.add(player2.getDice().getDicePanel());
            this.remove(player1.getDice().getDiceButton());
            this.remove(player1.getDice().getDicePanel());
            player2.getDice().getDiceButton().setEnabled(true);
        }

        this.add(map.getStartCell());

        setVisible(true);
    }

//    public void finishGame(int winner){
//        Player.winner = winner;
//        dispose();
//        new GameOver(getX(),getY());
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextTurn) {
            int x = this.getX();
            int y = this.getY();
            if (Board.Treasure.num==8){
                if (Player.turn==1){
//                    finishGame(1);
                }else if (Player.turn==2){
//                    finishGame(2);
                }
            }
            if (Player.turn == 1) {
                Player.turn = 2;
                Player.remainingMoves=0;
                ScoreBoard.player1Name.setBackground(Color.WHITE);
                ScoreBoard.player2Name.setBackground(Color.CYAN);
                ScoreBoard.player2Name.setOpaque(true);
                ScoreBoard.player1Name.setOpaque(false);
                Piece.updateStatus();
                this.dispose();
                e.setSource(null);
                new GameManager(x, y);
            } else if (Player.turn == 2) {
                Player.turn = 1;
                Player.remainingMoves=0;
                ScoreBoard.player2Name.setBackground(Color.WHITE);
                ScoreBoard.player1Name.setBackground(Color.CYAN);
                ScoreBoard.player1Name.setOpaque(true);
                Piece.updateStatus();
                this.dispose();
                e.setSource(null);
                new GameManager(x, y);
            }
        }
    }
}
