package com.ttkp.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**菜单界面*/
public class MenuFrame extends JFrame implements MouseListener{

	/**
	 * @author Yanjl
	 */
	// 菜单选项界面
	JLabel start,help,exit;


	public MenuFrame() {
		//给开始按钮赋值图片
		start = new JLabel(new ImageIcon("image/hh1.png"));
		//设置start的位置
		start.setBounds(300, 250, 150, 40);
		//设置start控件不可用
		start.setEnabled(false);
		//start空间添加鼠标事件监听
		start.addMouseListener(this);

		//添加start到窗体上去
		this.add(start);
		/**添加帮助按钮，赋值  设置位置   添加*/
		help = new JLabel(new ImageIcon("image/hh2.png"));
		help.setBounds(300,320, 150, 40);
		//help控件添加鼠标事件监听
		help.addMouseListener(this);
		this.add(help);
		//帮助按钮 设置颜色为灰色
		help.setEnabled(false);

		/**添加退出按钮，赋值  设置位置   添加*/
		exit = new JLabel(new ImageIcon("image/hh3.png"));
		exit.setBounds(300,390, 150, 40);
		//离开按钮 设置颜色为灰色
		exit.setEnabled(false);
		//exit添加鼠标事件监听
		exit.addMouseListener(this);
		this.add(exit);


		//创建面板 并添加
		BackImg back = new BackImg();
		this.add(back);
		// 设置窗体大小
		this.setSize(1000, 550);
		//设置窗体位置居中
		this.setLocationRelativeTo( null);
		//设置窗体关闭按钮功能
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体 窗口菜单隐藏
		this.setUndecorated(true);
		//更换窗体 图标
		this.setIconImage(new ImageIcon("image/115.png").getImage());
		//设置窗体可见
		this.setVisible(true);
	}







	//  鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		//当点击start时，执行该方法体内容
		if(e.getSource().equals(start)){
			//鼠标点击start按钮
			/**界面跳转**/
			new WindowFrame().Start();
			/**关闭当前界面**/
			dispose();
		}
		//当鼠标点击help时，
		if(e.getSource().equals(help)){
			/**跳转到帮助界面*/
		}
		if(e.getSource().equals(exit)){
			//关闭当前界面
			dispose();
		}
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	// 鼠标指向控件的方法
	@Override
	public void mouseEntered(MouseEvent e) {
		//如果鼠标指向start控件
		if(e.getSource().equals(start)){
			start.setEnabled(true);//设置空间可用

		}
		//如果鼠标指向help控件
		//如果鼠标指向start控件
		if(e.getSource().equals(help)){
			help.setEnabled(true);//设置空间可用

		}

		//如果鼠标指向exit控件
		if(e.getSource().equals(exit)){
			exit.setEnabled(true);//设置空间可用

		}


	}



	// 鼠标离开事件
	@Override
	public void mouseExited(MouseEvent e) {
		//如果鼠标离开start控件
		if(e.getSource().equals(start)){
			start.setEnabled(false);//设置空间可用
		}
		//如果鼠标离开help控件
		if(e.getSource().equals(help)){
			help.setEnabled(false);//设置空间可用
		}
		//如果鼠标离开exit控件
		if(e.getSource().equals(exit)){
			exit.setEnabled(false);//设置空间可用

		}

	}

}




class BackImg extends JPanel{
	//声明背景图片变量
	Image background;
	//给背景图片变量赋值
	public BackImg() {
		// 赋值

		try {
			background = ImageIO.read(new File("image/main.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 绘制背景图片
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(background,0,0,1000,550,null);
	}




}





