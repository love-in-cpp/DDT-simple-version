package DDT;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
    private GamePanel pnl;
    private JPanel northPanel;
    private JPanel southPanel;
    private BorderLayout borderLayout;

    MainFrame() throws InterruptedException {
        borderLayout = new BorderLayout();
        pnl = new GamePanel(null);
        Init();
    }

    private void Init() throws InterruptedException {
        pnl.player.attack(this);//调用此方法，玩家可以选定力度和角度，完成武器的投掷
        this.setLayout(borderLayout);//设置窗口为边界布局，游戏面板在中心，北部面板标注palyer和boss的血量
        northPanel = CreatPanel.creatNorthPanel(this, pnl.getBoss(), pnl.getPlayer());//调用CreatPanel类的静态方法，创建顶部面板
        this.add(northPanel, BorderLayout.PAGE_START);

        this.add(pnl, BorderLayout.CENTER);//把游戏面板置中

        southPanel = CreatPanel.creatSouthPanel(pnl.getBoss(), pnl.getPlayer());//调用CreatPanel类的静态方法，创建底部面板
        this.add(southPanel, BorderLayout.PAGE_END);
        //设置窗口居中显示,调整窗体元素
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        this.setSize(1500, 900);
        int w = this.getWidth();
        int h = this.getHeight();
        this.setLocation((width - w) / 2, (height - h) / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        /*********************************以下是控制角色行为的方法调用**********************************************/
        pnl.player.movebyKeyboard(this, 20);//调用此方法，人物就可以受键盘操控

        Thread r=new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
        //r.start();

        Thread t = new Thread() {//新建一个线程，进行在地图上的boss的来回移动
            @Override
            public void run() {
                super.run();

                while (true) {
                    try {
                        Thread.sleep(64);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    pnl.getBoss().xbounce(MainFrame.this, 20);//调用boss的x方向来回移动方法，就像在一条线上弹来弹去的小球，所以叫xbounce

                    if (!pnl.getBoss().isAive)//如果boss死亡
                        break;//boss不动了
                }
            }
        };
        t.start();
        Thread q = new Thread() {//新建一个线程，调整boss的攻击频率
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.currentThread().sleep(1000);//每一秒，boss丢出武器一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (pnl.getBoss().isAive)
                        pnl.getBoss().attack(pnl.getPlayer());//调用boss的攻击方法，完成武器的自动跟踪投掷
                }
            }
        };
        q.start();


        //this.setUndecorated(true);
        this.setVisible(true);

    }
}
