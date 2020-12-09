package DDT;

public class Collision {

    static public boolean isCollisionWithRect(Weapon w,Character b) {//矩形碰撞检测，检测武器与人物是否碰撞
        int x1,x2,y1,y2,w1,w2,h1,h2;//定义变量
        //完成变量赋值
        x1=w.getX();;x2=b.getX();
        y1=w.getY();;y2=b.getY();
        w1=w.getWidth();w2=b.getWidth();
        h1=w.getHeight();h2=b.getHeight();
        //反向求解矩形碰撞
        if (x1 >= x2 && x1 >= x2 + w2) {//如果矩形1在矩形2的偏右方，且矩形1和矩形2的同点距大于等于矩形2的宽
            return false;//没发生碰撞
        } else if (x1 <= x2 && x1 + w1 <= x2) {//继续判断，如果是偏左方且同点距大于等于矩形1的宽
            return false;//没发生碰撞
        } else if (y1 >= y2 && y1 >= y2 + h2) {//继续判断，同理，矩形2Y方向的判断
            return false;
        } else if (y1 <= y2 && y1 + h1 <= y2) {//同理，矩形1Y方向的判断
            return false;
        }
        return true;//四者都不满足，则发生碰撞
    }

}
