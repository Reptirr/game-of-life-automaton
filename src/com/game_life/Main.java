package com.game_life;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

    // main
    public static void main(String[] args) {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        int display = screen_size.height;
        Scanner in = new Scanner(System.in);

        JFrame frame = new JFrame("Окновввв"); // Создаем окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // По какой кнопке закрывается окно (выход)
        frame.setSize(display-60, display-40-60); // Устанавливаем размеры


        Drawer drawer = new Drawer(game_life.get_map());
        frame.add(drawer);
        frame.setVisible(true); // Открываем окно

        double delay = 0.1;

        javax.swing.Timer game = new javax.swing.Timer((int) (delay*1000), e -> {
            boolean[][] map = game_life.next_gen();
            drawer.setMap(map);
            drawer.repaint();
        });

        while (true) {
            String q;
            if (game.isRunning()) {
                System.out.print("Остановить: ");
                q = in.nextLine();
                if (q.contains("y")) game.stop();
            } else {
                System.out.print("Начать: ");
                q = in.nextLine();
                if (q.contains("y")) game.start();
            }
        }

    }

}
