import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Piece extends JLabel implements KeyListener {
    public Piece(int playerNumber, Color color) {
        setBounds((playerNumber - 1) * 25 + 2, 500, 20, 20);
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.lightGray,3));
        setOpaque(true);
    }

    private void moveDown(int x,int y) {
        if (y > 0) {
            if (Player.turn == 1) {
                if (Board.getMap()[x][y - 1].getColor() == Color.black) {//not Passing the wall
                    GameManager.player1.getMap().getMapPlayer()[x][y-1].getPanel().setVisible(false);
                } else {
                    setLocation(getX(), getY() + 50);
                    y--;
                    GameManager.player1.location[0] = x;
                    GameManager.player1.location[1] = y;
                    Player.remainingMoves--;
                }
            } else if (Player.turn == 2) {
                if (Board.getMap()[x][y - 1].getColor() == Color.black) {
                    GameManager.player2.getMap().getMapPlayer()[x][y - 1].getPanel().setVisible(false);
                } else {
                    setLocation(getX(), getY() + 50);
                    GameManager.player2.location[0] = x;
                    GameManager.player2.location[1] = y;
                    Player.remainingMoves--;
                }
            }
            readyForFight();
            findTreasure(x,y);
            voidLoot(x,y);
            fallIntoTrap(x,y);
            namingCell(x,y);
            openMarket(x,y);
            try {
                closeMarket(x,y+1);
            }catch (ArrayIndexOutOfBoundsException Ignored){

            }
        }
    }

    private void moveRight(int x,int y) {
        if (x < 9 && y >= 0) {
            if (Player.turn == 1) {
                if (Board.getMap()[x + 1][y].getColor() == Color.black) {
                    GameManager.player1.getMap().getMapPlayer()[x+ 1][y].getPanel().setVisible(false);
                } else {
                    setLocation(getX() + 50, getY());
                    x++;
                    GameManager.player1.location[0] = x;
                    GameManager.player1.location[1] = y;
                    Player.remainingMoves--;
                }
            } else if (Player.turn == 2) {
                if (Board.getMap()[x + 1][y].getColor() == Color.black) {
                    GameManager.player2.getMap().getMapPlayer()[x + 1][y].getPanel().setVisible(false);
                } else {
                    setLocation(getX() + 50, getY());
                    x++;
                    GameManager.player2.location[0] = x;
                    GameManager.player2.location[1] = y;
                    Player.remainingMoves--;
                }
            }
            readyForFight();
            findTreasure(x,y);
            voidLoot(x,y);
            fallIntoTrap(x,y);
            namingCell(x,y);
            openMarket(x,y);
            try {
                closeMarket(x-1,y);
            }catch (ArrayIndexOutOfBoundsException ignored){

            }
        }
    }

    private void moveUP(int x,int y) {
        if (y < 9) {
            if (Player.turn == 1) {
                if (Board.getMap()[x][y+1]instanceof Board.Wall ) {
                    GameManager.player1.getMap().getMapPlayer()[x][y+1].getPanel().setVisible(false);
                }else {
                    setLocation(getX(), getY() - 50);
                    y++;
                    GameManager.player1.location[0] = x;
                    GameManager.player1.location[1] = y;
                    Player.remainingMoves--;
                }
            } else if (Player.turn == 2) {
                if (Board.getMap()[x][y + 1].getColor() == Color.black) {
                    GameManager.player2.getMap().getMapPlayer()[x][y+1].getPanel().setVisible(false);
                } else {
                    setLocation(getX(), getY() - 50);
                    y++;
                    GameManager.player2.location[0] = x;
                    GameManager.player2.location[1] = y;
                    Player.remainingMoves--;
                }
            }
            readyForFight();
            findTreasure(x,y);
            voidLoot(x,y);
            fallIntoTrap(x,y);
            namingCell(x,y);
            openMarket(x,y);
            try {
                closeMarket(x,y-1);
            }catch (ArrayIndexOutOfBoundsException Ignored){

            }
        }
    }

    private void moveLeft(int x,int y) {
        if (x > 0 && y <= 9) {
            if (Player.turn == 1) {
                if (Board.getMap()[x - 1][y] instanceof Board.Wall) {
                    GameManager.player1.getMap().getMapPlayer()[((getX() - 2) / 50) - 1][9 - (getY() / 50)].getPanel().setVisible(false);
                } else if (Board.getMap()[x][y] instanceof Board.Market) {
                    GameManager.market.setEnabled(false);
                }
                else {
                    setLocation(getX() - 50, getY());
                    x--;
                    GameManager.player1.location[0] = x;
                    GameManager.player1.location[1] = y;
                    Player.remainingMoves--;
                }
            } else if (Player.turn == 2) {
                if (Board.getMap()[x - 1][y] instanceof Board.Wall) {
                    GameManager.player2.getMap().getMapPlayer()[x - 1][y].getPanel().setVisible(false);
                } else {
                    setLocation(getX() - 50, getY());
                    x--;
                    GameManager.player2.location[0] = x;
                    GameManager.player2.location[1] =y;
                    Player.remainingMoves--;
                }
            }
            readyForFight();
            findTreasure(x,y);
            voidLoot(x,y);
            fallIntoTrap(x,y);
            namingCell(x,y);
            openMarket(x,y);
            try {
                closeMarket(x+1,y);
            }catch (ArrayIndexOutOfBoundsException Ignored){

            }
        }
    }
    private void readyForFight(){
        if (GameManager.player1.location[0] == GameManager.player2.location[0]
                && GameManager.player1.location[1] == GameManager.player2.location[1]) {
            fight();
        }
    }
    private void fight() {
        int power1 = GameManager.player1.getPower();
        int power2 = GameManager.player2.getPower();
        int transferMoney;
        if (GameManager.player1.getPower() > GameManager.player2.getPower()
                || (GameManager.player1.getPower() == GameManager.player2.getPower() && Player.turn==1 )) { //player 1 wins
            try{
                transferMoney = GameManager.player2.getMoney() * ((power1 - power2) / (power1 + power2));
            }catch (RuntimeException e){
                transferMoney = 0;
            }
            GameManager.player1.setMoney(GameManager.player1.getMoney() + transferMoney);
            GameManager.player1.setPower(power1 - power2);
            GameManager.player2.setMoney(GameManager.player2.getMoney() - transferMoney);
            GameManager.player2.setPower(0);
        } else if (GameManager.player1.getPower() < GameManager.player2.getPower()
                || (GameManager.player1.getPower() == GameManager.player2.getPower() && Player.turn==2 )){ //player 2 wins
            try{
                transferMoney = GameManager.player1.getMoney() * ((power2 - power1) / (power1 + power2));
            }catch (RuntimeException e){
                transferMoney=0;
            }
            GameManager.player2.setMoney(GameManager.player2.getMoney() + transferMoney);
            GameManager.player2.setPower(power2 - power1);
            GameManager.player1.setMoney(GameManager.player1.getMoney() - transferMoney);
            GameManager.player1.setPower(0);
        }
    }
    private void findTreasure(int x,int y) {
        if (Player.turn==1){
            if (Board.getMap()[x][y] instanceof Board.Treasure treasure) {
                if (Board.questItems[Board.Treasure.num].name.equals(treasure.name)){
                    GameManager.player1.findTreasure = true;
                }
            } else if (Board.getMap()[x][y] instanceof Board.Castle) {
                if (GameManager.player1.findTreasure) {
                    GameManager.player1.setScore(GameManager.player1.getScore() + 1);
                    GameManager.player1.setMoney(GameManager.player1.getMoney() + Board.questItems[Board.Treasure.num].value);
                    voidTreasure();
                }
            }
        }else if (Player.turn==2){
            if (Board.getMap()[x][y] instanceof Board.Treasure treasure) {
                if (Board.questItems[Board.Treasure.num].name.equals(treasure.name))
                    GameManager.player2.findTreasure = true;
            } else if (Board.getMap()[x][y] instanceof Board.Castle) {
                if (GameManager.player2.findTreasure) {
                    GameManager.player2.setScore(GameManager.player2.getScore() + 1);
                    GameManager.player2.setMoney(GameManager.player2.getMoney() + Board.questItems[Board.Treasure.num].value);
                    voidTreasure();
                }
            }
        }
    }
    private void voidTreasure() {
        Board.getMap()[Board.questItems[Board.Treasure.num].getX()][Board.questItems[Board.Treasure.num].getY()]
                = new Board.Block(true, Color.white,Board.questItems[Board.Treasure.num].getX(),Board.questItems[Board.Treasure.num].getY());
        Board.Treasure.num++;
        GameManager.player1.findTreasure=false;
        GameManager.player2.findTreasure=false;
    }

    private void voidLoot(int x,int y){
        if (Player.turn==1) {
            if (Board.getMap()[x][y] instanceof Board.Loot) {
                GameManager.player1.setMoney(GameManager.player1.getMoney() + ((Board.Loot) Board.getMap()[x][y]).getValue());
                Board.getMap()[x][y] = new Board.Block(true, Color.white, x, y);
            }
        }else if (Player.turn==2){
            if (Board.getMap()[x][y] instanceof Board.Loot){
                GameManager.player2.setMoney(GameManager.player2.getMoney() + ((Board.Loot) Board.getMap()[x][y]).getValue());
                Board.getMap()[x][y] = new Board.Block(true,Color.white,x,y);
            }
        }
    }

    private void fallIntoTrap(int x,int y) {
        if (Player.turn == 1) {
            if (Board.getMap()[x][y] instanceof Board.Traps) {
                GameManager.player1.setPower(Math.max(GameManager.player1.getPower() - (((Board.Traps) Board.getMap()[x][y]).getCostPowerValue()), 0));
                GameManager.player1.setMoney(Math.max(GameManager.player1.getMoney() - ((Board.Traps) Board.getMap()[x][y]).getCostMoneyValue(), 0));
                Board.getMap()[x][y] = new Board.Block(true, Color.white, x, y);
            }
        } else if (Player.turn == 2) {
            if (Board.getMap()[x][y] instanceof Board.Traps) {
                GameManager.player2.setPower(GameManager.player2.getPower() - (((Board.Traps) Board.getMap()[x][y]).getCostPowerValue()));
                GameManager.player2.setMoney(GameManager.player2.getMoney() - ((Board.Traps) Board.getMap()[x][y]).getCostMoneyValue());
                Board.getMap()[x][y] = new Board.Block(true, Color.white, x, y);
            }
        }
    }
    private void openMarket(int x,int y){
        if (Board.getMap()[x][y]instanceof Board.Market)
            GameManager.market.setEnabled(true);

    }
    private void closeMarket(int x,int y){
        if (Board.getMap()[x][y]instanceof Board.Market)
            GameManager.market.setEnabled(false);
    }
    private void namingCell(int x,int y){
        if (Board.getMap()[x][y] instanceof Board.Treasure treasure){
            ScoreBoard.currentCell.setText("Current Cell: "+treasure.name);
        }
        else if(Board.getMap()[x][y]instanceof Board.Block){
            ScoreBoard.currentCell.setText("Current Cell: Empty");
        }
        else if(Board.getMap()[x][y]instanceof Board.Castle){
            ScoreBoard.currentCell.setText("Current Cell: Castle");
        }
        else if(Board.getMap()[x][y]instanceof Board.Loot){
            ScoreBoard.currentCell.setText("Current Cell: Loot");
        }
        else if(Board.getMap()[x][y]instanceof Board.Market){
            ScoreBoard.currentCell.setText("Current Cell: Market");
        }
        else if(Board.getMap()[x][y]instanceof Board.Traps){
            ScoreBoard.currentCell.setText("Current Cell: Trap");
        }
    }
    public static void updateStatus(){
        ScoreBoard.player1Score.setText("Score: "+ GameManager.player1.getScore());
        ScoreBoard.player1Money.setText("Money: "+ GameManager.player1.getMoney());
        ScoreBoard.player1Power.setText("Power: "+ GameManager.player1.getPower());

        ScoreBoard.player2Score.setText("Score: "+ GameManager.player2.getScore());
        ScoreBoard.player2Money.setText("Money: "+ GameManager.player2.getMoney());
        ScoreBoard.player2Power.setText("Power: "+ GameManager.player2.getPower());

        ScoreBoard.quest.setText("Quest: "+Board.questItems[Board.Treasure.num].name);
        ScoreBoard.playerTurn.setText("Turn: "+Player.turn);
        ScoreBoard.completedQuests.setText("Completed Quests: "+ (8-Board.Treasure.num));
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Player.remainingMoves > 0) {
            int x=0,y=0;
            if (Player.turn==1) {
                x = (getX()-2) / 50;
                y = 9 - (getY() / 50);
            }
            else if (Player.turn==2){
                x = (getX()-27) / 50;
                y = 9 - (getY() / 50);
            }
            switch (e.getKeyCode()) {
                case 65, 37 -> moveLeft(x,y);//left
                case 83, 40 -> moveDown(x,y);// down
                case 68, 39 -> moveRight(x,y);// right
                case 87, 38 -> moveUP(x,y);// up
            }
        }
        updateStatus();
        if (Player.turn == 1) {
            try {
                if (!GameManager.player1.getMap().getMapPlayer()[(getX() - 2) / 50][9 - (getY() / 50)].isShowSw()) {
                    GameManager.player1.getMap().getMapPlayer()[(getX() - 2) / 50][9 - (getY() / 50)].getPanel().setVisible(false);
                }
            }catch (ArrayIndexOutOfBoundsException exception){
//                exception.printStackTrace();
            }
        } else if (Player.turn == 2) {
            try {
                if (!GameManager.player2.getMap().getMapPlayer()[(getX() - 27) / 50][9 - (getY() / 50)].isShowSw()) {
                    GameManager.player2.getMap().getMapPlayer()[(getX() - 27) / 50][9 - (getY() / 50)].getPanel().setVisible(false);
                }
            }catch (ArrayIndexOutOfBoundsException exception){
//                exception.printStackTrace();
            }

        }
//
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
