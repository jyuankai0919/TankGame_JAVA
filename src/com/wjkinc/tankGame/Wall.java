package com.wjkinc.tankGame;

import java.io.Serializable;

public class Wall implements Serializable {
    int x;
    int y;
    boolean isLive;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
