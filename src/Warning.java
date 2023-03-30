import javax.swing.*;
import java.awt.*;

public class Warning extends JFrame {
    private JLabel warnInfo;
    private JFrame tst;
    private Alarm alarm1;
    public Warning(String title){
        super (title);
        warnInfo=new JLabel();
        warnInfo.setFont(new Font("Arial", Font.PLAIN, 50));
        warnInfo.setForeground(Color.red);
        this.setContentPane(warnInfo);
        alarm1 = new Alarm("ding.wav");
    }

    public JLabel getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(char a){
        if(a=='h') {
            warnInfo.setText("temperature is too high!");
            this.setVisible(true);
            alarm1.getClip().start();
        }
        else if(a=='l'){
            warnInfo.setText("temperature is too low!");
            this.setVisible(true);
            alarm1.getClip().start();
        }
        else if(a=='c') {
            warnInfo.setText("Too many people!");
            this.setVisible(true);
            alarm1.getClip().start();
        }
        else{
            this.setVisible(false);
            alarm1.getClip().stop();
            alarm1.getClip().setFramePosition(0);
        }
    }
}
