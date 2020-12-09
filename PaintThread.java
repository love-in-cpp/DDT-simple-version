package DDT;

import java.awt.*;


class PaintThread extends Thread//绘图线程类
{
    GamePanel gp;
    public PaintThread(GamePanel gp) //构造函数
    {
        this.gp=gp;
    }
    public void run()//重载run()函数
    {
        while(true)//线程中的无限循环
        {//在单独的线程类中，完成武器跟随角色

            //如果武器没有被丢出，实现武器跟随Player
            if(gp.getPlayer().getWeapon().notThrowOut) {//如果武器没有丢出
                if (gp.getPlayer().isRight)//如果人物朝向向右（默认）（防止出现人脸朝右，武器朝左丢的情况）
                    gp.getPlayer().getWeapon().setLocation(gp.player.getX() - 5, gp.player.getY() - 5);//循环内不断设置武器的位置（以玩家为参考系）
                else
                    gp.getPlayer().getWeapon().setLocation(gp.player.getX() + 50, gp.player.getY());//人物朝左的情况
            }
            //如果武器没有丢出，实现武器跟随Boss
            if(gp.getBoss().getWeapon().notThrowOut) {//如果武器没有丢出
                if (!gp.getBoss().isRight)//如果Boss朝左（默认）
                    gp.getBoss().getWeapon().setLocation(gp.getBoss().getX() -20, gp.getBoss().getY()+120);//循环内不断设置武器的位置（以Boss为参考系）
                else
                    gp.getBoss().getWeapon().setLocation(gp.getBoss().getX() + 117, gp.getBoss().getY()+120);//人物朝右的情况
            }

            gp.repaint();//窗口重绘
        }
    }
}
