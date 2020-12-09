package DDT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Player extends Character {
    private Weapon weapon;//玩家持有的武器
    private int strenth;//玩家使用武器的力量
    private int angle=0;//玩家使用武器的角度
    ImageIcon rightimg;//玩家朝右的图片
    ImageIcon leftimg;//玩家朝左的图片
    Boolean isRight = true;//朝向状态 朝右
    Boolean isAlive=true;//生存状态 生存


    Player(String name) {
        super(name);
        weapon = new Weapon();
        rightimg = new ImageIcon("gameimg/player_normal_right.png");//初始化人物图像
        leftimg = new ImageIcon("gameimg/player_normal_left.png");//
        this.setLocation(200, 500);//初始化角色位置
        weapon.setLocation(x - 20, y - 20);//初始化武器位置
        setWidth(rightimg.getIconWidth());
        setHeight(rightimg.getIconHeight());
        setHp(100);
    }

    @Override
    public void drawCharacter(Graphics g) {//画笔画出角色
        if(isAlive)
        if (isRight)
            g.drawImage(rightimg.getImage(), x, y, null);
        else
            g.drawImage(leftimg.getImage(), x, y, null);
    }
    public void staticMove(JFrame jFrame, Directions directions, int speed) {//传递“上下左右”中的一个参数，实现一次单向移动
        //参数选择Jframe而不选择Jpanel是因为需要测量窗体长宽
        this.speed = speed;
        switch (directions) {
            case up://当传递“上”参数
                if (getY() <= 0)//如果碰壁
                    this.speed = -speed;//什么都不需要执行，等于do nothing
                else
                    setY(getY() - speed);//相当于y-=speed
                break;
            case down://当传递“下”参数
                if (getY() + getHeight() >= jFrame.getHeight() - 38)//如果碰壁
                    this.speed = -speed;
                else
                    setY(getY() + speed);
                break;
            case left://当传递“左”参数
                if (getX() <= 0)//如果碰壁
                    this.speed = -speed;
                else
                    setX(getX() - speed);
                break;
            case right://当传递“右”参数
                if (getX() + getWidth() >= jFrame.getWidth())//如果碰壁
                    this.speed = -speed;
                else
                    setX(getX() + speed);
        }
    }

    public void movebyKeyboard(JFrame jFrame, int speed) {//将单向移动方法注册键盘按键，实现键盘控制移动的效果
        jFrame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();
                switch (keycode) {
                    case KeyEvent.VK_W://当按下w
                        staticMove(jFrame, Directions.up, speed);
                        break;
                    case KeyEvent.VK_S://当按下s
                        staticMove(jFrame, Directions.down, speed);
                        break;
                    case KeyEvent.VK_A://当按下a
                        isRight = false;
                        staticMove(jFrame, Directions.left, speed);
                        break;
                    case KeyEvent.VK_D://当按下d
                        isRight = true;
                        staticMove(jFrame, Directions.right, speed);
                        break;
                }
            }
        });

    }

    public void attack(JFrame jf) {//人物攻击行为，将会在主窗口线程中控制
        jf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE) {
                    strenth++;//人物使用武器的力量增加（要抛出武器）
                    System.out.println("strenth:"+strenth);
                }
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    if(angle<180) {//控制角度最大为180
                        angle++;//（人物抛出武器的角度增加）
                        System.out.println("angle:" + angle);
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                    if(angle>-180) {//控制角度最小为-180
                        angle--;//人物抛出武器的角度减少
                        System.out.println("angle:" + angle);
                    }
            }
            @Override
            public void keyReleased(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_SPACE) {
                    //保证发射朝向与人物朝向一致
                    if(getAngle()>=-90&&getAngle()<=90)//如果角度在第一第四象限
                        isRight=true;//人脸朝右
                    else
                        isRight=false;//人脸朝左
                    weapon.throwOut(angle, strenth);//松开空格的瞬间，人物丢出武器
                    strenth=0;
                }
            }
        });
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
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
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public int getAngle() {
        return angle;
    }

    public int getStrenth() {
        return strenth;
    }

    public String getName() {
        return name;
    }



}
