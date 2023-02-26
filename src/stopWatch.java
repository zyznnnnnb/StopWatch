import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.TimerTask;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;

public class stopWatch extends JLabel{
    private JLabel currentTimeLabel; //显示标签
    private JButton startJButton;    //开始按钮
    private JButton stopJButton;     //停止按钮

    private long countMis,countSec,countMin;//计时变量
    private DecimalFormat textFormat=new DecimalFormat("00");//格式化输出
    Timer timer=new Timer(10,new TestActionListener());//计时单位10ms

    public stopWatch() {
        timer.start();
        JPanel panel=new JPanel(new GridLayout(1,3,5,10)); //网格布局嵌入按钮
        JPanel panel2=new JPanel();
        currentTimeLabel=new JLabel(" ");
        TestActionListener actionListener=new TestActionListener();
        currentTimeLabel.setForeground(Color.blue);
        currentTimeLabel.setFont(new Font("SAN_SERIF",Font.BOLD,50));

        startJButton=new JButton("Start");
        stopJButton=new JButton("Stop");

        //设置JButton相关属性
        startJButton.setBorder(BorderFactory.createRaisedBevelBorder());
        stopJButton.setBorder(BorderFactory.createRaisedBevelBorder());


        startJButton.setFont(new java.awt.Font("Times New Roman", 1, 30));
        stopJButton.setFont(new java.awt.Font("Times New Roman", 1, 30));


        stopJButton.setBackground(Color.cyan);
        startJButton.setBackground(Color.red);


        stopJButton.addActionListener(actionListener);
        startJButton.addActionListener(actionListener);


        this.setLayout(new BorderLayout());

        panel2.setBackground(Color.gray);
        panel2.setBorder(BorderFactory.createLoweredBevelBorder());
        panel2.add(currentTimeLabel);
        panel.add(stopJButton);
        panel.add(startJButton);


        this.add(panel2,BorderLayout.NORTH);
        this.add(panel,BorderLayout.CENTER);

    }
    //处理相关事件
    class TestActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==startJButton){
                timer.start();
                startJButton.setEnabled(false);
            }

            else if(e.getSource()==stopJButton){
                timer.stop();
                startJButton.setEnabled(true);
            }



            else{//满位后复位
                countMis++;
                if(countMis>=99){
                    countSec++;
                    countMis=0;
                    if(countSec>=59){
                        countMin++;
                        countSec=0;
                        if(countMin>=59){
                            countMin=0;
                        }
                    }
                }

            }

        }
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        currentTimeLabel.setText(textFormat.format(countMin)+
                ":"+textFormat.format(countSec));
        repaint();
    }


    public static void main(String args[]) {
        JFrame frame = new JFrame("秒表演示");
        stopWatch stopwatch = new stopWatch();
        frame.setSize(480, 280);
        frame.getContentPane().add(stopwatch);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}





