import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Cell {

    private boolean showSw;
    private JPanel panel;
    private int x,y;
    private Border border = BorderFactory.createLineBorder(Color.darkGray);
    private Color color;
    public Cell(boolean showSw,Color color, int x, int y) {
        this.showSw = showSw;
        this.setPanel(new JPanel());
        this.x = x;
        this.y = y;
        this.color =color;
        this.setBounds();
        if (!showSw)
            this.setColor(Color.lightGray);
        else
            this.setColor(color);
    }

    private void setBounds (){
        getPanel().setBounds(getX()*50,(9-getY())*50,50,50);
        getPanel().setBorder(border);
    }
    private void setColor(Color color){
        getPanel().setBackground(color);
    }

    public boolean isShowSw() {
        return showSw;
    }

    public void setShowSw(boolean showSw) {
        this.showSw = showSw;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public Color getColor() {
        return color;
    }

}
