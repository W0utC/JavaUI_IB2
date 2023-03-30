import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;

public class Alarm  {
    private Clip clip;

    public Alarm(String filename) {
       // super("Audio Player");
        //(JFrame.EXIT_ON_CLOSE);

        try {
            // 加载音频文件
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));

            // 获取音频格式
            AudioFormat format = audioInputStream.getFormat();

            // 转换音频格式
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);

            // 打开音频流
            clip.open(audioInputStream);

            // 创建播放按钮
//            JButton playButton = new JButton("播放");
//            playButton.addActionListener(e -> {
//                clip.start();
//            });

            // 添加播放按钮到窗口
           // add(playButton);

            // 显示窗口
//            pack();
//            setLocationRelativeTo(null);
//            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Clip getClip() {
        return clip;
    }
    //    public static void main(String[] args) {
//        new Alarm();
//    }
}
