package com.wjkinc.tankGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Scanner;

public class TankGame002 extends JFrame {

    MyPanel mp = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TankGame002 tankGame002 = new TankGame002();

    }


    public TankGame002() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("是否載入上局進度:( 1:新遊戲 / 2:上局進度 )");
        int s = scanner.nextInt();
        mp = new MyPanel(s);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1440, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    GameData.saveGameData();
                    System.out.println("儲存成功");
                } catch (IOException exception) {

                    System.out.println("儲存失敗");
                }
            }
        });

    }


}
