package com.ttkp.jpanel;

import com.ttkp.entity.Pedal;
import com.ttkp.entity.Three;
import com.ttkp.frame.GameFrame;
import com.ttkp.jpanel.GameJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * 开始页面
 * @author yura
 * @version 1.0.0 2019.6.21
 */
public class Start extends JPanel {
    private int index=0;
    private Image background, showField, distanceField;           //背景图，表现栏，距离栏
    public boolean isOver=true;

    public Start() throws HeadlessException{
        background = new ImageIcon("image/cc.png").getImage();        //获取背景图
        showField = new ImageIcon("image/a12.png").getImage();        //获取表现栏
        distanceField = new ImageIcon("image/a12.png").getImage();    //获取距离栏

        Three three=new Three();
    }


    public void paint(Graphics g) {

        super.paint(g);
        g.drawImage(background, 0, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        //绘制表现栏
        g.drawImage(showField, 120, 30, 150, 30, null);
        g.setColor(Color.orange);       //设置文字颜色
        g.setFont(new Font("黑体", Font.BOLD, 16));
        g.drawString("表现: 0 分", 145, 50);

        //绘制距离栏 属性值不变，可不二次设置
        g.drawImage(distanceField, 350, 30, 150, 30, null);
        g.drawString("距离: 0 米", 375, 50);

//        paintNum(g);

    }

    public void action(){

    }

}
