package com.ttkp.entity;

import com.ttkp.frame.GameFrame;
import com.ttkp.jpanel.GameJPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author yura
 * @version 1.0.0 2019.6.17
 * Person
 */
public class Person {
    private int x;      //person坐标x
    private int y;      //person坐标y
    private int score;  //person得分
    public static final int WIDTH = 90;      //per宽度
    public static final int HEIGHT = 120;    //per高度
    private BufferedImage rw1, rw2, rw3, rw4, rw5, rw6, rw7, rw8, rw9, tw1,tw2;   //人物碎片变量
    public BufferedImage[] images = {},imagest={};      //per图片
    public BufferedImage image;
    public int life;

    //pet attributes
    private BufferedImage[] dimages;           //宠物图片数组
    private BufferedImage d1, d2, d3, d4, d5, d6;    // 组成宠物动态图的图片变量
    public BufferedImage dimage;               //首张出现的宠物图片
    private int dx;     //pet坐标x
    private int dy;     //pet坐标y
    private int width = 90;          //宠物宽度
    private int height = 90;         //宠物高度
    private int index;          //切换图片的变量
    private int distance;       //移动距离


    public Person() {
        life = 10;
        score = 0;
        initPerson();
        initpat();
        images = new BufferedImage[]{rw1, rw2, rw3, rw4, rw5, rw6, rw7, rw8, rw9};
        imagest=new BufferedImage[]{ tw1,tw2,rw1, rw2, rw3, rw4,rw5,  rw6, rw7, rw8, rw9};
        dimages = new BufferedImage[]{d1, d2, d3, d4, d5, d6};
        x = 50;
        y = 300;
        dx = x + 85;
        dy = 345;
        index = 0;
        distance = 0;
    }

    /**
     * 初始化人物
     */
    private void initPerson() {
        try {
            rw1 = ImageIO.read(new File("image/1.png"));
            rw2 = ImageIO.read(new File("image/2.png"));
            rw3 = ImageIO.read(new File("image/3.png"));
            rw4 = ImageIO.read(new File("image/4.png"));
            rw5 = ImageIO.read(new File("image/5.png"));
            rw6 = ImageIO.read(new File("image/6.png"));
            rw7 = ImageIO.read(new File("image/7.png"));
            rw8 = ImageIO.read(new File("image/8.png"));
            rw9 = ImageIO.read(new File("image/9.png"));
            tw1=ImageIO.read(new File("image/1_1.png"));
            tw2=ImageIO.read(new File("image/1_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化宠物
     */
    public void initpat() {
        try {
            d1 = ImageIO.read(new File("image/d1.png"));
            d2 = ImageIO.read(new File("image/d2.png"));
            d3 = ImageIO.read(new File("image/d3.png"));
            d4 = ImageIO.read(new File("image/d4.png"));
            d5 = ImageIO.read(new File("image/d5.png"));
            d6 = ImageIO.read(new File("image/d6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 构造人物
     *
     * @param g //绘制对象
     */
    public void paintPerson(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
        g.drawImage(dimage, dx, dy, null);
    }

    /**
     * 移动
     */
    public void step() {
        if( GameJPanel.f){
            image = images[index++ / 10 % images.length];
        }else{
            image = imagest[index++ / 10 % imagest.length];
        }
        dimage = dimages[index++ / 10 % dimages.length];
    }

    /**
     * 自动下落
     */
    //TODO 跳自行完成
    public void drop() {
        y += 2;
        if (y > 300) {
            y = 300;
            GameJPanel.f=true;
        }
    }


    //setter getter

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public BufferedImage getRw1() {
        return rw1;
    }

    public void setRw1(BufferedImage rw1) {
        this.rw1 = rw1;
    }

    public BufferedImage getRw2() {
        return rw2;
    }

    public void setRw2(BufferedImage rw2) {
        this.rw2 = rw2;
    }

    public BufferedImage getRw3() {
        return rw3;
    }

    public void setRw3(BufferedImage rw3) {
        this.rw3 = rw3;
    }

    public BufferedImage getRw4() {
        return rw4;
    }

    public void setRw4(BufferedImage rw4) {
        this.rw4 = rw4;
    }

    public BufferedImage getRw5() {
        return rw5;
    }

    public void setRw5(BufferedImage rw5) {
        this.rw5 = rw5;
    }

    public BufferedImage getRw6() {
        return rw6;
    }

    public void setRw6(BufferedImage rw6) {
        this.rw6 = rw6;
    }

    public BufferedImage getRw7() {
        return rw7;
    }

    public void setRw7(BufferedImage rw7) {
        this.rw7 = rw7;
    }

    public BufferedImage getRw8() {
        return rw8;
    }

    public void setRw8(BufferedImage rw8) {
        this.rw8 = rw8;
    }

    public BufferedImage getRw9() {
        return rw9;
    }

    public void setRw9(BufferedImage rw9) {
        this.rw9 = rw9;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public BufferedImage[] getDimages() {
        return dimages;
    }

    public void setDimages(BufferedImage[] dimages) {
        this.dimages = dimages;
    }

    public BufferedImage getD1() {
        return d1;
    }

    public void setD1(BufferedImage d1) {
        this.d1 = d1;
    }

    public BufferedImage getD2() {
        return d2;
    }

    public void setD2(BufferedImage d2) {
        this.d2 = d2;
    }

    public BufferedImage getD3() {
        return d3;
    }

    public void setD3(BufferedImage d3) {
        this.d3 = d3;
    }

    public BufferedImage getD4() {
        return d4;
    }

    public void setD4(BufferedImage d4) {
        this.d4 = d4;
    }

    public BufferedImage getD5() {
        return d5;
    }

    public void setD5(BufferedImage d5) {
        this.d5 = d5;
    }

    public BufferedImage getD6() {
        return d6;
    }

    public void setD6(BufferedImage d6) {
        this.d6 = d6;
    }

    public BufferedImage getDimage() {
        return dimage;
    }

    public void setDimage(BufferedImage dimage) {
        this.dimage = dimage;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public BufferedImage[] getImagest() {
        return imagest;
    }

    public void setImagest(BufferedImage[] imagest) {
        this.imagest = imagest;
    }

    public BufferedImage getTw1() {
        return tw1;
    }

    public void setTw1(BufferedImage tw1) {
        this.tw1 = tw1;
    }

    public BufferedImage getTw2() {
        return tw2;
    }

    public void setTw2(BufferedImage tw2) {
        this.tw2 = tw2;
    }
}
