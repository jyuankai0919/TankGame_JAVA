package com.wjkinc.tankGame;

import java.io.Serializable;

public class Bomb implements Serializable {

    int x,y;
    int life = 18;
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void lifeDown(){

        if (life>0){
            life--;
        }else {
            isLive = false;
        }

    }

}
