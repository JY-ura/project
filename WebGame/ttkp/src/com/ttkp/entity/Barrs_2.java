package com.ttkp.entity;

import com.ttkp.frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 障碍物之导弹
 *
 * @author yura
 * @version 1.0.0 2019.6.17
 */
public class Barrs_2 {

    public static final int WIDTH = 55;
    public static final int HEIGHT = 40;
    private int x;
    private int y;
    private Image image;
    private int xSpeed;                 //导弹速度


    Random random = new Random();

    /**
     * 无参初始化导弹
     */
    public Barrs_2() {
        x = random.nextInt(WIDTH) + GameFrame.WIDTH + 400;
        y = random.nextInt(100) + 100;
        image = new ImageIcon("image/daodan.png").getImage();
        xSpeed = 5;
    }

    /**
     * 导弹绘制
     *
     * @param g //绘制导弹变量
     */
    public void paintBarr2(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * 移动方法
     */
    public void step() {
        x -= xSpeed;          //x-6
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
}
