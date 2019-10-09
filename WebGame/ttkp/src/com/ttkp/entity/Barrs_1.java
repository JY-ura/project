package com.ttkp.entity;

import com.ttkp.frame.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


/**
 * 障碍物之螃蟹
 *
 * @author yura
 * @version 1.0.0 2019.6.17
 */

public class Barrs_1 {

    //barrier attributes
    public static final int WIDTH = 80, HEIGHT = 80;
    private BufferedImage[] images;     //barr1形象碎片
    private BufferedImage image;        //barr1形象
    private int x;                      //barr1 X
    private int y;                      //barr1 Y
    private int index;                  //barr1 位置 切换图片
    private BufferedImage bar1, bar2;    //the images of vary
    private int xSpeed;                 //移动单位


    Random random = new Random();
    /**
     * 无参构造函数
     */
    public Barrs_1() {
        initBarrs1();
        images = new BufferedImage[]{bar1, bar2};
        index = 0;
        x = GameFrame.WIDTH + random.nextInt(500);      //the situation of barrs_1
        y = 345;
        xSpeed = 2;
    }


    /**
     * init barrs_1
     */
    private void initBarrs1() {
        try {
            bar1 = ImageIO.read(new File("image/a4.png"));
            bar2 = ImageIO.read(new File("image/a2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制螃蟹
     *
     * @param g 绘制螃蟹变量
     */
    public void paintBarr_1(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * 移动
     */
    public void step() {
        image = images[index++ / 80 % images.length];
        x -= xSpeed;
    }


    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage getBar1() {
        return bar1;
    }

    public void setBar1(BufferedImage bar1) {
        this.bar1 = bar1;
    }

    public BufferedImage getBar2() {
        return bar2;
    }

    public void setBar2(BufferedImage bar2) {
        this.bar2 = bar2;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
}
