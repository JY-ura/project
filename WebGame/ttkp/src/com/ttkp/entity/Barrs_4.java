package com.ttkp.entity;

import com.ttkp.frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 障碍物之蜗牛
 *
 * @author yura
 * @version 1.0.0 2019.6.18
 */
public class Barrs_4 {

    private Image image;
    public static final int WIDTH = 100, HEIGHT = 80;
    private int x;
    private int y;
    private int xSpeed;

    Random random = new Random();

    /**
     * 无参构造方法
     * init 小蜗牛
     */
    public Barrs_4() {
        image = new ImageIcon("image/wobull.png").getImage();
        x = GameFrame.WIDTH + random.nextInt(200);
        y = 350;
        xSpeed = 5;
    }

    /**
     * 绘制蜗牛
     *
     * @param g 绘制蜗牛变量
     */
    public void paintBarrs4(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * 移动
     */
    public void step() {
        x -= xSpeed;
    }

    /**
     * 坐标构造方法
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    public Barrs_4(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return "Barrs_4{" +
                "image=" + image +
                ", x=" + x +
                ", y=" + y +
                ", xSpeed=" + xSpeed +
                ", random=" + random +
                '}';
    }
}
