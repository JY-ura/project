package com.ttkp.jpanel;

import com.ttkp.entity.Gold;
import com.ttkp.entity.Person;
import com.ttkp.frame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 金币panel
 */
public class GoldJPanel extends JPanel implements KeyListener {

    //序列化
    private static final long serialVersionUID = 1L;
    private int diss,score;
    private int dismy = 0;
    private Image background;       //背景图片
    private Image showfield;        //表现栏
    private Image distancefield;    //距离栏
    public boolean isOver = false;
    public static boolean quit=false;
    Person person;          //声明人物
    Gold[] gold = {};       //声明金币
    int a = 0;

    /**
     * 构造方法
     */
    public GoldJPanel(){
        person = new Person();      //创建人物对象
        background = new ImageIcon("image/11.jpg").getImage();  //背景图片赋值
        showfield = new ImageIcon("image/a12.png").getImage();  //表现栏赋值
        distancefield = new ImageIcon("image/a12.png").getImage();  //距离栏赋值
    }
    /**
     * 绘制
     * @param g //初始绘制变量
     */
    @Override
    public void paint(Graphics g){
        a--;
        //绘制背景图片
        super.paint(g);
        g.drawImage(background, a, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        g.drawImage(background, GameFrame.WIDTH + a, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        if(a == -GameFrame.WIDTH){
            a = 0;
        }

        //绘制玩家
        person.paintPerson(g);

        //绘制金币
        for(int i=0; i<gold.length; ++i){
            gold[i].paintGold(g);
        }

        //绘制表现栏
        diss= person.getDistance();
        g.drawImage(showfield, 120, 30, 150, 30, null);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 16));
        g.drawString("表现：" + person.getScore() + "分", 145, 50);
        g.drawImage(distancefield, 350, 30, 150, 30, null);
        person.setDistance(++diss);
        g.drawString("距离：" + diss + "米", 375, 50);
        if(person.getDistance() == 1000){
            quit = true;
        }

    }

    /**
     * 移动
     */
    public void stepAction() {
        //玩家移动
        person.drop();
        person.step();

        //金币移动
        for(int i=0; i<gold.length; ++i){
            gold[i].step();
        }
    }

    /**
     * 障碍物入场
     */
    int index = 0;
    public void enteredAction(){
        ++index;

        //0.2个时间单位生成一个金币
        if(index%2 == 0){
            //生成一个金币
            Gold g = new Gold();

            //对数组gold扩容一个单位
            gold = Arrays.copyOf(gold, gold.length+1);

            //将生成的金币放入数组
            gold[gold.length - 1] = g;
        }
    }

    /**
     * 玩家被障碍物遮挡
     */
    boolean flag = true;
    public void wardAction() {
        //吃金币
        for(int i=0; i<gold.length; i++){
            if(person.getX()+person.WIDTH > gold[i].getX()
                    && person.getX() < gold[i].getX() + gold[i].WIDTH
                    && person.getY() + person.HEIGHT > gold[i].getY()
                    && person.getY() < gold[i].getY() + gold[i].HEIGHT){
                //玩家碰到金币后的操作
                //1 玩家 加分
                int s = person.getScore();
                person.setScore(s+100);
                //2 该金币消失     gold[i]
                gold[i]= gold[gold.length-1];
                gold = Arrays.copyOf(gold,gold.length-1);
            }
        }
    }


/**
 * 重复操作
 */
public void action(){
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                if(flag){
                    enteredAction();    //金币
                    stepAction();   //物体移动方法
                    wardAction();
                }
                //重绘
                repaint();
                try {
                    Thread.sleep(10);   //休眠10毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    thread.start();
}


    /**
     * 按下键时调用
     * @param e  //键盘活动变量
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // 获取person当前的坐标
        int x = person.getX();
        int y = person.getY();

        //上移
        if(e.getKeyCode() == KeyEvent.VK_UP){
            person.setY(y - 120);
            if(!flag){
                flag = true;
            }
        }
        if(person.getY() <= 20){
            person.setY(y);
        }

        //下移
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            person.setY(y + 120);
        }
        if(person.getY() >= 315){
            person.setY(315);
        }

        //左移
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            person.setX(x - 20);
            person.setDx(person.getDx() - 20);
            if(person.getX() <= 0){
                person.setX(0);
                person.setDx(person.image.getWidth() + 20);
            }
        }

        //右移
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(flag){
                person.setX(x + 20);
                person.setDx(person.getDx() + 20);
            }
        }
        if(person.getDx() >= GameFrame.WIDTH-person.dimage.getWidth()){
            person.setX(GameFrame.WIDTH - person.dimage.getWidth() - 85 - person.image.getWidth());
            person.setDx(GameFrame.WIDTH - person.dimage.getWidth());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //空
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //空
    }
}
