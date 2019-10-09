package com.ttkp.entity;

import com.ttkp.entity.Pedal;
import com.ttkp.jpanel.GameJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


    /**
     * 321页面
     * @author yura
     * @version 1.0.0 2019.6.21
     */
    public class Three {
        private int index=0;
        public BufferedImage rw1, rw2, rw3, rw4,imagee,imagee1,imagee2,imagee3;
        public BufferedImage[] images = {};      //per图片

        public Three(){
            init();
            images = new BufferedImage[]{rw1, rw2, rw3, rw4};
        }

        public void init(){
            try {
                rw1 = ImageIO.read(new File("image/n0.png"));
                rw2 = ImageIO.read(new File("image/n1.png"));
                rw3 = ImageIO.read(new File("image/n2.png"));
                rw4 = ImageIO.read(new File("image/n3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * 构造人物
         *
         * @param g 绘制对象
         */
        public void paintNum(Graphics g) {
            g.drawImage(imagee, 230, 80, 500, 500, null);
        }

        public void step(){
            imagee = images[index++ / 10 % images.length];
        }


    }


