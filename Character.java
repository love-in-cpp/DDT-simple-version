package DDT;

import java.awt.*;

public abstract class Character{
    protected int speed=20;//移速
    protected int hp=100;//生命值
    protected String name;
    protected int x=0,y=0;
    protected int size;
    protected int width;
    protected int height;
    Character(String name){
        this.name=name;
    }

    public abstract void drawCharacter(Graphics g);
    public abstract void setHp(int hp);
    public abstract void setName(String name);
    public abstract void setSpeed(int speed);
    public abstract void setLocation(int x,int y);
    public abstract int getHp();
    public abstract int getSpeed();
    public abstract int getX();
    public abstract int getY();
    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract void setSize(int width,int height);
    public abstract void setWidth(int width);
    public abstract void setHeight(int height);
    public abstract int getHeight();
    public abstract String getName();

    public abstract int getWidth();
    //public abstract int getSize();



}

