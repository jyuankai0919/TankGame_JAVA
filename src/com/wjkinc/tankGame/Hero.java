package com.wjkinc.tankGame;

import java.util.Vector;

public class Hero extends Tank {
    Ammo ammo = null;
    int ammoSpeed = 20;
    Vector<Ammo> vectorAmmo = new Vector<>();
    int Live = 3;
    boolean isLive = true;

    public Hero(int x, int y, int direct, int speed) {
        super(x, y, direct, speed);
    }

    public void shotEnemyTank() {

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


