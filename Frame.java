import javax.swing.*;

public class Frame extends JFrame {
    public int height=570;
    public int width=514;
    Frame(int x,int y){
        this.setLayout(null);
        this.setResizable(true);
        this.setLocation(x,y);
        this.setSize(width,height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setVisible(true);
    }
}
