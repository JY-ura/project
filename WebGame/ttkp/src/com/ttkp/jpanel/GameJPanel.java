package com.ttkp.jpanel;

import com.ttkp.entity.*;
import com.ttkp.frame.EndFrame;
import com.ttkp.frame.GameFrame;
import com.ttkp.frame.GoldFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Thread.sleep;

/**
 * 游戏的各类操作整合类
 *
 * @author yura
 * @version 1.0.0 2019.6.18
 */
public class GameJPanel extends JPanel implements KeyListener {
    //private static final long serialVersionUID=5531823511959381036L;

    private Image background, showField, distanceField;           //背景图，表现栏，距离栏
    public boolean isOver = false;

    public static boolean f=true;                               //游戏是否结束
    public static boolean fa=true;
    public static boolean goldd=false;
    Person person =null;                                      //人物对象
    Barrs_1[] barrs_1s = new Barrs_1[]{};                                      //螃蟹对象
    Barrs_2[] barrs_2s = new Barrs_2[]{};                                      //导弹对象
    Barrs_3[] barrs_3s = new Barrs_3[]{};                                      //空中障碍对象
    Barrs_4[] barrs_4s = new Barrs_4[]{};                                      //蜗牛对象
    Gold[] golds1 = {};                                           //金币对象
//    Gold[] golds2 = {};                                            //金币对象
    Pedal[] pedals = new Pedal[]{};                                           //踏板对象
    Pedal start=new Pedal();
    public static int s = 0;
    public static int dis = 0;


    /**
     * 完成对象的初始化
     */
    public GameJPanel() {

        person = new Person();        //创建人物对象
//        bars = new Barrs_2();          //创建导弹对象
        background = new ImageIcon("image/cc.png").getImage();        //获取背景图
        showField = new ImageIcon("image/a12.png").getImage();        //获取表现栏
        distanceField = new ImageIcon("image/a12.png").getImage();    //获取距离栏

    }

