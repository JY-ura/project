package com.ttkp.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * 登录界面
 * @author yura
 * @version 1.0.0 2019.6.20
 */
public class LoginFrame extends JFrame {
    JLabel userLabel;       //用户名
    JLabel pwdLabel;        //密码
    JTextField userText;        //user输入框
    JPasswordField pwdText;     //密码输入框
    JButton loginBtn, cancelBtn;     //登录取消按钮
    String username;            //姓名串
    String userpwd;             //密码串
    /*声明加载背景音乐的变量*/
    File file;
    URL url;
    URI uri;
    AudioClip aau;          //音频对象

    /**
     * 创建对象时，即播放音乐
     */
    public LoginFrame() throws HeadlessException {
        file = new File("assets/300 Violin Orchestra.mp3");
        uri = file.toURI();
        try {
            url = uri.toURL();
            aau = Applet.newAudioClip(url);
            aau.loop();//循环播放
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        aau =Applet.newAudioClip(url);
        aau.loop();
        userLabel=new JLabel("用户名：");
        userLabel.setBounds(40,160,150,30);
        this.add(userLabel);
        pwdLabel=new JLabel("密码：");
        pwdLabel.setBounds(40,210,150,30);
        this.add(pwdLabel);

        //添加用户输入框
        userText=new JTextField(10);
        userText.setBounds(90,165,120,25);
        userText.setFocusable(true);        //获取鼠标焦点
        userText.setBorder(BorderFactory.createLoweredBevelBorder());       //设置输入框边界凹陷效果
        this.add(userText);

        //添加密码输入框
        pwdText=new JPasswordField();
        pwdText.setBounds(90,215,120,25);
        pwdText.setFocusable(true);
        pwdText.setBorder(BorderFactory.createLoweredBevelBorder());         //设置输入框边界凹陷效果
        this.add(pwdText);

        //添加登录、取消按钮
        loginBtn=new JButton("登录");
        loginBtn.setBounds(80,265,65,30);
        //设置按钮前景色
        loginBtn.setForeground(Color.orange);
        //设置按钮边框颜色
        loginBtn.setBorder(BorderFactory.createLineBorder(Color.PINK));
        this.add(loginBtn);

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    dispose();
                }
            }
        });

        login();

        //test();

        //窗口的设置
        BackImage back = new BackImage();
        back.setBounds(0, 0, 599, 330);
        this.add(back);
        this.setLayout(null);//不设置的话，排版会乱
        this.setSize(599,330);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        //禁用frame窗体的装饰（窗口栏）
        //this.setUndecorated(true);
        //修改程序运行显示图标
        this.setFocusable(true);
        this.setIconImage(new ImageIcon("image/115.png").getImage());
        this.setVisible(true);

    }




    public void login(){


        // 添加登录按钮的事件监听
        loginBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击登录按钮所执行的具体功能
                username=userText.getText();//获取用户名输入框的内容
                //获取密码框输入的内容 赋值给userpwd
                userpwd = pwdText.getText();
                if(username.length()==0){
                    JOptionPane.showMessageDialog(null, "用户名和密码不能为空");

                }else if(username.equals("张三")==false){
                    //用户名错误
                    //提示窗口  ，内容 为 用户名错误
                    JOptionPane.showMessageDialog(null, "用户名错误");

                }else if(userpwd.equals("123")==false){
                    JOptionPane.showMessageDialog(null, "密码错误");
                }
                /**任务：
                 *     登录成功的情况，大家自己写   （登录成功）*/

                if(username.equals("张三")==true && userpwd.equals("123")==true){
                    //JOptionPane.showMessageDialog(null, "登录成功");
                    /** 跳转界面**/
                    new MenuFrame();//创建生成菜单界面

                    /**当前界面关闭*/
                    dispose();//关闭当前界面

                }
            }
        });
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();

    }
}
class  BackImage extends JPanel{
    Image background1;
    public BackImage() {
        try {
            background1 = ImageIO.read(new File("image/login.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        g.drawImage(background1, 0, 0 ,null);
    }


}
