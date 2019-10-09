package com.ttkp.entity;

import com.ttkp.frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 障碍物之空中障碍物
 *
 * @author yura
 * @version 1.0.0 2019.6.18
 * //@deprecated //已过时，调用该标签
 * 和导弹的写法一致
 */
public class Barrs_3 {

    private Image image;                            //障碍物图
    private int x, y;                                //坐标
    public static final int WIDTH = 50, HEIGHT = 250;    //宽高
    private int xSpeed;                             //速度

    /**
     * 绘制空中障碍物
     *
     * @param g 绘制空中障碍物变量
     */
    public void patintBarr3(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * 移动速度
     */
    public void step() {
        x -= xSpeed;
    }

    Random random = new Random();

    /**
     * 无参构造方法
     */
    public Barrs_3() {
        image = new ImageIcon("image/" + (random.nextInt(4) + 11) + ".png").getImage();
        x = GameFrame.WIDTH + random.nextInt(100);
        y = 0;
        xSpeed = 2;
    }

    /**
     * 有参构造方法
     *
     * @param x 横坐标
     * @param y 纵坐标
     */

    public Barrs_3(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 有三个参数的构造方法
     *
     * @param image 图像
     * @param x     横坐标
     * @param y     纵坐标
     */
    public Barrs_3(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    /**
     * 有4个参数的构造方法
     *
     * @param image  图像
     * @param x      横坐标
     * @param y      纵坐标
     * @param xSpeed 速度
     */
    public Barrs_3(Image image, int x, int y, int xSpeed) {
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
        return "Barrs_3{" +
                "image=" + image +
                ", x=" + x +
                ", y=" + y +
                ", xSpeed=" + xSpeed +
                '}';
    }
}
