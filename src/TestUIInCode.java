import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TestUIInCode extends JFrame implements ActionListener{

    private JLabel timeLabel;
    private JButton counterButton;
    private JLabel counter;
    private JButton temperatureButton;
    private JLabel temperature;
    JLabel counterLabel;
    JLabel temperatureLabel;
    private boolean flag1;
    private boolean flag2;
    private Box box1;
    private Box box2;
    private Box box3;
    private Box box4;
    private Box box5;
    private Box Vbox;
   // private JPanel clockPanel;
    private Sub_Con countWin;//counter sub-window
    private Sub_Tem temWin;//tem sub-window
    private DB db;//get data
    private int update;
    private Warning warn;


    public TestUIInCode(String title){
        //generate Jframe
        super(title);
        flag1=true;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create box
        box1=Box.createHorizontalBox();
        box2=Box.createHorizontalBox();
        box3=Box.createHorizontalBox();
        box4=Box.createHorizontalBox();
        box5=Box.createHorizontalBox();
        Vbox=Box.createVerticalBox();

        this.setSize(550, 550);
        //initialize db
        db = new DB();



        //lively clock
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 50));

        Timer timer = new Timer(1000, this); // update every 1 second
        timer.start();
        ImageIcon icon1 = new ImageIcon("D:\\OneDrive - KU Leuven\\AAAALeuven2_2\\IB2\\UI_ultimate\\src\\clock2.png");
        Image img = icon1.getImage(); // get image
        JLabel clockIcon=new JLabel();
        clockIcon.setSize(50,50);
        img = img.getScaledInstance(clockIcon.getWidth(), clockIcon.getHeight(), Image.SCALE_SMOOTH); // 缩放图像
        icon1 = new ImageIcon(img); //
        clockIcon.setIcon(icon1);

        box1.add(clockIcon);
        box1.add(timeLabel);

        //add clock
        box1.setSize(550,50);
        //this.add(BorderLayout.NORTH, clockPanel);



        // create a JPanel
//        JPanel stuffPanel = new JPanel();
//        stuffPanel.setLayout(new GridLayout(4, 1));

        //shows info of parameters
        counter = new JLabel();
        counter.setFont(new Font("Arial", Font.PLAIN, 50));
        counter.setText("no value yet");
        //box3.setSize(550,110);
        box3.add(counter);
        //box3.setSize();
        temperature = new JLabel();
        temperature.setFont(new Font("Arial", Font.PLAIN, 50));
        temperature.setText("no value yet");
        //box5.setSize(550,110);
        box5.add(temperature);

        //update temp and counter
        Timer t2=new Timer(100,(e) -> {
            //String response = db.makeGETRequest("https://studev.groept.be/api/a22ib2b06/allvalues");
            this.giveValue();
        });
        t2.start();

        // create a JLabel and add some placeholder text
        counterLabel = new JLabel();
        counterLabel.setText("Now we have:");
        counterLabel.setFont(new Font("BOLD", Font.PLAIN, 30));
        counterLabel.setForeground(Color.blue);
        box2.add(counterLabel);
        box2.add(Box.createHorizontalGlue());

        temperatureLabel = new JLabel();
        temperatureLabel.setText("Current temperature is :");
        temperatureLabel.setFont(new Font("BOLD", Font.PLAIN, 30));
        temperatureLabel.setForeground(Color.green);
        box4.add(temperatureLabel);
        box4.add(Box.createHorizontalGlue());

        //create a button
        counterButton = new JButton("➕");
        counterButton.setPreferredSize(new Dimension(50,50));
        box2.setSize(550,100);
        box2.add(counterButton);
        temperatureButton = new JButton("➕");
        temperatureButton.setSize(50,50);

        //self scaling icon
//        ImageIcon icon = new ImageIcon("D:\\OneDrive - KU Leuven\\AAAALeuven2_2\\IB2\\UI_ultimate\\src\\plus-icon-21717.png");
//        Image img = icon.getImage(); // get image
//        img = img.getScaledInstance(temperatureButton.getWidth(), temperatureButton.getHeight(), Image.SCALE_SMOOTH); // 缩放图像
//        icon = new ImageIcon(img); //
//        temperatureButton.setIcon(icon);

         //temperatureButton.setIcon(i);

         box4.setSize(550,100);
         box4.add(temperatureButton);




        //make action when pressed
        counterButton_Act();
        TemperatureButton_Act();
        // counterLabel.setBackground(Color.black);
        //counterPanel.setBackground(Color.black);
        Vbox.add(box1);
        Vbox.add(Box.createVerticalGlue());
        Vbox.add(box2);
        Vbox.add(Box.createVerticalGlue());
        Vbox.add(box3);
        Vbox.add(Box.createVerticalGlue());
        Vbox.add(box4);
        Vbox.add(Box.createVerticalGlue());
        Vbox.add(box5);
        //set vertical box as content
        this.setContentPane(Vbox);

        temWin=new Sub_Tem(550,0,"Temperature", this);
        temWin.setSize(400,550);
        countWin=new Sub_Con(950,0,"Counter", this);
        countWin.setSize(400,550);

        //warning
        warn=new Warning("Warning!");
        warn.setBounds(500,500,600,100);

        warn.setVisible(false);


    }

    public DB getDb() {
        return db;
    }

    public void giveValue() {
            boolean x= getDb().isNtc();
            if(x){
            TestUIInCode.this.temperature.setText(db.getLastTemp() + " C");
            temWin.setInfo1("Current Temperature is:"+db.getLastTemp()+"C");
            db.setLastTemp();
            }
            else {
                TestUIInCode.this.counter.setText(db.getLastCount() + " People");
                countWin.setInfo1("Current number is:" + db.getLastCount() + "people");
                db.setLastCounter();
            }
    }


    public void counterButton_Act(){
        counterButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag1){
                    flag1=false;
                    counterButton.setText("-");
                    countWin.setVisible(true);
                    counterButton.setSize(100,100);
                    counterButton.setBackground(Color.RED);
                }
                else {
                    flag1=true;
                    counterButton.setText("➕");
                    countWin.setVisible(false);
                    counterButton.setSize(50,50);
                    counterButton.setBackground(null);
                }

            }
        });
    }

    public void TemperatureButton_Act(){
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i = 0;
                //System.out.println("habibi");

            }
        };
        Timer timer= new Timer(1000,listener);
        temperatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag2){
                    flag2=false;
                    temperatureButton.setText("-");
                    temWin.setVisible(true);
                    temperatureButton.setBackground(Color.RED);

                    timer.start();
                }
                else {
                    flag2=true;

                    temperatureButton.setText("➕");
                    temWin.setVisible(false);
                    temperatureButton.setBackground(null);
                    timer.stop();
                }

            }
        });
    }

    //check


    //Code always starts running at main
    public static void main(String[] args) {
        //generate my UI
        JFrame ui= new TestUIInCode("Main");
        //the frame needs to become visible
        ui.setVisible(true);
        System.out.println(UIManager.getInstalledLookAndFeels());


}



    //event that update every sec and in main routine
    @Override
    public void actionPerformed(ActionEvent e) {
        updateTime();
        update++;
        if(update==8) {
            db.storeCon(db.getLastCount());
            db.storeTem(db.getLastTemp());
            update=0;
        }
        countWin.updateHisCon();
        temWin.updateHisTem();
        warn.setWarnInfo(db.checkBound());
    }


    private void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String time = dateFormat.format(new Date());
        timeLabel.setText(time);
        //System.out.println("width: "+box1.getSize());//.width+" length: "+counterButton.getSize().height);
    }
}