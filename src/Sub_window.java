import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Sub_window extends JFrame {
    private int x;
    private  int y;
    private  Box box1;
    private  Box box2;
    protected  Box vBox;
    private  Box box3;
    private JLabel info1;
    private JLabel info2;
    protected JLabel[] labels ;
    private  JLabel[] space;
    protected TestUIInCode mainWindow;

    public Sub_window(int x, int y, String title, TestUIInCode t){
        mainWindow = t;
        this.x=x;
        this.y=y;
        setTitle(title);
        vBox=Box.createVerticalBox();
        this.setBounds(x,y,300,550);
        setVisible(false);
        labels = new JLabel[5];
        space = new JLabel[3];
        //info1
        info1=new JLabel();
        box1=Box.createHorizontalBox();
        info1.setText("current value");
        info1.setFont(new Font("BOLD",Font.PLAIN,25));
        info1.setSize(300,275);
        info1.setBackground(Color.yellow);
        box1.add(info1);
        box1.setSize(300,275);


        //info2
        info2=new JLabel();
        box2=Box.createHorizontalBox();
        info2.setText("historical value");
        info2.setFont(new Font("BOLD",Font.PLAIN,25));
        box2.add(info2);
        info2.setSize(300,275);
        info2.setBackground(Color.blue);
        box2.setSize(300,275);
        vBox.add(info1);
        vBox.add(new JLabel(" "));
        for(int i =0;i<3;i++){
            space[i]=new JLabel();
            space[i].setText(" ");
            vBox.add(space[i]);
        }
        vBox.add(info2);
        setContentPane(vBox);

      //historical value
        for(int i =0;i<labels.length;i++){
            int a=i+1;
            labels[i]=new JLabel();
            labels[i].setText("value "+a+" mins ago: ");
            labels[i].setFont(new Font("BOLD",Font.PLAIN,25));
            labels[i].setSize(300,275);
            labels[i].setBackground(Color.blue);
            vBox.add(labels[i]);
        }
//        for(int i =0;i<2;i++){
//            space[i]=new JLabel();
//            space[i].setText(" ");
//            vBox.add(space[i]);
//        }
    }

    //update display of historical value




    public void setInfo1(String text) {
        info1.setText(text);
    }

    public void setInfo2(String text) {
        info2.setText(text);
    }


}
