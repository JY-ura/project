package com.ttkp.frame;



import com.ttkp.jpanel.GoldJPanel;
import com.ttkp.entity.Person;
import com.ttkp.jpanel.GameJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏主窗体类
 * @author VN_Minya
 * @version 1.0.0
 * 2019-06-19
 */

public class GameFrame extends JFrame{
    public static final int HEIGHT = 550;
    public static final int WIDTH = 1000;
    GameJPanel gameJPanel = null;
    GoldJPanel goldJPanel = null;
    Person person;
    public GameFrame() throws HeadlessException{
        super();

        System.out.println("gameframe" + goldJPanel.quit);
        gameJPanel = new GameJPanel();
        person = new Person();

        gameJPanel.action();
        this.addKeyListener(gameJPanel);
        this.add(gameJPanel);
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


        //游戏结束处理
        while (true){
            if(gameJPanel.goldd){
                System.out.println("1 "+gameJPanel.goldd);gameJPanel.goldd=false;
                goldJPanel.quit = false;
                System.out.println("2 "+gameJPanel.goldd);

                this.dispose();
                new GoldFrame();

            }
            else if(gameJPanel.isOver){
                this.dispose();
            }
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //开始
    public static void main(String[] args){
        new GameFrame();
    }
}
