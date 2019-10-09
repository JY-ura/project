package com.ttkp.frame;

import com.ttkp.entity.Person;
import com.ttkp.jpanel.GameJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * 游戏结束页面
 * @author yura
 * @version 1.0.0 2019.6.20
 */
public class EndFrame extends JFrame implements MouseListener {

    JLabel again,menu;       //组件的声明
    Image image;        //图像
    Person person=new Person();      //声明人物

    public EndFrame() throws HeadlessException {
        super();
    }

    public EndFrame(Person person) throws HeadlessException {
        this.person = person;
        again=new JLabel(new ImageIcon("image/hh5.png"));
        again.setBounds(360,350,60,25);    //设置b1组件位置
        again.addMouseListener(this);                          //鼠标监听

        //第二个组件
        menu=new JLabel(new ImageIcon("image/hh6.png"));
        menu.setBounds(460,350,60,25);    //设置b2组件位置
        menu.addMouseListener(this);                          //鼠标监听
        BackEnd backEnd=new BackEnd(person);
        backEnd.setBounds(0,0,599,384);

        //添加窗体对象
        this.add(menu);
        this.add(again);
        this.add(backEnd);
        this.setLayout(null);
        this.setSize(599,440);
        this.setLocation(399,220);
        this.setIconImage(new ImageIcon("image/end_1.png").getImage());     //设置窗体图标
        this.setVisible(true);      //菜单栏可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println(person.toString());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //再来一次组件的使用
        if(e.getSource().equals(again)){
            this.dispose();         //关闭该窗口
            new WindowFrame().Start();        //重新开启游戏界面
        }

        //主界面组件的使用
        if(e.getSource().equals(menu)){
            this.dispose();         //关闭该窗口
            new MenuFrame();        //主界面
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * 内部类 绘制结束界面
     * @author yura
     */
    class BackEnd extends JPanel{
        Image img1,img2,img3;
        Person p1=null;     //TODO
        public BackEnd(){
            super();
        }

        /**
         * 结束页面构造方法
         * @param person 人物
         */
        public BackEnd(Person person){
            this.p1=person;
            try {
                img1= ImageIO.read(new File("image/chou.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
         * 绘制图像
         * @param g 绘制图像变量
         */
        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(img1,0,0,599,384,null);
            g.setFont(new Font("黑体", Font.BOLD, 18));
            g.drawString(p1.getDistance()+"",440,265);
            g.setFont(new Font("黑体", Font.BOLD, 18));
            g.drawString(p1.getScore()+"",440,302);
            g.setColor(Color.orange);       //设置文字颜色
            g.setFont(new Font("迷你简卡通", Font.BOLD, 40));
            g.drawString((p1.getScore()+p1.getDistance())+"",410,217);
        }

    }
}
