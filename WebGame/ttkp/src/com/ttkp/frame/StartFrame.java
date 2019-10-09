package com.ttkp.frame;

import com.ttkp.jpanel.Start;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * 游戏页面窗口
 * @author yura
 * @version 1.0.0 2019.6.17
 *
 */
public class StartFrame extends JFrame{

    //固定游戏屏幕大小，不做适配调整
    public static final int WIDTH=1000;
    public static final int HEIGHT=550;


    Start a=null;
    public StartFrame() throws HeadlessException{
        super();
        a=new Start();
        this.add(a);
        a.action();
        this.setTitle("天天酷跑");
        this.setSize(WIDTH,HEIGHT);
        //设置
        this.setIconImage(new ImageIcon("image/115.png").getImage());
        this.setVisible(true);

        while(true){
            if(a.isOver){
                this.dispose();
                new GameFrame();
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //开始
    public static void main(String[] args){
        new StartFrame();
    }

}
