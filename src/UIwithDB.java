//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class UIwithDB extends JFrame {
    private static JLabel myLabel;
    private JLabel myLabel1;
    private JLabel myLabel2;
    private JLabel myLabel3;
    private JLabel myLabel4;
    private JPanel myPanel;
    private JButton myButton;
    private JTextField myTextField;
    private DB N = new DB();

    public UIwithDB(String title) {
        super(title);
        this.setDefaultCloseOperation(3);
        myLabel = new JLabel();
        this.myLabel1 = new JLabel();
        this.myLabel2 = new JLabel();
        this.myLabel3 = new JLabel();
        this.myLabel4 = new JLabel();
        this.myTextField = new JTextField();
        myLabel = new JLabel();
        this.myPanel = new JPanel();
        this.myButton = new JButton();
        Timer timer = new Timer(1000, (e) -> {
            this.giveValue();
        });
        timer.start();
        this.myPanel.add(myLabel);
        this.myPanel.add(this.myLabel1);
        this.myPanel.add(this.myLabel2);
        this.myPanel.add(this.myLabel3);
        this.myPanel.add(this.myLabel4);
        this.myPanel.add(this.myTextField);
        this.myPanel.add(this.myButton);
        //this.myButton.addActionListener(new UIwithDB(this));
        this.setContentPane(this.myPanel);
    }

    public void giveValue() {
        if (this.N.isNtc()) {
            this.myLabel1.setText("New measurement is " + this.N.getLastTemp() + " C");
        } else {
            this.myLabel1.setText("New measurement is " + this.N.getLastCount() + " People");
        }

    }

    public static void main(String[] args) {
        UIwithDB ui = new UIwithDB("Temperature and Counter ");
        Container a = ui.getContentPane();
        BoxLayout c = new BoxLayout(a, 3);
        ui.setSize(300, 200);
        ui.setLayout(c);
        ui.setVisible(true);
    }
}
