package DDT;

import javax.swing.*;
import java.awt.*;

public class Boss extends Character {
    ImageIcon boss;//boss朝左的图片
    ImageIcon rightboss;//boss朝右的图片
    Weapon weapon;//boss的武器
    ImageIcon deathboss;//朝左的时候死亡的boss的图片
    ImageIcon rightdeathboss;//朝右的时候死亡的boss的图片
    public boolean isAive=true;//Alive 生存状态 活着
    public boolean isRight=false;//朝向状态 朝右

    Boss(String name) {
        super(name);
        weapon=new Weapon();
        boss=new ImageIcon("gameimg/boss_left_angry.png");
        deathboss=new ImageIcon("gameimg/boss_death.png");
        rightboss=new ImageIcon("gameimg/boss_right_angry.png");
        rightdeathboss=new ImageIcon("gameimg/boss_right_death.png");
        setLocation(1250,250);
        weapon.setLocation(x - 20, y + 120);//初始化武器位置
        weapon.setDamage(20);//设置武器伤害
        setWidth(boss.getIconWidth());
        setHeight(boss.getIconHeight());
        setHp(100);
    }
    public void drawCharacter(Graphics g) {//用画笔画出boss的图像
        if(isAive) {
            if(!isRight)
            g.drawImage(boss.getImage(), x, y, null);
            else
                g.drawImage(rightboss.getImage(),x,y,null);
        }
        else
            drawDeathCharacter(g);
    }
    public void drawDeathCharacter(Graphics g){//用画笔画出boss死亡的图像
        if(!isRight)
        g.drawImage(deathboss.getImage(),x,y,null);
        else
            g.drawImage(rightdeathboss.getImage(),x,y,null);
    }
    public void xbounce(JFrame jFrame,int speed){//使boss在一定位置的x轴来回运动
        //因感觉像小球一样具有弹性所以命名为xbounce
        this.speed=speed;
        if(isRight)//如果向右运动
            setX(getX() + this.speed);//x+=speed
        if(!isRight)//如果向左运动
            setX(getX() - this.speed);//x-=speed
        if(getX()+getWidth()>=jFrame.getWidth()||getX()<=900) {//碰壁判断
            isRight = !isRight;//更改方向
        }
    }
    public void attack(Player p){//boss的攻击方法
        weapon.throwOut(p);//调用武器的丢出方法

    }

    public void setHp(int hp) {
        this.hp=hp;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setSpeed(int speed) {
        this.speed=speed;
    }

    public void setLocation(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
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

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
