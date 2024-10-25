package com.yuanyuan.snake;

import java.util.LinkedList;

public class Snake {
    // 蛇身体
    private LinkedList<Node> body;

    // 蛇的运动方向 一开始默认向右
    private Direction direction = Direction.RIGHT;

    // 是否存活
    private boolean alive = true;

    public boolean died() {
        return !alive;
    }

    public void eat(Node food) {
        body.addFirst(new Node(food.getX(), food.getY()));
    }

    public void checkAlive(Node node) {
        if (body.contains(node)) {
            System.out.println("snake died. node = " + node + ", body = " + body);
            alive = false;
        }
    }

    // 创建 snake 对象时执行
    public Snake() {
        // 初始化蛇身体
        initSnake();
    }

    private void initSnake() {
        body = new LinkedList<>();
        body.add(new Node(24, 20));
        body.add(new Node(23, 20));
        body.add(new Node(22, 20));
        body.add(new Node(21, 20));
        body.add(new Node(20, 20));
        body.add(new Node(19, 20));
        body.add(new Node(18, 20));
    }

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public void move() {
        Node head = body.getFirst();
        // 增加头部节点
        Node neo;
        switch (direction) {
            case UP:
                neo = new Node(head.getX(), head.getY() - 1 == -1 ? 40 : head.getY() - 1);
                checkAlive(neo);
                body.addFirst(neo);
                break;
            case DOWN:
                neo = new Node(head.getX(), (head.getY() + 1) % 40);
                checkAlive(neo);
                body.addFirst(neo);
                break;
            case LEFT:
                neo = new Node(head.getX() - 1 == -1 ? 40 : head.getX() - 1, head.getY());
                checkAlive(neo);
                body.addFirst(neo);
                break;
            case RIGHT:
                neo = new Node((head.getX() + 1) % 40, head.getY());
                checkAlive(neo);
                body.addFirst(neo);
                break;
        }
        // 删除尾部节点
        body.removeLast();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
