package com.wjkinc.tankGame;

import javax.swing.plaf.TableHeaderUI;
import java.util.Vector;

public class Enemy extends Tank implements Runnable {
    boolean isLive = true;
    Vector<Ammo> vectorAmmo = new Vector<>();
    Vector<Enemy> enemies = new Vector<>();
    int ammoSpeed = 10;
    Ammo ammo = null;

    public Enemy(int x, int y, int direct, int speed) {
        super(x, y, direct, speed);
    }

    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }

    public boolean touchEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy != this) {
                switch (this.getDirect()) {
                    case 0:
                        //判斷此坦克左上角是否撞擊坦克
                        if ((getX() >= enemy.getX() && getX() <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() >= enemy.getX() && getX() <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        //判斷此坦克右上角是否撞擊坦克
                        if ((getX() + 40 >= enemy.getX() && getX() + 40 <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() + 40 >= enemy.getX() && getX() + 40 <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        break;
                    case 1:
                        //判斷此坦克左上角是否撞擊坦克
                        if ((getX() + 60 >= enemy.getX() && getX() + 60 <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() + 60 >= enemy.getX() && getX() + 60 <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        //判斷此坦克右上角是否撞擊坦克
                        if ((getX() + 60 >= enemy.getX() && getX() + 60 <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() + 40 >= enemy.getY() && getY() + 40 <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() + 60 >= enemy.getX() && getX() + 60 <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() + 40 >= enemy.getY() && getY() + 40 <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        break;
                    case 2:
                        //判斷此坦克右上角是否撞擊坦克
                        if ((getX() >= enemy.getX() && getX() <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() + 60 >= enemy.getY() && getY() + 60 <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() >= enemy.getX() && getX() <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() + 60 >= enemy.getY() && getY() + 60 <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        //判斷此坦克左上角是否撞擊坦克
                        if ((getX() + 40 >= enemy.getX() && getX() + 40 <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() + 60 >= enemy.getY() && getY() + 60 <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() + 40 >= enemy.getX() && getX() + 40 <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() + 60 >= enemy.getY() && getY() + 60 <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        break;
                    case 3:
                        //判斷此坦克左上角是否撞擊坦克
                        if ((getX() >= enemy.getX() && getX() <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() >= enemy.getX() && getX() <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() >= enemy.getY() && getY() <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        //判斷此坦克右上角是否撞擊坦克
                        if ((getX() >= enemy.getX() && getX() <= enemy.getX() + 40//判斷敵方坦克上下狀況的x座標
                                && getY() + 40 >= enemy.getY() && getY() + 40 <= enemy.getY() + 60)//判斷敵方坦克上下狀況的y座標
                                || (getX() >= enemy.getX() && getX() <= enemy.getX() + 60//判斷敵方坦克左右狀況的x座標
                                && getY() + 40 >= enemy.getY() && getY() + 40 <= enemy.getY() + 40)) {//判斷敵方坦克左右狀況的y座標
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
    }

    public void enemyTankMoving() {
        //隨機設定前進步數
        int movingDistance = 15 - (int) (Math.random() * 6);//5-10
        //隨機設定前進方向
        int movingDirect = (int) (Math.random() * 4);//0-3
        setDirect(movingDirect);
        //依照方向前進
        switch (getDirect()) {
            case 0:
                for (int i = 0; i < movingDistance; i++) {
                    boolean inEdge = moveUp();
                    if (!inEdge || touchEnemy()) {
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                for (int i = 0; i < movingDistance; i++) {
                    boolean inEdge = moveRight();
                    if (!inEdge || touchEnemy()) {
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                for (int i = 0; i < movingDistance; i++) {
                    boolean inEdge = moveDown();
                    if (!inEdge || touchEnemy()) {
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                for (int i = 0; i < movingDistance; i++) {
                    boolean inEdge = moveLeft();
                    if (!inEdge || touchEnemy()) {
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public void enemyTankShot() throws InterruptedException {
        if (vectorAmmo.size() <= 2) {

            switch (getDirect()) {
                case 0://上
                    //ammo.add(new Ammo(getX()+17,getY(),getDirect(),5));
                    ammo = new Ammo(getX() + 17, getY(), getDirect(), ammoSpeed);
                    break;
                case 1://右
                    //ammo.add(new Ammo(getX()+60,getY()+17,getDirect(),5));
                    ammo = new Ammo(getX() + 60, getY() + 17, getDirect(), ammoSpeed);
                    break;
                case 2://下
                    //ammo.add(new Ammo(getX()+17,getY()+60,getDirect(),5));
                    ammo = new Ammo(getX() + 17, getY() + 60, getDirect(), ammoSpeed);
                    break;
                case 3://左
                    //ammo.add(new Ammo(getX(),getY()+17,getDirect(),5));
                    ammo = new Ammo(getX(), getY() + 17, getDirect(), ammoSpeed);
                    break;

            }
            vectorAmmo.add(ammo);
            new Thread(ammo).start();
        }
    }

    @Override
    public void run() {
        while (isLive) {
            enemyTankMoving();

            try {
                enemyTankShot();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
