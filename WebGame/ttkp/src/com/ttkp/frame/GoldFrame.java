package com.ttkp.frame;

import com.ttkp.jpanel.GoldJPanel;
import com.ttkp.entity.Person;
import com.ttkp.jpanel.GameJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏结束类
 * @author VN_Minya
 * @version 1.0.0
 * 2019-06-20
 */

public class GoldFrame extends JFrame{
    public static final int HEIGHT = 550;
    public static final int WIDTH = 1000;
    GameJPanel gameJPanel = null;
    GoldJPanel goldJPanel = null;
    public static boolean goldf=false;
    Person person;

    public GoldFrame() throws HeadlessException {
        super();
//        person = new Person();
            goldJPanel = new GoldJPanel();
            goldJPanel.action();
            this.addKeyListener(goldJPanel);
            this.add(goldJPanel);
            goldf = true;
            //窗体属性
            this.setSize(WIDTH, HEIGHT);
            this.setTitle("天天酷跑");

            //设置窗体大小不可更改
            this.setResizable(false);
            this.setUndecorated(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setIconImage(new ImageIcon("image/115.png").getImage());
            this.setLocationRelativeTo(null);
            this.setVisible(true);

        while (true){
            if(goldJPanel.quit){
                System.out.println("1"+goldJPanel.quit);
                gameJPanel.goldd = false;

                goldJPanel.quit = false;System.out.println("2"+goldJPanel.quit);
                this.dispose();
                System.out.println("22"+goldJPanel.quit);
                new GameFrame();

                gameJPanel.isOver = false;
            }
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }
//    public static void main(String[] argc){
//        new GoldFrame();
//    }
}
