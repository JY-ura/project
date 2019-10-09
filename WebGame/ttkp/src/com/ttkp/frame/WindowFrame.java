package com.ttkp.frame;


import java.awt.BorderLayout;
        import java.awt.Color;

        import javax.swing.ImageIcon;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JProgressBar;

/**加载窗口**/
public class WindowFrame extends JFrame implements Runnable{

    /**
     * 背景图片  hbg.jpg
     * 宽  568   高  349;
     * JProgressBar :进度条类型
     */
    // 声明背景图片变量
    JLabel backImage;
    //声明进度条变量
    JProgressBar jdt;

    //利用线程实现进度条的方法
    public void Start(){
        WindowFrame window = new WindowFrame();
        //创建一个线程  并绑定一个窗体
        Thread  t = new Thread(window);
        //启动线程
        t.start();
        dispose();
    }




    //构造方法
    public WindowFrame() {
        //给背景图片变量赋值
        backImage = new JLabel(new ImageIcon("image/hbg.jpg"));
        //创建一个进度条
        jdt = new JProgressBar();
        //设置进度条属性
        //设置进度条内字符串的值
        jdt.setStringPainted(true);

        //设置进度条颜色
        jdt.setBackground(Color.ORANGE);
        this.add(backImage,BorderLayout.NORTH);
        this.add(jdt,BorderLayout.SOUTH);

        this.setSize(568, 349);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setIconImage(new ImageIcon("image/115.png").getImage());
        this.setVisible(true);
    }


    public static void main(String[] args) {
        new WindowFrame().Start();

    }


    @Override
    public void run() {
        // 不断执行的内容
        int [] progressValue ={0,50,55,60,75,100};
//        int [] progressValue ={0,100};
       for(int i =0;i<progressValue.length;i++){
            try {
                Thread.sleep(500);//每隔500毫秒执行一次
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("读取进度条出错");
            }
            //设置进度条的的值
            jdt.setValue(progressValue[i]);//将数组的元素的值复制给进度条

        }
        dispose();
        new GameFrame();
    }

}
