package DDT;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class WelcomeFrame extends JFrame {
    JLabel j;
    JButton b;

    WelcomeFrame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Init();
        playMusic();
    }
    public void Init(){

        ImageIcon background=new ImageIcon("gameimg/scene8.jpg");
        j=new JLabel(background);
        this.setSize(new Dimension(background.getIconWidth(),background.getIconHeight()));
        ImageIcon g=new ImageIcon("gameimg/gamestart_init.png");//按钮初始图片

        b=new JButton(g);
        b.setBounds(440,235,150,60);
        b.setBorderPainted(false);//不画出边界
        b.setFocusPainted(false);//选中时不画出边界
        ImageIcon g1=new ImageIcon("gameimg/gamestart_after.png");//按钮被选中时更新图片
        b.setRolloverIcon(g1);
        b.addActionListener(e ->
        {
            try {
                new MainFrame();//Lamada表达式实现进入游戏窗口线程
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        //设置窗口居中显示,调整窗体元素
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int w = this.getWidth();
        int h = this.getHeight();
        this.setLocation((width - w) / 2, (height - h) / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //增加控件
        this.add(b);
        this.add(j);
        this.setVisible(true);
    }
    static void playMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {//背景音乐播放
                //1 获取你要播放的音乐文件
                File file = new File("src/Notsofaraway.wav");
                //2、定义一个AudioInputStream用于接收输入的音频数据
                AudioInputStream am;
                //3、使用AudioSystem来获取音频的音频输入流(处理（抛出）异常)
                am = AudioSystem.getAudioInputStream(file);
                //4、使用AudioFormat来获取AudioInputStream的格式
                AudioFormat af = am.getFormat();
                //5、一个源数据行
                SourceDataLine sd;
                //6、获取与上面类型相匹配的行 写到源数据行里 二选一
                sd = AudioSystem.getSourceDataLine(af);//便捷写法
                //sd = (SourceDataLine) AudioSystem.getLine(dl);
                //7、打开具有指定格式的行，这样可以使行获得资源并进行操作
                sd.open();
                //8、允许某个数据行执行数据i/o
                sd.start();
                //9、写数据
                int sumByteRead = 0; //读取的总字节数
                byte[] b = new byte[320];//设置字节数组大小
                //11、从音频流读取指定的最大数量的数据字节，并将其放入给定的字节数组中。
                while (sumByteRead != -1) {//-1代表没有 不等于-1时就无限读取
                    sumByteRead = am.read(b, 0, b.length);//12、读取哪个数组
                    if (sumByteRead >= 0) {//13、读取了之后将数据写入混频器,开始播放
                        sd.write(b, 0, b.length);
                    }
                }
                //关闭
                sd.drain();
                sd.close();
    }


    public static void main(String []args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        new WelcomeFrame();
    }
}
