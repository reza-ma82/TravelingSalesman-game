import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketFrame extends Frame implements ActionListener {

    static JLabel text = new JLabel();
    static Weapon weapon1 =new Weapon("Sword",600,350);
    static Weapon weapon2 = new Weapon("Bow",300,180);
    static Weapon weapon3 = new Weapon("Spear",450,250);
    static Item treasureLocation = new Item("Treasure Location",500);
    static JButton back = new JButton();
    MarketFrame(int x, int y) {
        super(x, y);
        setSize(250,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6,1,10,5));

        text.setText("What dou you want to buy?");
        weapon1.setText(weapon1.getName());
        weapon2.setText(weapon2.getName());
        weapon3.setText(weapon3.getName());
        treasureLocation.setText(treasureLocation.getName());
        back.setText("Back");

        weapon1.addActionListener(this);
        weapon2.addActionListener(this);
        weapon3.addActionListener(this);
        treasureLocation.addActionListener(this);
        back.addActionListener(this);

        weapon1.setFocusable(false);
        weapon2.setFocusable(false);
        weapon3.setFocusable(false);
        treasureLocation.setFocusable(false);
        back.setFocusable(false);

        add(text);
        add(weapon1);
        add(weapon2);
        add(weapon3);
        add(treasureLocation);
        add(back);

        GameManager.market.addActionListener(this);

        setVisible(false);

    }
    static class Item extends JButton{
        private String name;
        private int value;
        Item(String name,int value){
            this.name=name;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String getName() {
            return name;
        }

    }
    static class Weapon extends Item{

        private int power;

        Weapon(String name,int value,int power){
            super(name,value);
            this.power = power;
        }

        public int getPower() {
            return power;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==GameManager.market){
            dispose();
            e.setSource(null);
            new MarketFrame(getX(),getY()).setVisible(true);
        }
        if (e.getSource()== weapon1){
            e.setSource(null);
            if (Player.turn==1){
                if (GameManager.player1.getMoney() >= weapon1.getValue()) {
                    GameManager.player1.setMoney(GameManager.player1.getMoney() - weapon1.getValue());
                    GameManager.player1.setPower(GameManager.player1.getPower() + weapon1.getPower());
                }else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ weapon1.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }else if (Player.turn==2){
                if (GameManager.player2.getMoney() >= weapon1.getValue()) {
                    GameManager.player2.setMoney(GameManager.player2.getMoney() - weapon1.getValue());
                    GameManager.player2.setPower(GameManager.player2.getPower() + weapon1.getPower());
                }else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ weapon1.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }
            Piece.updateStatus();
        }
        else if (e.getSource()== weapon2){
            e.setSource(null);
            if (Player.turn==1){
                if (GameManager.player1.getMoney() >= weapon2.getValue()) {

                    GameManager.player1.setMoney(GameManager.player1.getMoney() - weapon2.getValue());
                    GameManager.player1.setPower(GameManager.player1.getPower() + weapon2.getPower());
                }else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ weapon2.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }else if (Player.turn==2){
                if (GameManager.player2.getMoney() >= weapon2.getValue()) {
                    GameManager.player2.setMoney(GameManager.player2.getMoney() - weapon2.getValue());
                    GameManager.player2.setPower(GameManager.player2.getPower() + weapon2.getPower());
                }else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ weapon2.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }
            Piece.updateStatus();
        }
        else if (e.getSource()== weapon3){
            e.setSource(null);
            if (Player.turn==1){
                if (GameManager.player1.getMoney() >= weapon3.getValue()) {
                    GameManager.player1.setMoney(GameManager.player1.getMoney() - weapon3.getValue());
                    GameManager.player1.setPower(GameManager.player1.getPower() + weapon3.getPower());
                }else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ weapon3.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }else if (Player.turn==2){
                if (GameManager.player2.getMoney() >= weapon3.getValue()) {
                    GameManager.player2.setMoney(GameManager.player2.getMoney() - weapon3.getValue());
                    GameManager.player2.setPower(GameManager.player2.getPower() + weapon3.getPower());
                }else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ weapon3.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }
            Piece.updateStatus();
        }
        else if (e.getSource()==treasureLocation){
            e.setSource(null);
            int x;
            do {
                x=Board.createRandomNumber();
            }while (x < Board.Treasure.num || x > 7);
            int i = Board.questItems[x].getX();
            int j = Board.questItems[x].getY();
            if (Player.turn==1){
                if (GameManager.player1.getMoney() >= treasureLocation.getValue()) {
                    GameManager.player1.getMap().getMapPlayer()[i][j].getPanel().setBorder(BorderFactory.createLineBorder(Color.green, 3, true));
                    GameManager.player1.setMoney(GameManager.player1.getMoney() - treasureLocation.getValue());
                }else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ treasureLocation.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }else if (Player.turn==2){
                if (GameManager.player2.getMoney() >= treasureLocation.getValue()) {
                    GameManager.player2.getMap().getMapPlayer()[i][j].getPanel().setBorder(BorderFactory.createLineBorder(Color.green, 3, true));
                    GameManager.player2.setMoney(GameManager.player2.getMoney() - treasureLocation.getValue());
                }
                else {
                    JOptionPane.showMessageDialog(this,"You don't have enough money to buy "+ treasureLocation.getName(),"Not enough money",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else if (e.getSource()==back){
            dispose();
            e.setSource(null);
        }

    }
}
