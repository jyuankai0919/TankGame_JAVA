package com.wjkinc.tankGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class TankGame extends JFrame {

    MyPanel mp = null;
    Logger logger = Logger.getLogger(getClass().getName());

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new TankGame();
    }

    public TankGame() throws IOException, ClassNotFoundException {
        try (Scanner scanner = new Scanner(System.in)) {
            logger.info("是否載入上局進度:( 1:新遊戲 / 2:上局進度 )");
            int s = scanner.nextInt();
            mp = new MyPanel(s);
        }
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1440, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    GameData.saveGameData();
                    logger.info("儲存成功");
                } catch (IOException exception) {
                    logger.info("儲存失敗");
                }
            }
        });

    }

}
