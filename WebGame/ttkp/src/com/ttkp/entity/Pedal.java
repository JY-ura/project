package com.ttkp.entity;

import com.ttkp.frame.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 空中踏板
 *
 * @author yura
 * @version 1.0.0 2019.6.18
 */
public class Pedal {
    private Image image;
    public static final int WIDTH = 200, HEIGHT = 50;
    private int x;
    private int y;
    private int xSpeed;
    private int index=0;

    Random random = new Random();

    /**
     * 无参初始化踏板
     */
    public Pedal() {

        image = new ImageIcon("image/hhh.png").getImage();
        x = GameFrame.WIDTH + random.nextInt(50);
        y = random.nextInt(100)+180;
        xSpeed = 2;
    }


    /**
     * 绘制踏板
     * @param g 绘制踏板变量
     */
    public void paintPedal(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * 移动
     */
    public void step() {
        x -= xSpeed;
    }

    /**
     * 4参构造函数
     *
     * @param image  图片
     * @param x      横坐标
     * @param y      纵坐标
     * @param xSpeed 速度
     */
    public Pedal(Image image, int x, int y, int xSpeed) {
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

    @Override
    public String toString() {
        return "Pedal{" +
                "image=" + image +
                ", x=" + x +
                ", y=" + y +
                ", xSpeed=" + xSpeed +
                ", random=" + random +
                '}';
    }
}
