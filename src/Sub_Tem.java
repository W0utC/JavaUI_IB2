import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Sub_Tem extends Sub_window{
    private JLabel avgTem;
    private JLabel temIcon;
    private JLabel[] space;
    public Sub_Tem(int x, int y, String title, TestUIInCode t){
        super(x,y,title,t);
        space=new JLabel[3];
        avgTem=new JLabel("Average Temperature is:"+calculateAvg());
        avgTem.setFont(new Font("BOLD",Font.PLAIN,25));
        avgTem.setSize(300,275);
        vBox.add(avgTem);
        for(int i =0;i<space.length;i++){
            space[i]=new JLabel();
            space[i].setText(" ");
            vBox.add(space[i]);
        }
        ImageIcon icon1 = new ImageIcon("D:\\OneDrive - KU Leuven\\AAAALeuven2_2\\IB2\\UI_ultimate\\src\\thermo.png");
        Image img = icon1.getImage(); // get image

        Box iconBox=Box.createHorizontalBox();
        //iconBox.add(Box.createHorizontalStrut(10));
        iconBox.add(Box.createHorizontalGlue());
        iconBox.add(Box.createVerticalBox());
        temIcon=new JLabel();
        iconBox.add(temIcon);
        iconBox.add(Box.createHorizontalGlue());
        iconBox.add(Box.createVerticalBox());
        //iconBox.add(Box.createHorizontalStrut(10));

        temIcon.setSize(50,110);
        img = img.getScaledInstance(temIcon.getWidth(), temIcon.getHeight(), Image.SCALE_SMOOTH); // 缩放图像
        icon1 = new ImageIcon(img); //
        temIcon.setIcon(icon1);
        vBox.add(iconBox);
    }
    public void updateHisTem(){
        for(int i = 0; i < labels.length; i++) {
            int a = i+1;
            labels[i].setText("value "+(a)+" mins ago: "+mainWindow.getDb().getHisTem().get(a));
        }

        DecimalFormat df = new DecimalFormat("#.#");
        String formatted = df.format(calculateAvg());
        avgTem.setText("Average Temperature is:"+formatted);
    }

    public double calculateAvg(){
        return this.mainWindow.getDb().averageTem();
    }

}
