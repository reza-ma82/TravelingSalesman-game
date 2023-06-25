import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dice implements ActionListener {
    private int diceNumber;
    private JButton diceButton;
    private JLabel diceLabel;
    private JPanel dicePanel;
    private Border border = BorderFactory.createLineBorder(new Color(160, 160, 160),4);
    public Dice() {
        setDiceButton(new JButton());
        setDiceLabel(new JLabel());
        setDicePanel(new JPanel());
    }


    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber() {
        this.diceNumber = createRandomNumber();
    }

    public JButton getDiceButton() {
        return diceButton;
    }

    public void setDiceButton(JButton diceButton) {
        diceButton.setBounds(440,500,60,34);
        diceButton.setBackground(Color.lightGray);
        diceButton.setForeground(Color.darkGray);
        diceButton.setText("Dice");
        diceButton.addActionListener(this);
        diceButton.setBorder(border);
        diceButton.setFocusable(false);
        diceButton.setHorizontalTextPosition(JButton.CENTER);
        this.diceButton=diceButton;
    }

    public JLabel getDiceLabel() {
        return diceLabel;
    }

    public void setDiceLabel(JLabel diceLabel) {
        this.diceLabel = diceLabel;
    }

    public JPanel getDicePanel() {
        return dicePanel;
    }

    public void setDicePanel(JPanel dicePanel) {
        dicePanel.setBackground(Color.WHITE);
        dicePanel.setBounds(400,500,40,34);
        dicePanel.setBorder(border);
        dicePanel.add(diceLabel);
        this.dicePanel=dicePanel;
    }

    private int createRandomNumber(){
        int x;
        x = (int)(Math.random()*6 )+ 1;
        return x;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== diceButton){
            setDiceNumber();
            diceLabel.setText(""+getDiceNumber());
            diceLabel.setHorizontalTextPosition(JLabel.CENTER);
            diceLabel.setVerticalTextPosition(JLabel.CENTER);

            Player.remainingMoves =getDiceNumber();

            diceButton.setEnabled(false);
        }
    }
}
