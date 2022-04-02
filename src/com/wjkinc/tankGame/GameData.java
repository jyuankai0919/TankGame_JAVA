package com.wjkinc.tankGame;

import java.io.*;
import java.util.Vector;

public class GameData extends MyPanel{

    private static String savePath = "./src/com/wjkinc/tankGame/saveData/gameData.dat";

    private static Vector<Enemy> enemyVector;
    private static Vector<Wall> wallVector;
    private static Vector<Bomb> bombVector;

    public GameData(int s) throws IOException, ClassNotFoundException {
        super(s);
    }


    public static Vector<Enemy> getEnemyVector() {
        return enemyVector;
    }

    public static Vector<Wall> getWallVector() {
        return wallVector;
    }

    public static void setEnemyVector(Vector<Enemy> enemyVector) {
        GameData.enemyVector = enemyVector;
    }

    public static void setWallVector(Vector<Wall> wallVector) {
        GameData.wallVector = wallVector;
    }

    public static void setBombVector(Vector<Bomb> bombVector) {
        GameData.bombVector = bombVector;
    }

    public static void setHero(Hero hero) {
        GameData.hero = hero;
    }

    public static Vector<Bomb> getBombVector() {
        return bombVector;
    }

    public static Hero getHero() {
        return hero;
    }

    private static Hero hero;

    static ObjectOutputStream oos;
    static ObjectInputStream ois;

    public static void saveGameData() throws IOException {


        oos = new ObjectOutputStream(new FileOutputStream(savePath));
        oos.writeObject(enemyVector);
        oos.writeObject(wallVector);
        oos.writeObject(bombVector);
        oos.writeObject(hero);

        oos.close();

    }

    public static void readGameData() throws IOException, ClassNotFoundException {

        ois = new ObjectInputStream(new FileInputStream(savePath));

        enemyVector= (Vector<Enemy>) ois.readObject();
        wallVector = (Vector<Wall>) ois.readObject();
        bombVector= (Vector<Bomb>) ois.readObject();
        hero = (Hero) ois.readObject();
        System.out.println("讀取成功");
        ois.close();
    }

}
