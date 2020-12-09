package DDT;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public Player player=null;
    private Boss boss=null;
    private Image iBuffer=null;
    private Graphics gBuffer=null;
    public PaintThread pt;//绘图线程
    static private int  hitCount=0;//定义全局变量 用来计算武器打击次数



    private ImageIcon background;

    GamePanel(FlowLayout flowLayout){
        super(flowLayout);
        //绘图线程
        pt=new PaintThread(this);
        Init();
        pt.start();
    }
    private void Init(){
        player=new Player("玩家");
        boss=new Boss("远古族长");
        background=new ImageIcon("gameimg/scene7.jpg");
        this.setPreferredSize(new Dimension(background.getIconWidth(),background.getIconHeight()));
    }

    public Player getPlayer() {
        return player;
    }

    public Boss getBoss() {
        return boss;
    }

    @Override
    public void paint(Graphics g) {
        if(player.getHp()>0)
            player.isAlive=true;
        if(boss.getHp()>0)
            boss.isAive=true;
        //super.paint(g);
        g.drawImage(background.getImage(),0,0,null);
        player.drawCharacter(g);
        boss.drawCharacter(g);
        if(player.isAlive)
        player.getWeapon().paintWeaponPlayer(g);
        if(boss.isAive)
        boss.getWeapon().paintWeaponBoss(g);
        //碰撞检测
        if(Collision.isCollisionWithRect(player.getWeapon(),boss)) {//如果玩家的武器和boss发生碰撞
            player.getWeapon().notThrowOut = true;//武器状态变成“没有丢出”即回到玩家身边
            hitCount++;//统计打击次数，可用于计分
            if(boss.getHp()>0) {//如果bossHP大于零,注意，这里不用else if是因为boss死亡是在一次判断内完成的，如果用else if将造成boss血量为0还活着
                boss.setHp(boss.getHp() - player.getWeapon().getDamage());//每次打击player的武器造成伤害，减少boss血量

                 if (boss.getHp() <= 0) {//如果boss血量小于零
                    boss.isAive = false;//boss状态 变为死亡，用于在其他线程中刷新boss的图像，显示它的死亡图像
                    JOptionPane.showMessageDialog(this, "Boss:" + boss.getName() + "已被打败！", "恭喜！", JOptionPane.INFORMATION_MESSAGE);
                    //JDialog实现死亡提示
                }
            }

        }
        if (Collision.isCollisionWithRect(boss.getWeapon(),player)) {//如果boss的武器和玩家发生碰撞
            boss.getWeapon().notThrowOut = true;//boss的武器状态变成“没有丢出”，即回到玩家身边
            if (player.getHp() > 0) {//如果角色HP大于0
                player.setHp(player.getHp() - boss.getWeapon().getDamage());//每次boss武器造成伤害并扣除相应血量
                if (player.getHp() <= 0) {//一旦玩家血量小于等于0
                    player.isAlive = false;//玩家生存状态发生改变，变为死亡
                    JOptionPane.showMessageDialog(this, "你输了", "提示！", JOptionPane.INFORMATION_MESSAGE);
                    //JDialog实现死亡提示
                }
            }
        }

        //重绘
        repaint();
    }
    public void update(Graphics scr)//重写update方法实现双缓冲
    {
        if(iBuffer==null)
        {
            iBuffer=createImage(this.getSize().width,this.getSize().height);
            gBuffer=iBuffer.getGraphics();
        }
        gBuffer.setColor(getBackground());
        gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
        paint(gBuffer);
        scr.drawImage(iBuffer,0,0,this);
    }


}
