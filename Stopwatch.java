import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch {

    JLabel timeLabel = new JLabel();

    int elapsedTime = 0;
    int seconds =0;
    int minutes =0;

    String secondsString = String.format("%02d", seconds);
    String minutesString = String.format("%02d", minutes);

    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime=elapsedTime+1000;
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            timeLabel.setText(minutesString+":"+secondsString);
        }
    });
    Stopwatch(){
        timeLabel.setText(minutesString+":"+secondsString);
        timeLabel.setSize(100,30);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,20));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

}