    int x = 0;
    /**
     * 绘制图像
     *
     * @param g //绘图变量
     */
    @Override
    public void paint(Graphics g) {
        x--;
        super.paint(g);
        g.drawImage(background, x, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        g.drawImage(background, x + GameFrame.WIDTH, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        if (x == -GameFrame.WIDTH) {
            x = 0;
        }

        //绘制表现栏
        g.drawImage(showField, 120, 30, 150, 30, null);
        g.setColor(Color.orange);       //设置文字颜色
        g.setFont(new Font("黑体", Font.BOLD, 16));
        g.drawString("表现:" + person.getScore() + "分", 145, 50);

        //绘制距离栏 属性值不变，可不二次设置
        g.drawImage(distanceField, 350, 30, 150, 30, null);
        person.setDistance(++dis);
        g.drawString("距离:" + dis + "米", 375, 50);

        //绘制人物
        if(!f){
            person.setImages(person.imagest);           //滚动人物
        }else{
            person.setImages(person.images);            //跑步人物
        }
        person.paintPerson(g);

        //调用绘制导弹方法
        for (int i = 0; i < barrs_2s.length; ++i) {
            barrs_2s[i].paintBarr2(g);
        }

        //绘制螃蟹障碍物
        for (int i = 0; i < barrs_1s.length; ++i) {
            barrs_1s[i].paintBarr_1(g);
        }

        //绘制金币
        for (int i = 0; i < golds1.length; ++i) {
            golds1[i].paintGold(g);
        }

//        //绘制金币
//        for (int i = 0; i < golds2.length; ++i) {
//            golds2[i].paintGold(g);
//        }

        //绘制空中障碍
        for (int i = 0; i < barrs_3s.length; ++i) {
            barrs_3s[i].patintBarr3(g);
        }

        //绘制蜗牛
        for (int i = 0; i < barrs_4s.length; ++i) {
            barrs_4s[i].paintBarrs4(g);
        }

        //绘制踏板
        for (int i = 0; i < pedals.length; ++i) {
            pedals[i].paintPedal(g);
        }
    }

    /**
     * 移动
     */
    public void stepAction() {

        //玩家移动
        person.drop();
        person.step();

        //导弹障碍物移动
        for (int i = 0; i < barrs_2s.length; ++i) {
            barrs_2s[i].step();
        }

        //螃蟹移动
        for (int i = 0; i < barrs_1s.length; ++i) {
            barrs_1s[i].step();
        }

        //蜗牛移动
        for (int i = 0; i < barrs_4s.length; ++i) {
            barrs_4s[i].step();
        }

        //空中障碍物移动
        for (int i = 0; i < barrs_3s.length; ++i) {
            barrs_3s[i].step();
        }

        //金币移动
        for (int i = 0; i < golds1.length; ++i) {
            golds1[i].step();
        }

//        //金币移动
//        for (int i = 0; i < golds2.length; ++i) {
//            golds2[i].step();
//        }

        //踏板移动
        for (int i = 0; i < pedals.length; ++i) {
            pedals[i].step();
        }
    }

    /**
     * 障碍物入场
     */
    int index = 0;

    public void enteredAction() {
        ++index;
        //40个时间单位生成一个导弹
        if (index % 1200 == 0) {
            //生成一个导弹
            Barrs_2 barrs_2 = new Barrs_2();
            //对数组bars扩容一个单位
            barrs_2s = Arrays.copyOf(barrs_2s, barrs_2s.length + 1);
            //将生成的导弹放入数组
            barrs_2s[barrs_2s.length - 1] = barrs_2;
        }

        //50个时间单位生成一个螃蟹
        if (index % 612 == 0) {
            //生成一个螃蟹
            Barrs_1 barrs_1 = new Barrs_1();
            //对数组bars扩容一个单位
            barrs_1s = Arrays.copyOf(barrs_1s, barrs_1s.length + 1);
            //将生成的导弹放入数组
            barrs_1s[barrs_1s.length - 1] = barrs_1;
        }

        //40个时间单位生成一个空中障碍物
        if (index % 438 == 0) {
            //生成一个空中障碍物
            Barrs_3 barrs_3 = new Barrs_3();
            //对数组bars扩容一个单位
            barrs_3s = Arrays.copyOf(barrs_3s, barrs_3s.length + 1);
            //将生成的导弹放入数组
            barrs_3s[barrs_3s.length - 1] = barrs_3;
        }

        //40个时间单位生成一个蜗牛
        if (index % 999 == 0) {
            //生成一个蜗牛
            Barrs_4 barrs_4 = new Barrs_4();
            //对数组bars扩容一个单位
            barrs_4s = Arrays.copyOf(barrs_4s, barrs_4s.length + 1);
            //将生成的导弹放入数组
            barrs_4s[barrs_4s.length - 1] = barrs_4;
        }

        //40个时间单位生成一个金币
        if (index % 30 == 0) {
            //生成一个金币
            Gold gold1 = new Gold();
            //对数组bars扩容一个单位
            golds1 = Arrays.copyOf(golds1, golds1.length + 1);
            //将生成的导弹放入数组
            golds1[golds1.length - 1] = gold1;
        }

//        //40个时间单位生成一个金币
//        if (index % 60 == 0) {
//            //生成一个金币
//            Gold gold2 = new Gold();
//            //对数组bars扩容一个单位
//            golds2 = Arrays.copyOf(golds2, golds2.length + 1);
//            //将生成的导弹放入数组
//            golds2[golds2.length - 1] = gold2;
//        }

        //40个时间单位生成一个踏板
        if (index % 945 == 0) {
            //生成一个踏板
            Pedal pedal = new Pedal();
            //对数组bars扩容一个单位
            pedals = Arrays.copyOf(pedals, pedals.length + 1);
            //将生成的导弹放入数组
            pedals[pedals.length - 1] = pedal;
        }
    }

    /**
     * 玩家被障碍物遮挡的方法
     */
    boolean flag = true;

    public void wardAction() {
        //  person.getY()+person.HEIGHT>bars1[i].getY()
        // person.getY()<bars1[i].getY()+bars1[i].HEIGHT
        // person.getX()+person.WIDTH>bars1[i].getX()
        // person.getX()<bars1[i].getX()+bars1[i].WIDTH
        /**
         * 螃蟹（暂不修改）
         */
        for (int i = 0; i < barrs_1s.length; ++i) {
            if (
                    person.getY() + person.HEIGHT > barrs_1s[i].getY() &&
                            person.getY() < barrs_1s[i].getY() + barrs_1s[i].HEIGHT - 20
                            && person.getX() + person.WIDTH > barrs_1s[i].getX()
                            && person.getX() < barrs_1s[i].getX() + barrs_1s[i].WIDTH - 20
            ) {
                person.setX(barrs_1s[i].getX() - person.WIDTH);
            }
        }

        /**
         * 导弹（已修改）
         */
        for (int i = 0; i < barrs_2s.length; ++i) {
            if (person.getY() + person.HEIGHT + 20 > barrs_2s[i].getY() &&            //下
                    person.getY() < barrs_2s[i].getY() + barrs_2s[i].HEIGHT &&      //上
                    person.getX() + person.WIDTH - 25 > barrs_2s[i].getX() &&         //左
                    person.getX() < barrs_2s[i].getX() + barrs_2s[i].WIDTH) {        //右
                person.setLife(0);
            }
        }

        /**
         * 空中障碍
         */
        for (int i = 0; i < barrs_3s.length; ++i) {
            if (person.getY() + person.HEIGHT > barrs_3s[i].getY() &&
                    person.getY() < barrs_3s[i].getY() + barrs_3s[i].HEIGHT &&
                    person.getX() + person.WIDTH > barrs_3s[i].getX() &&
                    person.getX() < barrs_3s[i].getX() + barrs_3s[i].WIDTH) {
                person.setX(barrs_3s[i].getX() - person.WIDTH);
            }
        }

        // 落在踏板上
        for (int i = 0; i < pedals.length; i++) {
            if (person.getX() + person.WIDTH > pedals[i].getX()
                    && person.getX() < pedals[i].getX() + pedals[i].WIDTH) {
                if (person.getY() + person.HEIGHT > pedals[i].getY()
                        && person.getY() < pedals[i].getY() + pedals[i].HEIGHT) {
                    person.setY(pedals[i].getY() - person.HEIGHT);
                    goldd=true;
                }
            }
        }


        /**
         * 蜗牛
         */
        for (int i = 0; i < barrs_4s.length; ++i) {
            if (person.getY() + person.HEIGHT > barrs_4s[i].getY() &&
                    person.getY() + 40 < barrs_4s[i].getY() + barrs_4s[i].HEIGHT &&
                    person.getX() + person.WIDTH - 30 > barrs_4s[i].getX() &&
                    person.getX() < barrs_4s[i].getX() + barrs_4s[i].WIDTH - 20) {
                person.setLife(0);
            }
        }

        /**
         * 玩家吃金币
         */
        for (int i = 0; i < golds1.length; ++i) {
            if (person.getX() + person.WIDTH > golds1[i].getX() &&
                    person.getX() < golds1[i].getX() + golds1[i].WIDTH &&
                    person.getY() + person.HEIGHT > golds1[i].getY() &&
                    person.getY() < golds1[i].getY() + golds1[i].HEIGHT) {
                //玩家碰到金币后的操作
                //1 玩家 加分
                s = person.getScore();
                person.setScore(s + 100);
                //2 金币消失
                golds1[i] = golds1[golds1.length - 1];
                golds1 = Arrays.copyOf(golds1, golds1.length - 1);
            }
        }


//        for (int i = 0; i < golds2.length; ++i) {
//            if (person.getX() + person.WIDTH > golds2[i].getX() &&
//                    person.getX() < golds2[i].getX() + golds2[i].WIDTH &&
//                    person.getY() + person.HEIGHT > golds2[i].getY() &&
//                    person.getY() < golds2[i].getY() + golds2[i].HEIGHT) {
//                //玩家碰到金币后的操作
//                //1 玩家 加分
//                s = person.getScore();
//                person.setScore(s + 100);
//                //2 金币消失
//                golds2[i] = golds2[golds2.length - 1];
//                golds2 = Arrays.copyOf(golds2, golds2.length - 1);
//            }
//        }

    }

    /**
     * 游戏结束
     */
    public void gameOverAction() {
        if(GoldFrame.goldf){
            isOver = false;
        }
        else if (person.getX() + person.WIDTH <= 0 || person.getLife() <= 0) {
            JOptionPane.showMessageDialog(null, "游戏结束"); //TODO 可做拓展
            isOver = true;
            flag = false;
            new EndFrame(person);
            person = new Person();
        }
    }

    /**
     * 同一时间内不同的操作
     */
    public void action() {
        //线程
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (flag) {
//                        if(fa){
//                            try {
//                                sleep(4000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            fa=false;
//                        }
                        enteredAction();        //障碍物入场
                        stepAction();           //物体移动
                        wardAction();           //障碍物遮挡
                        gameOverAction();       //游戏结束
                    }
                    //重绘画面
                    repaint();
                    try {
                        sleep(10);       //休息
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    /**
     * 按下键时调用
     */
    @Override
    public void keyPressed(KeyEvent e) {

//        Person pe=new Person(1);
        // 获取person当前的坐标
        int x = person.getX();
        int y = person.getY();

        //上移
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            f=false;                //滚
            person.setY(y - 130);
            person.setX(x+20);
            person.setDx(x+100);
            if (!flag) {
                flag = true;
            }
//            try {
//                person.image = ImageIO.read(new File("image/1_1.png"));
//                person.image = ImageIO.read(new File("image/1_2.png"));
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
        }
        if (person.getY() <= 20) {
            person.setY(y);
        }

        //下移
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            person.setY(y + 120);
        }
        if (person.getY() >= 300) {
            person.setY(300);
        }

        //左移
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            person.setX(x - 20);
            person.setDx(person.getDx() - 20);
            if (person.getX() <= 0) {
                person.setX(0);
                person.setDx(person.image.getWidth() + 20);
            }
        }

        //右移
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (flag) {
                person.setX(x + 20);
                person.setDx(person.getDx() + 20);
            }
        }
        if (person.getDx() >= GameFrame.WIDTH - person.dimage.getWidth()) {
            person.setX(GameFrame.WIDTH - person.dimage.getWidth() - 85 - person.image.getWidth());
            person.setDx(GameFrame.WIDTH - person.dimage.getWidth());
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
