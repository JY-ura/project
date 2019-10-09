package com.ttkp.entity;

import com.ttkp.frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 金币
 *
 * @author yura
 * @version 1.0.0 2019.6.18
 */
public class Gold {
    private Image image;
    public static final int WIDTH = 20, HEIGHT = 20;
    private int x;
    private int y;
    private int xSpeed;


    Random random = new Random();
    /**
     * 无参初始化金币
     */
    public Gold() {
        for(int i=0;i<random.nextInt(20);++i){
            image = new ImageIcon("image/"+(random.nextInt(6)+20)+".png").getImage();
            x = GameFrame.WIDTH + random.nextInt(200);
            y = random.nextInt(300)+50;
            xSpeed = 2;
        }

    }

    /**
     * 绘制金币
     *
     * @param g //绘制金币变量
     */
    public void paintGold(Graphics g) {
        g.drawImage(image,x,y,WIDTH,HEIGHT,null);
    }

    /**
     * 移动
     */
    public void step() {
        x -= xSpeed;
    }

    /**
     * 四参构造函数
     *
     * @param image  图像
     * @param x      横坐标
     * @param y      纵坐标
     * @param xSpeed 速度
     */
    public Gold(Image image, int x, int y, int xSpeed) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public String toString() {
        return "Gold{" +
                "image=" + image +
                ", x=" + x +
                ", y=" + y +
                ", xSpeed=" + xSpeed +
                ", random=" + random +
                '}';
    }
}
