package DDT;

import javax.swing.*;
import java.awt.*;

public class Weapon {
    private int x;
    private int y;
    private int speed;
    public boolean notThrowOut=true;
    private int width;
    private int height;
    private int damage;

    ImageIcon boomerang;
    ImageIcon fire;

    Weapon(){
        setDamage(20);
        boomerang = new ImageIcon("gameimg/weapon_player.png");
        fire=new ImageIcon("gameimg/boss_weapon.png");
        width= boomerang.getIconWidth();
        height= boomerang.getIconWidth();
    }


    public void paintWeaponPlayer(Graphics g){
        g.drawImage(boomerang.getImage(), x, y, null);
    }
    public void paintWeaponBoss(Graphics g){
        g.drawImage(fire.getImage(),x,y,null);
    }
    public void setLocation(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x=x;
    }

    public void setY(int y) {
        this.y=y;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public void throwOut(int angle, int strenth){//玩家将武器丢出
        notThrowOut=false;//一旦执行“武器丢出”的方法，丢出状态改变成（已丢出）
        this.speed=strenth;//玩家力量直接换算成武器的速度

        Thread t=new Thread(){//建立一个新线程，在线程的循环中完成武器的直线移动
            @Override
            public void run() {
                super.run();
                while (true) {
                    try { Thread.sleep(32); } //60fps
                    catch (InterruptedException e) { e.printStackTrace(); }
                    //实现全角度发射武器的效果
                    setY((int) (getY() - speed * Math.sin(Math.PI * angle / 180)));
                    setX((int) (getX() + speed * Math.cos(Math.PI * angle / 180)));



                    if((x>=1500||x<=0||y<=0||y>=800)) {//一旦超出窗口边界，武器回到人物身边
                        notThrowOut=true;//丢出状态变为未丢出（在其他线程实现 未丢出->保持武器跟随）
                        break;//跳出循环
                    }

                }
            }
  };
        t.start();

    }
    public void throwOut(Player p){//重载throwOut方法，Boss将矩形丢出
        notThrowOut=false;//一旦执行“武器丢出”的方法，丢出状态改变成（已丢出）
        int xdistance=(x-p.getX())/100;//测量boss的武器与玩家的X轴距离/100，相当于武器每次执行循环走的X方向的距离
        int ydistance=(y-p.getY())/100;//测量boss的武器与玩家的Y轴距离/100，相当于武器每次执行循环走的Y方向的距离
        //之所以测量距离是因为要保持X,Y距离成固定比例，间接实现角度固定的效果，从而完成抛出的武器能够直线追踪角色的点位

       Thread t=new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(32);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //循环体内完成点位更新
                    setX(x - xdistance);
                    setY(y - ydistance);

                    if ((x >= 1500 || x <= 0 || y <= 0 || y >= 800)) {//出窗口边界，武器回到人物身边
                        notThrowOut = true;//丢出状态变为未丢出（在其他线程实现 未丢出->保持武器跟随）
                        break;//跳出循环
                    }
                }
            }
        };
        t.start();
    }


}

