package DDT;

import javax.swing.*;
import java.awt.*;

public class CreatPanel {
    public static JPanel creatNorthPanel(JFrame jf,Boss boss,Player p){//利用静态方法完成顶部面板的创建
        FlowLayout f=new FlowLayout(FlowLayout.LEFT,20,0);
        JPanel panel=new JPanel(f);
        panel.setPreferredSize(new Dimension(1500,60));
        JLabel bossHP=new JLabel(boss.getName()+" HP:"+boss.getHp());
        bossHP.setFont(new Font("宋体",Font.BOLD,20));
        bossHP.setForeground(Color.red);
        //bossHP.setBounds(1200,10,180,50);
        JLabel playerHP=new JLabel(p.getName()+" HP:"+p.getHp());
        playerHP.setFont(new Font("宋体",Font.BOLD,20));
        playerHP.setForeground(Color.CYAN);
        //playerHP.setBounds(90,10,180,50);

        Thread t=new Thread() {//血量刷新线程
            @Override
            public void run() {
                super.run();
                while(true) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bossHP.setText(boss.getName() + " HP:" + boss.getHp());//在循环中不断刷新血量值，实现血量实时更新
                    playerHP.setText(p.getName()+" HP:"+p.getHp());//在循环中不断刷新血量值，实现血量实时更新
                    jf.requestFocus();
                }
            }};
        t.start();
        JButton revise1 =new JButton("增加角色武器伤害");
        JButton revise2 =new JButton("增加角色血量");
        JButton revise3 =new JButton("增加BOSS武器伤害");
        JButton revise4 =new JButton("增加BOSS血量");
        JButton revise5=new JButton("减少角色武器伤害");
        JButton revise6=new JButton("减少角色血量");
        JButton revise7=new JButton("减少BOSS武器伤害");
        JButton revise8=new JButton("减少BOSS血量");
        revise1.addActionListener(e -> p.getWeapon().setDamage( p.getWeapon().getDamage()+10));
        revise2.addActionListener(e -> p.setHp(p.getHp()+20));
        revise3.addActionListener(e ->boss.getWeapon().setDamage(boss.getWeapon().getDamage()+10) );
        revise4.addActionListener(e ->boss.setHp(boss.getHp()+20));
        revise5.addActionListener(e -> p.getWeapon().setDamage( p.getWeapon().getDamage()-10));
        revise6.addActionListener(e -> p.setHp(p.getHp()-20));
        revise7.addActionListener(e ->boss.getWeapon().setDamage(boss.getWeapon().getDamage()-10) );
        revise8.addActionListener(e -> boss.setHp(boss.getHp()-20));

        panel.setBackground(Color.BLACK);

        panel.add(playerHP);
        panel.add(revise1);
        panel.add(revise2);
        panel.add(revise3);
        panel.add(revise4);
        panel.add(revise5);
        panel.add(revise6);
        panel.add(revise7);
        panel.add(revise8);
        panel.add(bossHP);

        return panel;
    }
    public static JPanel creatSouthPanel(Boss b,Player p){
        FlowLayout f=new FlowLayout(FlowLayout.LEFT,50,0);
        JPanel panel=new JPanel(f);
        //JPanel panel=new JPanel(null);
        panel.setPreferredSize(new Dimension(1500,50));
        JLabel strength=new JLabel("当前力度:"+p.getStrenth());
        strength.setFont(new Font("宋体",Font.BOLD,20));
        strength.setForeground(new Color(0x0000E1));
        JLabel angle=new JLabel("angle:"+p.getAngle());
        //strength.setBounds(230,5,180,50);
        //angle.setBounds(0,5,180,50);
        angle.setFont(new Font("宋体",Font.BOLD,20));
        angle.setForeground(new Color(0xBE0FE1));
        JLabel playerdmg=new JLabel("当前玩家武器伤害:"+p.getWeapon().getDamage());
        JLabel bossdmg=new JLabel("当前BOSS武器伤害:"+b.getWeapon().getDamage());
        playerdmg.setForeground(new Color(0x03F0FF));
        bossdmg.setForeground(new Color(0xFF0063));
        playerdmg.setFont(new Font("宋体",Font.BOLD,20));
        bossdmg.setFont(new Font("宋体",Font.BOLD,20));
        Thread t=new Thread() {
            @Override
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    strength.setText("当前力度:"+p.getStrenth());
                    angle.setText("当前角度:"+p.getAngle());
                    playerdmg.setText("当前玩家武器伤害:"+p.getWeapon().getDamage());
                    bossdmg.setText("当前BOSS武器伤害:"+b.getWeapon().getDamage());
                }

            }};
        t.start();
        panel.setBackground(Color.ORANGE);
        panel.add(strength);
        panel.add(angle);
        panel.add(playerdmg);
        panel.add(bossdmg);
        return panel;
    }

}
