package com.yuanyuan.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.TimerTask;

/**
 * Hello world!
 *
 */
public class SnakeFrame extends JFrame {

    private Snake snake;

    private Timer timer; // 定时器，在规定时间内调用蛇移动的方法

    private JPanel jpanel;

    private Node food;

    public SnakeFrame() {
        // 初始化窗体
        initFrame();
        // 初始化游戏棋盘
        initGamePanel();
        // 初始化 蛇
        initSnake();
        // 初始化定时器
        initTimer();
        // 设置键盘监听
        setKeyListener();
        // 初始化食物
        initFood();
    }

    private void initFood() {
        food = new Node();
        food.random();
    }

    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != Direction.DOWN)
                            snake.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != Direction.UP)
                            snake.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != Direction.RIGHT)
                            snake.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != Direction.LEFT)
                            snake.setDirection(Direction.RIGHT);
                        break;
                }
            }
        });
    }

    private void initTimer() {
        // 创建定时器对象;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                snake.move();
                Node head = snake.getBody().getFirst();
                if (head.equals(food)) {
                    snake.eat(food);
                    // 这里有个问题，如果直接随机放到 蛇 的身体里怎么办
                    food.random();
                }
                // 重绘游戏棋盘
                jpanel.repaint();
                if (snake.died()) {
                    this.cancel();
                }
            }
        };
        // 每 100 毫秒执行一次任务
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    private void initSnake() {
        snake = new Snake();
    }

    public static void main( String[] args )
    {
        new SnakeFrame();
    }

    private void initFrame() {
        setResizable(false);
        setTitle("Yuanyuan 贪吃蛇");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置点右上角的关闭按钮就是退出程序
        setSize(610, 640);
        setLocation(300, 300); // 默认从左上角出现，现在就左、上各 400 像素
        setVisible(true);
    }

    private void initGamePanel() {
        jpanel = new JPanel(){
            @Override
            public void paint(Graphics g) {

                // 重绘棋盘时需要清空棋盘中的内容
                g.clearRect(0, 0,600, 600);
//                System.out.println("clearRect width = " + getWidth() + ", height = " + getHeight());

                // Graphics g 可以看作是画笔
                // 绘制 40 条横线
                for (int i = 0; i < 40; i++) {
                    g.drawLine(0, i * 15, 600, i * 15);
                }
                // 绘制 40 条竖线
                for (int i = 0; i < 40; i++) {
                    g.drawLine(i * 15, 0, i * 15, 600);
                }
                // 绘制 蛇
                LinkedList<Node> body = snake.getBody();
                for (Node node : body) {
                    g.fillRect(node.getX() * 15, node.getY() * 15, 15, 15);
                }
                // 绘制 食物
                g.fillRect(food.getX() * 15, food.getY() * 15, 15, 15);
            }
        };
        // 把棋盘放入窗体
        add(jpanel);
    }
}
