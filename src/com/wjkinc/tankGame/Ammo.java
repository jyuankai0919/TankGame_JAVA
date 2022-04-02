package com.wjkinc.tankGame;

import java.io.Serializable;

public class Ammo implements Runnable, Serializable {
    int x;
    int y;
    int direct;
    int speed;
    boolean isLive = true;


    public Ammo(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direct) {

                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }

            //System.out.println("子彈當前坐標：(" + x + "," + y + ")");

            //判斷子彈是否到達邊界，如果到邊界後結束線程
            if (!(x <= 1440 && x >= 0 && y <= 730 && y >= 0 && isLive)) {
                isLive = false;
                System.out.println("子彈撞擊，線程停止");
                break;
            }

        }


    }
}
