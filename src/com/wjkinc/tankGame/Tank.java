package com.wjkinc.tankGame;

import java.io.Serializable;
import java.util.Vector;

public class Tank implements Serializable {
    /**
     * 坦克座標(x,y)
     */

    private int x;
    private int y;
    private int direct;//0:上 1:右 2:下 3:左
    private int speed;
    Vector<Wall> walls = new Vector<>();

    public void setWalls(Vector<Wall> walls) {
        this.walls = walls;
    }

    public boolean touchWalls() {
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);
            int nextX = 0;
            int nextY = 0;
            switch (getDirect()) {
                case 0:
                    nextX = getX();
                    nextY = getY() - speed;
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX >= wall.x && nextX <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY >= wall.y && nextY <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX + 40 >= wall.x && nextX + 40 <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY >= wall.y && nextY <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    break;

                case 1:
                    nextX = getX() + speed;
                    nextY = getY();
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX + 60 >= wall.x && nextX + 60 <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY >= wall.y && nextY <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX + 60 >= wall.x && nextX + 60 <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY + 40 >= wall.y && nextY + 40 <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    break;

                case 2:
                    nextX = getX();
                    nextY = getY() + speed;
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX >= wall.x && nextX <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY + 60 >= wall.y && nextY + 60 <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX + 40 >= wall.x && nextX + 40 <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY + 60 >= wall.y && nextY + 60 <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    break;

                case 3:
                    nextX = getX() - speed;
                    nextY = getY();
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX >= wall.x && nextX <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY >= wall.y && nextY <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    //判斷此坦克左上角是否撞擊障礙物
                    if (nextX >= wall.x && nextX <= wall.x + 61//判斷坦克是否進入牆壁x座標
                            && nextY + 40 >= wall.y && nextY + 40 <= wall.y + 26) {//判斷坦克是否進入牆壁y座標
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public boolean moveUp() {
        if (getY() > 0 && !touchWalls()) {
            y -= speed;
            return true;
        } else {
            y += speed;
            return false;
        }

    }

    public boolean moveDown() {
        if (getY() + 80 < 730 && !touchWalls()) {
            y += speed;
            return true;
        } else {
            y -= speed;
            return false;
        }

    }

    public boolean moveRight() {
        if (getX() + 60 < 1440 && !touchWalls()) {
            x += speed;
            return true;
        } else {
            x -= speed;
            return false;
        }
    }

    public boolean moveLeft() {
        if (getX() > 0 && !touchWalls()) {
            x -= speed;
            return true;
        } else {
            x += speed;
            return false;
        }
    }

    public Tank(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int setX(int x) {
        this.x = x;
        return x;
    }

    public int getY() {
        return y;
    }

    public int setY(int y) {
        this.y = y;
        return y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
