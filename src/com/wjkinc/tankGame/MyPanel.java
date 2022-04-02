package com.wjkinc.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable{
    //定義我的坦克
    Hero hero = null;
    //定義敵人坦克
    Vector<Enemy> enemyTank = new Vector<>();
    int enemySize = 6;
    //定義爆炸效果
    Vector<Bomb> bombs = new Vector<>();
    Image image01 = null;
    Image image02 = null;
    Image image03 = null;
    //定義障礙物
    Vector<Wall> walls = new Vector<>();
    int wallsHigh = 5;
    int wallsWide = 20;


    public MyPanel(int s) throws IOException, ClassNotFoundException {

        switch (s) {
            case 1:
            //初始化自己坦克
            hero = new Hero(600, 600, 0, 30);
            hero.setWalls(walls);

            //初始化敵人坦克
            for (int i = 0; i < enemySize; i++) {
                Enemy enemy = new Enemy(200 * (i + 1), 100, 2, 5);
                //給Enemy加入一顆子彈
                Ammo ammo = new Ammo(enemy.getX() + 20, enemy.getY() + 60, enemy.getDirect(), 20);
                //把ammo加入enemy的Vector成員
                enemy.vectorAmmo.add(ammo);
                //將敵人坦克的Vector傳入Enemy中的enemies
                enemy.setEnemies(enemyTank);
                //將創建的障礙物傳給Tank中的vector
                enemy.setWalls(walls);
                //啟動坦克
                new Thread(enemy).start();
                //啟動 ammo
                new Thread(ammo).start();
                //加入
                enemyTank.add(enemy);
            }

            //初始化圖片
            image01 = Toolkit.getDefaultToolkit().getImage("out/production/chapter20/bomb_1.gif");
            image02 = Toolkit.getDefaultToolkit().getImage("out/production/chapter20/bomb_2.gif");
            image03 = Toolkit.getDefaultToolkit().getImage("out/production/chapter20/bomb_3.gif");

            //初始化障礙物
            for (int i = 0; i < wallsHigh; i++) {
                for (int j = 0; j < wallsWide; j++) {
                    Wall wall = new Wall(100 + (j * 61), 310 + (i * 26));//一半365
                    walls.add(wall);

                }
            }
            break;

            case 2:
                //讀取紀錄
                GameData.readGameData();

                //載入紀錄中的自己坦克
                this.hero = GameData.getHero();
                for (int i = 0; i < hero.vectorAmmo.size(); i++) {
                    Ammo ammo = hero.vectorAmmo.get(i);
                    new Thread(ammo).start();
                }
                hero.setWalls(walls);


                //載入紀錄中的敵人坦克
                for (int i = 0; i < GameData.getEnemyVector().size(); i++) {
                    Enemy enemy = GameData.getEnemyVector().get(i);
                    for (int j = 0; j < enemy.vectorAmmo.size(); j++) {
                        Ammo ammo = enemy.vectorAmmo.get(j);
                        new Thread(ammo).start();
                    }
                    //啟動坦克
                    new Thread(enemy).start();
                    enemyTank.add(enemy);
                }

                //初始化圖片
                image01 = Toolkit.getDefaultToolkit().getImage("out/production/chapter20/bomb_1.gif");
                image02 = Toolkit.getDefaultToolkit().getImage("out/production/chapter20/bomb_2.gif");
                image03 = Toolkit.getDefaultToolkit().getImage("out/production/chapter20/bomb_3.gif");

                //載入計入中的障礙物
                for (int i = 0; i < GameData.getWallVector().size(); i++) {
                    Wall wall = GameData.getWallVector().get(i);
                    walls.add(wall);
                }



                //將記錄中的bomb放入Vector
                for (int i = 0; i < GameData.getBombVector().size(); i++) {
                    Bomb bomb = GameData.getBombVector().get(i);
                    bombs.add(bomb);
                }
                break;
        }


    }

    public void theGameRecord(Graphics g) {
        String s = String.format("剩餘敵人數量:%d", enemyTank.size());
        g.setColor(Color.black);
        g.setFont(new Font(s, Font.BOLD, 20));
        g.drawString(s, 25, 757);

    }


    /**
     * 依照下方run function設定
     * 此function每50ms更新一次
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        GameData.setEnemyVector(enemyTank);
        GameData.setWallVector(walls);
        GameData.setBombVector(bombs);
        GameData.setHero(hero);

        g.fillRect(0, 0, 1440, 730);//填充矩形，默认黑色
        //紀錄面板
        theGameRecord(g);
        //畫出我方子彈
        for (int i = 0; i < hero.vectorAmmo.size(); i++) {
            Ammo ammo = hero.vectorAmmo.get(i);
            if (ammo != null && ammo.isLive) {
                g.setColor(Color.yellow);//將我方子彈設定成黃色
                g.fill3DRect(ammo.x, ammo.y, 5, 5, false);
            } else {
                hero.vectorAmmo.remove(ammo);
            }
        }

        //畫出我方坦克-封装方法
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }

        //畫出敵人坦克
        for (int i = 0; i < enemyTank.size(); i++) {
            //取出坦克
            Enemy enemy = enemyTank.get(i);
            //System.out.println(enemy.isLive);
            //判斷坦克是否存活
            if (enemy.isLive) {
                //畫出坦克
                drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 0);
                //畫出所有vectorAmmo中的子彈
                for (int j = 0; j < enemy.vectorAmmo.size(); j++) {
                    //取出子彈
                    Ammo ammo = enemy.vectorAmmo.get(j);
                    //繪製，須先判斷子彈是否存活，如果死亡則將子彈移除
                    if (ammo.isLive) {
                        g.fill3DRect(ammo.x, ammo.y, 5, 5, false);
                    } else {
                        //從vector中移除
                        enemy.vectorAmmo.remove(j);
                    }
                }
            }
        }

        //畫出炸彈
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);

            if (bomb.isLive) {
                if (bomb.life > 12) {
                    int aborted = MediaTracker.ABORTED;
                    g.drawImage(image01, bomb.x, bomb.y, 60, 60, this);
                } else if (bomb.life > 6) {
                    g.drawImage(image02, bomb.x, bomb.y, 60, 60, this);
                } else {
                    g.drawImage(image03, bomb.x, bomb.y, 60, 60, this);
                }
                //每畫出一次炸彈生命減一
                bomb.lifeDown();
                //判斷炸彈是否還存活，如果為零，就將此炸彈移除
                if (bomb.life <= 0) {
                    bombs.remove(i);
                }
            }
        }

        //畫出牆壁
        if (walls.size() > 0) {
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                g.setColor(Color.LIGHT_GRAY);
                g.fill3DRect(wall.x, wall.y, 60, 25, false);
            }
        }

    }

    //判斷子彈是否擊中障礙物
    public boolean hit(Ammo ammo, Wall wall) {
        if (ammo.x > wall.x && ammo.x < wall.x + 61//判斷x座標
                && ammo.y > wall.y && ammo.y < wall.y + 26) {//判斷y座標
            ammo.isLive = false;
            return true;
        } else {
            return false;
        }
    }

    //判斷子彈是否擊中坦克
    public boolean hit(Ammo a, Hero hero) {
        //判斷子彈a是否進入坦克中
        switch (hero.getDirect()) {
            //坦克面向上下
            case 0:
            case 2:
                if (a.x > hero.getX() && a.x < hero.getX() + 40 //判斷x座標
                        && a.y > hero.getY() && a.y < hero.getY() + 60) {//判斷y座標
                    a.isLive = false;
                    if (hero.Live > 1) {
                        --hero.Live;
                    } else {
                        hero.isLive = false;
                    }
                    //當子彈擊中敵人，在敵人的座標位置加入一個bomb到vector當中
                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
                    bombs.add(bomb);
                    return true;
                }
                break;

            //坦克面向左右
            case 1:
            case 3:
                if (a.x > hero.getX() && a.x < hero.getX() + 60 //判斷x座標
                        && a.y > hero.getY() && a.y < hero.getY() + 40) {//判斷y座標
                    a.isLive = false;
                    if (hero.Live > 1) {
                        --hero.Live;
                    } else {
                        hero.isLive = false;
                    }
                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
                    bombs.add(bomb);
                    return true;
                }
                break;
        }
        return false;
    }

    //判斷完是否擊中坦克後，調用此方法確定每顆子彈是否擊中任意坦克
    public void hitHeroTankAndWalls() {
        for (int i = 0; i < enemyTank.size(); i++) {
            //取出所有敵方坦克
            Enemy enemy = enemyTank.get(i);
            if (enemy.isLive && enemy.vectorAmmo != null) {
                for (int j = 0; j < enemy.vectorAmmo.size(); j++) {

                    //判斷子彈是否擊中hero
                    Ammo ammo = enemy.vectorAmmo.get(j);
                    hit(ammo, hero);

                    //判斷子彈是否擊中牆
                    for (int k = 0; k < walls.size(); k++) {
                        Wall wall = walls.get(k);
                        if (hit(ammo, wall)) {
                            walls.remove(wall);
                        }
                    }
                }
            }
        }
    }

    /**
     * 編寫出一個方法，判斷我方子彈是否擊中敵方坦克
     *
     * @param a     我方子彈
     * @param enemy 敵人坦克
     * @return 是否有擊中坦克
     */
    public boolean hit(Ammo a, Enemy enemy) {
        //判斷子彈a是否進入坦克中
        switch (enemy.getDirect()) {
            //坦克面向上下
            case 0:
            case 2:
                if (a.x > enemy.getX() && a.x < enemy.getX() + 40 //判斷x座標
                        && a.y > enemy.getY() && a.y < enemy.getY() + 60) {//判斷y座標
                    a.isLive = false;
                    enemy.isLive = false;
                    //當子彈擊中敵人，在敵人的座標位置加入一個bomb到vector當中
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                    return true;
                }
                break;

            //坦克面向左右
            case 1:
            case 3:
                if (a.x > enemy.getX() && a.x < enemy.getX() + 60 //判斷x座標
                        && a.y > enemy.getY() && a.y < enemy.getY() + 40) {//判斷y座標
                    a.isLive = false;
                    enemy.isLive = false;
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                    return true;
                }
                break;
        }
        return false;
    }

    //判斷完是否擊中坦克後，調用此方法確定每顆子彈是否擊中任意坦克
    public void hitEnemyTankAndWalls() {
        for (int i = 0; i < hero.vectorAmmo.size(); i++) {
            Ammo ammo = hero.vectorAmmo.get(i);
            if (ammo != null && ammo.isLive) {
                for (int j = 0; j < enemyTank.size(); j++) {
                    Enemy enemy = enemyTank.get(j);
                    //如果子彈擊中坦克，將坦克從vector中移除
                    if (hit(ammo, enemy)) {
                        enemyTank.remove(j);
                    }
                }
            }
        }
    }

    public void hitWalls() {
        for (int i = 0; i < hero.vectorAmmo.size(); i++) {
            Ammo ammo = hero.vectorAmmo.get(i);
            if (ammo != null && ammo.isLive) {

                //判斷子彈是否擊中牆
                for (int k = 0; k < walls.size(); k++) {
                    Wall wall = walls.get(k);
                    //System.out.println(wall.x + "," + wall.y);
                    if (hit(ammo, wall)) {
                        walls.remove(wall);
                    }
                }
            }
        }
    }

    //判斷完是否擊中障礙物後，使用此方法確定每顆子彈是否擊中任一障礙物
    /**
     * 設計方法，畫出坦克
     *
     * @param x      坦克的左上角x座標
     * @param y      坦克的左上角y座標
     * @param g      畫筆
     * @param direct 坦克方向（上下左右）
     * @param type   坦克類型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        //根據不同類型坦克，設計不同顏色
        switch (type) {
            case 0: //我們的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //敵人的坦克
                g.setColor(Color.yellow);
                break;
        }

        //根據坦克方向，来繪製坦克
        //0:表示向上 1:表示向右 2:表示向下 3:表示向左
        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);//畫出坦克左邊輪子
                g.fill3DRect(x + 30, y, 10, 60, false);//畫出坦克右邊輪子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//畫出坦克蓋子
                g.fillOval(x + 10, y + 20, 20, 20);//畫出圓形蓋子
                g.drawLine(x + 20, y + 30, x + 20, y);//畫出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);//畫出坦克左邊輪子
                g.fill3DRect(x, y + 30, 60, 10, false);//畫出坦克右邊輪子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//畫出坦克蓋子
                g.fillOval(x + 20, y + 10, 20, 20);//畫出圓形蓋子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//畫出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//畫出坦克左邊輪子
                g.fill3DRect(x + 30, y, 10, 60, false);//畫出坦克右邊輪子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//畫出坦克蓋子
                g.fillOval(x + 10, y + 20, 20, 20);//畫出圓形蓋子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//畫出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x, y, 60, 10, false);//畫出坦克左邊輪子
                g.fill3DRect(x, y + 30, 60, 10, false);//畫出坦克右邊輪子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//畫出坦克蓋子
                g.fillOval(x + 20, y + 10, 20, 20);//畫出圓形蓋子
                g.drawLine(x + 30, y + 20, x, y + 20);//畫出炮筒
                break;
            default:
                System.out.println("暫時沒處理");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    //當某鍵按下後，該方法觸發
    //0:表示向上 1:表示向右 2:表示向下 3:表示向左
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.setDirect(3);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.setDirect(1);
            hero.moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && hero.vectorAmmo.size() <= 5) {
            hero.shotEnemyTank();

        }
        this.repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    //此處設定畫面更新率Thread.sleep( ＃ );
    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判斷子彈是否擊中了坦克
            hitEnemyTankAndWalls();
            hitHeroTankAndWalls();
            hitWalls();
            this.repaint();
        }
    }


}


